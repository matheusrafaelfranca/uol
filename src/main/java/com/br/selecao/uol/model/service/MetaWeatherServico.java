package com.br.selecao.uol.model.service;

import com.br.selecao.uol.model.domain.MetaWeather;
import com.br.selecao.uol.model.repositorio.MetaWeatherRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetaWeatherServico {


    @Autowired
    MetaWeatherRepositorio metaWeatherRepositorio;

    public MetaWeather adicionaMetaWeather(MetaWeather metaWeather) {
        return metaWeatherRepositorio.saveAndFlush(metaWeather);
    }
}
