package com.br.selecao.uol.model.service;

import com.br.selecao.uol.model.domain.Config;
import com.br.selecao.uol.model.repositorio.ConfigRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServico {

    @Autowired
    private ConfigRepositorio configRepositorio;

    public Config buscarPorChave(String chave) {
        return configRepositorio.findByChave(chave);
    }
}
