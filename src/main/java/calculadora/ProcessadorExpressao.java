package calculadora;

public class ProcessadorExpressao {

    private final Calculadora calculadora = new Calculadora();

    // Novo método: Analisa a string e executa a operação
    public String processar(String expressao) {
        expressao = expressao.trim().replaceAll("\\s+", ""); // Remove espaços

        // Padrões de busca: + (escape com \\+), -, *, /
        String[] partes;
        String operacao;

        if (expressao.contains("+")) {
            partes = expressao.split("\\+");
            operacao = "+";
        } else if (expressao.contains("*")) {
            partes = expressao.split("\\*");
            operacao = "*";
        } else if (expressao.contains("/")) {
            partes = expressao.split("/");
            operacao = "/";
        } else if (expressao.contains("-")) {
            // Lidar com subtração (diferente de número negativo no início)
            int indice = expressao.lastIndexOf('-');
            if (indice > 0 && Character.isDigit(expressao.charAt(indice - 1))) {
                // É uma subtração (o - não está no início)
                partes = expressao.split("-", 2);
                operacao = "-";
            } else {
                // Trata caso de formato inválido (ex: só um número)
                return "Erro: Operação ou formato inválido. Use [num1 op num2].";
            }
        } else {
            // Caso em que nenhum operador é encontrado
            return "Erro: Operação ou formato inválido. Use [num1 op num2].";
        }

        if (partes.length != 2) {
            return "Erro: Expressão mal formatada.";
        }

        try {
            double num1 = Double.parseDouble(partes[0]);
            double num2 = Double.parseDouble(partes[1]);
            double resultado;

            switch (operacao) {
                case "+":
                    resultado = calculadora.somar(num1, num2);
                    break;
                case "-":
                    resultado = calculadora.subtrair(num1, num2);
                    break;
                case "*":
                    resultado = calculadora.multiplicar(num1, num2);
                    break;
                case "/":
                    resultado = calculadora.dividir(num1, num2);
                    break;
                default:
                    return "Erro interno."; // Não deve ser alcançado
            }

            // Formatação inteligente da expressão original
            String num1Formatado = Calculadora.formatarResultado(num1);
            String num2Formatado = Calculadora.formatarResultado(num2);

            System.out.printf("Executando: %s %s %s%n", num1Formatado, operacao, num2Formatado);
            return "Resultado: " + Calculadora.formatarResultado(resultado);

        } catch (NumberFormatException e) {
            return "Erro: Um ou ambos os valores não são números válidos.";
        } catch (IllegalArgumentException e) {
            // Captura exceção de Divisão por Zero
            return "Erro: " + e.getMessage();
        }
    }
}