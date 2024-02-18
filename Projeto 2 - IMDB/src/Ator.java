public class Ator extends ProfissionalCinema{
    public boolean validarAtor(String nome, String dataNascimento) {
        return validaNome(nome) && validaDataNascimento(dataNascimento);
    }

    public void mostrarAtor(){
        mostrarProfissional();
    }

    public boolean setAtor(String nome, String dataNascimento) {
        return setProfissional(nome, dataNascimento);
    }
}
