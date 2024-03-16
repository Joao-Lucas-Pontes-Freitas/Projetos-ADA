package Cliente;

import Persistencia.*;
import Servicos.*;
import Veiculo.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ClienteCPF implements Cliente {
    static protected HashSet<String> CPFs = Arquivo.getCPFs();
    private String CPF;
    private String nome;
    private ArrayList<Aluguel> alugueis;
    private ArrayList<Devolucao> devolucoes;

    public static HashSet<String> getCPFs() {
        return CPFs;
    }

    public String getNome() {
        return nome;
    }

    public ClienteCPF(String CPF) {
        if (!CPFs.contains(CPF)) {
            this.CPF = CPF;
            CPFs.add(CPF);
        } else throw new IllegalArgumentException("CPF já existente: " + CPF);
    }

    public ClienteCPF() {
    }

    public String getCPF() {
        return CPF;
    }

    @Override
    public void alterarDados() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite novo nome: ");
        nome = sc.nextLine();
    }

    public String mostrarDados() {
        return "CPF: " + CPF + "Nome: " + nome;
    }

    @Override
    public void alugarVeiculo(Veiculo veiculo, String local, LocalDate data, LocalDateTime horario) {
        Aluguel aluguel = new Aluguel(veiculo, local, data, horario);
        alugueis.add(aluguel);
    }

    @Override
    public void devolverVeiculo(Aluguel aluguel, Double valor, String local, LocalDate data, LocalDateTime horario) {
        alugueis.remove(aluguel);
        Devolucao devolucao = new Devolucao(valor, local, data, horario);
        devolucoes.add(devolucao);
    }

    @Override
    public ArrayList<Aluguel> getAlugueis() {
        return alugueis;
    }
}
