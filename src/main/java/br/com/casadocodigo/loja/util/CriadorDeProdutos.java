package br.com.casadocodigo.loja.util;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.domain.Produto;
import br.com.casadocodigo.loja.domain.Role;
import br.com.casadocodigo.loja.domain.Usuario;
import br.com.casadocodigo.loja.repository.ProdutoRepository;
import br.com.casadocodigo.loja.repository.RoleRepository;
import br.com.casadocodigo.loja.repository.UsuarioRepository;
import br.com.casadocodigo.loja.service.UsuarioService;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;

@Component
public class CriadorDeProdutos {

    @Autowired
    private UsuarioService userService;

    @Autowired
    private ProdutoRepository produtoDAO;

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Argon2PasswordEncoder argon2PasswordEncoder;

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
        Usuario usuario = userService.findByUsername("admin@casadocodigo.com.br");
        if (usuario == null) {
            /**
             * Creating admin user;
             */
            Usuario adminUser = new Usuario();
            adminUser.setName("Administrador");
            adminUser.setUsername("admin@casadocodigo.com.br");

            adminUser.setPassword(argon2PasswordEncoder.encode("123456"));

            Role role = new Role();
            role.setName("ROLE_ADMIN");

            adminUser.setRoles(new HashSet<>(roleRepository.findAll()));
            userRepository.save(adminUser);
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
        produtoDAO.saveAll(defaultTemplateProdutos);
    }

}