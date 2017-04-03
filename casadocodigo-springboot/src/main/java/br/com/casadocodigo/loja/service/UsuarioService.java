package br.com.casadocodigo.loja.service;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.repository.RoleDAO;
import br.com.casadocodigo.loja.repository.UsuarioDAO;

@Service("userService")
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioDAO userRepository;
	@Autowired
	private RoleDAO roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	public void save(Usuario usuario) {
		usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getPassword()));
		Role userRole = roleRepository.findByName("ADMIN");
		usuario.setRoles(Arrays.asList(userRole));
		userRepository.save(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username);
	}

}
