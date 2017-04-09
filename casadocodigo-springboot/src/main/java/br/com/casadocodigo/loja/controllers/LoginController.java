package br.com.casadocodigo.loja.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Sets;

import br.com.casadocodigo.loja.models.User;
import br.com.casadocodigo.loja.repository.RoleRepository;
import br.com.casadocodigo.loja.service.UserService;

@Controller
public class LoginController  {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("/login")
    public String login(){
        return "login";
    }
    
    @GetMapping("/logout")
    public ModelAndView logout(){
    	return new ModelAndView("redirect:/produtos");
    }
    
    @GetMapping("/login/criar")
    public String criar(User user){
    	return "formLogin";
    }
    
    @PostMapping("/login/gravar")
    public String gravar(@Valid User user, BindingResult bindingResult, String role, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            return "formLogin";
        }
        user.setRoles(Sets.newHashSet(roleRepository.findByName(role)));
    	userService.save(user);
    	redirectAttributes.addFlashAttribute("sucesso", "Usuario cadastrado com sucesso!");
    	return "redirect:/login";
    }
}
