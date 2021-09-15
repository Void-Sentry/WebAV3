package com.example.restgames.dto.request;

import com.example.restgames.Model.Fornecedora;
import com.example.restgames.Model.Game;
import com.example.restgames.Model.Titulo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameRequest {

    private Titulo titulo;
    private String classificacao;
    private Fornecedora fornecedora;

    public Game buildGame(){
        return new Game()
                .setTitulo(this.titulo)
                .setClassificacao(this.classificacao)
                .setFornecedora(this.fornecedora);
    }
}
