package com.br.selecao.uol.model.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class IpVigilante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String ip;

    private String cidade;

    public IpVigilante(String ip, String cidade) {
        this.ip = ip;
        this.cidade = cidade;
    }

    public IpVigilante() {
    }
}

