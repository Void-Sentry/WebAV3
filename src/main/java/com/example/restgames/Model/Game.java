package com.example.restgames.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Table(name = "game")
public class Game implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_game")
    private Long id;
    @Column(name = "game_class")
    private String classificacao;
    @Column(name = "game_deleted")
    private Date deleted;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "titulo_id_titulo")
    private Titulo titulo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fornecedora_fornecedora_id")
    private Fornecedora fornecedora;
}