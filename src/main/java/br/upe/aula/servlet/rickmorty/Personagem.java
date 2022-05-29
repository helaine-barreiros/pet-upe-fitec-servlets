package br.upe.aula.servlet.rickmorty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Personagem {

    @JsonProperty("name")
    private String nome;

    private String status;
    private String species;
    private String gender;
    private String image;

}
