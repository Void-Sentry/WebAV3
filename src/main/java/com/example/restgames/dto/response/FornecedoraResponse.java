package com.example.restgames.dto.response;

import com.example.restgames.Model.Distribuidora;
import com.example.restgames.Model.Fornecedora;
import com.example.restgames.Model.Game;
import com.example.restgames.RestController.FornecedoraController;
import com.example.restgames.RestController.GameController;
import org.springframework.hateoas.RepresentationModel;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class FornecedoraResponse extends RepresentationModel<GameResponse> {

    //Dado não sensível
    private Long id;
    private String nome;
    private List<Distribuidora> distribuidoraList;

    public FornecedoraResponse(Fornecedora fornecedora){

        this.id = fornecedora.getId();
        this.nome = fornecedora.getNome();

        this.add(linkTo(FornecedoraController.class).slash(fornecedora.getId()).withSelfRel());
        this.add(linkTo(FornecedoraController.class).slash("/editar/" + fornecedora.getId()).withRel("editar fornecedora"));
        this.add(linkTo(FornecedoraController.class).slash("/deletar/" + fornecedora.getId()).withRel("deletar fornecedora"));
        this.add(linkTo(FornecedoraController.class).withRel("Todos os fornecedores"));
    }
}