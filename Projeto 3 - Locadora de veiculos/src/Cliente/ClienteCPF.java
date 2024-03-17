package Cliente;

import Persistencia.*;
import Servicos.*;
import Veiculo.*;

import java.time.*;
import java.util.*;

public class ClienteCPF implements Cliente {
    static protected HashSet<String> CPFs = Arquivo.getCPFs();
    private final String CPF;
    private String nome;
    private final ArrayList<Aluguel> alugueis = new ArrayList<>();
    private final ArrayList<Devolucao> devolucoes = new ArrayList<>();

    public ClienteCPF(String cpf, String nome) {
        this.CPF = cpf;
        this.nome = nome;
        CPFs.add(CPF);
    }

    public String getNome() {
        return nome;
    }

    public ClienteCPF(String CPF) {
        if (!CPFs.contains(CPF)) {
            this.CPF = CPF;
            CPFs.add(CPF);
        } else throw new IllegalArgumentException("      CPF já existente: " + CPF);
    }

    public ClienteCPF() {
        System.out.print("      Digite novo CPF: ");
        Scanner sc = new Scanner(System.in);
        String cpf = sc.nextLine();

        if (!CPFs.contains(cpf)) {
            this.CPF = cpf;
            CPFs.add(CPF);
        } else {
            throw new IllegalArgumentException("      CPF já existente: " + cpf);
        }

        alterarDados();
    }

    public String getCPF() {
        return CPF;
    }

    @Override
    public void alterarDados() {
        Scanner sc = new Scanner(System.in);

        System.out.print("      Digite novo nome: ");
        nome = sc.nextLine();
    }

    public String mostrarDados() {
        return "CPF: " + CPF + " - Nome: " + nome;
    }

    public void mostrarAluguiesEDevolucoes() {
        System.out.println("      CPF: " + CPF);

        System.out.println("      - Alugueis: ");
        for (Aluguel aluguel : alugueis) {
            System.out.println("      " + aluguel.mostrarDados());
        }
        System.out.println("      - Devolucoes: ");
        for (Devolucao devolucao : devolucoes) {
            System.out.println("      " + devolucao.mostrarDados());
        }
    }

    @Override
    public void alugarVeiculo(Veiculo veiculo, String local, LocalDate data, LocalTime horario) {
        Aluguel aluguel = new Aluguel(veiculo, local, data, horario);
        aluguel.alugar();
        alugueis.add(aluguel);
    }

    @Override
    public void devolverVeiculo(Aluguel aluguel, Double valor, String local, LocalDate data, LocalTime horario) {
        alugueis.remove(aluguel);
        aluguel.devolver();
        Devolucao devolucao = new Devolucao(valor, local, data, horario);
        devolucoes.add(devolucao);
    }

    @Override
    public ArrayList<Aluguel> getAlugueis() {
        return alugueis;
    }
}
