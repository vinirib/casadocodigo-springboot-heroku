package br.com.casadocodigo.loja.builders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import br.com.casadocodigo.loja.domain.Role;
import br.com.casadocodigo.loja.domain.User;

public class UsuarioBuilder {

    private List<User> usuarios = new ArrayList<>();
    
    private static Random random = new Random();
    
    private UsuarioBuilder(User usuario) {
        usuarios.add(usuario);

    }

    public static UsuarioBuilder newUsuario(String role) {
        User usuario = create(role);
        return new UsuarioBuilder(usuario);
    }

    public static UsuarioBuilder newUsuario() {
        User usuario = create("admin");
        return new UsuarioBuilder(usuario);
    }

    private static User create(String role) {
    	Role role2 = new Role();
    	role2.setName(role);
    	User usuario = new User();
    	usuario.setId(random.nextLong());
    	usuario.setName("Usuario" + random.nextInt(200));
    	usuario.setPassword("123");
    	usuario.setPasswordConfirm("123");
    	usuario.setRoles(new HashSet<>(Arrays.asList(role2)));
    	usuario.setUsername("user@user.com.br");
    	return usuario;
    }

    public UsuarioBuilder more(int number) {
        for (int i = 0; i < number; i++) {
            usuarios.add(create("admin"));
        }
        return this;
    }

    public User buildOne() {
        return usuarios.get(0);
    }

    public List<User> buildAll() {
        return usuarios;
    }
}
