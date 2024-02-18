public class Diretor extends ProfissionalCinema{

    public boolean validarDiretor(String nome, String dataNascimento) {
        return validaNome(nome) && validaDataNascimento(dataNascimento);
    }
    public void mostrarDiretor(){
        mostrarProfissional();
    }
    public boolean setDiretor(String nome, String dataNascimento) {
        return setProfissional(nome, dataNascimento);
    }
}
