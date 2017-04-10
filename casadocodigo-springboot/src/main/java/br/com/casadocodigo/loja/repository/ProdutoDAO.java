package br.com.casadocodigo.loja.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.casadocodigo.loja.models.Categoria;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

public interface ProdutoDAO extends CrudRepository<Produto, Integer>{
	
	@Query("select q from Produto q  order by rand()")
	List<Produto> limitedList(Pageable pageable);

	@Query("select p from Produto p join fetch p.categorias categorias where categorias = :categoria")
	List<Produto> listByCategory(@Param("categoria")Categoria Categoria);
	
	@Query("select sum(preco.valor) from Produto p inner join p.tipoPrecos preco where preco.preco = :tipoPreco")
	BigDecimal sumPricesByType(@Param("tipoPreco")TipoPreco tipoPreco);
	
}
