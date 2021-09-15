package com.example.restgames.dto.request;

import com.example.restgames.Model.Distribuidora;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistribuidoraRequest {

    private Long id;
    private String nome;

    public Distribuidora buildNome(){
        return new Distribuidora()
                .setId(this.id)
                .setNome(this.nome);
    }
}
