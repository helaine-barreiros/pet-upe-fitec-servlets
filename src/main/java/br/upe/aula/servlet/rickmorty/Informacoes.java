package br.upe.aula.servlet.rickmorty;

import lombok.Data;

@Data
public class Informacoes {
    private Integer count;
    private Integer pages;
    private String next;
    private String prev;
}
