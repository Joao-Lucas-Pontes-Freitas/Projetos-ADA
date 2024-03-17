package Servicos;

import Veiculo.*;

import java.time.*;

public class Aluguel extends Servico {
    public Aluguel(Veiculo veiculo, String local, LocalDate data, LocalTime horario) {
        super(local, data, horario, veiculo);
    }

    public void alugar() {
        if (veiculo != null) veiculo.alugar();
    }

    public void devolver() {
        if (veiculo != null) veiculo.devolver();
    }
}
