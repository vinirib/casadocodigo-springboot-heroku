package br.com.casadocodigo.loja.web;

import br.com.casadocodigo.loja.domain.CarrinhoCompras;
import br.com.casadocodigo.loja.domain.Pagamento;
import br.com.casadocodigo.loja.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.Callable;

@RequestMapping("/pagamento")
@Controller
public class PagamentoController {

    private static final String urlPagamento = "http://book-payment.herokuapp.com/payment";

//	@Autowired
//	private RestTemplate restTemplate;

    //	@Autowired
//	private MailSender sender;
    @Autowired
    private CarrinhoCompras carrinhoCompras;

    @RequestMapping(value = "/finalizar", method = RequestMethod.POST)
    public Callable<ModelAndView> finalizar(@AuthenticationPrincipal Usuario usuario, RedirectAttributes model, HttpServletRequest request, HttpServletResponse response) {
        return () -> {

            try {
                Pagamento pagamento = new Pagamento(carrinhoCompras.getTotal());
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null) {
                    new SecurityContextLogoutHandler().logout(request, response, auth);
                }
//	    		String response = restTemplate.postForObject(urlPagamento, pagamento, String.class);
//	    		model.addFlashAttribute("sucesso", response);
//	    		enviaEmailCompraProduto(usuario);
                carrinhoCompras.limpar();
                return new ModelAndView("redirect:/");
            } catch (HttpClientErrorException e) {
                e.printStackTrace();
                model.addFlashAttribute("falha", "Valor maior que o permitido");
                return new ModelAndView("redirect:/produtos");
            }
        };
    }

//	private void enviaEmailCompraProduto(Usuario usuario) {
//	    SimpleMailMessage email = new SimpleMailMessage();
//	    email.setSubject("Compra finalizada com sucesso");
//	    email.setTo(usuario.getEmail());
//	    email.setText("Compra aprovada com sucesso no valor de " + carrinho.getTotal());
//	    email.setFrom("compras@casadocodigo.com.br");
//	    
//	    sender.send(email);
//		
//	}
}
