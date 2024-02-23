import java.util.*;
public class Ator extends ProfissionalCinema{


    public void mostrarAtor(){
        mostrarProfissional();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Ator other = (Ator) obj;
        return Objects.equals(nome, other.nome) && Objects.equals(dataNascimento, other.dataNascimento);
    }

    public boolean setAtor(String nome, String dataNascimento) {
        return setProfissional(nome, dataNascimento);
    }
}
