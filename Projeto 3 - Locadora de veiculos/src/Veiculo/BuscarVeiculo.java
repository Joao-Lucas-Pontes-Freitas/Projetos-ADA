package Veiculo;

import java.util.*;

public class BuscarVeiculo {
    public void buscar(ArrayList<Veiculo> veiculos) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Digite parte da placa do veículo: ");
        String placa = sc.nextLine();

        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().contains(placa)) {
                System.out.println(veiculo.mostrarDados());
            }
        }
    }
}
