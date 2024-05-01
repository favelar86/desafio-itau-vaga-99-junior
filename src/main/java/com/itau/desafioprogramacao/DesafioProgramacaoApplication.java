package com.itau.desafioprogramacao;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "desafio-itau-vaga-99-junior",
								version = "1",
								description = "API desenvolvida para o Desafio de Programação Itaú"))

public class DesafioProgramacaoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DesafioProgramacaoApplication.class, args);
	}

}
