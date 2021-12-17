package com.example.recuperacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

@EntityScan( basePackages = { "com.example.recuperacao.Model" } )
@EnableJpaRepositories( basePackages = { "com.example.recuperacao.Repository"} )

@RestController
@SpringBootApplication
public class RecuperacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecuperacaoApplication.class, args);
    }

}
