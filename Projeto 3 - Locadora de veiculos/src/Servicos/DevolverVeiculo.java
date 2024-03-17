package Servicos;

import Cliente.*;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class DevolverVeiculo {
    public void devolver(ArrayList<Cliente> clientes) {

        for (int i = 0; i < clientes.size(); i++)
            System.out.println("      " + (i + 1) + " - " + clientes.get(i).mostrarDados());

        System.out.println();

        System.out.print("      Digite o ID do cliente que deseja devolver o veículo: ");

        Scanner scanner = new Scanner(System.in);
        int clienteId = scanner.nextInt();

        System.out.println();

        ArrayList<Aluguel> alugueis = clientes.get(clienteId - 1).getAlugueis();

        if (alugueis.isEmpty()){
            System.out.println("      Nenhum veículo para ser devolvido");
            return;
        }

        for (int i = 0; i < alugueis.size(); i++)
            System.out.println("      " + (i + 1) + " - " + alugueis.get(i).mostrarDados());

        System.out.println();

        System.out.print("      Digite o ID do aluguel que será devolvido: ");
        int aluguelId = scanner.nextInt();

        Aluguel aluguel = alugueis.get(aluguelId - 1);

        double valorDiaria = aluguel.getVeiculo().valorDiaria();

        System.out.println();

        scanner.nextLine();

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

        double valor = calcularDias(aluguel, data, horario) * valorDiaria;

        cliente.devolverVeiculo(aluguel, valor, local, data, horario);
        cliente.mostrarAluguiesEDevolucoes();
    }

    private long calcularDias(Aluguel aluguel, LocalDate data, LocalTime hora) {

        long dias;

        if(aluguel.getHorario().isAfter(hora))
            dias = ChronoUnit.DAYS.between(aluguel.getData(), data);

        else
            dias = ChronoUnit.DAYS.between(aluguel.getData(), data) + 1;

        return dias;
    }
}
