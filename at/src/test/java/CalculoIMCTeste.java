import org.example.CalculoIMC;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Disabled
public class CalculoIMCTeste {
    public final static String MSM_ERRO_ALTURA = "Erro: altura inválida. Entre com um valor entre 0.5 e 3.5 metros.";
    public final static String MSM_ERRO_PESO = "Erro: peso inválido. Entre com um valor entre 0 e 500 quilogramas.";

    @Test
    void testCalculoIMCComMockStatic() {
        try (MockedStatic<CalculoIMC> mockedMain = mockStatic(CalculoIMC.class)) {
            mockedMain
                    .when(() -> CalculoIMC.calcularPeso(80, 1.80))
                    .thenReturn(24.69);
            double imc = CalculoIMC.calcularPeso(80, 1.80);
            assertThat(imc).isEqualTo(24.69);
            mockedMain.verify(() -> CalculoIMC.calcularPeso(80, 1.80));
        }
    }

    @Test
    @DisplayName("Deve calcular IMC para valores válidos (partição válida)")
    void calcularPeso_valoresValidos() {
        double peso = 70.0;
        double altura = 1.75;

        double resultado = CalculoIMC.calcularPeso(peso, altura);

        double esperado = 70.0 / (1.75 * 1.75);
        assertEquals(esperado, resultado, 1e-6);
    }

    @Test
    @DisplayName("Deve lançar exceção para peso negativo (partição inválida de peso)")
    void calcularPeso_pesoNegativo_deveLancarExcecao() {
        double peso = -10.0;
        double altura = 1.75;

        assertThrows(IllegalArgumentException.class,
                () -> CalculoIMC.calcularPeso(peso, altura), MSM_ERRO_PESO);
    }

    @Test
    @DisplayName("Deve lançar exceção para peso maior que 500 (partição inválida de peso)")
    void calcularPeso_pesoAcimaDoLimite_deveLancarExcecao() {
        double peso = 501.0;
        double altura = 1.75;

        assertThrows(IllegalArgumentException.class,
                () -> CalculoIMC.calcularPeso(peso, altura), MSM_ERRO_PESO);
    }

    @Test
    @DisplayName("Deve lançar exceção para altura menor que 0.5 (partição inválida de altura)")
    void calcularPeso_alturaAbaixoDoLimite_deveLancarExcecao() {
        double peso = 70.0;
        double altura = 0.4;

        assertThrows(IllegalArgumentException.class,
                () -> CalculoIMC.calcularPeso(peso, altura), MSM_ERRO_ALTURA);
    }

    @Test
    @DisplayName("Deve lançar exceção para altura maior que 3.5 (partição inválida de altura)")
    void calcularPeso_alturaAcimaDoLimite_deveLancarExcecao() {
        double peso = 70.0;
        double altura = 3.6;

        assertThrows(IllegalArgumentException.class,
                () -> CalculoIMC.calcularPeso(peso, altura), MSM_ERRO_ALTURA);
    }


    @Test
    @DisplayName("Deve aceitar altura exatamente 0.5 (limite inferior de altura)")
    void calcularPeso_alturaNoLimiteInferior_05() {
        double peso = 70.0;
        double altura = 0.5;

        double resultado = CalculoIMC.calcularPeso(peso, altura);

        double esperado = peso / (altura * altura); // 70 / (0.25)
        assertEquals(esperado, resultado, 1e-6);
    }

    @Test
    @DisplayName("Deve aceitar altura exatamente 3.5 (limite superior de altura)")
    void calcularPeso_alturaNoLimiteSuperior_35() {
        double peso = 70.0;
        double altura = 3.5;

        double resultado = CalculoIMC.calcularPeso(peso, altura);

        double esperado = peso / (altura * altura); // 70 / (12.25)
        assertEquals(esperado, resultado, 1e-6);
    }

    @Test
    @DisplayName("Deve lançar exceção para altura ligeiramente menor que 0.5")
    void calcularPeso_alturaAbaixoDoLimite_exemplo049() {
        double peso = 70.0;
        double altura = 0.49; // logo abaixo do limite

        assertThrows(IllegalArgumentException.class,
                () -> CalculoIMC.calcularPeso(peso, altura), MSM_ERRO_ALTURA);
    }

    @Test
    @DisplayName("Deve lançar exceção para altura ligeiramente maior que 3.5")
    void calcularPeso_alturaAcimaDoLimite_exemplo351() {
        double peso = 70.0;
        double altura = 3.51; // logo acima do limite

        assertThrows(IllegalArgumentException.class,
                () -> CalculoIMC.calcularPeso(peso, altura), MSM_ERRO_ALTURA);
    }

    // ---- LIMITE INFERIOR E SUPERIOR DE PESO (0 e 500) ----

    @Test
    @DisplayName("Deve aceitar peso exatamente 0 (limite inferior de peso)")
    void calcularPeso_pesoNoLimiteInferior_0() {
        double peso = 0.0;
        double altura = 1.80;

        double resultado = CalculoIMC.calcularPeso(peso, altura);

        double esperado = 0.0 / (altura * altura);
        assertEquals(esperado, resultado, 1e-6);
        assertEquals(0.0, resultado, 1e-6);
    }

    @Test
    @DisplayName("Deve aceitar peso exatamente 500 (limite superior de peso)")
    void calcularPeso_pesoNoLimiteSuperior_500() {
        double peso = 500.0;
        double altura = 3.5;

        double resultado = CalculoIMC.calcularPeso(peso, altura);

        double esperado = peso / (altura * altura); // 500 / 12.25
        assertEquals(esperado, resultado, 1e-6);
    }

    @Test
    @DisplayName("Deve lançar exceção para peso ligeiramente menor que 0")
    void calcularPeso_pesoAbaixoDoLimite_exemploNegativo() {
        double peso = -0.01;
        double altura = 1.75;

        assertThrows(IllegalArgumentException.class,
                () -> CalculoIMC.calcularPeso(peso, altura), MSM_ERRO_PESO);
    }

    @Test
    @DisplayName("Deve lançar exceção para peso ligeiramente maior que 500")
    void calcularPeso_pesoAcimaDoLimite_exemplo501() {
        double peso = 500.01;
        double altura = 1.75;

        assertThrows(IllegalArgumentException.class,
                () -> CalculoIMC.calcularPeso(peso, altura), MSM_ERRO_PESO);
    }

    // ---- VALORES CRÍTICOS MENCIONADOS NO ENUNCIADO ----

    @Test
    @DisplayName("Valor crítico: 0 kg e 0.5 m")
    void calcularPeso_valorCritico_0kg_05m() {
        double peso = 0.0;
        double altura = 0.5;

        double resultado = CalculoIMC.calcularPeso(peso, altura);

        double esperado = 0.0 / (0.5 * 0.5);
        assertEquals(esperado, resultado, 1e-6);
        assertEquals(0.0, resultado, 1e-6);
    }

    @Test
    @DisplayName("Valor crítico: 500 kg e 3.5 m")
    void calcularPeso_valorCritico_500kg_35m() {
        double peso = 500.0;
        double altura = 3.5;

        double resultado = CalculoIMC.calcularPeso(peso, altura);

        double esperado = 500.0 / (3.5 * 3.5); // ≈ 40.8163
        assertEquals(esperado, resultado, 1e-6);
    }
}
