import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Contato {
    private final long id;
    private String nome;
    private String sobrenome;
    private ArrayList<Telefone> telefones = new ArrayList<>();
    private long ultimoId = 0;

    public Contato(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Contato(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNome() {
        Scanner sc = new Scanner(System.in);
        String nome;
        do {
            System.out.print("\nDigite o nome: ");
            nome = sc.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("\u001B[38;2;255;51;51mNome inválido. Tente novamente.\u001B[0m");
            } else {
                System.out.print("\u001B[38;2;0;165;0mNome adicionado com sucesso\u001B[0m\n");
                this.nome = nome;
                break;
            }
        } while (true);
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setSobrenome() {
        Scanner sc = new Scanner(System.in);
        String sobrenome;
        do {
            System.out.print("\nDigite o novo sobrenome: ");
            sobrenome = sc.nextLine().trim();
            if (sobrenome.isEmpty()) {
                System.out.println("\u001B[38;2;255;51;51mSobrenome inválido. Tente novamente.\u001B[0m");
            } else {
                System.out.print("\u001B[38;2;0;165;0mSobrenome adicionado com sucesso\u001B[0m\n");
                this.sobrenome = sobrenome;
                break;
            }
        } while (true);
    }

    public boolean adicionarTelefone(String ddd, long numero, HashSet<String> pilhaTelefones) {
        String telefone = ddd + numero;

        if (!pilhaTelefones.contains(telefone)) {
            ultimoId += 1;
            telefones.add(new Telefone(ultimoId, ddd, numero));
            pilhaTelefones.add(telefone);
            return true;
        }
        return false;
    }

    public void adicionarTelefone(Telefone telefone, HashSet<String> pilhaTelefones) {
        String telefoneString = telefone.getDdd() + telefone.getNumero();
        if (!pilhaTelefones.contains(telefoneString)) {
            telefones.add(telefone);
            pilhaTelefones.add(telefoneString);
            ultimoId = telefone.getId();
        }
    }

    public ArrayList<Telefone> getTelefones() {
        return telefones;
    }

    public String mostrarTelefones() {
        StringBuilder telefoneStr = new StringBuilder();
        for (Telefone telefone : telefones) {
            telefoneStr.append(telefone.toString()).append(", ");
        }
        // Remover a última vírgula e espaço, se houver telefones
        if (!telefoneStr.isEmpty()) {
            telefoneStr.setLength(telefoneStr.length() - 2);
        }
        return telefoneStr.toString();
    }


    public String getSobrenome() {
        return sobrenome;
    }

    public String toString() {
        return id + " | " + nome;
    }

    public void removerTelefone(Telefone telefone, HashSet<String> pilhaTelefones) {
        if (telefone != null) {
            pilhaTelefones.remove(telefone.getDdd() + telefone.getNumero());
            telefones.remove(telefone);
        }
    }

    public long getUltimoId() {
        return ultimoId;
    }

    public void aumenta() {
        ultimoId += 1;
    }
}
