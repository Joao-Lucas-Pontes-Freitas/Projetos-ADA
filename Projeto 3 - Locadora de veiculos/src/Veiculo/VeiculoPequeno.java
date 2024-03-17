package Veiculo;

public class VeiculoPequeno extends Veiculo {
    public VeiculoPequeno(String placa) {
        super(placa);
    }

    public VeiculoPequeno(String placa, String cor) {
        super(placa, cor);
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
        return "Tipo: Pequeno - Placa: " + placa + " - Cor: " + cor;
    }

}
