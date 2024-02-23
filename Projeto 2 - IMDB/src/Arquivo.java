import java.io.*;
import java.util.*;

public class Arquivo {
    private static final String arquivo = "src/Itens.txt";

    public static List<String> lerItens() {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Problema ao ler arquivo");
        }
        return linhas;
    }

    public static void escreverItens(List<String> linhas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (String linha : linhas) {
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Problema ao escrever no arquivo");
        }
    }
}
