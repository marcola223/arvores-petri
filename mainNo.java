import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("\n--- MENU ÁRVORE BINÁRIA ---");
            System.out.println("1. Inserir nó");
            System.out.println("2. Remover nó");
            System.out.println("3. Percorrer em Pré-ordem");
            System.out.println("4. Percorrer Em-ordem");
            System.out.println("5. Percorrer em Pós-ordem");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o valor para inserir: ");
                        int valorInserir = Integer.parseInt(scanner.nextLine());
                        arvoreBinaria.inserir(valorInserir);
                        break;
                    case 2:
                        System.out.print("Digite o valor para remover: ");
                        int valorRemover = Integer.parseInt(scanner.nextLine());
                        arvoreBinaria.remover(valorRemover);
                        break;
                    case 3:
                        arvoreBinaria.percurso("Pre");
                        break;
                    case 4:
                        arvoreBinaria.percurso("Em");
                        break;
                    case 5:
                        arvoreBinaria.percurso("Pos");
                        break;
                    case 0:
                        System.out.println("Encerrando o programa...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, insira apenas números inteiros.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}