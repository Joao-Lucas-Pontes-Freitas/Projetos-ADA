package Cliente;

import Veiculo.*;
import Servicos.*;

import java.time.*;
import java.util.*;

public interface Cliente {
    void alterarDados();

    String mostrarDados();

    void alugarVeiculo(Veiculo veiculo, String local, LocalDate data, LocalTime horario);

    void devolverVeiculo(Aluguel aluguel, Double valor, String local, LocalDate data, LocalTime horario);

    ArrayList<Aluguel> getAlugueis();

    void mostrarAluguiesEDevolucoes();
}
