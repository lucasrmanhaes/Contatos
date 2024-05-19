package br.com.lucas.contatos.service;

import br.com.lucas.contatos.dto.UsuarioCadastroDto;
import br.com.lucas.contatos.dto.UsuarioExibicaoDto;
import br.com.lucas.contatos.exception.UsuarioExisteException;
import br.com.lucas.contatos.exception.UsuarioNaoEncontradoException;
import br.com.lucas.contatos.model.Usuario;
import br.com.lucas.contatos.model.UsuarioRole;
import br.com.lucas.contatos.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDto gravar(UsuarioCadastroDto usuarioCadastroDto){
        //Criptografando a senha
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioCadastroDto.senha());
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDto, usuario);
        usuario.setSenha(senhaCriptografada);
        //Verificando role
        if(usuario.getRole() == null){
            usuario.setRole(UsuarioRole.USER);
        }
        return new UsuarioExibicaoDto(usuarioRepository.save(usuario));
    }

    public UsuarioExibicaoDto listar(UUID id){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if(usuarioOpt.isPresent()){
            return new UsuarioExibicaoDto(usuarioOpt.get());
        }
        else{
            throw new UsuarioNaoEncontradoException("Usuário não localizado na base de dados");
        }
    }

    public UsuarioExibicaoDto atualizar(Usuario usuario){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuario.getIdUsuario());
        if(usuarioOpt.isPresent()){
            if(usuario.getSenha() == null){
                usuario.setSenha(usuarioOpt.get().getSenha());
            }
            return new UsuarioExibicaoDto(usuarioRepository.save(usuario));
        }
        else{
            throw new UsuarioNaoEncontradoException("Usuário não localizado na base de dados");
        }
    }

    public void remover(UUID id){
        usuarioRepository.deleteById(id);
    }

}
