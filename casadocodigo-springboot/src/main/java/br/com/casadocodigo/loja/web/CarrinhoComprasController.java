package br.com.casadocodigo.loja.web;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
//	@Autowired
//	private FileLoader fileLoader;
	
	
	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipo){
	    ModelAndView modelAndView = new ModelAndView("redirect:/");
	    CarrinhoItem carrinhoItem = criaItem(produtoId, tipo);
	    carrinhoCompras.add(carrinhoItem);
	    return modelAndView;
	}

	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = produtoDao.findOne(produtoId);
//		String imageFile = fileLoader.load(produto.getSumarioPath());
//		produto.setImageFile(imageFile);
		return new CarrinhoItem(produto, tipoPreco);
	}
	
	@RequestMapping(value="itens", method=RequestMethod.GET)
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

}
