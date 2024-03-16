package Servicos;

import java.time.*;

public abstract class Servico {
    protected String local;
    protected LocalDate data;
    protected LocalDateTime horario;

    public Servico(String local, LocalDate data, LocalDateTime horario){
        this.local = local;
        this.data = data;
        this.horario = horario;
    }

    public String getLocal() {
        return local;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public String mostrarDados(){
        return "Local: " + local + " Data: " + data + " Horario: " + horario;
    }
}
