package com.br.selecao.uol.model.service;


import com.br.selecao.uol.model.domain.Cliente;
import com.br.selecao.uol.model.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServico {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public Cliente adicionaCliente(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepositorio.findAll();
    }

    public Optional<Cliente> listarPorId(Integer id) {
        return clienteRepositorio.findById(id);
    }

    public void deletaPorId(Integer id) {
        clienteRepositorio.deleteById(id);
    }
}
