package com.example.restgames.Service;

import com.example.restgames.Model.Fornecedora;
import com.example.restgames.Model.Game;
import com.example.restgames.Model.Titulo;
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
public class TituloService {

    private TituloRepository repository;

    @Autowired
    public void setRepository(TituloRepository repository){ this.repository = repository; }

    public Optional<Titulo> TituloById(Long id){ return repository.findByDeletedIsNullAndId(id); }

    public List<TituloResponse> TodoTitulo(){

        ArrayList<TituloResponse> lista = new ArrayList<>();

        for(Titulo titulo : repository.findByDeletedIsNull())
            lista.add(new TituloResponse(titulo));

        return lista;
    }

    public Titulo create(Titulo g){ return repository.save(g); }

    public Titulo update(Titulo g){ return repository.saveAndFlush(g); }

    public Titulo delete(Long id){

        Titulo g = repository.getById(id);
        g.setDeleted(new Date());
        return repository.saveAndFlush(g);
    }
}
