package Calculadora;

import calculadora.Calculadora;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testes Unitários para o Método Potencia")
class PotenciaTest {

    private final Calculadora calculadora = new Calculadora();
    private static final double DELTA = 0.0001;

    @ParameterizedTest(name = "{0} ^ {1} = {2}")
    @CsvSource({
            "2, 3, 8",          // Positivo ^ Positivo
            "5, 0, 1",          // Base ^ Zero
            "4, 0.5, 2",        // Raiz Quadrada
            "2, -1, 0.5",       // Expoente Negativo
            "-2, 2, 4",         // Base Negativa ^ Expoente Par
            "-2, 3, -8"         // Base Negativa ^ Expoente Ímpar
    })
    @DisplayName("🧪 Potencia: Testes de Sucesso")
    void potencia_deveRetornarResultadoCorreto(double base, double expoente, double esperado) {
        double resultado = calculadora.potencia(base, expoente);
        assertEquals(esperado, resultado, DELTA);
    }
}