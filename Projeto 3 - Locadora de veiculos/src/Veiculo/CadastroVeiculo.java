package Veiculo;

import java.util.*;

public class CadastroVeiculo {

    public void cadastro(List<Veiculo> veiculos) {

        Scanner sc = new Scanner(System.in);

        System.out.println("      1 - Pequeno");
        System.out.println("      2 - Medio");
        System.out.println("      3 - SUV");

        System.out.println();

        System.out.print("      Digite o tipo de veiculo: ");
        int tipo = sc.nextInt();
        System.out.println();

        switch (tipo) {
            case 1:
                try{
                    VeiculoPequeno veiculoP = new VeiculoPequeno();
                    veiculos.add(veiculoP);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }

                break;
            case 2:
                try{
                    VeiculoMedio veiculoM = new VeiculoMedio();
                    veiculos.add(veiculoM);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                try{
                    VeiculoSUV veiculoS = new VeiculoSUV();
                    veiculos.add(veiculoS);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
        }
    }
}
