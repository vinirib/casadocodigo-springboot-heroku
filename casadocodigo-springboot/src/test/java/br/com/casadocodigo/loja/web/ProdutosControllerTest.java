package br.com.casadocodigo.loja.web;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.domain.Produto;
import br.com.casadocodigo.loja.repository.ProdutoRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { ProdutosController.class })
@AutoConfigureMockMvc(secure=false)
public class ProdutosControllerTest {
	
	@MockBean
	private ProdutoRepository produtoRepository;
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void listarTest() throws Exception{
		given(produtoRepository.findAll())
			.willReturn(ProdutoBuilder.newProduto().more(10).buildAll());
    	mockMvc.perform(get("/produtos/listar"))
			.andExpect(status().isOk())
			.andExpect(view().name("produtos/lista"))
			.andExpect(model().attributeExists("produtos"));
	}
	
	@Test
	public void formTest() throws Exception{
    	mockMvc.perform(get("/produtos/form"))
			.andExpect(status().isOk())
			.andExpect(view().name("produtos/form"))
			.andExpect(model().attributeExists("tipo"))
    		.andExpect(model().attributeExists("categorias"));
	}
	
	@Test
	public void gravarTest() throws Exception{
    	mockMvc.perform(post("/produtos/gravar")
    			.param("titulo", "novo livro") 
		    	.param("descricao", "Um novo livro") 
				.param("paginas", "101")
				.param("tipoPrecos[0].preco", "EBOOK") 
				.param("tipoPrecos[0].valor", "10.00") 
				.param("dataLancamento", "01/01/2016")
		    	.param("sumarioPath", "http://hahwyallo.com") 
		    	.param("categorias[0]", "AGILE") 
		    	.param("categorias[1]", "GAMES"))
				.andExpect(status().is(302))
				.andExpect(redirectedUrl("/produtos/listar"))
				.andExpect(flash().attribute("sucesso", "Produto cadastrado com sucesso!"));
    	Mockito.verify(produtoRepository).save(any(Produto.class));
	}
	
	@Test
	public void detalheTest() throws Exception{
		given(produtoRepository.findOne(any(Integer.class)))
			.willReturn(ProdutoBuilder.newProduto().buildOne());
    	mockMvc.perform(get("/produtos/detalhe/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("/produtos/detalhe"))
			.andExpect(model().attributeExists("produto"));
	}

	@Test
	public void removerTest() throws Exception{
		given(produtoRepository.findOne(any(Integer.class)))
			.willReturn(ProdutoBuilder.newProduto().buildOne());
		mockMvc.perform(post("/produtos/remover")
			.param("id", "1"))
			.andExpect(flash().attribute("sucesso", "Produto removido com sucesso!"))
			.andExpect(status().is(302))
			.andExpect(redirectedUrl("/produtos"));
	}

	@Test
	public void editarTest() throws Exception{
		given(produtoRepository.findOne(any(Integer.class)))
			.willReturn(ProdutoBuilder.newProduto().buildOne());
		mockMvc.perform(post("/produtos/editar")
				.param("id", "3"))
		.andExpect(status().isOk())
		.andExpect(view().name("produtos/form"))
		.andExpect(model().attributeExists("tipos"))
		.andExpect(model().attributeExists("categorias"))
		.andExpect(model().attributeExists("produto"));
	}

}
