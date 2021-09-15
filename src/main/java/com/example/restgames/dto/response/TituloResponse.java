package com.example.restgames.dto.response;

import com.example.restgames.Model.Fornecedora;
import com.example.restgames.Model.Game;
import com.example.restgames.Model.Titulo;
import com.example.restgames.RestController.GameController;
import com.example.restgames.RestController.TituloController;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TituloResponse extends RepresentationModel<GameResponse> {

    //Dado não sensível
    private Long id;
    private String titulo;

    public TituloResponse(Titulo titulo){


        if(titulo != null) {

            this.id = titulo.getId();
            this.titulo = titulo.getTitulo();
            this.add(linkTo(TituloController.class).slash(titulo.getId()).withSelfRel());
            this.add(linkTo(TituloController.class).slash("/editar/" + titulo.getId()).withRel("editar titulo"));
            this.add(linkTo(TituloController.class).slash("/deletar/" + titulo.getId()).withRel("deletar titulo"));
            this.add(linkTo(TituloController.class).withRel("Todos os titulos"));
        }

    }

}
