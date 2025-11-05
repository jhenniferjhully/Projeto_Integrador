package calculadora;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProcessadorExpressao processador = new ProcessadorExpressao();
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Calculadora Independente ---");
        System.out.println("Digite a expressão (ex: 5 + 5). Digite 'sair' para encerrar.");

        while (true) {
            System.out.print("> ");
            String entrada = scanner.nextLine();

            if (entrada.equalsIgnoreCase("sair")) {
                System.out.println("Encerrando a calculadora. Até mais!");
                break;
            }

            if (entrada.trim().isEmpty()) {
                continue;
            }

            String resultado = processador.processar(entrada);
            System.out.println(resultado);
            System.out.println("---------------------------------");
        }
        scanner.close();
    }
}