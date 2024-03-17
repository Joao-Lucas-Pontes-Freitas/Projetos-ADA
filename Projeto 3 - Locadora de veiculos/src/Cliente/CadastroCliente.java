package Cliente;

import Veiculo.Veiculo;

import java.util.ArrayList;
import java.util.Scanner;

public class CadastroCliente {

    public void cadastro(ArrayList<Cliente> clientes) {
        Scanner sc = new Scanner(System.in);

        System.out.println("      1 - Pessoa Física");
        System.out.println("      2 - Pessoa Jurídica");

        System.out.println();

        System.out.print("      Digite o tipo de pessoa: ");
        int tipo = sc.nextInt();

        System.out.println();

        switch (tipo) {
            case 1:
                try {
                    ClienteCPF clienteCPF = new ClienteCPF();
                    clientes.add(clienteCPF);
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                try {
                    ClienteCNPJ clienteCNPJ = new ClienteCNPJ();
                    clientes.add(clienteCNPJ);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }

                break;

        }
    }
}
