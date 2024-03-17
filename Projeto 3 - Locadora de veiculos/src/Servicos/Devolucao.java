package Servicos;

import Veiculo.Veiculo;

import java.time.*;

public class Devolucao extends Servico {
    private final double valor;

    public Devolucao(double valor, String local, LocalDate data, LocalTime horario, Veiculo veiculo) {
        super(local, data, horario, veiculo);
        this.valor = valor;
    }
    public String mostrarDados() {
        return "Valor: " + valor + " - " + super.mostrarDados();
    }
}
