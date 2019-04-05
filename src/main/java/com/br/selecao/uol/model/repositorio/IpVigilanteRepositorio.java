package com.br.selecao.uol.model.repositorio;

import com.br.selecao.uol.model.domain.IpVigilante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpVigilanteRepositorio extends JpaRepository<IpVigilante, Integer>{
}
