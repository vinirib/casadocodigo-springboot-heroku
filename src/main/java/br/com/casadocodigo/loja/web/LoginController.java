package br.com.casadocodigo.loja.web;

import br.com.casadocodigo.loja.domain.Usuario;
import br.com.casadocodigo.loja.repository.RoleRepository;
import br.com.casadocodigo.loja.repository.UsuarioRepository;
import br.com.casadocodigo.loja.service.UsuarioService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsuarioRepository userRepository;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("users", userRepository.findByUsernameNot("admin@casadocodigo.com.br"));
        return "login";
    }


    @GetMapping("/logout")
    public ModelAndView logout() {
        return new ModelAndView("redirect:/produtos");
    }

    @GetMapping("/login/criar")
    public String criar(Usuario user) {
        return "formLogin";
    }

    @PostMapping("/login/gravar")
    public String gravar(@Valid Usuario user, BindingResult bindingResult, String role, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "formLogin";
        }
        user.setRoles(Sets.newHashSet(roleRepository.findByName(role)));
        userService.save(user);
        redirectAttributes.addFlashAttribute("sucesso", "Usuario cadastrado com sucesso!");
        return "redirect:/login";
    }
}
