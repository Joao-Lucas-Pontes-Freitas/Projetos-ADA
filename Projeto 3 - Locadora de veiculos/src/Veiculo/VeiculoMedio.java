package Veiculo;

public class VeiculoMedio extends Veiculo {
    public VeiculoMedio(String placa) {
        super(placa);
    }

    public VeiculoMedio() {
        super();
    }

    @Override
    public String mostrarDados() {
        return "Médio: " + placa;
    }

    @Override
    public double valorDiaria() {
        return 150;
    }
}
