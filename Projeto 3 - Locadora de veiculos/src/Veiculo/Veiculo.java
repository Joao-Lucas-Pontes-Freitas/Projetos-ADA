package Veiculo;

import Persistencia.Arquivo;

import java.util.*;

public abstract class Veiculo {
    static protected HashSet<String> placasExistentes = Arquivo.getPlacas();
    protected String placa;
    protected String cor = "";
    protected boolean disponivel = true;

    public Veiculo() {
        System.out.print("      Digite nova placa: ");
        Scanner sc = new Scanner(System.in);
        String placa = sc.nextLine();

        if (!placasExistentes.contains(placa)) {
            this.placa = placa;
            placasExistentes.add(placa);
        }
        else{
            throw new IllegalArgumentException("      Placa já existente: " + placa);
        }

        alterarDados();
    }

    public Veiculo(String placa) {
        if (!placasExistentes.contains(placa)) {
            this.placa = placa;
            placasExistentes.add(placa);
        } else
            throw new IllegalArgumentException("      Placa já existente: " + placa);

        cor = "Preto";
    }

    public Veiculo(String placa, String cor) {
        this.placa  = placa;
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void alterarDados() {
        Scanner sc = new Scanner(System.in);

        System.out.print("      Digite nova cor: ");
        cor = sc.nextLine();
    }

    public void alugar(){
        disponivel = false;
    }

    public void devolver(){
        disponivel = true;
    }

    public static HashSet<String> getPlacasExistentes() {
        return placasExistentes;
    }

    public String getCor() {
        return cor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public abstract String mostrarDados();

    public abstract double valorDiaria();

}
