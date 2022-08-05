package br.com.casadocodigo.loja.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    private Long id;
    @NotNull
    private String name;

    private Set<Usuario> users = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles")
    public Set<Usuario> getUsers() {
        return users;
    }

    public void setUsers(Set<Usuario> users) {
        this.users = users;
    }
}
