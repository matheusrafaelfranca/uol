package com.br.selecao.uol.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Integer idade;

    @JsonIgnore //Para trazer essa propriedade no metodo GET basta retirar a anotação de JsonIgnore (Não ficou claro no enunciado do teste)
    @OneToOne
    private MetaWeather metaWeather;

    public Cliente(String nome, Integer idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public Cliente() {
    }
}
