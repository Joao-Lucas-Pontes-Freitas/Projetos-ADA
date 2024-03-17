package Servicos;

import Cliente.*;
import Veiculo.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public interface MetodosAuxiliares {
    default int escolherCliente(ArrayList<Cliente> clientes){

        for (int i = 0; i < clientes.size(); i++)
            System.out.println("      " + (i + 1) + " - " + clientes.get(i).mostrarDados());

        System.out.println();

        System.out.print("      Digite o ID do cliente que deseja alugar o veículo: ");

        Scanner scanner = new Scanner(System.in);

        return scanner.nextInt();
    }

    default String escolherLocal(){
        Scanner  scanner = new Scanner(System.in);

        System.out.print("      Digite o local: ");
        return scanner.nextLine();
    }

    default LocalDate escolherData(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("      Digite a data (dia/mes/ano): ");
        return LocalDate.parse(scanner.nextLine(), java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    default LocalTime escolherHorario(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("      Digite o horario (hora:min): ");
        return LocalTime.parse(scanner.nextLine(), java.time.format.DateTimeFormatter.ofPattern("HH:mm"));
    }
}
