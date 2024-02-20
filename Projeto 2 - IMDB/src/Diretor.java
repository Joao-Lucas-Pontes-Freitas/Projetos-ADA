import java.util.Objects;

public class Diretor extends ProfissionalCinema{
    public void mostrarDiretor(){
        mostrarProfissional();
    }
    public boolean setDiretor(String nome, String dataNascimento) {
        return setProfissional(nome, dataNascimento);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Diretor other = (Diretor) obj;
        return Objects.equals(nome, other.nome) && Objects.equals(dataNascimento, other.dataNascimento);
    }
}
