import java.util.*;
import java.util.regex.Pattern;

public abstract class ProfissionalCinema{
    protected String nome;
    protected String dataNascimento;
    private List<Filme> filmes;


    public boolean setProfissional(String nome, String dataNascimento){

        if(!validaNome(nome) || !validaDataNascimento(dataNascimento))
            return false;

        this.nome = nome;
        this.dataNascimento = dataNascimento;

        return true;
    }
    protected boolean validaDataNascimento(String dataNascimento){
        return dataNascimento != null && !dataNascimento.isEmpty() && Pattern.matches("\\d{2}/\\d{2}/\\d{4}", dataNascimento);
    }

    protected boolean validaNome(String nome){
        return nome != null && !nome.isEmpty() && Pattern.matches("[a-zA-Z\\s]+", nome);
    }
    void mostrarProfissional(){
        System.out.println("Nome: " + nome + " - Data de Nascimento: " + dataNascimento);
        mostrarFilmes();
    }
    void mostrarFilmes(){
        System.out.println("Filmes: ");
        for (Filme filme : filmes){
            System.out.print("- ");
            System.out.println(filme.getNome());
        }
    }
    public String getNome() {
        return nome;
    }

    public void adicionaFilme(Filme filme) {
        filmes.add(filme);
    }
}
