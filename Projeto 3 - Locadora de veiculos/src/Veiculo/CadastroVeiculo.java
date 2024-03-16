package Veiculo;

import java.util.*;

public class CadastroVeiculo {

    public void cadastro(List<Veiculo> veiculos) {

        Scanner sc = new Scanner(System.in);

        System.out.println("1 - Pequeno");
        System.out.println("2 - Medio");
        System.out.println("3 - SUV");

        System.out.println();

        System.out.print("Digite o tipo de veiculo: ");
        int tipo = sc.nextInt();

        switch (tipo) {
            case 1:
                VeiculoPequeno veiculoP = new VeiculoPequeno();
                veiculoP.alterarDados();
                veiculos.add(veiculoP);
                break;
            case 2:
                VeiculoMedio veiculoM = new VeiculoMedio();
                veiculoM.alterarDados();
                veiculos.add(veiculoM);
                break;
            case 3:
                VeiculoSUV veiculoS = new VeiculoSUV();
                veiculoS.alterarDados();
                veiculos.add(veiculoS);
                break;
        }
    }
}
