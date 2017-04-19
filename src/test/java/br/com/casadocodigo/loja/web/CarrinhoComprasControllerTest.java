package br.com.casadocodigo.loja.web;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.domain.CarrinhoCompras;
import br.com.casadocodigo.loja.domain.CarrinhoItem;
import br.com.casadocodigo.loja.domain.TipoPreco;
import br.com.casadocodigo.loja.repository.ProdutoRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { CarrinhoComprasController.class })
@AutoConfigureMockMvc(secure=false)
public class CarrinhoComprasControllerTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ProdutoRepository produtoRepository;
    
    @MockBean
    private CarrinhoCompras carrinhoCompras;
    
    @Test
    public void addTest() throws Exception{
		given(produtoRepository.findOne(any(Integer.class)))
			.willReturn(ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).buildOne());
		mockMvc.perform(post("/carrinho/add"))
			.andExpect(status().is(302))
			.andExpect(redirectedUrl("/"));
		verify(carrinhoCompras).add(any(CarrinhoItem.class));
		
	}
    
    @Test
    public void itensTest() throws Exception{
    	given(produtoRepository.limitedList(any(PageRequest.class)))
    		.willReturn(ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(5).buildAll());
    	mockMvc.perform(get("/carrinho/itens"))
			.andExpect(status().isOk())
			.andExpect(view().name("carrinho/itens"))
			.andExpect(model().attributeExists("produtos"));
    }
    
    @Test
    public void removerTest() throws Exception{
    	mockMvc.perform(get("/carrinho/remover"))
	    	.andExpect(status().is(302))
			.andExpect(redirectedUrl("/carrinho"));
    	verify(carrinhoCompras).remove(any(Integer.class), any(TipoPreco.class));
    }

}
