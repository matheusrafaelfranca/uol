package com.br.selecao.uol.model.repositorio;

import com.br.selecao.uol.model.domain.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConfigRepositorio extends JpaRepository<Config, Integer> {
    Config findByChave(String chave);
}
