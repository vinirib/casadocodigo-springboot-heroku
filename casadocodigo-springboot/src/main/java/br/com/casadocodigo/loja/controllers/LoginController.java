package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Sets;

import br.com.casadocodigo.loja.models.User;
import br.com.casadocodigo.loja.repository.RoleRepository;
import br.com.casadocodigo.loja.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(){
        return "login";
    }
    
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public ModelAndView logout(){
    	return new ModelAndView("redirect:/produtos");
    }
    
    @RequestMapping(value="/login/criar", method=RequestMethod.GET)
    public String criar(User usuario){
    	return "formLogin";
    }
    
    @RequestMapping(value="/login/gravar",method=RequestMethod.POST)
    public String gravar(User usuario, String role, RedirectAttributes redirectAttributes){
    	usuario.setRoles(Sets.newHashSet(roleRepository.findByName(role)));
    	userService.save(usuario);
    	redirectAttributes.addFlashAttribute("sucesso", "Usuario cadastrado com sucesso!");
    	return "redirect:/login";
    }
}
