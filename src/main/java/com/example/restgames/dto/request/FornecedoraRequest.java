package com.example.restgames.dto.request;

import com.example.restgames.Model.Distribuidora;
import com.example.restgames.Model.Fornecedora;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FornecedoraRequest {

    private Long id;
    private String nome;
    private List<DistribuidoraRequest> distribuidoraRequestList;

    public Fornecedora buildNome(){

        List<Distribuidora> distribuidoraRequest = new ArrayList<>();

        for(DistribuidoraRequest distRequest : this.distribuidoraRequestList)
            distribuidoraRequest.add(distRequest.buildNome());

        return new Fornecedora()
                .setId(this.id)
                .setNome(this.nome)
                .setDistribuidoras(distribuidoraRequest);
    }
}
