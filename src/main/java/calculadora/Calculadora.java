package calculadora;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Calculadora {

    // Método para formatar o resultado de forma inteligente (inteiro ou decimal)
    public static String formatarResultado(double resultado) {
        if (resultado == (long) resultado) {
            // Se for um número exato (ex: 5.0), retorna como inteiro
            return String.format(Locale.US, "%d", (long) resultado);
        } else {
            // Caso contrário, retorna com formato decimal
            BigDecimal bd = new BigDecimal(resultado).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros();

            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat df = new DecimalFormat("#.##########", symbols);
            return df.format(bd.doubleValue());
        }
    }

    // 1. Adição
    public double somar(double num1, double num2) {
        return num1 + num2;
    }

    // 2. Subtração
    public double subtrair(double num1, double num2) {
        return num1 - num2;
    }

    // 3. Multiplicação
    public double multiplicar(double num1, double num2) {
        return num1 * num2;
    }

    // 4. Divisão
    public double dividir(double num1, double num2) {
        if (num2 == 0) {
            throw new IllegalArgumentException("Divisão por zero não é permitida.");
        }
        return num1 / num2;
    }

    // Potencia (Método auxiliar para fins de teste)
    public double potencia(double base, double expoente) {
        return Math.pow(base, expoente);
    }
}