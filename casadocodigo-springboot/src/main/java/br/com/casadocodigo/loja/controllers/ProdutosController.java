package br.com.casadocodigo.loja.controllers;

import java.io.UnsupportedEncodingException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.Categoria;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.repository.ProdutoDAO;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoDAO produtoDao;

	@GetMapping("/listar")
	public ModelAndView listar() throws UnsupportedEncodingException {
		Iterable<Produto> listaDeProdutos = produtoDao.findAll();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", listaDeProdutos);
		return modelAndView;
	}

	@GetMapping("/form")
	public ModelAndView form(Produto produto) {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipo", TipoPreco.values());
		modelAndView.addObject("categorias", Categoria.values());
		return modelAndView;
	}

	@PostMapping("/gravar")
	@CacheEvict(value="produtosHome", allEntries=true)
	public String gravar(@Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			 return "produtos/form";
		}
//		String filePath = fileSaver.gravar("casadocodigo-imgs", sumario);
		produtoDao.save(produto);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
		return "redirect:/produtos";
	}

	@GetMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id")Integer id){
		ModelAndView modelAndView = new ModelAndView("/produtos/detalhe");
		Produto produto = produtoDao.findOne(id);
		modelAndView.addObject("produto", produto);
		return modelAndView;
	}
	
	@PostMapping("/remover")
	@CacheEvict(value="produtosHome", allEntries=true)
	public ModelAndView remover(Integer id, RedirectAttributes redirectAttributes){
		Produto produto = produtoDao.findOne(id);
		produtoDao.delete(produto);
		redirectAttributes.addFlashAttribute("sucesso", "Produto removido com sucesso!");
		return new ModelAndView("redirect:/produtos");
	}
	
	@PostMapping("/editar")
	@CacheEvict(value="produtosHome", allEntries=true)
	public ModelAndView editar(Integer id){
		Produto produto = produtoDao.findOne(id);
		ModelAndView view = new ModelAndView("produtos/form");
		view.addObject("tipos", TipoPreco.values());
		view.addObject("categorias", Categoria.values());
		view.addObject("produto", produto);
		return view;
	}
}
