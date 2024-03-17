package Veiculo;

public class VeiculoSUV extends Veiculo {
    public VeiculoSUV(String placa) {
        super(placa);
    }

    public VeiculoSUV() {
        super();
    }

    public VeiculoSUV(String placa, String cor) {
        super(placa, cor);
    }

    @Override
    public double valorDiaria() {
        return 200;
    }

    @Override
    public String mostrarDados() {
        return "Tipo: SUV - Placa: " + placa + " - Cor: " + cor;
    }
}
