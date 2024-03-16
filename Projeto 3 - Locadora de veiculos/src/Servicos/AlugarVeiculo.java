package Servicos;

import Cliente.*;
import Veiculo.Veiculo;

import java.time.*;
import java.util.*;

public class AlugarVeiculo {
    public void alugar(ArrayList<Cliente> clientes, ArrayList<Veiculo> veiculos) {

        for (int i = 0; i < clientes.size(); i++)
            System.out.println((i + 1) + " - " + clientes.get(i).mostrarDados());

        System.out.println();

        System.out.print("Digite o ID do cliente que deseja alugar o veículo: ");

        Scanner scanner = new Scanner(System.in);
        int clienteId = scanner.nextInt();

        System.out.println();

        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo veiculo = veiculos.get(i);

            if (veiculo.isDisponivel())
                System.out.println((i + 1) + " - " + veiculo.mostrarDados());
        }

        System.out.println();

        System.out.print("Digite o ID do veículo que será alugado: ");
        int veiculoId = scanner.nextInt();

        Veiculo veiculo = veiculos.get(veiculoId - 1);

        System.out.println();

        System.out.print("Digite o local: ");
        String local = scanner.nextLine();

        System.out.println();

        System.out.print("Digite a data (dia/mes/ano): ");
        LocalDate data = LocalDate.parse(scanner.nextLine(), java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.println();

        System.out.print("Digite o horario (hora:min): ");
        LocalDateTime horario = LocalDateTime.parse(scanner.nextLine(), java.time.format.DateTimeFormatter.ofPattern("HH:mm"));

        System.out.println();

        Cliente cliente = clientes.get(clienteId - 1);
        cliente.alugarVeiculo(veiculo, local, data, horario);
    }
}
