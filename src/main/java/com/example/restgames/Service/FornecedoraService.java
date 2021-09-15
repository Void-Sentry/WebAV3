package com.example.restgames.Service;

import com.example.restgames.Model.Fornecedora;
import com.example.restgames.Model.Titulo;
import com.example.restgames.Repository.FornecedoraRepository;
import com.example.restgames.Repository.TituloRepository;
import com.example.restgames.dto.response.FornecedoraResponse;
import com.example.restgames.dto.response.GameResponse;
import com.example.restgames.dto.response.TituloResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FornecedoraService {

    private FornecedoraRepository repository;

    @Autowired
    public void setRepository(FornecedoraRepository repository){ this.repository = repository; }

    public Optional<Fornecedora> FornecedoraId(Long id){ return repository.findByDeletedIsNullAndId(id); }

    public List<FornecedoraResponse> TodaFornecedora(){

        ArrayList<FornecedoraResponse> lista = new ArrayList<>();

        for(Fornecedora fornecedora : repository.findByDeletedIsNull())
            lista.add(new FornecedoraResponse(fornecedora));

        return lista;
    }

    public Fornecedora create(Fornecedora g){ return repository.save(g); }

    public Fornecedora update(Fornecedora g){ return repository.saveAndFlush(g); }

    public Fornecedora delete(Long id){

        Fornecedora g = repository.getById(id);
        g.setDeleted(new Date());
        return repository.saveAndFlush(g);
    }
}
