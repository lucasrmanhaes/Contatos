package br.com.lucas.contatos.config.security;
import br.com.lucas.contatos.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    //----------------------------------- GERAR TOKEN -------------------------------------------
    @Value("${key.auth}")
    private String chaveSecreta;

    //Metado para gerar o tempo usado no token
    public Instant gerarDataDeExpiracao(){
        //Criando o tempo a partir de agora, com duração de 2 horas e o fuso horario de São Paulo
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    //Método de gerar token, se usuário existir e estiver autenticado
    public String gerarToken(Usuario usuario){
        //Irá tentar gerar o token
        try{
            //Criando o algoritmo com a chave secreta
            Algorithm algorithm = Algorithm.HMAC256(chaveSecreta);
            //Criando o token com o algoritimo
            String token = JWT
                    .create()
                    //Emissor do token
                    .withIssuer("contatos")
                    //Usuario
                    .withSubject(usuario.getEmail())
                    //Tempo de expiração usando o metodo de gerar tempo
                    .withExpiresAt(gerarDataDeExpiracao())
                    //Assinar o token, aplicar a criptografia
                    .sign(algorithm);
                //Retornando a String token
                return token;
        }
        //Senão lança a exceção erro de criação de token JWT
        catch(JWTCreationException erro){
            throw new RuntimeException("Não foi possível gerar o token");
        }
    }

    //----------------------------------- VALIDAR TOKEN -------------------------------------------
    public String validarToken(String token){
        try{
            //usando o HMAC256 junto com a chave secreta para decifrar o token e verificar o hash
            Algorithm algorithm = Algorithm.HMAC256(chaveSecreta);
            //
            return JWT.require(algorithm)
                    //Verificando emissor
                    .withIssuer("contatos")
                    //Construir o algoritmo
                    .build()
                    //Verficar o token
                    .verify(token)
                    //Verificar o usuário do token
                    .getSubject();
        }
        catch(JWTVerificationException erro){
            throw new RuntimeException("Não foi possível validar o token");
        }
    }

}
