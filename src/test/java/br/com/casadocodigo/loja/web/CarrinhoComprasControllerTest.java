package br.com.casadocodigo.loja.web;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.config.TestSecurityConfig;
import br.com.casadocodigo.loja.domain.CarrinhoCompras;
import br.com.casadocodigo.loja.domain.CarrinhoItem;
import br.com.casadocodigo.loja.domain.TipoPreco;
import br.com.casadocodigo.loja.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = CarrinhoComprasController.class, properties = "server.port=10000")
@Import(TestSecurityConfig.class)
public class CarrinhoComprasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoRepository produtoRepository;

    @MockBean
    private CarrinhoCompras carrinhoCompras;

    @Test
    public void addTest() throws Exception {
        given(produtoRepository.findById(any(Integer.class)))
                .willReturn(Optional.of(ProdutoBuilder
                        .newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).buildOne()));
        mockMvc.perform(post("/carrinho/add")
                        .param("produtoId", "10")
                        .param("tipo", "IMPRESSO"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/"));
        verify(carrinhoCompras).add(any(CarrinhoItem.class));

    }

    @Test
    public void itensTest() throws Exception {
        given(produtoRepository.limitedList(any(PageRequest.class)))
                .willReturn(ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(5).buildAll());
        mockMvc.perform(get("/carrinho/itens"))
                .andExpect(status().isOk())
                .andExpect(view().name("carrinho/itens"))
                .andExpect(model().attributeExists("produtos"));
    }

    @Test
    public void removerTest() throws Exception {
        doNothing().when(carrinhoCompras).remove(anyInt(), any(TipoPreco.class));
        mockMvc.perform(get("/carrinho/remover")
                        .param("produtoId", "20")
                        .param("tipoPreco", "EBOOK"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/carrinho/itens"));
        verify(carrinhoCompras).remove(any(Integer.class), any(TipoPreco.class));
    }

}
