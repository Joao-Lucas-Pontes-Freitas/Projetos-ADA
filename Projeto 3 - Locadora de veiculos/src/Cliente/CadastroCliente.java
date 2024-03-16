package Cliente;

import Veiculo.Veiculo;

import java.util.ArrayList;
import java.util.Scanner;

public class CadastroCliente {

    public void cadastro(ArrayList<Cliente> clientes) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");

        System.out.println();

        System.out.print("Digite o tipo de pessoa: ");
        int tipo = sc.nextInt();

        switch (tipo) {
            case 1:
                ClienteCPF clienteCPF = new ClienteCPF();
                clienteCPF.alterarDados();
                clientes.add(clienteCPF);
                break;
            case 2:
                ClienteCNPJ clienteCNPJ = new ClienteCNPJ();
                clienteCNPJ.alterarDados();
                clientes.add(clienteCNPJ);
                break;

        }
    }
}
