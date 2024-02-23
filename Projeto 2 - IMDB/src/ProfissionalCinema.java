import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public abstract class ProfissionalCinema{
    protected String nome;
    protected String dataNascimento;

    public boolean setProfissional(String nome, String dataNascimento){

        if(!validaNome(nome) || !validaDataNascimento(dataNascimento))
            return false;

        this.nome = nome;
        this.dataNascimento = dataNascimento;

        return true;
    }

    protected boolean validaDataNascimento(String dataNascimento){

        // Converte a string de data para LocalDate
        LocalDate dataNasc;

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNasc = LocalDate.parse(dataNascimento, formatter);

        } catch (DateTimeParseException e) {
            return false;
        }

        // Verifica se a data de nascimento é no futuro
        LocalDate hoje = LocalDate.now();

        return !dataNasc.isAfter(hoje);
    }


    public String getDataNascimento() {
        return dataNascimento;
    }

    protected boolean validaNome(String nome){
        return nome != null && !nome.isEmpty() && Pattern.matches("[\\p{L}\\s]+", nome);
    }

    void mostrarProfissional(){
        System.out.println("Nome: " + nome + " - Data de Nascimento: " + dataNascimento);
    }

    public String getNome() {
        return nome;
    }
}
