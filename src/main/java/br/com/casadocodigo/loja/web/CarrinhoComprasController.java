package br.com.casadocodigo.loja.web;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.domain.CarrinhoCompras;
import br.com.casadocodigo.loja.domain.CarrinhoItem;
import br.com.casadocodigo.loja.domain.Produto;
import br.com.casadocodigo.loja.domain.TipoPreco;
import br.com.casadocodigo.loja.repository.ProdutoRepository;

@Controller
@RequestMapping("/carrinho")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {
	
	@Autowired
	private ProdutoRepository produtoDao;
	
	@Autowired
	private CarrinhoCompras carrinhoCompras;
	
	@PostMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipo){
	    ModelAndView modelAndView = new ModelAndView("redirect:/");
	    CarrinhoItem carrinhoItem = criaItem(produtoId, tipo);
	    carrinhoCompras.add(carrinhoItem);
	    return modelAndView;
	}
	
	@PostMapping("/changeQuantidadeItens")
	public String addAsync(Integer produtoId, TipoPreco tipo, int quantidade) {
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipo);
		int quantidadeAtual = carrinhoCompras.getQuantidade(carrinhoItem);
		if(quantidade == 0){
			carrinhoCompras.removeItemFromCarrinho(carrinhoItem);
		} else {
			if (quantidadeAtual < quantidade) {
				carrinhoCompras.add(carrinhoItem);
			} else {
				carrinhoCompras.remove(carrinhoItem);
			}
		}
		return "redirect:/carrinho/itens";
	}

	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = produtoDao.findOne(produtoId);
		return new CarrinhoItem(produto, tipoPreco);
	}
	
	@GetMapping(value="itens")
	public ModelAndView itens(){
		List<Produto> produtos = produtoDao.limitedList(new PageRequest(0,5));
		ModelAndView view = new ModelAndView("carrinho/itens");
		Collections.shuffle(produtos);
		view.addObject("produtos", produtos);
		return view;
	}
	
	@RequestMapping("/remover")
	public ModelAndView remover(Integer produtoId, TipoPreco tipoPreco){
		carrinhoCompras.remove(produtoId, tipoPreco);
		return new ModelAndView("redirect:/carrinho");
	}
	
	@PostMapping("/finalizar")
	public String finalizar(HttpServletRequest request){
        HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        if(session != null) {
            session.invalidate();
        }
		request.getSession().removeAttribute("CarrinhoCompras");
		return "/logout";
	}
	
	

}
