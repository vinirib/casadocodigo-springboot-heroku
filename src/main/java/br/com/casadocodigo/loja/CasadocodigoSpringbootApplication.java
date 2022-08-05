package br.com.casadocodigo.loja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CasadocodigoSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasadocodigoSpringbootApplication.class, args);
    }

}
