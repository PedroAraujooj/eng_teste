import org.example.CalculoIMCRefatorado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class CalculoImcRefatoradoTeste {
    public final static String MSM_ERRO_ALTURA = "Erro: altura inválida. Entre com um valor entre 0.5 e 3.5 metros.";
    public final static String MSM_ERRO_PESO = "Erro: peso inválido. Entre com um valor entre 0 e 500 quilogramas.";

    @Test
    void testCalculoIMCRefatoradoComMockStatic() {
        try (MockedStatic<CalculoIMCRefatorado> mockedMain = mockStatic(CalculoIMCRefatorado.class)) {
            mockedMain
                    .when(() -> CalculoIMCRefatorado.calcularPeso(80, 1.80))
                    .thenReturn(24.69);
            double imc = CalculoIMCRefatorado.calcularPeso(80, 1.80);
            assertThat(imc).isEqualTo(24.69);
            mockedMain.verify(() -> CalculoIMCRefatorado.calcularPeso(80, 1.80));
        }
    }

    @Test
    @DisplayName("Deve calcular IMC para valores válidos (partição válida)")
    void calcularPeso_valoresValidos() {
        double peso = 70.0;
        double altura = 1.75;

        double resultado = CalculoIMCRefatorado.calcularPeso(peso, altura);

        double esperado = 70.0 / (1.75 * 1.75);
        assertEquals(esperado, resultado, 1e-6);
    }

    @Test
    @DisplayName("Deve lançar exceção para peso negativo (partição inválida de peso)")
    void calcularPeso_pesoNegativo_deveLancarExcecao() {
        double peso = -10.0;
        double altura = 1.75;

        assertThrows(IllegalArgumentException.class,
                () -> CalculoIMCRefatorado.calcularPeso(peso, altura), MSM_ERRO_PESO);
    }

    @Test
    @DisplayName("Deve lançar exceção para peso maior que 500 (partição inválida de peso)")
    void calcularPeso_pesoAcimaDoLimite_deveLancarExcecao() {
        double peso = 501.0;
        double altura = 1.75;

        assertThrows(IllegalArgumentException.class,
                () -> CalculoIMCRefatorado.calcularPeso(peso, altura), MSM_ERRO_PESO);
    }

    @Test
    @DisplayName("Deve lançar exceção para altura menor que 0.5 (partição inválida de altura)")
    void calcularPeso_alturaAbaixoDoLimite_deveLancarExcecao() {
        double peso = 70.0;
        double altura = 0.4;

        assertThrows(IllegalArgumentException.class,
                () -> CalculoIMCRefatorado.calcularPeso(peso, altura), MSM_ERRO_ALTURA);
    }

    @Test
    @DisplayName("Deve lançar exceção para altura maior que 3.5 (partição inválida de altura)")
    void calcularPeso_alturaAcimaDoLimite_deveLancarExcecao() {
        double peso = 70.0;
        double altura = 3.6;

        assertThrows(IllegalArgumentException.class,
                () -> CalculoIMCRefatorado.calcularPeso(peso, altura), MSM_ERRO_ALTURA);
    }


    @Test
    @DisplayName("Deve aceitar altura exatamente 0.5 (limite inferior de altura)")
    void calcularPeso_alturaNoLimiteInferior_05() {
        double peso = 70.0;
        double altura = 0.5;

        double resultado = CalculoIMCRefatorado.calcularPeso(peso, altura);

        double esperado = peso / (altura * altura);
        assertEquals(esperado, resultado, 1e-6);
    }

    @Test
    @DisplayName("Deve aceitar altura exatamente 3.5 (limite superior de altura)")
    void calcularPeso_alturaNoLimiteSuperior_35() {
        double peso = 70.0;
        double altura = 3.5;

        double resultado = CalculoIMCRefatorado.calcularPeso(peso, altura);

        double esperado = peso / (altura * altura);
        assertEquals(esperado, resultado, 1e-6);
    }

    @Test
    @DisplayName("Deve lançar exceção para altura ligeiramente menor que 0.5")
    void calcularPeso_alturaAbaixoDoLimite_exemplo049() {
        double peso = 70.0;
        double altura = 0.49;

        assertThrows(IllegalArgumentException.class,
                () -> CalculoIMCRefatorado.calcularPeso(peso, altura), MSM_ERRO_ALTURA);
    }

    @Test
    @DisplayName("Deve lançar exceção para altura ligeiramente maior que 3.5")
    void calcularPeso_alturaAcimaDoLimite_exemplo351() {
        double peso = 70.0;
        double altura = 3.51; // logo acima do limite

        assertThrows(IllegalArgumentException.class,
                () -> CalculoIMCRefatorado.calcularPeso(peso, altura), MSM_ERRO_ALTURA);
    }

    // ---- LIMITE INFERIOR E SUPERIOR DE PESO (0 e 500) ----

    @Test
    @DisplayName("Deve aceitar peso exatamente 0 (limite inferior de peso)")
    void calcularPeso_pesoNoLimiteInferior_0() {
        double peso = 0.0;
        double altura = 1.80;

        double resultado = CalculoIMCRefatorado.calcularPeso(peso, altura);

        double esperado = 0.0 / (altura * altura);
        assertEquals(esperado, resultado, 1e-6);
        assertEquals(0.0, resultado, 1e-6);
    }

    @Test
    @DisplayName("Deve aceitar peso exatamente 500 (limite superior de peso)")
    void calcularPeso_pesoNoLimiteSuperior_500() {
        double peso = 500.0;
        double altura = 3.5;

        double resultado = CalculoIMCRefatorado.calcularPeso(peso, altura);

        double esperado = peso / (altura * altura); // 500 / 12.25
        assertEquals(esperado, resultado, 1e-6);
    }

    @Test
    @DisplayName("Deve lançar exceção para peso ligeiramente menor que 0")
    void calcularPeso_pesoAbaixoDoLimite_exemploNegativo() {
        double peso = -0.01;
        double altura = 1.75;

        assertThrows(IllegalArgumentException.class,
                () -> CalculoIMCRefatorado.calcularPeso(peso, altura), MSM_ERRO_PESO);
    }

    @Test
    @DisplayName("Deve lançar exceção para peso ligeiramente maior que 500")
    void calcularPeso_pesoAcimaDoLimite_exemplo501() {
        double peso = 500.01;
        double altura = 1.75;

        assertThrows(IllegalArgumentException.class,
                () -> CalculoIMCRefatorado.calcularPeso(peso, altura), MSM_ERRO_PESO);
    }

    // ---- VALORES CRÍTICOS

    @Test
    @DisplayName("Valor crítico: 0 kg e 0.5 m")
    void calcularPeso_valorCritico_0kg_05m() {
        double peso = 0.0;
        double altura = 0.5;

        double resultado = CalculoIMCRefatorado.calcularPeso(peso, altura);

        double esperado = 0.0 / (0.5 * 0.5);
        assertEquals(esperado, resultado, 1e-6);
        assertEquals(0.0, resultado, 1e-6);
    }

    @Test
    @DisplayName("Valor crítico: 500 kg e 3.5 m")
    void calcularPeso_valorCritico_500kg_35m() {
        double peso = 500.0;
        double altura = 3.5;

        double resultado = CalculoIMCRefatorado.calcularPeso(peso, altura);

        double esperado = 500.0 / (3.5 * 3.5); // ≈ 40.8163
        assertEquals(esperado, resultado, 1e-6);
    }

    @ParameterizedTest
    @DisplayName("Deve classificar corretamente IMC em cada faixa típica")
    @CsvSource({
            // imc        , classificação esperada
            "15.0        , Magreza grave",
            "16.5        , Magreza moderada",
            "17.5        , Magreza leve",
            "22.0        , Saudável",
            "27.0        , Sobrepeso",
            "32.0        , Obesidade Grau I",
            "37.0        , Obesidade Grau II",
            "41.0        , Obesidade Grau III"
    })
    void deveClassificarCorretamenteImcNasFaixas(double imc, String classificacaoEsperada) {
        String resultado = CalculoIMCRefatorado.classificarIMC(imc);
        assertEquals(classificacaoEsperada, resultado);
    }


    @Test
    @DisplayName("IMC < 16 deve ser 'Magreza grave'")
    void deveClassificarMagrezaGraveQuandoImcMenorQue16() {
        String resultado = CalculoIMCRefatorado.classificarIMC(15.99);
        assertEquals("Magreza grave", resultado);
    }

    @Test
    @DisplayName("IMC = 16 entra em 'Magreza moderada'")
    void deveClassificarMagrezaModeradaQuandoImcIgualA16() {
        String resultado = CalculoIMCRefatorado.classificarIMC(16.0);
        assertEquals("Magreza moderada", resultado);
    }

    @Test
    @DisplayName("IMC = 17 entra em 'Magreza leve'")
    void deveClassificarMagrezaLeveQuandoImcIgualA17() {
        String resultado = CalculoIMCRefatorado.classificarIMC(17.0);
        assertEquals("Magreza leve", resultado);
    }

    @Test
    @DisplayName("IMC = 18.5 entra em 'Saudável'")
    void deveClassificarSaudavelQuandoImcIgualA18_5() {
        String resultado = CalculoIMCRefatorado.classificarIMC(18.5);
        assertEquals("Saudável", resultado);
    }

    @Test
    @DisplayName("IMC = 25 entra em 'Sobrepeso'")
    void deveClassificarSobrepesoQuandoImcIgualA25() {
        String resultado = CalculoIMCRefatorado.classificarIMC(25.0);
        assertEquals("Sobrepeso", resultado);
    }

    @Test
    @DisplayName("IMC = 30 entra em 'Obesidade Grau I'")
    void deveClassificarObesidadeGrauIQuandoImcIgualA30() {
        String resultado = CalculoIMCRefatorado.classificarIMC(30.0);
        assertEquals("Obesidade Grau I", resultado);
    }

    @Test
    @DisplayName("IMC = 35 entra em 'Obesidade Grau II'")
    void deveClassificarObesidadeGrauIIQuandoImcIgualA35() {
        String resultado = CalculoIMCRefatorado.classificarIMC(35.0);
        assertEquals("Obesidade Grau II", resultado);
    }

    @Test
    @DisplayName("IMC = 40 entra em 'Obesidade Grau III'")
    void deveClassificarObesidadeGrauIIIQuandoImcIgualA40() {
        String resultado = CalculoIMCRefatorado.classificarIMC(40.0);
        assertEquals("Obesidade Grau III", resultado);
    }
}
