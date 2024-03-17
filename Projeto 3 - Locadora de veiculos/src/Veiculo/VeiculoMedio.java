package Veiculo;

public class VeiculoMedio extends Veiculo {
    public VeiculoMedio(String placa) {
        super(placa);
    }

    public VeiculoMedio(String placa, String cor) {
        super(placa, cor);
    }

    public VeiculoMedio() {
        super();
    }

    @Override
    public String mostrarDados() {
        return "Tipo: Médio - Placa: " + placa + " - Cor: " + cor;
    }

    @Override
    public double valorDiaria() {
        return 150;
    }
}
