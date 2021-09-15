package com.example.restgames.dto.request;

import com.example.restgames.Model.Titulo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TituloRequest {

    private Long id;
    private String titulo;

    public Titulo buildTitulo(){
        return new Titulo()
                .setId(this.id)
                .setTitulo(this.titulo);
    }
}
