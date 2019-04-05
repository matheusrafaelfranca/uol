package com.br.selecao.uol.model.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class MetaWeather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tempMax;
    private String tempMin;

    public MetaWeather(String tempMax, String tempMin) {
        this.tempMax = tempMax;
        this.tempMin = tempMin;
    }

    public MetaWeather() {

    }
}
