package Servicos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Devolucao extends Servico {
    private final double valor;

    public Devolucao(double valor, String local, LocalDate data, LocalDateTime horario) {
        super(local, data, horario);
        this.valor = valor;
    }
    public double getValor() {
        return valor;
    }
}
