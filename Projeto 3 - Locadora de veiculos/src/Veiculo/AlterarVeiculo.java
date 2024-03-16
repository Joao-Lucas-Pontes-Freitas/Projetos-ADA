package Veiculo;

import java.util.ArrayList;

public class AlterarVeiculo {
    public void alterar(ArrayList<Veiculo> veiculos) {

        for (int i = 0; i < veiculos.size(); i++)
            System.out.println((i+1) + " - " + veiculos.get(i).mostrarDados());

        System.out.println();

        System.out.print("Digite o ID do veículo que deseja alterar: ");
        int id = Integer.parseInt(System.console().readLine());

        System.out.println();

        Veiculo veiculo = veiculos.get(id - 1);
        veiculo.alterarDados();
    }
}
