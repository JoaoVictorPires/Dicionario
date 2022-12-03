package br.edu.univas;

import java.util.Scanner;

public class Dictionary {
    public static void main(String[] args) {

        iniciar();
    }

    public static void iniciar() {
        Scanner scanner = new Scanner(System.in);
        int escolha = 0;
        String[] dicionarioIngles = new String[100];
        String[] dicionarioPortugues = new String[100];

        do {
            printMenu();
            escolha = scanner.nextInt();
            if(escolha == 1) {
                cadastrarPalavras(dicionarioIngles, dicionarioPortugues);
            } else if(escolha == 2) {
                editarPalavras(dicionarioIngles, dicionarioPortugues);
            } else if(escolha == 3) {
                excluirPalavras(dicionarioIngles, dicionarioPortugues);
            } else if(escolha == 4) {
                consultarPalavras(dicionarioIngles, dicionarioPortugues);
            } else if(escolha == 0) {
                System.out.println("Até a próxima!!");
            } else {
                System.out.println("Opção Inválida!");
                System.out.println();
            }
        } while (escolha != 0);
        scanner.close();
    }

    public static void printMenu() {
        System.out.println("1- Cadastrar Dicionários");
        System.out.println("2- Editar Dicionário");
        System.out.println("3- Excluir Dicionário");
        System.out.println("4- Consultar Dicionário");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }
    public static void cadastrarPalavras(String[] dicionarioIngles, String[] dicionarioPortugues) {

        for(int i = 0; i < dicionarioIngles.length; i++) {
            if(i == 0 && dicionarioIngles[i] == null) {
                cadastrarPalavraIngles(i, dicionarioIngles,dicionarioPortugues);
            } else if(dicionarioIngles[i] == null) {
                cadastrarPalavraIngles(i, dicionarioIngles,dicionarioPortugues);
            }
        }
        System.out.println("O dicionário está completo!");
        System.out.println();
    }

    public static void cadastrarPalavraIngles(int index, String[] dicionarioIngles, String[] dicionarioPortugues) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor, digite a palavra em inglês: ");
        String palavraDesejada = scanner.nextLine();
        if(verificaDuplicidade(index, palavraDesejada, dicionarioIngles) == true) {
            System.out.println();
            System.out.println("Está palavra já está cadastrada em seu dicionário!");
            corrigirDicionarioIngles(index, dicionarioIngles, dicionarioPortugues);
        } else {
            dicionarioIngles[index] = palavraDesejada.trim();
            cadastrarPlavraPortugues(index, palavraDesejada, dicionarioPortugues);
        }
    }


    public static void cadastrarPlavraPortugues(int index, String palavraDesejada, String[] dicionarioPortugues) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor, digite o significado da palavra '" + palavraDesejada.trim() +"': ");
        String significadoPalavra = scanner.nextLine();
        dicionarioPortugues[index] = significadoPalavra.trim();
    }

    public static void corrigirDicionarioIngles(int index, String[] dicionarioIngles, String[] dicionarioPortugues) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor, digite a palavra em inglês novamente: ");
        String palavraDesejada = scanner.nextLine();
        if(verificaDuplicidade(index, palavraDesejada, dicionarioIngles) == true) {
            System.out.println();
            System.out.println("Está palavra já está cadastrada em seu dicionário!");
            corrigirDicionarioIngles(index, dicionarioIngles, dicionarioPortugues);
        } else {
            dicionarioIngles[index] = palavraDesejada.trim();
            cadastrarPlavraPortugues(index, palavraDesejada, dicionarioPortugues);
        }
    }

    public static void editarPalavras(String[] dicionarioIngles, String[] dicionarioPortugues) {
        Scanner scanner = new Scanner(System.in);

        if(verificaDicionario(dicionarioIngles) == true) {
            System.out.println("É preciso preencher todo o seu dicionário.");
            System.out.println();
        } else {
            imprimiDicionario(dicionarioIngles);
            System.out.print("Qual palavra deseja editar? ");
            String palavraDesejada = scanner.nextLine();
            if(retornaIndex(dicionarioIngles, dicionarioPortugues, palavraDesejada) == -1) {
                System.out.println("Palavra não encontrada!");
                System.out.println();
            } else {
                int index = retornaIndex(dicionarioIngles, dicionarioPortugues, palavraDesejada);
                alterarPalavra(dicionarioIngles, dicionarioPortugues, index, palavraDesejada);
            }
        }
    }

    public static void excluirPalavras(String[] dicionarioIngles, String[] dicionarioPortugues) {
        Scanner scanner = new Scanner(System.in);

        if(verificaDicionario(dicionarioIngles) == true) {
            System.out.println("É preciso preencher todo o seu dicionário.");
            System.out.println();
        } else {
            imprimiDicionario(dicionarioIngles);
            System.out.print("Qual palavra deseja excluir? ");
            String palavraDesejada = scanner.nextLine();
            if(retornaIndex(dicionarioIngles, dicionarioPortugues, palavraDesejada) == -1) {
                System.out.println("Palavra não encontrada!");
                System.out.println();
            } else {
                int index = retornaIndex(dicionarioIngles, dicionarioPortugues, palavraDesejada);
                excluirPalavra(dicionarioIngles, dicionarioPortugues, index);
                System.out.println("A palavra " + palavraDesejada + " foi excluída!");
                System.out.println();
            }
        }
    }

    public static void consultarPalavras(String[] dicionarioIngles, String[] dicionarioPortugues) {
        Scanner scanner = new Scanner(System.in);
        if(verificaDicionario(dicionarioIngles) == true) {
            System.out.println("É preciso preencher todo o seu dicionário.");
            System.out.println();
        } else {
            imprimiDicionario(dicionarioIngles);
            System.out.print("Qual palavra deseja saber o significado? ");
            String palavraDesejada = scanner.nextLine();
            if(retornaIndex(dicionarioIngles, dicionarioPortugues, palavraDesejada) == -1) {
                System.out.println("Palavra não encontrada!");
                System.out.println();
            } else {
                int index = retornaIndex(dicionarioIngles, dicionarioPortugues, palavraDesejada);
                consultarSignificado(dicionarioPortugues, palavraDesejada, index);
            }
        }
    }

    public static void consultarSignificado(String[] dicionarioPortugues, String palavraDesejada, int index) {
        System.out.println("O significado da palavra " + palavraDesejada + " é: " + dicionarioPortugues[index]);
        System.out.println();
    }

    public static void imprimiDicionario(String[] dicionarioIngles) {
        for(int i = 0; i < dicionarioIngles.length; i++) {
            if(i < dicionarioIngles.length - 1) System.out.print(dicionarioIngles[i] + " - ");
            else {
                System.out.println(dicionarioIngles[i]);
                System.out.println();
            }
        }
    }

    public static void alterarPalavra(String[] dicionarioIngles, String[] dicionarioPortugues, int index, String palavraDesejada) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("A palavra '" + palavraDesejada + "'  será substituida por: ");
        String palavraSubstituta = scanner.nextLine();
        if(verificaDuplicidade(index, palavraSubstituta, dicionarioIngles) == true) {
            System.out.println();
            System.out.println("Está palavra já está cadastrada em seu dicionário!");
            alterarPalavra(dicionarioIngles, dicionarioPortugues, index, palavraDesejada);
        } else {
            dicionarioIngles[index] = palavraSubstituta.trim();
            cadastrarPlavraPortugues(index, palavraSubstituta, dicionarioPortugues);
        }
    }
    public static void excluirPalavra(String[] dicionarioIngles, String[] dicionarioPortugues, int index) {
        dicionarioIngles[index] = null;
        dicionarioPortugues[index] = null;
    }

    public static int retornaIndex(String[] dicionarioIngles, String[] dicionarioPortugues, String palavraDesejada) {
        int index = -1;
        for(int i = 0; i < dicionarioIngles.length; i++) {
            if(palavraDesejada.equals(dicionarioIngles[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static boolean verificaDuplicidade(int index, String palavraDesejada, String[] dicionarioIngles) {
        if(index == 0) return false;
        for(int i = 0; i < index; i++) {
            if(palavraDesejada.equals(dicionarioIngles[i])) return true;
        }
        return false;
    }

    public static boolean verificaDicionario(String[] dicionarioIngles) {
        for(int i = 0; i < dicionarioIngles.length; i++) {
            if(dicionarioIngles[i] == null) return true;
        }
        return false;
    }

}
