package br.com.lucas.contatos.service;

import br.com.lucas.contatos.model.Contato;
import br.com.lucas.contatos.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContatoService{

    @Autowired
    private ContatoRepository contatoRepository;

    public Contato gravar(Contato contato){
        return contatoRepository.save(contato);
    }

    public Optional<Contato> buscarPorId(UUID id){
        return contatoRepository.findById(id);
    }

    public List<Contato> buscarTodos(){
        return contatoRepository.findAll();
    }

    public Contato atualizar(Contato contato){
        Optional<Contato> contatoOptional = contatoRepository.findById(contato.getId());
        if(contatoOptional.isPresent()){
            return contatoRepository.save(contato);
        }
        else{
            throw new RuntimeException("Contato não encontrado");
        }
    }

    public void remover(UUID id){
        Optional<Contato> contatoOptional = contatoRepository.findById(id);
        if(contatoOptional.isPresent()){
            contatoRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("Contato não encontrado");
        }
    }

    public List<Contato> buscarAniversariantes(LocalDate dataInicial, LocalDate dataFinal){
        return contatoRepository.findByDataNascimentoBetween(dataInicial, dataFinal);
    }

}

