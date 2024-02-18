import java.util.*;
import java.util.regex.Pattern;

public class Filme implements Valida{
    private String nome;
    private String genero;
    private String ano;
    private String nota;
    private List<Ator> atores;
    private List<Diretor> diretores;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public void setAtores(List<Ator> atores) {
        this.atores = atores;
    }

    public void setDiretores(List<Diretor> diretores) {
        this.diretores = diretores;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public String getAno() {
        return ano;
    }

    public String getNota() {
        return nota;
    }

    public List<Ator> getAtores() {
        return atores;
    }

    public List<Diretor> getDiretores() {
        return diretores;
    }

    public void mostrarFilme(){
        System.out.print("----> Título: " + nome);
        System.out.print("\n--> Gênero: " + genero);
        System.out.print("\n--> Ano: " + ano);
        System.out.print("\n--> Nota: " + nota);

        System.out.println("\n-> Atores: ");

        for (Ator ator : atores){
            System.out.print(" - ");
            ator.mostrarAtor();
        }
        for (Diretor diretor : diretores){
            System.out.print(" - ");
            diretor.mostrarDiretor();
        }
    }

    private boolean validaAno(String ano){
        return ano != null && !ano.isEmpty() && !Pattern.matches("\\d{4}", ano);
    }
    public boolean validarFilme(String titulo, String genero, String ano, String nota) {
        return validaNome(titulo) && validaAno(ano) && validaGenero(genero) && validaNota(nota);
    }
    private boolean validaNota(String nota) {
        return nota != null && !nota.isEmpty() && !Pattern.matches("\\d{1,2}", nota);
    }
    private boolean validaGenero(String genero) {
        return genero != null && !genero.isEmpty() && !Pattern.matches("[a-zA-Z]+", genero);
    }
}
