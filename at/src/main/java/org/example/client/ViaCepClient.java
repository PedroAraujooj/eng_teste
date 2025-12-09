package org.example.client;


import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.example.dto.ViaCepResponse;

import java.util.List;

@Headers("Accept: application/json")
public interface ViaCepClient {

    @RequestLine("GET /ws/{cep}/json/")
    ViaCepResponse getByCep(@Param("cep") String cep);

    @RequestLine("GET /ws/{uf}/{cidade}/{logradouro}/json/")
    List<ViaCepResponse> getByAddress(
            @Param("uf") String uf,
            @Param("cidade") String cidade,
            @Param("logradouro") String logradouro
    );
}