package br.com.casadocodigo.loja.util;

import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.domain.Produto;
import br.com.casadocodigo.loja.domain.Role;
import br.com.casadocodigo.loja.domain.User;
import br.com.casadocodigo.loja.repository.ProdutoRepository;
import br.com.casadocodigo.loja.repository.RoleRepository;
import br.com.casadocodigo.loja.repository.UserRepository;
import br.com.casadocodigo.loja.service.UserService;

@Component
public class CriadorDeProdutos {

	@Autowired
	private UserService userService;

	@Autowired
	private ProdutoRepository produtoDAO;
	
    @Autowired
    private UserRepository  userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

	@PostConstruct
	public void init() {
		verifyRoles();
		verifyUsers();
		verifyProdutos();
	}

	private void verifyProdutos() {
		Iterable<Produto> listaDeProdutosInterator = produtoDAO.findAll();
		if (IteratorUtils.toList(listaDeProdutosInterator.iterator()).isEmpty()) {
			criaProdutos();
		}
	}

	private void verifyUsers() {
		User usuario = userService.findByUsername("admin@casadocodigo.com.br");
		if (usuario == null) {
			/**
			 * Creating admin user;
			 */
			User adminUser = new User();
			adminUser.setName("Administrador");
			adminUser.setUsername("admin@casadocodigo.com.br");
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			adminUser.setPassword(encoder.encode("123456"));
			
			Role role = new Role();
			role.setName("ROLE_ADMIN");
			
			adminUser.setRoles(new HashSet<>(roleRepository.findAll()));
			userRepository.save(adminUser);;
		}
	}

	private void verifyRoles() {
		Role roleAdminExists = roleRepository.findByName("ROLE_ADMIN");
		if (roleAdminExists == null) {
			Role roleAdmin = new Role();
			roleAdmin.setName("ROLE_ADMIN");
			roleRepository.save(roleAdmin);
		}

		Role roleClientExists = roleRepository.findByName("ROLE_CLIENT");
		if (roleClientExists == null) {
			Role roleClient = new Role();
			roleClient.setName("ROLE_CLIENT");
			roleRepository.save(roleClient);
			
		}
	}

	private void criaProdutos() {
		List<Produto> defaultTemplateProdutos = ProdutoBuilder.createDefaultTemplateProdutos();
		produtoDAO.save(defaultTemplateProdutos);
	}

}