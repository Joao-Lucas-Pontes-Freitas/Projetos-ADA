import java.util.*;
import java.util.regex.Pattern;

public abstract class ProfissionalCinema implements Valida{
    private String nome;
    private String dataNascimento;
    private List<Filme> filmes;


    public boolean setProfissional(String nome, String dataNascimento){

        if(validaNome(nome) && validaDataNascimento(dataNascimento)){
            setNome(nome);
            setDataNascimento(dataNascimento);
            return true;
        }
        return false;
    }

    protected void setNome(String nome) {
        this.nome = nome;
    }
    protected void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getNome() {
        return nome;
    }
    public String getDataNascimento() {
        return dataNascimento;
    }

    protected boolean validaDataNascimento(String dataNascimento){
        return dataNascimento != null && !dataNascimento.isEmpty() && !Pattern.matches("\\d{2}/\\d{2}/\\d{4}", dataNascimento);
    }
    void mostrarProfissional(){
        System.out.println("Nome: " + nome + " Data de Nascimento: " + dataNascimento);
    }
}
