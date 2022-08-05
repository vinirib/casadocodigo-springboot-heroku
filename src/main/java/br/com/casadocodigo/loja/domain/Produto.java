package br.com.casadocodigo.loja.domain;


import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Digite um título")
    private String titulo;
    @NotBlank(message = "Digite a descrição")
    @Column(columnDefinition = "TEXT")
    private String descricao;
    @NotNull(message = "Preencha o campo páginas")
    @Min(value = 20, message = "Produto deve conter no mínimo 20 páginas")
    private int paginas;
    @ElementCollection(fetch = FetchType.EAGER)
    @NotEmpty(message = "Preencha os preços")
    private List<Preco> tipoPrecos = new ArrayList<Preco>();
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Digite a data de lançamento")
    private Calendar dataLancamento;
    @NotBlank(message = "Digite a url da imagem")
    @URL(message = "Digite uma url valida")
    private String sumarioPath;
    @ElementCollection(targetClass = Categoria.class)
    @CollectionTable(name = "produto_categoria", joinColumns = @JoinColumn(name = "categoria_id"))
    @Column(name = "categoria", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotEmpty(message = "Escolha uma categoria")
    private List<Categoria> categorias = new ArrayList<>();


    private boolean isNew;

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public List<Preco> getTipoPrecos() {
        return tipoPrecos;
    }

    public void setTipoPrecos(List<Preco> tipoPrecos) {
        this.tipoPrecos = tipoPrecos;
    }

    public Calendar getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Calendar dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getSumarioPath() {
        return sumarioPath;
    }

    public void setSumarioPath(String sumarioPath) {
        this.sumarioPath = sumarioPath;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString() {
        return "Produto [titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getId();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produto other = (Produto) obj;
        return getId() == other.getId();
    }

    public BigDecimal precoPara(TipoPreco tipoPreco) {
        return tipoPrecos.stream().filter(preco -> preco.getPreco().equals(tipoPreco)).findFirst().get().getValor();
    }

}
