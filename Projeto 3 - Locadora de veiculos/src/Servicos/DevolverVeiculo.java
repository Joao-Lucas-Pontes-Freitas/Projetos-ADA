package Servicos;

import Cliente.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class DevolverVeiculo {
    public void devolver(ArrayList<Cliente> clientes) {

        for (int i = 0; i < clientes.size(); i++)
            System.out.println((i + 1) + " - " + clientes.get(i).mostrarDados());

        System.out.println();

        System.out.print("Digite o ID do cliente que deseja devolver o veículo: ");

        Scanner scanner = new Scanner(System.in);
        int clienteId = scanner.nextInt();

        System.out.println();

        ArrayList<Aluguel> alugueis = clientes.get(clienteId - 1).getAlugueis();

        for (int i = 0; i < alugueis.size(); i++)
            System.out.println((i + 1) + " - " + alugueis.get(i).mostrarDados());

        System.out.println();

        System.out.print("Digite o ID do aluguel que será devolvido: ");
        int aluguelId = scanner.nextInt();

        Aluguel aluguel = alugueis.get(aluguelId - 1);

        double valorDiaria = aluguel.getVeiculo().valorDiaria();

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

        double valor = calcularDias(aluguel, data, horario) * valorDiaria;

        cliente.devolverVeiculo(aluguel, valor, local, data, horario);
    }

    private long calcularDias(Aluguel aluguel, LocalDate data, LocalDateTime hora) {

        Duration diferenca = Duration.between(aluguel.getHorario(), hora);

        long dias = diferenca.toDaysPart();
        if (diferenca.toHoursPart() > 0 || diferenca.toMinutesPart() > 0)
            dias++;

        
        return dias;
    }

}
