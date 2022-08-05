package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.casadocodigo.loja.annotations.IntegrationTestCustom;
import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.domain.Categoria;
import br.com.casadocodigo.loja.domain.Produto;
import br.com.casadocodigo.loja.domain.TipoPreco;
import br.com.casadocodigo.loja.repository.ProdutoRepository;

@RunWith(SpringRunner.class)
@IntegrationTestCustom
public class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoDao;

    @Test
    public void mustListProdutos() {
        List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(3).buildAll();
        livrosImpressos.stream().forEach(produtoDao::save);
        Iterable<Produto> produtosRetornados = produtoDao.findAll();
        Assert.assertEquals(livrosImpressos, produtosRetornados);
    }

    @Test
    public void findProduto() {
        Produto produto = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN).buildOne();
        produtoDao.save(produto);
        Produto produtoEncontrado = produtoDao.findOne(produto.getId());
        Assert.assertEquals(produto, produtoEncontrado);
    }

    @Test
    public void deletarProduto() {
        Produto produto = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN).buildOne();
        produtoDao.save(produto);
        produtoDao.delete(produto);
        Produto produtoNaoEncontrado = null;
        try {
            produtoNaoEncontrado = produtoDao.findOne(produto.getId());
        } catch (Exception e) {
            Assert.assertNull(produtoNaoEncontrado);
        }
    }

    @Test
    public void atualizarProduto() {
        Produto produto = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN).buildOne();
        String tituloAnterior = produto.getTitulo();
        produtoDao.save(produto);
        Produto produtoEncontrado = produtoDao.findOne(produto.getId());
        produtoEncontrado.setTitulo("Produto Editado");
        produtoDao.save(produtoEncontrado);
        Assert.assertNotEquals(tituloAnterior, produtoEncontrado.getTitulo());
    }

    @Test
    public void listarProdutoPorCategoria() {
        List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(3).buildAll();
        livrosImpressos.stream().forEach(produtoDao::save);
        List<Produto> livrosAgile = produtoDao.findAllByCategorias(Categoria.AGILE);
        livrosAgile.forEach(livro -> {
            Assert.assertEquals(livro.getCategorias().stream().filter(
                    categoria ->
                            categoria.equals(Categoria.AGILE)).findFirst().get(), Categoria.AGILE);
        });

    }

}
