import Persistencia.*;
import Veiculo.*;
import Cliente.*;
import Servicos.*;


import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private final ArrayList<Cliente> clientes = Arquivo.getClientes();
    private final ArrayList<Veiculo> veiculos = Arquivo.getVeiculos();

    public Menu() {
        mostrarMenu();
    }

    public void mostrarMenu() {
        System.out.println();
        System.out.printf("      \u001B[38;2;%d;%d;%dmMenu:\u001B[0m%n", 64, 224, 208);
        System.out.printf("      1 - \u001B[34mCadastrar Veículo\u001B[0m%n");
        System.out.printf("      2 - \u001B[34mAlterar Veículo\u001B[0m%n");
        System.out.printf("      3 - \u001B[34mBuscar Veículo\u001B[0m%n");
        System.out.printf("      4 - \u001B[34mCadastrar Cliente\u001B[0m%n");
        System.out.printf("      5 - \u001B[34mAlterar Cliente\u001B[0m%n");
        System.out.printf("      6 - \u001B[34mAlugar Veículo\u001B[0m%n");
        System.out.printf("      7 - \u001B[34mDevolver Veículo\u001B[0m%n");
        System.out.printf("      8 - \u001B[34mSair\u001B[0m%n");

        boolean opcaoInvalida = true;
        String escolha;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("\n      Escolha uma opção: ");
            escolha = scanner.nextLine();

            if (escolha.matches("[1-8]"))
                opcaoInvalida = false;

            else
                System.out.println("      \u001B[31mOpção inválida\u001B[0m");


        } while (opcaoInvalida);

        System.out.println();

        int opcao = Integer.parseInt(escolha);

        switch (opcao) {
            case 1:
                CadastroVeiculo cadastroV = new CadastroVeiculo();
                cadastroV.cadastro(veiculos);
                break;
            case 2:
                AlterarVeiculo alterarV = new AlterarVeiculo();
                alterarV.alterar(veiculos);
                break;
            case 3:
                BuscarVeiculo buscar = new BuscarVeiculo();
                buscar.buscar(veiculos);
                break;
            case 4:
                CadastroCliente cadastroC = new CadastroCliente();
                cadastroC.cadastro(clientes);
                break;
            case 5:
                AlterarCliente alterarC = new AlterarCliente();
                alterarC.alterar(clientes);
                break;
            case 6:
                AlugarVeiculo alugar = new AlugarVeiculo();
                alugar.alugar(clientes, veiculos);
                break;

            case 7:
                DevolverVeiculo devolver = new DevolverVeiculo();
                devolver.devolver(clientes);
                break;

            case 8:
                System.out.printf("\n\u001B[38;2;%d;%d;%dm      Saindo do Sistema...\u001B[0m%n", 255, 215, 0);
                System.exit(0);
                break;
        }

    }
}
