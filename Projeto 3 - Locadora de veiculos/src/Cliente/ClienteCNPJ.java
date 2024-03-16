package Cliente;

import Persistencia.Arquivo;

import java.util.*;

public class ClienteCNPJ implements Cliente {
    static private HashSet<String> CNPJs = Arquivo.getCNPJs();
    private String CNPJ;
    private String empresa;

    public ClienteCNPJ(String CNPJ) {
        if (!CNPJs.contains(CNPJ)) {
            this.CNPJ = CNPJ;
            CNPJs.add(CNPJ);
        } else
            throw new IllegalArgumentException("CNPJ já existente: " + CNPJ);

    }

    public ClienteCNPJ() {
    }

    public String getCNPJ() {
        return CNPJ;
    }

    @Override
    public void alterarDados() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite nova empresa: ");
        empresa = sc.nextLine();
    }


    public static HashSet<String> getCNPJs() {
        return CNPJs;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String mostrarDados() {
        return "CNPJ: " + CNPJ + " Empresa: " + empresa;
    }
}
