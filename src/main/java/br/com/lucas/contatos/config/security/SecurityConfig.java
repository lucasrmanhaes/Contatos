package br.com.lucas.contatos.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //Classe de configuração de segurança
@EnableWebSecurity //Todas as requisições irão passar por essa classe
public class SecurityConfig {

    //Injetando dependencia de verificar token
    @Autowired
    VerificarToken verificarToken;

    @Bean //Objeto gerenciado pelo Spring
    public SecurityFilterChain filtrarCadeiaDeSeguranca(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        //Cadastrar usuário -> permitido para todos
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        //Logar usuário -> permitido para todos
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        //Listar contatos -> apenas ADMIN e USER
                        .requestMatchers(HttpMethod.GET, "/api/contatos").hasAnyRole("ADMIN", "USER")
                        //Criar, atualizar e deletar -> apenas ADMIN
                        .requestMatchers(HttpMethod.POST, "/api/contatos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/contatos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/contatos").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated()
                )
                //VERIFICANDO TOKEN - Verificar token antes do UsernamePasswordAuthenticationFilter
                .addFilterBefore(verificarToken, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
