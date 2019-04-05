package com.br.selecao.uol.controller;


import com.br.selecao.uol.Type.Chaves;
import com.br.selecao.uol.model.domain.Cliente;
import com.br.selecao.uol.model.domain.Config;
import com.br.selecao.uol.model.domain.IpVigilante;
import com.br.selecao.uol.model.domain.MetaWeather;
import com.br.selecao.uol.model.service.*;
import com.br.selecao.uol.util.DateUtil;
import com.br.selecao.uol.util.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cliente/v1")
public class ClienteController {


    @Autowired
    private ClienteServico clienteServico;

    @Autowired
    private ConfigServico configServico;

    @Autowired
    private IpVigilanteServico ipVigilanteServico;

    @Autowired
    private MetaWeatherServico metaWeatherServico;

    private RestServico restService = new RestServico();

    private static String complementoUrlIpVigilante = "/city_name";
    private static String formatoDate = "yyyy/MM/dd";


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity adicionaCliente(@RequestBody Cliente cliente, HttpServletRequest request) {
        try {
            String ipAddress = request.getRemoteAddr();
            ipAddress = "172.217.30.67";
            String today = DateUtil.formatDate(new Date(), formatoDate);

            Config configIpVigilante = configServico.buscarPorChave(Chaves.URL_IP_VIGILANTE.toString());
            Config configWeatherCidade = configServico.buscarPorChave(Chaves.URL_META_WEATHER_CIDADE.toString());
            Config configWeatherId = configServico.buscarPorChave(Chaves.URL_META_WEATHER_ID.toString());

            ResponseEntity<String> resultadoIpVigilante = restService.restPorUrlEEntidade(configIpVigilante.getValor() + ipAddress + complementoUrlIpVigilante);

            String cidade = (
                    JsonUtil.readTree(
                            resultadoIpVigilante.getBody())
            )
                    .get("data")
                    .get("city_name")
                    .toString()
                    .replace(
                            "\"", ""
                    );

            ipVigilanteServico.adicionaIpVigilante(ipAddress, cidade);

            cidade = cidade.replace(" ", "+");
            ResponseEntity<String> resultadoWetherCidade = restService.restPorUrlEEntidade(configWeatherCidade.getValor() + cidade);

            String woeid = JsonUtil.percorreJsonNode((JsonUtil.readTree(resultadoWetherCidade.getBody())), "woeid");


            ResponseEntity<String> resultadoWetherId = restService.restPorUrlEEntidade(configWeatherId.getValor() + woeid + "/" + today);

            String tempMax = JsonUtil.percorreJsonNode((JsonUtil.readTree(resultadoWetherId.getBody())), "max_temp");
            String tempMin = JsonUtil.percorreJsonNode((JsonUtil.readTree(resultadoWetherId.getBody())), "min_temp");

            MetaWeather metaWeather = metaWeatherServico.adicionaMetaWeather(new MetaWeather(tempMax, tempMin));

            cliente.setMetaWeather(metaWeather);
            Cliente clienteAdicionado = clienteServico.adicionaCliente(cliente);

            return new ResponseEntity<>(clienteAdicionado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao inserir novo cliente!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listarTodos() {
        List<Cliente> clienteList = clienteServico.listarTodos();
        return new ResponseEntity<>(clienteList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity consultaPorId(@PathVariable("id") Integer id) {
        Optional<Cliente> cliente = clienteServico.listarPorId(id);
        if (cliente.isPresent()) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(cliente, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletaPorId(@PathVariable("id") Integer id) {
        clienteServico.deletaPorId(id);
        return new ResponseEntity<>("Deletado com Sucesso!",HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/{novoNome}/{novaIdade}", method = RequestMethod.PUT)
    public ResponseEntity atualizaPorId(@PathVariable("id") Integer id, @PathVariable("novoNome") String novoNome, @PathVariable("novaIdade") Integer novaIdade) {
        Optional<Cliente> clienteOpcional = clienteServico.listarPorId(id);
        Cliente cliente = null;
        if (clienteOpcional.isPresent()) {
            cliente = clienteOpcional.get();
            cliente.setNome(novoNome);
            cliente.setIdade(novaIdade);
            clienteServico.adicionaCliente(cliente);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(cliente, HttpStatus.NOT_FOUND);
        }
    }
}
