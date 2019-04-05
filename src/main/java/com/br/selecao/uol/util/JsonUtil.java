package com.br.selecao.uol.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    public static JsonNode readTree(String body) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(body);
    }

    public static String percorreJsonNode(JsonNode jsonNode, String chave) {
        String resultado = null;
        for (JsonNode node : jsonNode) {
            resultado = node.get(chave).toString();
            break;
        }
        return resultado;
    }
}
