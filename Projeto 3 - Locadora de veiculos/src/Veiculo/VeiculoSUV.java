package Veiculo;

public class VeiculoSUV extends Veiculo {
    public VeiculoSUV(String placa) {
        super(placa);
    }

    public VeiculoSUV() {
        super();
    }

    @Override
    public double valorDiaria() {
        return 200;
    }

    @Override
    public String mostrarDados() {
        return "SUV: " + placa;
    }
}
