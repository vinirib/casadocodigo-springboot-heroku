package br.com.casadocodigo.loja.repository;

import br.com.casadocodigo.loja.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);

    List<Usuario> findByUsernameNot(String username);
}
