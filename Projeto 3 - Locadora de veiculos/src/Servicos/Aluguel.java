package Servicos;

import Veiculo.*;

import java.time.*;

public class Aluguel extends Servico {

    private final Veiculo veiculo;

    public Aluguel(Veiculo veiculo, String local, LocalDate data, LocalDateTime horario) {
        super(local, data, horario);
        this.veiculo = veiculo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    @Override
    public String mostrarDados() {
        return "Veiculo: " + veiculo.getPlaca() + " " + super.mostrarDados();
    }
}
