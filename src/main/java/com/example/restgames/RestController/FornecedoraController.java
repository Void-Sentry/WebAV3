package com.example.restgames.RestController;

import com.example.restgames.Model.Fornecedora;
import com.example.restgames.Model.Mensagem;
import com.example.restgames.Model.Titulo;
import com.example.restgames.Service.FornecedoraService;
import com.example.restgames.Service.TituloService;
import com.example.restgames.dto.request.FornecedoraRequest;
import com.example.restgames.dto.request.TituloRequest;
import com.example.restgames.dto.response.FornecedoraResponse;
import com.example.restgames.dto.response.TituloResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-total-Count")
@RequestMapping("/fornecedoras")
public class FornecedoraController {

    private FornecedoraService service;

    @Autowired
    public void setService(FornecedoraService service){ this.service = service; }

    @GetMapping
    public List<FornecedoraResponse> getAllGames(){ return service.TodaFornecedora(); }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<FornecedoraResponse> getGameById(@PathVariable Long id){

        Optional<Fornecedora> g = service.FornecedoraId(id);

        if(g.isEmpty()){ return ResponseEntity.notFound().build(); }
        else{ return ResponseEntity.ok().body(new FornecedoraResponse(g.get())); }
    }

    @PostMapping
    public ResponseEntity<FornecedoraResponse> create(@RequestBody FornecedoraRequest fornecedoraRequest){

        Fornecedora g = fornecedoraRequest.buildNome();
        g = service.create(g);
        return ResponseEntity.created(URI.create("/fornecedoras/"+g.getId())).body(new FornecedoraResponse(g));
    }

    @PutMapping(path = "/editar/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Fornecedora p){
        return service.FornecedoraId(id)
                .map( record -> {
                    if (record.getId().equals(p.getId())){
                        service.update(p);
                        return ResponseEntity.ok(new FornecedoraResponse(p));
                    }else{
                        return ResponseEntity.notFound().build();
                    }
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/deletar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return service.FornecedoraId(id)
                .map( record -> {
                    Mensagem m = new Mensagem();
                    m.setMensagem("Deletado com sucesso");
                    service.delete(record.getId());
                    return ResponseEntity.ok(m);
                }).orElse(ResponseEntity.notFound().build());
    }
}