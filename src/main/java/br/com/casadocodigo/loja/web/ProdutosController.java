package br.com.casadocodigo.loja.web;

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

import br.com.casadocodigo.loja.domain.Categoria;
import br.com.casadocodigo.loja.domain.Produto;
import br.com.casadocodigo.loja.domain.TipoPreco;
import br.com.casadocodigo.loja.repository.ProdutoRepository;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("/listar")
	public ModelAndView listar() throws UnsupportedEncodingException {
		Iterable<Produto> listaDeProdutos = produtoRepository.findAll();
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
	@CacheEvict(value={"produtosHome","collections"}, allEntries=true)
	public String gravar(@Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			 return "produtos/form";
		}
		produto.setNew(true);
		produtoRepository.save(produto);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
		return "redirect:/produtos/listar";
	}

	@GetMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id")Integer id){
		ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
		Produto produto = produtoRepository.findOne(id);
		modelAndView.addObject("produto", produto);
		return modelAndView;
	}
	
	@PostMapping("/remover")
	@CacheEvict(value={"produtosHome","collections"}, allEntries=true)
	public ModelAndView remover(Integer id, RedirectAttributes redirectAttributes){
		Produto produto = produtoRepository.findOne(id);
		produtoRepository.delete(produto);
		redirectAttributes.addFlashAttribute("sucesso", "Produto removido com sucesso!");
		return new ModelAndView("redirect:/produtos");
	}
	
	@PostMapping("/editar")
	@CacheEvict(value={"produtosHome","collections"}, allEntries=true)
	public ModelAndView editar(Integer id){
		Produto produto = produtoRepository.findOne(id);
		ModelAndView view = new ModelAndView("produtos/form");
		view.addObject("tipos", TipoPreco.values());
		view.addObject("categorias", Categoria.values());
		view.addObject("produto", produto);
		return view;
	}
}
