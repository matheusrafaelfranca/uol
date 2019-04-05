package com.br.selecao.uol.model.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private Integer idade;

    @OneToOne
    private MetaWeather metaWeather;

    public Cliente(String nome, Integer idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public Cliente() {
    }
}
