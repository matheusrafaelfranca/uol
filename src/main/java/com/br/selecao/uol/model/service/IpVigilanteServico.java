package com.br.selecao.uol.model.service;

import com.br.selecao.uol.model.domain.IpVigilante;
import com.br.selecao.uol.model.repositorio.IpVigilanteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpVigilanteServico {

    @Autowired
    private IpVigilanteRepositorio ipVigilanteRepositorio;

    public void adicionaIpVigilante(String ip, String cidade) {
        IpVigilante ipVigilante  = new IpVigilante(ip, cidade);
        ipVigilanteRepositorio.save(ipVigilante);
    }
}
