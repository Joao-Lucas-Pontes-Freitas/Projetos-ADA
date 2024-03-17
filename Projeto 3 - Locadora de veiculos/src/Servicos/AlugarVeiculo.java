package Servicos;

import Cliente.*;
import Veiculo.Veiculo;

import java.time.*;
import java.util.*;

public class AlugarVeiculo implements MetodosAuxiliares{
    public void alugar(ArrayList<Cliente> clientes, ArrayList<Veiculo> veiculos) {

        Scanner scanner = new Scanner(System.in);

        if(clientes.isEmpty() || veiculos.isEmpty()){
            System.out.println("      Veiculos ou Clientes não cadastrados");
            return;
        }

        int clienteId = escolherCliente(clientes);

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

        String local = escolherLocal();

        LocalDate  data = escolherData();

        LocalTime horario = escolherHorario();

        System.out.println();

        Cliente cliente = clientes.get(clienteId - 1);
        cliente.alugarVeiculo(veiculo, local, data, horario);
        cliente.mostrarAluguiesEDevolucoes();
    }
}
