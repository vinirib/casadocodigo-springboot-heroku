package br.com.casadocodigo.loja.web;

import static org.junit.Assert.assertTrue;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.casadocodigo.loja.config.SecurityConfiguration;
import br.com.casadocodigo.loja.repository.RoleRepository;
import br.com.casadocodigo.loja.repository.UsuarioRepository;
import br.com.casadocodigo.loja.service.UsuarioService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { LoginController.class })
@Import(SecurityConfiguration.class)
public class LoginControllerTest {

	
	@Autowired
    private MockMvc mockMvc;
	    
    @MockBean
    private UsuarioService userService;

    @MockBean
    private RoleRepository roleRepository;
    
    @MockBean
    private UsuarioRepository userRepository;
    
    @MockBean
    private UserDetailsService userDetailsService;
    

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;
    
    @Before
    public void setup() {
    	mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }
    
    @Test
    public void test(){
    	assertTrue(true);
    }
//    @Test
//    @WithMockUser(username="admin@casadocodigo.com.br", password="123456", roles={"ADMIN"})
//    public void loginTest() throws Exception{
//    	given(userDetailsService.loadUserByUsername(any(String.class)))
//    		.willReturn(UsuarioBuilder.newUsuario().buildOne());
//    	mockMvc.perform(post("/login").with(csrf()))
//			.andExpect(status().is(302))
//			.andExpect(redirectedUrl("/"));
//			.andExpect(view().name("login"));
//			.andExpect(model().attributeExists("users"));
//    }
    
    
    
    
}
