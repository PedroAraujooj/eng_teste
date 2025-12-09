package org.example.factory;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.example.client.ViaCepClient;

public class ViaCepClientFactory {

    private static final String BASE_URL = "https://viacep.com.br";

    public static ViaCepClient create() {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .target(ViaCepClient.class, BASE_URL);
    }
}