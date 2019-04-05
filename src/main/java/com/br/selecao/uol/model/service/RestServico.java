package com.br.selecao.uol.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class RestServico {

    public ResponseEntity<String> restPorUrlEEntidade(String url) throws URISyntaxException {
        URI uri = new URI(url);
        return restTemplate().getForEntity(uri, String.class);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
