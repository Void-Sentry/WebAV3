package com.example.restgames.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.mapping.List;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
@Table(name = "distribuidora")
public class Distribuidora implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "distribuidora_id")
    private Long id;
    @Column(name = "distribuidora_nome")
    private String nome;
    @Column(name = "distribuidora_date")
    private Date deleted;

}
