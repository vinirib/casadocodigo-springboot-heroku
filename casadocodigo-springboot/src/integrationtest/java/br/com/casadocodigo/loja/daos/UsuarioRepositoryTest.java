package br.com.casadocodigo.loja.daos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Sets;

import br.com.casadocodigo.loja.annotations.IntegrationTestCustom;
import br.com.casadocodigo.loja.domain.Role;
import br.com.casadocodigo.loja.domain.User;
import br.com.casadocodigo.loja.repository.RoleRepository;
import br.com.casadocodigo.loja.repository.UserRepository;

@RunWith(SpringRunner.class)
@IntegrationTestCustom
public class UsuarioRepositoryTest {

	@Autowired
	private UserRepository usuarioRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Test
	public void saveLogin(){
		User usuario = createUsuario();
		usuarioRepository.save(usuario);
		User usuarioSaved = usuarioRepository.findByUsername(usuario.getUsername());
		assertEquals(usuarioSaved,usuario);
	}
	
	public void emptyUsuario(){
		User emptyUser = usuarioRepository.findByUsername("something@org.com");
		assertEquals(emptyUser, null);
	}

	private User createUsuario() {
		Role role = createRole();
		User usuario = new User();
		usuario.setName("Vinicius");
		usuario.setPassword("123456");
		usuario.setUsername("test@test.com.br");
		usuario.setRoles(Sets.newHashSet(role));
		return usuario;
	}

	private Role createRole() {
		Role role = new Role();
		role.setName("ADMIN");
		roleRepository.save(role);
		return role;
	}
}
