package br.com.lucas.contatos.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //Classe de configuração de segurança
@EnableWebSecurity //Todas as requisições irão passar por essa classe
public class SecurityConfig {

    //Retorna lista de itens que devem ser verificados
    //Durando o processo de autenticação
    @Bean //Objeto gerenciado pelo Spring
    public SecurityFilterChain filtrarCadeiaDeSeguranca(HttpSecurity httpSecurity) throws Exception {
        System.out.println("Chegou na config");
        //Configurando a segurança HTTP -> Desabilitar o cross side request forgered e criando um gerenciador de sessão
        //E informando ao gerenciador de sessao que a sessão vai ser criada utlizando o Stateless
        return httpSecurity.csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
    }

}
