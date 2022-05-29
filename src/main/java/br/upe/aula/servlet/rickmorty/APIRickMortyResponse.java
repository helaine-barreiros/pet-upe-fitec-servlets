package br.upe.aula.servlet.rickmorty;

import java.util.List;

import lombok.Data;

@Data
public class APIRickMortyResponse {

    private Informacoes info;
    private List<Personagem> results;

}
