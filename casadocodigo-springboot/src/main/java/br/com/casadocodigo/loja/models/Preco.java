package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Embeddable
public class Preco {

		@NotNull
		@DecimalMin(value="1.00")
	private BigDecimal valor;
		@NotNull
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
