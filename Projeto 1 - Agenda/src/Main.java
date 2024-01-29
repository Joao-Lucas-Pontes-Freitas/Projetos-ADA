import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static long idAtual = 0; // marcação do último id utilizado

    public static void main(String[] args) {

        ArrayList<Contato> contatos = new ArrayList<>(); // lista de contatos para manipulação
        HashSet<String> pilhaTelefones = new HashSet<>(); // pilha de telefones para verificação de duplicados

        lerArquivo(contatos, pilhaTelefones);
        agenda();
        contatos(contatos);
        menu(contatos, pilhaTelefones);
    }
    private static void agenda(){
        // mostra o cabeçalho da agenda

        System.out.println("\n\u001B[38;2;255;165;0m#\u001B[0m#################");
        System.out.println("\u001B[38;2;255;165;0m#\u001B[0m#### \u001B[38;2;255;255;0mAGENDA\u001B[0m #####");
        System.out.println("\u001B[38;2;255;165;0m#\u001B[0m#################\n");
    }
    private static void lerArquivo(ArrayList<Contato> contatos, HashSet<String> pilhaTelefones) {
        // abre o arquivo que contém a agenda

        try (BufferedReader reader = new BufferedReader(new FileReader("src/Contatos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                adicionarArquivo(linha, contatos, pilhaTelefones);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static void adicionarArquivo(String linha, ArrayList<Contato> contatos, HashSet<String> pilhaTelefones) {
        // adiciona na lista de contatos os contatos e telefones lidos do arquivo além de atualziar o último id

        String[] dadosContato = linha.split(" - ");

        if (dadosContato.length == 4) {
            long id = Long.parseLong(dadosContato[0].trim());
            String nome = dadosContato[1].trim();
            String sobrenome = dadosContato[2].trim();

            Contato novoContato = new Contato(id, nome);
            novoContato.setSobrenome(sobrenome);

            String[] telefones = dadosContato[3].split(", ");

            for (String telefoneStr : telefones) {
                String[] dadosTelefone = telefoneStr.split(" ");
                if (dadosTelefone.length == 3) {
                    long idTelefone = Long.parseLong(dadosTelefone[0].trim());
                    String ddd = dadosTelefone[1].trim();
                    long numero = Long.parseLong(dadosTelefone[2].trim());

                    Telefone telefone = new Telefone(idTelefone, ddd, numero);
                    novoContato.adicionarTelefone(telefone, pilhaTelefones);
                }
            }
            idAtual = novoContato.getId();
            contatos.add(novoContato);
        }
    }
    private static void contatos(ArrayList<Contato> contatos){
        // mostra os contatos da agenda

        System.out.println("\n\u001B[38;2;255;165;0m>\u001B[0m>>> Contatos <<<<");
        System.out.println("Id | Nome");

        for (Contato contato : contatos) {
            System.out.print(contato.getId());
            if (contato.getId() < 10)
                System.out.print(" ");
            System.out.println(" | " + contato.getNome());
        }
        System.out.println();
    }

    private static void mostrarContato(Contato contato){
        // mostra as informações de um contato

        System.out.println(contato.toString());
        ArrayList<Telefone> telefones = contato.getTelefones();

        for(Telefone telefone : telefones){
            System.out.println(telefone.toString());
        }
    }
    private static void menu(ArrayList<Contato> contatos, HashSet<String> pilhaTelefones) {
        // menu de operações do programa

        try (Scanner sc = new Scanner(System.in)) {
            // try com recursos que não permite uma entrada não é vazia
            // fecha o sccaner sozinho

            while (true) {  // em loop até a entrada ser a esperada
                System.out.println("\u001B[38;2;255;165;0m>\u001B[0m>>> Menu <<<<");
                System.out.println("1 - Adicionar Contato");
                System.out.println("2 - Remover Contato");
                System.out.println("3 - Editar Contato");
                System.out.println("4 - Mostrar Agenda");
                System.out.println("5 - Sair\n");

                System.out.print("\u001B[38;2;255;165;0m>\u001B[0m>>> Escolha uma opção: ");

                String input = sc.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("\u001B[38;2;255;51;51mEntrada inválida. Digite um número.\u001B[0m\n");
                    continue;
                }

                try { // try pra saber se o input é do tipo esperado
                    int opcao = Integer.parseInt(input);

                    // Verifica se a opção está dentro do intervalo desejado
                    if (opcao >= 1 && opcao <= 5) {
                        switch (opcao) {
                            case 1:
                                adicionarContato(contatos, pilhaTelefones);
                                break;
                            case 2:
                                removerContato(contatos, pilhaTelefones);
                                break;
                            case 3:
                                editarContato(contatos, pilhaTelefones);
                                break;
                            case 4:
                                contatos(contatos);
                                break;
                            case 5:
                                System.out.println("\u001B[38;2;255;165;0m>\u001B[0m>>> \u001B[38;2;255;255;0mSaindo...\u001B[0m");
                                System.exit(0);
                                break;
                        }
                    } else {
                        System.out.println("\u001B[38;2;255;51;51mOpção inválida.\u001B[0m\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\u001B[38;2;255;51;51mEntrada inválida. Digite um número.\u001B[0m\n");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("\u001B[38;2;255;51;51mEntrada inválida\u001B[0m\n");
        }
    }
    private static void adicionarContato(ArrayList<Contato> contatos, HashSet<String> pilhaTelefones){
        // adiciona o contato na agenda

        Scanner sc = new Scanner(System.in);
        boolean controle = true;

        idAtual++;
        Contato novo = new Contato(idAtual);

        novo.setNome();
        novo.setSobrenome();

        String ddd;
        long numero;

        do { // em loop até a entrada ser a esperada
            if (controle){
                do {
                    ddd = validaDDD();

                    if(ddd.equals("0")){
                        System.out.println("\u001B[38;2;255;51;51mOperação cancelada\u001B[0m\n");
                        return;
                    }

                    numero = validaNumero();

                    if(numero == 0){
                        System.out.println("\u001B[38;2;255;51;51mOperação cancelada\u001B[0m\n");
                        return;
                    }
                    controle = false;

                    if (novo.adicionarTelefone(ddd, numero, pilhaTelefones)) {
                        System.out.println("\u001B[38;2;0;165;0mTelefone adicionado com sucesso\u001B[0m");
                        break;
                    } else {
                        System.out.println("\u001B[38;2;255;51;51mTelefone já cadastrado\u001B[0m");
                    }

                } while (true);

                contatos.remove(novo);
                contatos.add(novo);
                atualizarArquivo(contatos);

                // sempre atualiza o arquivo para que seja possível acompanhar o desenvolvimento da agenda
                // de forma prática

            }

            try { // não permite entrada do tipo errado
                System.out.print("\n\u001B[38;2;255;165;0m>\u001B[0m>>> Digite 1 para adicionar mais telefones e 0 para terminar: ");
                int opcao = Integer.parseInt(sc.nextLine().trim());

                if (opcao == 0) {
                    break;
                } else if (opcao != 1) {
                    System.out.println("\u001B[38;2;255;51;51mOpção inválida. Digite apenas 0 ou 1.\u001B[0m");
                }
                else
                    controle = true;
            } catch (NumberFormatException e) {
                System.out.println("\u001B[38;2;255;51;51mOpção inválida. Digite apenas 0 ou 1.\u001B[0m\n");
            }

        } while (true); // fica em loop até a entrada ser a esperada

        System.out.println();
        menu(contatos, pilhaTelefones);
    }
    private static void removerContato(ArrayList<Contato> contatos, HashSet<String> pilhaTelefones) {

        contatos(contatos);
        System.out.print("\u001B[38;2;255;165;0m>\u001B[0m>>> Escolha o id do contato (0 para cancelar): ");

        Scanner sc = new Scanner(System.in);

        try { // não permite entrada do tipo errado
            long id = sc.nextInt();
            sc.nextLine();  // Limpa o buffer

            if(id == 0){
                System.out.println("\u001B[38;2;255;51;51mOperação cancelada\u001B[0m\n");
                return;
            }

            Contato temp = achaContato(contatos, id);

            if (temp != null) {
                contatos.remove(temp);

                System.out.println("\n\u001B[38;2;255;165;0m>\u001B[0m>>> Contato: " + temp);
                System.out.println("\u001B[38;2;0;165;0mOperação realizada com sucesso\u001B[0m\n");

                atualizarArquivo(contatos);

            } else {
                System.out.println("\u001B[38;2;255;51;51mÍndice inválido\u001B[0m\n");

            }
        } catch (InputMismatchException e) {
            System.out.println("\u001B[38;2;255;51;51mOpção inválida\u001B[0m\n");
        }

        menu(contatos, pilhaTelefones);
    }
    private static void editarContato(ArrayList<Contato> contatos, HashSet<String> pilhaTelefones){

        contatos(contatos);
        System.out.print("\u001B[38;2;255;165;0m>\u001B[0m>>> Escolha o id do contato: ");

        Scanner sc = new Scanner(System.in);

        try { // não permite entrada do tipo errado
            long id = sc.nextInt();
            sc.nextLine();  // Limpa o buffer
            Contato temp = achaContato(contatos, id);

            if (temp != null) {
                opcoesEdicao(temp, contatos, pilhaTelefones);

            } else {
                System.out.println("\u001B[38;2;255;51;51mÍndice inválido\u001B[0m\n");

            }
        } catch (InputMismatchException e) {
            System.out.println("\u001B[38;2;255;51;51mOpção inválida\u001B[0m\n");
        }

        menu(contatos, pilhaTelefones);

    }
    private static void opcoesEdicao(Contato contato, ArrayList<Contato> contatos, HashSet<String> pilhaTelefones) {
        Scanner sc = new Scanner(System.in);

        while (true) { // loop até a entrada ser a esperada
            atualizarArquivo(contatos);

            mostrarContato(contato);

            System.out.println("\n\u001B[38;2;255;165;0m>\u001B[0m>>> Editar <<<<");
            System.out.println("1 - Editar Nome");
            System.out.println("2 - Editar Sobrenome");
            System.out.println("3 - Adicionar Telefone");
            System.out.println("4 - Remover Telefone");
            System.out.println("5 - Voltar\n");

            System.out.print("\u001B[38;2;255;165;0m>\u001B[0m>>> Escolha uma opção: ");

            try {
                int opcao = sc.nextInt();
                sc.nextLine();  // Limpa o buffer

                if (opcao == 0) {
                    System.out.println("\u001B[38;2;255;51;51mOperação cancelada\u001B[0m");
                    return;
                }

                switch (opcao) {
                    case 1:
                        contato.setNome();
                        break;

                    case 2:
                        contato.setSobrenome();
                        break;

                    case 3:
                        String ddd = validaDDD();

                        if(ddd.equals("0")){
                            System.out.println("\u001B[38;2;255;51;51mOperação cancelada\u001B[0m\n");
                            return;
                        }

                        long numero = validaNumero();

                        if(numero == 0){
                            System.out.println("\u001B[38;2;255;51;51mOperação cancelada\u001B[0m\n");
                            return;
                        }

                        if (contato.adicionarTelefone(ddd, numero, pilhaTelefones))
                            System.out.println("\u001B[38;2;0;165;0mTelefone adicionado com sucesso\u001B[0m");
                        else
                            System.out.println("\u001B[38;2;255;51;51mTelefone já cadastrado\u001B[0m");
                        break;

                    case 4:
                        System.out.print("\nTelefones: ");
                        System.out.println(contato.mostrarTelefones());
                        long id = 0;
                        boolean idValido = false;
                        do {
                            System.out.print("Digite o id do telefone a ser removido (ou 0 para cancelar): ");

                            String input = sc.nextLine().trim();

                            if (input.isEmpty()) {
                                System.out.println("\u001B[38;2;255;51;51mEntrada inválida. Digite um número válido ou 0 para cancelar.\u001B[0m\n");
                                continue;
                            }

                            try {
                                id = Long.parseLong(input);
                                if (id == 0) {
                                    System.out.println("\u001B[38;2;255;51;51mOperação cancelada\u001B[0m\n");
                                    return;
                                }
                                idValido = true;
                            } catch (NumberFormatException e) {
                                System.out.println("\u001B[38;2;255;51;51mEntrada inválida. Digite um número válido ou 0 para cancelar.\u001B[0m\n");
                            }
                        } while (!idValido);

                        Telefone telefoneRemover = achaTelefone(contato, id);

                        if (telefoneRemover != null) {
                            contato.removerTelefone(telefoneRemover, pilhaTelefones);
                            System.out.println("\u001B[38;2;0;165;0mTelefone removido com sucesso\u001B[0m");
                        } else {
                            System.out.println("\u001B[38;2;255;51;51mId de telefone inválido\u001B[0m");
                        }

                        break;

                    case 5:
                        System.out.println();
                        return;

                    default:
                        System.out.println("\u001B[38;2;255;51;51mOpção inválida\u001B[0m\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\u001B[38;2;255;51;51mEntrada inválida\u001B[0m\n");
                sc.nextLine();  // Limpa o buffer
            }
        }
    }
    private static void atualizarArquivo(ArrayList<Contato> contatos) {
        // função para abrir arquivo e escrever

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Contatos.txt"))) {
            for (Contato contato : contatos) {
                writer.write(formatarContatoParaAtualizar(contato));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static String formatarContatoParaAtualizar(Contato contato) {
        // função para formatar da forma correta o texto antes de escrever

        return contato.getId() + " - " +
                contato.getNome() + " - " +
                contato.getSobrenome() + " - " +
                contato.mostrarTelefones();
    }
    public static Contato achaContato(ArrayList<Contato> contatos, long id){
        // acha um contato especifico com base no id

        for(Contato contato : contatos){
            if(contato.getId() == id){
                return contato;
            }
        }
        return null;
    }
    public static Telefone achaTelefone(Contato contato, long id) {
        // acha um telefone especifico com base no id

        for (Telefone telefone : contato.getTelefones()) {
            if (telefone.getId() == id) {
                return telefone;
            }
        }
        return null;
    }
    public static String validaDDD(){
        // valida os ddd pra serem corretos

        String ddd;
        Scanner  sc = new Scanner(System.in);

        do { // loop enquanto a entrada não for a esperada
            System.out.print("\nDigite o novo DDD (0 para cancelar): ");
            ddd = sc.nextLine().trim();
            if (ddd.equals("0")) {
                return ddd;
            }
            else if (ddd.length() != 2 || !ddd.matches("\\d+")) {
                System.out.println("\u001B[38;2;255;51;51mDDD deve conter dois números\u001B[0m");
            } else {
                break;
            }
        } while (true);

        return ddd;
    }
    public static long validaNumero() {
        // valida os nuemros pra serem corretos

        long numero;
        Scanner sc = new Scanner(System.in);

        do { // loop enquanto entrada não for esperada
            System.out.print("\nDigite o número (0 para cancelar): ");
            try {
                String input = sc.nextLine().trim();

                if (input.equals("0")) {
                    return 0;
                }

                else if (input.isEmpty() || !input.matches("\\d+")) {
                    System.out.println("\u001B[38;2;255;51;51mNúmero inválido. Tente novamente.\u001B[0m");
                    continue;
                }

                numero = Long.parseLong(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("\u001B[38;2;255;51;51mEntrada inválida. Digite apenas números.\u001B[0m");
            }
        } while (true);

        return numero;
    }

}