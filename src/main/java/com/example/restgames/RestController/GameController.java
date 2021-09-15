package com.example.restgames.RestController;

import com.example.restgames.Model.Game;
import com.example.restgames.Model.Mensagem;
import com.example.restgames.Repository.GameRepository;
import com.example.restgames.Service.GameService;
import com.example.restgames.dto.request.GameRequest;
import com.example.restgames.dto.response.GameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-total-Count")
@RequestMapping("/jogos")
public class GameController {

    private GameService service;

    @Autowired
    public void setService(GameService service){ this.service = service; }

    @GetMapping
    public List<GameResponse> getAllGames(){ return service.getAllGames(); }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<GameResponse> getGameById(@PathVariable Long id){

        Optional<Game> g = service.getGameById(id);

        if(g.isEmpty()){ return ResponseEntity.notFound().build(); }
        else{ return ResponseEntity.ok().body(new GameResponse(g.get())); }
    }

    @PostMapping
    public ResponseEntity<GameResponse> create(@RequestBody GameRequest gameRequest){

        Game g = gameRequest.buildGame();
        g = service.create(g);
        return ResponseEntity.created(URI.create("/home/"+g.getId())).body(new GameResponse(g));
    }

    @PutMapping(path = "/editar/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Game p){
        return service.getGameById(id)
                .map( record -> {
                    if (record.getId().equals(p.getId())){
                        service.update(p);
                        return ResponseEntity.ok(new GameResponse(p));
                    }else{
                        return ResponseEntity.notFound().build();
                    }
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/deletar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return service.getGameById(id)
                .map( record -> {
                    Mensagem m = new Mensagem();
                    m.setMensagem("Deletado com sucesso");
                    service.delete(record.getId());
                    return ResponseEntity.ok(m);
                }).orElse(ResponseEntity.notFound().build());
    }
}