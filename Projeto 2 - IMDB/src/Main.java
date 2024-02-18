import java.util.*;

public class Main {
    public static void main(String[] args) {

        Stack<Filme> filmesCadastrados = new Stack<>();
        Stack<Diretor> diretoresCadastrados = new Stack<>();
        Stack<Ator> atoresCadastrados = new Stack<>();

        Menu Menu = new Menu(filmesCadastrados, atoresCadastrados, diretoresCadastrados);
        Menu.mostrarMenu();
    }
}