package br.com.casadocodigo.loja.web;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.domain.CarrinhoCompras;
import br.com.casadocodigo.loja.domain.Produto;
import br.com.casadocodigo.loja.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(value = ProdutosController.class, properties = "server.port=10000")
public class ProdutosControllerTest {

    @MockBean
    private ProdutoRepository produtoRepository;

    @MockBean
    private CarrinhoCompras carrinhoCompras;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser("cod")
    public void listarTest() throws Exception {
        given(produtoRepository.findAll())
                .willReturn(ProdutoBuilder.newProduto().more(10).buildAll());
        mockMvc.perform(get("/produtos/listar"))
                .andExpect(status().isOk())
                .andExpect(view().name("produtos/lista"))
                .andExpect(model().attributeExists("produtos"));
    }

    @Test
    @WithMockUser("cod")
    public void formTest() throws Exception {
        mockMvc.perform(get("/produtos/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("produtos/form"))
                .andExpect(model().attributeExists("tipo"))
                .andExpect(model().attributeExists("categorias"));
    }

    @Test
    @WithMockUser("cod")
    public void gravarTest() throws Exception {
        mockMvc.perform(post("/produtos/gravar").with(csrf())
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
    @WithMockUser("cod")
    public void detalheTest() throws Exception {
        given(produtoRepository.findById(any(Integer.class)))
                .willReturn(Optional.of(ProdutoBuilder.newProduto().buildOne()));
        mockMvc.perform(get("/produtos/detalhe/1").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("produtos/detalhe"))
                .andExpect(model().attributeExists("produto"));
    }

    @Test
    @WithMockUser("cod")
    public void removerTest() throws Exception {
        given(produtoRepository.findById(any(Integer.class)))
                .willReturn(Optional.of(ProdutoBuilder.newProduto().buildOne()));
        mockMvc.perform(post("/produtos/remover").with(csrf())
                        .param("id", "1"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/produtos"));
    }

    @Test
    @WithMockUser("cod")
    public void editarTest() throws Exception {
        given(produtoRepository.findById(any(Integer.class)))
                .willReturn(Optional.of(ProdutoBuilder.newProduto().buildOne()));
        mockMvc.perform(post("/produtos/editar").with(csrf())
                        .param("id", "3"))
                .andExpect(status().isOk())
                .andExpect(view().name("produtos/form"))
                .andExpect(model().attributeExists("categorias"))
                .andExpect(model().attributeExists("produto"));
    }

}
