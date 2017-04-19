package br.com.casadocodigo.loja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casadocodigo.loja.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
    List<Usuario> findByUsernameNotIn(String username);
}
