package com.example.restgames.Service;

import com.example.restgames.Model.Game;
import com.example.restgames.Repository.GameRepository;
import com.example.restgames.dto.response.GameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private GameRepository repository;

    @Autowired
    public void setRepository(GameRepository repository){ this.repository = repository; }

    public Optional<Game> getGameById(Long id){ return repository.findByDeletedIsNullAndId(id); }

    public List<GameResponse> getAllGames(){

        ArrayList<GameResponse> lista = new ArrayList<>();

        for(Game g : repository.findByDeletedIsNull())
            lista.add(new GameResponse(g));

        return lista;
    }

    public Game create(Game g){ return repository.save(g); }

    public Game update(Game g){ return repository.saveAndFlush(g); }

    public Game delete(Long id){

        Game g = repository.getById(id);
        g.setDeleted(new Date());
        return repository.saveAndFlush(g);
    }
}