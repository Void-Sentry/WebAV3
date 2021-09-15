package com.example.restgames.Service;

import com.example.restgames.Model.Distribuidora;
import com.example.restgames.Model.Titulo;
import com.example.restgames.Repository.DistribuidoraRepository;
import com.example.restgames.Repository.TituloRepository;
import com.example.restgames.dto.response.DistribuidoraResponse;
import com.example.restgames.dto.response.GameResponse;
import com.example.restgames.dto.response.TituloResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DistribuidoraService {

    private DistribuidoraRepository repository;

    @Autowired
    public void setRepository(DistribuidoraRepository repository){ this.repository = repository; }

    public Optional<Distribuidora> DistribuidoraId(Long id){ return repository.findByDeletedIsNullAndId(id); }

    public List<DistribuidoraResponse> TodoTitulo(){

        ArrayList<DistribuidoraResponse> lista = new ArrayList<>();

        for(Distribuidora distribuidora : repository.findByDeletedIsNull())
            lista.add(new DistribuidoraResponse(distribuidora));

        return lista;
    }

    public Distribuidora create(Distribuidora g){ return repository.save(g); }

    public Distribuidora update(Distribuidora g){ return repository.saveAndFlush(g); }

    public Distribuidora delete(Long id){

        Distribuidora g = repository.getById(id);
        g.setDeleted(new Date());
        return repository.saveAndFlush(g);
    }
}
