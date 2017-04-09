package br.com.casadocodigo.loja.service;

import br.com.casadocodigo.loja.models.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
