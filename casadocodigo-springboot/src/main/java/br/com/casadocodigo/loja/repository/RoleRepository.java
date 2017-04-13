package br.com.casadocodigo.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.casadocodigo.loja.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	@Query("select r from Role r where r.name = :roleName")
	Role findByName(@Param("roleName")String roleName);
}
