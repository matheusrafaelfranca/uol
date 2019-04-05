package com.br.selecao.uol.model.repositorio;

import com.br.selecao.uol.model.domain.MetaWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaWeatherRepositorio extends JpaRepository<MetaWeather, Integer>{
}
