package Veiculo;

import java.util.*;

public class AlterarVeiculo {
    public void alterar(ArrayList<Veiculo> veiculos) {

        if (veiculos.isEmpty()) {
            System.out.println("      Nenhum veículo cadastrado.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < veiculos.size(); i++)
            System.out.println("      " + (i+1) + " - " + veiculos.get(i).mostrarDados());

        System.out.println();

        System.out.print("      Digite o ID do veículo que deseja alterar: ");
        int id = sc.nextInt();

        System.out.println();

        Veiculo veiculo = veiculos.get(id - 1);
        veiculo.alterarDados();
    }
}
