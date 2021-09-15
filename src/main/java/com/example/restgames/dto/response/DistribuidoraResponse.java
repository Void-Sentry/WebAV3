package com.example.restgames.dto.response;

import com.example.restgames.Model.Distribuidora;
import com.example.restgames.Model.Fornecedora;
import com.example.restgames.RestController.DistribuidoraController;
import com.example.restgames.RestController.FornecedoraController;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class DistribuidoraResponse extends RepresentationModel<GameResponse> {

    //Dado não sensível
    private Long id;
    private String nome;

    public DistribuidoraResponse(Distribuidora distribuidora){

        this.id = distribuidora.getId();
        this.nome = distribuidora.getNome();

        this.add(linkTo(DistribuidoraController.class).slash(distribuidora.getId()).withSelfRel());
        this.add(linkTo(DistribuidoraController.class).slash("/editar/" + distribuidora.getId()).withRel("editar distribuidora"));
        this.add(linkTo(DistribuidoraController.class).slash("/deletar/" + distribuidora.getId()).withRel("deletar distribuidora"));
        this.add(linkTo(DistribuidoraController.class).withRel("Todos os distribuidores"));
    }
}
