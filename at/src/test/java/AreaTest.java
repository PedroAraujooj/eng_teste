
import org.example.Area;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreaTest {

    private static final double EPS = 1e-6;
    @Test
    void deveCalcularAreaCuboQuandoLadoValido() {
        double lado = 2.0;
        double esperado = 6 * lado * lado; // 24
        double resultado = Area.surfaceAreaCube(lado);

        assertEquals(esperado, resultado, EPS);
    }

    @Test
    void deveLancarExcecaoQuandoLadoCuboForZero() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaCube(0.0)
        );
        assertEquals("Must be a positive sideLength", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoLadoCuboForNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> Area.surfaceAreaCube(-1.0));
    }


    @Test
    void deveCalcularAreaEsferaQuandoRaioValido() {
        double r = 3.0;
        double esperado = 4 * Math.PI * r * r;
        double resultado = Area.surfaceAreaSphere(r);

        assertEquals(esperado, resultado, EPS);
    }

    @Test
    void deveLancarExcecaoQuandoRaioEsferaNaoPositivo() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaSphere(0.0)
        );
        assertEquals("Must be a positive radius", ex.getMessage());
    }


    @Test
    void deveCalcularAreaPiramideQuandoValoresValidos() {
        double lado = 4.0;
        double alturaInclinada = 5.0;

        double base = lado * lado;
        double areaLateral = 2 * lado * alturaInclinada;
        double esperado = base + areaLateral;

        double resultado = Area.surfaceAreaPyramid(lado, alturaInclinada);

        assertEquals(esperado, resultado, EPS);
    }

    @Test
    void deveLancarExcecaoQuandoLadoPiramideNaoPositivo() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaPyramid(0.0, 5.0)
        );
        assertEquals("Must be a positive sideLength", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoAlturaInclinadaPiramideNaoPositiva() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaPyramid(4.0, 0.0)
        );
        assertEquals("Must be a positive slantHeight", ex.getMessage());
    }

    @Test
    void deveCalcularAreaRetanguloQuandoValoresValidos() {
        double comprimento = 5.0;
        double largura = 3.0;
        double esperado = comprimento * largura;

        double resultado = Area.surfaceAreaRectangle(comprimento, largura);

        assertEquals(esperado, resultado, EPS);
    }

    @Test
    void deveLancarExcecaoQuandoComprimentoRetanguloNaoPositivo() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaRectangle(0.0, 2.0)
        );
        assertEquals("Must be a positive length", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoLarguraRetanguloNaoPositiva() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaRectangle(2.0, -1.0)
        );
        assertEquals("Must be a positive width", ex.getMessage());
    }


    @Test
    void deveCalcularAreaCilindroQuandoValoresValidos() {
        double r = 2.0;
        double h = 5.0;
        double esperado = 2 * (Math.PI * r * r + Math.PI * r * h);

        double resultado = Area.surfaceAreaCylinder(r, h);

        assertEquals(esperado, resultado, EPS);
    }

    @Test
    void deveLancarExcecaoQuandoRaioCilindroNaoPositivo() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaCylinder(0.0, 5.0)
        );
        assertEquals("Must be a positive radius", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoAlturaCilindroNaoPositiva() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaCylinder(2.0, -1.0)
        );
        assertEquals("Must be a positive height", ex.getMessage());
    }

    @Test
    void deveCalcularAreaQuadradoQuandoLadoValido() {
        double lado = 4.0;
        double esperado = lado * lado;

        double resultado = Area.surfaceAreaSquare(lado);

        assertEquals(esperado, resultado, EPS);
    }

    @Test
    void deveLancarExcecaoQuandoLadoQuadradoNaoPositivo() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaSquare(0.0)
        );
        assertEquals("Must be a positive sideLength", ex.getMessage());
    }


    @Test
    void deveCalcularAreaTrianguloQuandoValoresValidos() {
        double base = 6.0;
        double altura = 4.0;
        double esperado = base * altura / 2;

        double resultado = Area.surfaceAreaTriangle(base, altura);

        assertEquals(esperado, resultado, EPS);
    }

    @Test
    void deveLancarExcecaoQuandoBaseTrianguloNaoPositiva() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaTriangle(0.0, 4.0)
        );
        assertEquals("Must be a positive base", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoAlturaTrianguloNaoPositiva() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaTriangle(6.0, -1.0)
        );
        assertEquals("Must be a positive height", ex.getMessage());
    }

    @Test
    void deveCalcularAreaParalelogramoQuandoValoresValidos() {
        double base = 5.0;
        double altura = 3.0;
        double esperado = base * altura;

        double resultado = Area.surfaceAreaParallelogram(base, altura);

        assertEquals(esperado, resultado, EPS);
    }

    @Test
    void deveLancarExcecaoQuandoBaseParalelogramoNaoPositiva() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaParallelogram(0.0, 3.0)
        );
        assertEquals("Must be a positive base", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoAlturaParalelogramoNaoPositiva() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaParallelogram(5.0, 0.0)
        );
        assertEquals("Must be a positive height", ex.getMessage());
    }

    @Test
    void deveCalcularAreaTrapezioQuandoValoresValidos() {
        double b1 = 4.0;
        double b2 = 6.0;
        double h = 5.0;
        double esperado = (b1 + b2) * h / 2;

        double resultado = Area.surfaceAreaTrapezium(b1, b2, h);

        assertEquals(esperado, resultado, EPS);
    }

    @Test
    void deveLancarExcecaoQuandoBase1TrapezioNaoPositiva() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaTrapezium(0.0, 6.0, 5.0)
        );
        assertEquals("Must be a positive base1", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoBase2TrapezioNaoPositiva() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaTrapezium(4.0, -1.0, 5.0)
        );
        assertEquals("Must be a positive base2", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoAlturaTrapezioNaoPositiva() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaTrapezium(4.0, 6.0, 0.0)
        );
        assertEquals("Must be a positive height", ex.getMessage());
    }
    @Test
    void deveCalcularAreaCirculoQuandoRaioValido() {
        double r = 3.0;
        double esperado = Math.PI * r * r;

        double resultado = Area.surfaceAreaCircle(r);

        assertEquals(esperado, resultado, EPS);
    }

    @Test
    void deveLancarExcecaoQuandoRaioCirculoNaoPositivo() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaCircle(0.0)
        );
        assertEquals("Must be a positive radius", ex.getMessage());
    }

    @Test
    void deveCalcularAreaHemisferioQuandoRaioValido() {
        double r = 2.0;
        double esperado = 3 * Math.PI * r * r;

        double resultado = Area.surfaceAreaHemisphere(r);

        assertEquals(esperado, resultado, EPS);
    }

    @Test
    void deveLancarExcecaoQuandoRaioHemisferioNaoPositivo() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaHemisphere(-1.0)
        );
        assertEquals("Must be a positive radius", ex.getMessage());
    }

    @Test
    void deveCalcularAreaConeQuandoValoresValidos() {
        double r = 3.0;
        double h = 4.0;
        double esperado = Math.PI * r * (r + Math.sqrt(h * h + r * r));

        double resultado = Area.surfaceAreaCone(r, h);

        assertEquals(esperado, resultado, EPS);
    }

    @Test
    void deveLancarExcecaoQuandoRaioConeNaoPositivo() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaCone(0.0, 4.0)
        );
        assertEquals("Must be a positive radius", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoAlturaConeNaoPositiva() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> Area.surfaceAreaCone(3.0, 0.0)
        );
        assertEquals("Must be a positive height", ex.getMessage());
    }
}
