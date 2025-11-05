package Calculadora;

import calculadora.Calculadora;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes Unitários para a Classe Calculadora")
class CalculadoraTest {

    private final Calculadora calculadora = new Calculadora();
    private static final double DELTA = 0.0001; // Margem de erro para comparações de double

    // --- Testes de Adição ---
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "10, 5, 15",
            "-10, -5, -15",
            "10, -5, 5",
            "0, 5, 5",
            "10.5, 5.2, 15.7",
            "1000000, 1, 1000001"
    })
    @DisplayName("🧪 Somar: Casos de Sucesso")
    void somar_deveRetornarResultadoCorreto(double num1, double num2, double esperado) {
        double resultado = calculadora.somar(num1, num2);
        assertEquals(esperado, resultado, DELTA);
    }

    // --- Testes de Subtração ---
    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource({
            "10, 5, 5",
            "-10, -5, -5",
            "10, -5, 15",
            "0, 5, -5",
            "15.7, 5.2, 10.5",
            "5, 5, 0"
    })
    @DisplayName("🧪 Subtrair: Casos de Sucesso")
    void subtrair_deveRetornarResultadoCorreto(double num1, double num2, double esperado) {
        double resultado = calculadora.subtrair(num1, num2);
        assertEquals(esperado, resultado, DELTA);
    }

    // --- Testes de Multiplicação ---
    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvSource({
            "10, 5, 50",
            "-10, -5, 50",
            "10, -5, -50",
            "0, 5, 0",
            "10.5, 2, 21.0"
    })
    @DisplayName("🧪 Multiplicar: Casos de Sucesso")
    void multiplicar_deveRetornarResultadoCorreto(double num1, double num2, double esperado) {
        double resultado = calculadora.multiplicar(num1, num2);
        assertEquals(esperado, resultado, DELTA);
    }

    // --- Testes de Divisão ---
    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({
            "10, 5, 2",
            "-10, -5, 2",
            "10, -5, -2",
            "0, 5, 0",
            "10, 3, 3.3333333333333335"
    })
    @DisplayName("🧪 Dividir: Casos de Sucesso")
    void dividir_deveRetornarResultadoCorreto(double num1, double num2, double esperado) {
        double resultado = calculadora.dividir(num1, num2);
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("🚨 Dividir: Caso de Falha (Divisão por Zero)")
    void dividir_deveLancarExcecaoAoDividirPorZero() {
        Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.dividir(10, 0);
        });

        // A mensagem esperada é apenas "Divisão por zero não é permitida."
        assertEquals("Divisão por zero não é permitida.", excecao.getMessage());
    }

    // --- Teste do Formatador (Funcionalidade de Formatação Inteligente) ---
    @Test
    @DisplayName("✨ Formatador: Deve formatar para inteiro se o valor for exato")
    void formatarResultado_deveRetornarInteiroSeExato() {
        assertEquals("5", Calculadora.formatarResultado(5.0));
        assertEquals("-10", Calculadora.formatarResultado(-10.0));
    }

    @Test
    @DisplayName("✨ Formatador: Deve formatar para decimal se o valor não for exato")
    void formatarResultado_deveRetornarDecimalSeNaoExato() {
        assertEquals("5.5", Calculadora.formatarResultado(5.5));
        assertEquals("3.3333333333", Calculadora.formatarResultado(10.0 / 3.0));
    }
}