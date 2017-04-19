package br.com.casadocodigo.loja.web;

import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice
public class ExceptionHandlerController {

//    @ExceptionHandler(Exception.class)
    public ModelAndView trataExceptionGenerica(Exception exception){
        System.out.println("Erro genérico acontecendo");
        exception.printStackTrace();

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
