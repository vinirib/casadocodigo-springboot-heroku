package br.com.casadocodigo.loja.web;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.domain.Categoria;
import br.com.casadocodigo.loja.domain.TipoPreco;
import br.com.casadocodigo.loja.repository.ProdutoRepository;	

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { HomeController.class })
@AutoConfigureMockMvc(secure=false)
public class HomeControllerTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ProdutoRepository produtoRepository;
    
	@Test
	public void testIndex() throws Exception {
		given(produtoRepository.findAll())
			.willReturn(ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(3).buildAll());
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(model().attributeExists("produtos"));
	}
	
	@Test
	public void testCollection() throws Exception{
		given(produtoRepository.findAllByCategorias(any(Categoria.class)))
			.willReturn(ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(3).buildAll());
		mockMvc.perform(get("/collection/GAMES"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(model().attributeExists("produtos"));
	}
}
