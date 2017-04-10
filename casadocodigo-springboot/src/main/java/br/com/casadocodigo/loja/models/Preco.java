package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMin;

@Embeddable
public class Preco {

	@Column(nullable=false)
	@DecimalMin(value="1.00")
	private BigDecimal valor;
	@Column(nullable=false)
	private TipoPreco preco;
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoPreco getPreco() {
		return preco;
	}
	public void setPreco(TipoPreco preco) {
		this.preco = preco;
	}
	
	public String toString() {
	    return this.preco.name() + " - " + this.valor;
	}
	
}
