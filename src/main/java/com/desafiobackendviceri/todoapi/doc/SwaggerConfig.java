package com.desafiobackendviceri.todoapi.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private Contact contato() {
        return new Contact(
                "Alex Oliveira",
                "https://github.com/Lexleon-Oliver",
                "lexleonmg@gmail.com");
    }
    private ApiInfoBuilder informacoesApi() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("ProjetoToDoApi");
        apiInfoBuilder.description("Projeto de desenvolvimento de um aplicativo para " +
                "gerenciamento de tarefas para resolução do desafio técnico de programação proposto pelo processo " +
                "seletivo Dev Java Júnior Viceri");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.termsOfServiceUrl("Termo de uso: Open Source");
        apiInfoBuilder.license("Licença - Alex Oliveira");
        apiInfoBuilder.licenseUrl("https://github.com/Lexleon-Oliver");
        apiInfoBuilder.contact(this.contato());

        return apiInfoBuilder;

    }
    @Bean
    public Docket detalheApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        docket
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.desafiobackendviceri.todoapi.resource"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.informacoesApi().build())
                .consumes(new HashSet<String>(Arrays.asList("application/json")))
                .produces(new HashSet<String>(Arrays.asList("application/json")));

        return docket;
    }
}
