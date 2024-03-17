package Servicos;

import Cliente.*;
import Veiculo.Veiculo;

import java.time.*;
import java.util.*;

public class AlugarVeiculo {
    public void alugar(ArrayList<Cliente> clientes, ArrayList<Veiculo> veiculos) {

        if(clientes.isEmpty() || veiculos.isEmpty()){
            System.out.println("      Veiculos ou Clientes não cadastrados");
            return;
        }


        for (int i = 0; i < clientes.size(); i++)
            System.out.println("      " + (i + 1) + " - " + clientes.get(i).mostrarDados());

        System.out.println();

        System.out.print("      Digite o ID do cliente que deseja alugar o veículo: ");

        Scanner scanner = new Scanner(System.in);
        int clienteId = scanner.nextInt();

        System.out.println();

        int quantidadeDisponiveis = 0;

        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo veiculo = veiculos.get(i);

            if (veiculo.isDisponivel()){
                System.out.println("      " + (i + 1) + " - " + veiculo.mostrarDados());
                quantidadeDisponiveis++;
            }
        }

        if (quantidadeDisponiveis == 0){
            System.out.println("      Nenhum veículo disponível");
            return;
        }


        System.out.println();

        System.out.print("      Digite o ID do veículo que será alugado: ");
        int veiculoId = scanner.nextInt();

        Veiculo veiculo = veiculos.get(veiculoId - 1);

        scanner.nextLine();
        System.out.println();

        System.out.print("      Digite o local: ");
        String local = scanner.nextLine();

        System.out.println();

        System.out.print("      Digite a data (dia/mes/ano): ");
        LocalDate data = LocalDate.parse(scanner.nextLine(), java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.println();

        System.out.print("      Digite o horario (hora:min): ");
        LocalTime horario = LocalTime.parse(scanner.nextLine(), java.time.format.DateTimeFormatter.ofPattern("HH:mm"));

        System.out.println();

        Cliente cliente = clientes.get(clienteId - 1);
        cliente.alugarVeiculo(veiculo, local, data, horario);
        cliente.mostrarAluguiesEDevolucoes();
    }
}
