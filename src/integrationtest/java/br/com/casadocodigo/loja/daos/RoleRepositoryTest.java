package br.com.casadocodigo.loja.daos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.casadocodigo.loja.annotations.IntegrationTestCustom;
import br.com.casadocodigo.loja.domain.Role;
import br.com.casadocodigo.loja.exception.AppException;
import br.com.casadocodigo.loja.repository.RoleRepository;

@RunWith(SpringRunner.class)
@IntegrationTestCustom
public class RoleRepositoryTest {


    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void findByName() throws AppException {
        createRole("ADMIN");
        createRole("USER");
        Role roleAdmin = roleRepository.findByName("ADMIN");
        Role roleUser = roleRepository.findByName("USER");
        assertEquals(roleAdmin.getName(), "ADMIN");
        assertEquals(roleUser.getName(), "USER");
    }

    private Role createRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        roleRepository.save(role);
        return role;
    }
}
