package Veiculo;

public class VeiculoPequeno extends Veiculo {
    public VeiculoPequeno(String placa) {
        super(placa);
    }

    public VeiculoPequeno() {
        super();
    }

    @Override
    public double valorDiaria() {
        return 100;
    }

    @Override
    public String mostrarDados() {
        return "Pequeno: " + placa;
    }
}
