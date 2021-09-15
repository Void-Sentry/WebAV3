package com.example.restgames.RestController;

import com.example.restgames.Model.Distribuidora;
import com.example.restgames.Model.Mensagem;
import com.example.restgames.Model.Titulo;
import com.example.restgames.Service.DistribuidoraService;
import com.example.restgames.Service.TituloService;
import com.example.restgames.dto.request.DistribuidoraRequest;
import com.example.restgames.dto.request.TituloRequest;
import com.example.restgames.dto.response.DistribuidoraResponse;
import com.example.restgames.dto.response.TituloResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-total-Count")
@RequestMapping("/distribuidoras")
public class DistribuidoraController {

    private DistribuidoraService service;

    @Autowired
    public void setService(DistribuidoraService service){ this.service = service; }

    @GetMapping
    public List<DistribuidoraResponse> getAllGames(){ return service.TodoTitulo(); }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<DistribuidoraResponse> getGameById(@PathVariable Long id){

        Optional<Distribuidora> g = service.DistribuidoraId(id);

        if(g.isEmpty()){ return ResponseEntity.notFound().build(); }
        else{ return ResponseEntity.ok().body(new DistribuidoraResponse(g.get())); }
    }

    @PostMapping
    public ResponseEntity<DistribuidoraResponse> create(@RequestBody DistribuidoraRequest distribuidoraRequest){

        Distribuidora g = distribuidoraRequest.buildNome();
        g = service.create(g);
        return ResponseEntity.created(URI.create("/distribuidoras/"+g.getId())).body(new DistribuidoraResponse(g));
    }

    @PutMapping(path = "/editar/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Distribuidora p){
        return service.DistribuidoraId(id)
                .map( record -> {
                    if (record.getId().equals(p.getId())){
                        service.update(p);
                        return ResponseEntity.ok(new DistribuidoraResponse(p));
                    }else{
                        return ResponseEntity.notFound().build();
                    }
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/deletar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return service.DistribuidoraId(id)
                .map( record -> {
                    Mensagem m = new Mensagem();
                    m.setMensagem("Deletado com sucesso");
                    service.delete(record.getId());
                    return ResponseEntity.ok(m);
                }).orElse(ResponseEntity.notFound().build());
    }
}