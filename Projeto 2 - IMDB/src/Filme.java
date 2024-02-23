import java.util.*;
import java.util.regex.Pattern;

public class Filme{
    private String nome;
    private String genero;
    private String ano;
    private String nota;
    private final List<Ator> atores = new ArrayList<>();
    private final List<Diretor> diretores = new ArrayList<>();

    public boolean setFilme(String nome, String genero, String ano, String nota) {
        if (validarFilme(nome, genero, ano, nota)) {
            this.nome = nome;
            this.genero = genero;
            this.ano = ano;
            this.nota = nota;
            return true;
        }
        return false;
    }

    public List<Ator> getAtores() {
        return atores;
    }

    public List<Diretor> getDiretores() {
        return diretores;
    }

    public String getNome() {
        return nome;
    }

    public void mostrarFilme(){
        System.out.println("Nome: " + nome + " - Gênero: " + genero + " - Ano: " + ano);
    }

    public void dadosFilme(){
        System.out.println();
        System.out.print("      ---> Título: " + nome);
        System.out.print("\n      --> Gênero: " + genero);
        System.out.print("\n      --> Ano: " + ano);
        System.out.print("\n      --> Nota: " + nota);

        System.out.println("\n\n      -> Atores: ");

        for (Ator ator : atores){
            System.out.print("        - ");
            ator.mostrarAtor();
        }

        System.out.println("\n\n      -> Diretores: ");

        for (Diretor diretor : diretores){
            System.out.print("        - ");
            diretor.mostrarDiretor();
        }
    }

    private boolean validaAno(String ano){
        return ano != null && !ano.isEmpty() && Pattern.matches("\\d{4}", ano);
    }
    public boolean validarFilme(String titulo, String genero, String ano, String nota) {
        return validaTitulo(titulo) && validaAno(ano) && validaGenero(genero) && validaNota(nota);
    }

    private boolean validaTitulo(String titulo) {
        return titulo != null && !titulo.isEmpty();
    }

    private boolean validaNota(String nota) {
        return nota != null && !nota.isEmpty() && Pattern.matches("\\d{1,2}([.,]\\d{1,2})?", nota);
    }

    private boolean validaGenero(String genero) {
        return genero != null && !genero.isEmpty() && Pattern.matches("[\\p{L}\\s]+", genero);
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

    public boolean adicionarAtor(Ator ator) {
        if(atores.contains(ator))
            return false;
        atores.add(ator);
        return true;
    }

    public boolean adicionarDiretor(Diretor diretor) {
        if(diretores.contains(diretor))
            return false;
        diretores.add(diretor);
        return true;
    }

}
