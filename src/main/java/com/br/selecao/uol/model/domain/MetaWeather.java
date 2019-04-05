package com.br.selecao.uol.model.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class MetaWeather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String tempMax;
    private String tempMin;

}
