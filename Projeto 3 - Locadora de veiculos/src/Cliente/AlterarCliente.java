package Cliente;

import java.util.*;
import java.util.Scanner;

public class AlterarCliente {
    public void alterar(ArrayList<Cliente> clientes) {

        if (clientes.isEmpty()) {
            System.out.println("      Nenhum cliente cadastrado.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < clientes.size(); i++)
            System.out.println("      " + (i+1) + " - " + clientes.get(i).mostrarDados());

        System.out.println();

        System.out.print("      Digite o ID do cliente que deseja alterar: ");
        int id = sc.nextInt();

        System.out.println();

        Cliente cliente = clientes.get(id - 1);
        cliente.alterarDados();
    }
}
