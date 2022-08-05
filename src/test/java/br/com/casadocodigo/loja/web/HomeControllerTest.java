package br.com.casadocodigo.loja.web;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.config.TestSecurityConfig;
import br.com.casadocodigo.loja.domain.Categoria;
import br.com.casadocodigo.loja.domain.TipoPreco;
import br.com.casadocodigo.loja.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = HomeController.class, properties = "server.port=10000")
@Import(TestSecurityConfig.class)
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
    public void testCollection() throws Exception {
        given(produtoRepository.findAllByCategorias(any(Categoria.class)))
                .willReturn(ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(3).buildAll());
        mockMvc.perform(get("/collection/GAMES"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("produtos"));
    }
}
