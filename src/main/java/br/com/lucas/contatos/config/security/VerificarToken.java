package br.com.lucas.contatos.config.security;

import br.com.lucas.contatos.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class VerificarToken extends OncePerRequestFilter {

    //Injeção TokenService e UsuarioRepository
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository  usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Pegando cabeçalho que vem na requisição
        String authorizationHeader = request.getHeader("Authorization"); //Valor: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
        //Inicializando uma variavel vazia que irá receber o token
        String token = "";
        //verificando se está nulo
        if(authorizationHeader == null){
            token = null;
        }
        //Senão iremos atribuir o valor do header na String token e extrair o token sem a palavra Bearer
        else{
            token = authorizationHeader.replace("Bearer", "").trim(); //Replace(Fazer a troca da palavra Bearer por nada) //trim(Trocar os espaçõs vazios no começo e no fim)
            //Validando o token que será enviado na requisição e caso seja validado conterá o subject que será o email do usuario
            String login = tokenService.validarToken(token);
            //Usando as autorizações do usuário(email) para validar o token
            UserDetails usuario = usuarioRepository.findByEmail(login);
            //Usando autenticacao do usuario para autenticar o token, (usando o objeto usuario, credenciais nao estamos usando, e a role do usuario)
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            //Garantindo que esse processo aconteça nessa thread nesse mesmo processo usando o contexto da aplicação
            //com essa linha de código a aplicação conhece o usuario que está solicitando a autenticação
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        //Fazendo o filtro e devolvendo a requisição e a resposta
        filterChain.doFilter(request, response);
    }
}
