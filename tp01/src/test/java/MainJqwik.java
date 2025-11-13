import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;
import org.example.Main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainJqwik {
    @Provide
    Arbitrary<Double> alturasValidas() {
        return Arbitraries.doubles()
                .between(0.5, 3.5);
    }

    @Provide
    Arbitrary<Double> pesosValidos() {
        return Arbitraries.doubles()
                .between(0.0, 500.0);
    }
    @Provide
    Arbitrary<Double> alturasInvalidas() {
        return Arbitraries.oneOf(
                Arbitraries.doubles().lessThan(0.5),
                Arbitraries.doubles().greaterThan(3.5)
        );
    }
    @Provide
    Arbitrary<Double> pesosInvalidos() {
        return Arbitraries.oneOf(
                Arbitraries.doubles().lessThan(0.0),
                Arbitraries.doubles().greaterThan(500.0)
        );
    }

    @Property
    void imcNuncaDeveSerNegativo(@ForAll @Positive double peso, @ForAll @Positive double altura) {
        double imc = Main.calcularPeso(peso, altura);
        assertThat(imc).isGreaterThanOrEqualTo(0);
    }

    @Property
    void imcAlturaExtrema(@ForAll("pesosValidos") double peso, @ForAll("alturasInvalidas") double altura) {
        assertThrows(
                IllegalArgumentException.class,
                () -> Main.calcularPeso(peso, altura),
                MainTest.MSM_ERRO_ALTURA
        );
    }

    @Property
    boolean imcNuncaEhNegativo(
            @ForAll("pesosValidos") double peso,
            @ForAll("alturasValidas") double altura
    ) {
        double imc = Main.calcularPeso(peso, altura);
        return imc >= 0.0;
    }


}