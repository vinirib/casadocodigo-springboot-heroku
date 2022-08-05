package br.com.casadocodigo.loja.repository;

import br.com.casadocodigo.loja.domain.Categoria;
import br.com.casadocodigo.loja.domain.Produto;
import br.com.casadocodigo.loja.domain.TipoPreco;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("select q from Produto q  order by rand()")
    List<Produto> limitedList(Pageable pageable);

    List<Produto> findAllByCategorias(Categoria Categoria);

    @Query("select sum(preco.valor) from Produto p inner join p.tipoPrecos preco where preco.preco = :tipoPreco")
    BigDecimal sumPricesByType(@Param("tipoPreco") TipoPreco tipoPreco);

    List<Produto> findByIsNew(Boolean isNew);

}
