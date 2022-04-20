package br.com.dh.ctd.ecommerce.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Entity
@Table
public class Categories implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    // Timestamps automáticos
    @Column(columnDefinition = "TIMESTAMP")
    private Instant criado;
    @Column(columnDefinition = "TIMESTAMP")
    private Instant atualizado;

    @PrePersist
    public void AntesDeSalvar() {
        criado = Instant.now();
    }

    @PreUpdate
    public void AntesDeAtualizar() {
        atualizado = Instant.now();
    }

    public Categories() {
    }

    public Categories(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Categories(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCriado() {
        return criado;
    }

    public Instant getAtualizado() {
        return atualizado;
    }
}
