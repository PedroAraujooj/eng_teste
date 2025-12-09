import org.example.client.ViaCepClient;
import org.example.dto.ViaCepResponse;
import org.example.factory.ViaCepClientFactory;
import org.junit.jupiter.api.Test;
import feign.FeignException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ViaCepTest {

    private final ViaCepClient client = ViaCepClientFactory.create();

    // 1) CEP válido existente
    @Test
    void deveRetornarEnderecoParaCepValidoExistente() {
        ViaCepResponse resp = client.getByCep("01001000");

        assertNotNull(resp);
        assertEquals("01001-000", resp.cep());
        assertNull(resp.erro());
    }

    // 2) CEP válido no formato, mas inexistente
    @Test
    void deveRetornarErroTrueParaCepValidoInexistente() {
        ViaCepResponse resp = client.getByCep("99999999");

        assertNotNull(resp);
        assertEquals(Boolean.TRUE, resp.erro());
    }

    // 3) CEP com letras – formato incorreto
    @Test
    void deveLancarBadRequestParaCepComLetras() {
        assertThrows(FeignException.BadRequest.class,
                () -> client.getByCep("95010A10"));
    }

    // 4) Busca por endereço válido
    @Test
    void deveRetornarListaParaEnderecoValido() {
        List<ViaCepResponse> results =
                client.getByAddress("SP", "Sao Paulo", "Avenida Paulista");

        assertNotNull(results);
        assertFalse(results.isEmpty());
    }

    // 5) Logradouro inexistente
    @Test
    void deveRetornarListaVaziaParaLogradouroInexistente() {
        List<ViaCepResponse> results =
                client.getByAddress("SP", "Sao Paulo", "ZZZAlgumaCoisaQueNaoExiste");

        assertNotNull(results);
        assertTrue(results.isEmpty());
    }
}