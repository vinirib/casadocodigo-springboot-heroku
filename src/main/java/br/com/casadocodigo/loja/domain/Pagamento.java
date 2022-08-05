package br.com.casadocodigo.loja.domain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private final BigDecimal value;

    public Pagamento(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

}
