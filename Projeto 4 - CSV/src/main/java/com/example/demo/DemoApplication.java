package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {


    private final JdbcTemplate jdbcTemplate;

    public DemoApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        limparBanco();

        ArrayList<Produto> insercoes = lerCsvNaRaca();
        insert(insercoes);
        List<Produto> produtos = selectProdutos();
        quantasCategorias(produtos);
        proporcaoProdutos(produtos);
        mediaProdutos(produtos);
        produtosBaixos(produtos);
    }

    private static ArrayList<Produto> lerCsvNaRaca() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\JoaoLucas\\Desktop\\Git\\Projetos-ADA\\Projeto 4 - CSV\\Produtos.csv"));

        String linha;

        br.readLine();

        ArrayList<Produto> Produtos = new ArrayList<>();

        while ((linha = br.readLine()) != null) {
            String[] valores = linha.split(",");
            Produtos.add(
                    new Produto(
                            0,
                            Integer.parseInt(valores[0]),
                            valores[1],
                            Double.parseDouble(valores[2]),
                            valores[3]
                    )
            );
        }

        return Produtos;
    }

    private static void openCsv() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\JoaoLucas\\Desktop\\Git\\Projetos-ADA\\Projeto 4 - CSV\\Produtos.csv"));
        CsvToBean<Produto> csvToBean =
                new CsvToBeanBuilder<Produto>(reader)
                        .withType(Produto.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

        List<Produto> Produtos = csvToBean.parse();
        Produtos.forEach(System.out::println);
    }

    private void limparBanco() {
        String sql = "delete from produtos";
        jdbcTemplate.update(sql);

        sql = "ALTER TABLE produtos AUTO_INCREMENT = 1";
        jdbcTemplate.update(sql);
    }

    private void insert(List<Produto> produtos) {
        String sql = "INSERT INTO produtos (quantidade, nome, preco, categoria) VALUES (?, ?, ?, ?)";

        produtos.forEach(p -> {
            jdbcTemplate.update(sql, p.getQuantidade(), p.getNome(), p.getPreco(), p.getCategoria());
        });
    }

    private List<Produto> selectProdutos() {
        String sql = "SELECT * FROM produtos";

        RowMapper<Produto> rowMapper = ((rs, rowNum) -> new Produto(
                rs.getInt("ID"),
                rs.getInt("QUANTIDADE"),
                rs.getString("NOME"),
                rs.getDouble("PRECO"),
                rs.getString("CATEGORIA")
        ));

        List<Produto> listaProdutos = jdbcTemplate.query(sql, rowMapper);

        listaProdutos.forEach(System.out::println);

        return listaProdutos;
    }

    private void quantasCategorias(List<Produto> produtos){
        // quero percorrer produtos susando stream e contar o numero de cateogrias diferntes
        System.out.println(
                produtos.stream()
                        .map(Produto::getCategoria)
                        .distinct()
                        .count());
    }

    private void proporcaoProdutos(List<Produto> produtos){
        List<String> categorias =
                produtos.stream()
                        .map(Produto::getCategoria)
                        .distinct()
                        .toList();


        categorias.
                forEach(c -> System.out.println(c + " " +
                        produtos.stream()
                                .filter(p -> p.getCategoria().equals(c))
                                .count()));
    }

    private void mediaProdutos(List<Produto> produtos){
        System.out.println(
                produtos.stream()
                        .mapToDouble(Produto::getPreco)
                        .average()
                        .getAsDouble());
    }

    private void produtosBaixos(List<Produto> produtos){

    }
}
