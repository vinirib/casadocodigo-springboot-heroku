package br.com.casadocodigo.loja.service;

import br.com.casadocodigo.loja.domain.Usuario;

public interface UsuarioService {
    void save(Usuario user);

    Usuario findByUsername(String username);
}
