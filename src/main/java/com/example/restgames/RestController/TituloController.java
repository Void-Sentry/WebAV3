package com.example.restgames.RestController;

import com.example.restgames.Model.Game;
import com.example.restgames.Model.Mensagem;
import com.example.restgames.Model.Titulo;
import com.example.restgames.Service.GameService;
import com.example.restgames.Service.TituloService;
import com.example.restgames.dto.request.GameRequest;
import com.example.restgames.dto.request.TituloRequest;
import com.example.restgames.dto.response.GameResponse;
import com.example.restgames.dto.response.TituloResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-total-Count")
@RequestMapping("/titulos")
public class TituloController {

    private TituloService service;

    @Autowired
    public void setService(TituloService service){ this.service = service; }

    @GetMapping
    public List<TituloResponse> getAllGames(){ return service.TodoTitulo(); }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<TituloResponse> getGameById(@PathVariable Long id){

        Optional<Titulo> g = service.TituloById(id);

        if(g.isEmpty()){ return ResponseEntity.notFound().build(); }
        else{ return ResponseEntity.ok().body(new TituloResponse(g.get())); }
    }

    @PostMapping
    public ResponseEntity<TituloResponse> create(@RequestBody TituloRequest tituloRequest){

        Titulo g = tituloRequest.buildTitulo();
        g = service.create(g);
        return ResponseEntity.created(URI.create("/titulos/"+g.getId())).body(new TituloResponse(g));
    }

    @PutMapping(path = "/editar/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Titulo p){
        return service.TituloById(id)
                .map( record -> {
                    if (record.getId().equals(p.getId())){
                        service.update(p);
                        return ResponseEntity.ok(new TituloResponse(p));
                    }else{
                        return ResponseEntity.notFound().build();
                    }
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/deletar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return service.TituloById(id)
                .map( record -> {
                    Mensagem m = new Mensagem();
                    m.setMensagem("Deletado com sucesso");
                    service.delete(record.getId());
                    return ResponseEntity.ok(m);
                }).orElse(ResponseEntity.notFound().build());
    }
}
