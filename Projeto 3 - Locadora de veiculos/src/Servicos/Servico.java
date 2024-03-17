package Servicos;

import Veiculo.*;

import java.time.*;

public abstract class Servico {
    protected String local;
    protected LocalDate data;
    protected LocalTime horario;

    protected final Veiculo veiculo;

    public Servico(String local, LocalDate data, LocalTime horario, Veiculo veiculo){
        this.local = local;
        this.data = data;
        this.horario = horario;
        this.veiculo = veiculo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public String mostrarDados(){
        return "Veiculo: " + veiculo.mostrarDados() + " - Local: " + local + " - Data: " + data + " - Horario: " + horario;
    }
}
