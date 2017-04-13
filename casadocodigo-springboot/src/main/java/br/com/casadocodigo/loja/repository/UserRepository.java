package br.com.casadocodigo.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casadocodigo.loja.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
