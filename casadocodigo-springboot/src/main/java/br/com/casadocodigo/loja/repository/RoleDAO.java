package br.com.casadocodigo.loja.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.casadocodigo.loja.models.Role;

public interface RoleDAO extends CrudRepository<Role, Long>{ 

	@Query("select r from Role r where r.nome = :roleName")
	Role findByName(String roleName);

}
