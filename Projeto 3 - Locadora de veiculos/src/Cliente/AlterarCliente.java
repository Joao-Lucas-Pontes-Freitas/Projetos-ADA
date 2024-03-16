package Cliente;

import java.util.ArrayList;

public class AlterarCliente {
    public void alterar(ArrayList<Cliente> clientes) {

        for (int i = 0; i < clientes.size(); i++)
            System.out.println((i+1) + " - " + clientes.get(i).mostrarDados());

        System.out.println();

        System.out.print("Digite o ID do cliente que deseja alterar: ");
        int id = Integer.parseInt(System.console().readLine());

        System.out.println();

        Cliente cliente = clientes.get(id - 1);
        cliente.alterarDados();
    }
}
