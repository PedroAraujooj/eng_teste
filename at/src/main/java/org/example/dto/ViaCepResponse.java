package org.example.dto;

public record ViaCepResponse(
        String cep,
        String logradouro,
        String localidade,
        String uf,
        Boolean erro
) {
}
