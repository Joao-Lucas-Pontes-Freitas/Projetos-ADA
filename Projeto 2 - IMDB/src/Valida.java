import java.util.regex.Pattern;

public interface Valida {
    default boolean validaNome(String nome){
        return nome != null && !nome.isEmpty() && !Pattern.matches("[a-zA-Z]+", nome);
    }


}
