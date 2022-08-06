package br.com.casadocodigo.loja.service;

import br.com.casadocodigo.loja.domain.Usuario;
import br.com.casadocodigo.loja.repository.RoleRepository;
import br.com.casadocodigo.loja.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private Argon2PasswordEncoder argon2PasswordEncoder;

    @Override
    public void save(Usuario user) {
        user.setPassword(argon2PasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public Usuario findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
