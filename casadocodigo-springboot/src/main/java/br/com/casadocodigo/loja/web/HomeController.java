package br.com.casadocodigo.loja.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.domain.Categoria;
import br.com.casadocodigo.loja.domain.Produto;
import br.com.casadocodigo.loja.repository.ProdutoRepository;

@Controller
public class HomeController {
	
	@Autowired
	private ProdutoRepository produtoDao;
	
	@RequestMapping("/")
	@Cacheable(value="produtosHome")
	public ModelAndView index(){
		Iterable<Produto> produtos = produtoDao.findAll();
		ModelAndView model = new ModelAndView("home");
		model.addObject("produtos", produtos);
		return model;
	}
	@RequestMapping(value="/collection/{categoria}", method=RequestMethod.GET)
	public ModelAndView collection(@PathVariable String categoria){
		List<Produto> produtos = produtoDao.findAllByCategorias(Categoria.valueOf(categoria));
		ModelAndView model = new ModelAndView("home");
		model.addObject("produtos", produtos);
		return model;
	}

}
