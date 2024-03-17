package Cliente;

import Persistencia.Arquivo;
import Servicos.Aluguel;
import Servicos.Devolucao;
import Veiculo.Veiculo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class ClienteCNPJ implements Cliente {
    static private HashSet<String> CNPJs = Arquivo.getCNPJs();
    private final String CNPJ;
    private String empresa;
    private final ArrayList<Aluguel> alugueis = new ArrayList<>();
    private final ArrayList<Devolucao> devolucoes = new ArrayList<>();

    public ClienteCNPJ(String CNPJ) {
        if (!CNPJs.contains(CNPJ)) {
            this.CNPJ = CNPJ;
            CNPJs.add(CNPJ);
        } else throw new IllegalArgumentException("      CNPJ já existente: " + CNPJ);

    }

    public ClienteCNPJ() {
        System.out.print("      Digite novo CNPJ: ");
        Scanner sc = new Scanner(System.in);
        String cnpj = sc.nextLine();

        if (!CNPJs.contains(cnpj)) {
            this.CNPJ = cnpj;
            CNPJs.add(cnpj);
        } else {
            throw new IllegalArgumentException("      CNPJ já existente: " + cnpj);
        }

        alterarDados();
    }

    public ClienteCNPJ(String cnpj, String empresa) {
        this.CNPJ = cnpj;
        this.empresa = empresa;
        CNPJs.add(cnpj);
    }

    public void mostrarAluguiesEDevolucoes() {
        System.out.println("      CNPJ: " + CNPJ);

        System.out.println("      - Alugueis: ");
        for (Aluguel aluguel : alugueis) {
            System.out.println("      " + aluguel.mostrarDados());
        }
        System.out.println("      - Devolucoes: ");
        for (Devolucao devolucao : devolucoes) {
            System.out.println("      " + devolucao.mostrarDados());
        }
    }

    public String getCNPJ() {
        return CNPJ;
    }

    @Override
    public void alterarDados() {
        Scanner sc = new Scanner(System.in);

        System.out.print("      Digite nova empresa: ");
        empresa = sc.nextLine();
    }

    public String mostrarDados() {
        return "CNPJ: " + CNPJ + " - Empresa: " + empresa;
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
