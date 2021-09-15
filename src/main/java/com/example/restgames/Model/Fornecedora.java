package com.example.restgames.Model;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
@Table(name = "fornecedora")
public class Fornecedora implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "fornecedora_id")
    private Long id;
    @Column(name = "fornecedora_nome")
    private String nome;
    @Column(name = "fornecedora_date")
    private Date deleted;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "fornecedora_distribuidoras",
            joinColumns = @JoinColumn(name = "fornecedora_fornecedora_id"),
            inverseJoinColumns = @JoinColumn(name = "distribuidoras_distribuidora_id"))
    private List<Distribuidora> distribuidoras;
}