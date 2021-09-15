package com.example.restgames.dto.response;

import com.example.restgames.Model.Fornecedora;
import com.example.restgames.Model.Game;
import com.example.restgames.Model.Titulo;
import com.example.restgames.RestController.GameController;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameResponse extends RepresentationModel<GameResponse>{

    //Dado não sensível
    private Long id;
    private TituloResponse titulo;
    private String classificacao;
    private FornecedoraResponse fornecedora;

    public GameResponse(Game game){

        this.id = game.getId();
        this.titulo = new TituloResponse(game.getTitulo());
        this.fornecedora = new FornecedoraResponse(game.getFornecedora());
        this.classificacao = game.getClassificacao();

        this.add(linkTo(GameController.class).slash(game.getId()).withSelfRel());
        this.add(linkTo(GameController.class).slash("/editar/" + game.getId()).withRel("editar produto"));
        this.add(linkTo(GameController.class).slash("/deletar/" + game.getId()).withRel("deletar produto"));
        this.add(linkTo(GameController.class).withRel("Todos os jogos"));
    }
}
