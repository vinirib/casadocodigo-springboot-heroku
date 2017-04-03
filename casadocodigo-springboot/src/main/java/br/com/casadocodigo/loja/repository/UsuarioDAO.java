package br.com.casadocodigo.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casadocodigo.loja.models.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);

}
