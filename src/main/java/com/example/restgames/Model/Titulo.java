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
@Table(name = "titulo")
public class Titulo implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_titulo")
    private Long id;
    @Column(name = "titulo_tit")
    private String titulo;
    @Column(name = "titulo_date")
    private Date deleted;
}
