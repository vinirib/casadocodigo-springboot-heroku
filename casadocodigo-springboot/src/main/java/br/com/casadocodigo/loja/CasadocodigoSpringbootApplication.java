package br.com.casadocodigo.loja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"br.com.casadocodigo.loja*"})
public class CasadocodigoSpringbootApplication  {

	public static void main(String[] args) {
		SpringApplication.run(CasadocodigoSpringbootApplication.class, args);
	}
	
}
