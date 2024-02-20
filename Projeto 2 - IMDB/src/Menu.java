import java.util.*;
public class Menu {
    private final Stack<Filme> filmesCadastrados;
    private final Stack<Ator> atoresCadastrados;
    private final Stack<Diretor> diretoresCadastrados;

    public Menu(Stack<Filme> filmesCadastrados, Stack<Ator> atoresCadastrados, Stack<Diretor> diretoresCadastrados){
        this.filmesCadastrados = filmesCadastrados;
        this.atoresCadastrados = atoresCadastrados;
        this.diretoresCadastrados = diretoresCadastrados;
    }
    public void mostrarMenu() {
        System.out.println();
        System.out.println("1 - Cadastrar Filme");
        System.out.println("2 - Cadastrar Ator");
        System.out.println("3 - Cadastrar Diretor");
        System.out.println("4 - Consultar Filme");
        System.out.println("5 - Sair");

        boolean opcaoInvalida = true;

        String escolha;

        Scanner scanner = new Scanner(System.in);;
        do {
            System.out.print("\nEscolha uma opção: ");
            escolha = scanner.nextLine();

            if (escolha.equals("1") || escolha.equals("2") || escolha.equals("3") || escolha.equals("4") || escolha.equals("5"))
                opcaoInvalida = false;

        } while (opcaoInvalida);

        System.out.println();

        int opcao = Integer.parseInt(escolha);

        switch (opcao) {
            case 1:
                cadastrarFilme();
                break;
            case 2:
                cadastrarAtor();
                break;
            case 3:
                cadastrarDiretor();
                break;
            case 4:
                cosultarFilme();
                break;
        }

        System.out.println("Saindo do sistema...");
        System.exit(0);

        scanner.close();

        System.out.println("Saindo do sistema...");
        System.exit(0);
    }
    public void cadastrarFilme(){

        Filme filme = new Filme();

        if (atoresCadastrados.isEmpty() || diretoresCadastrados.isEmpty()){
            System.out.println("Não há atores ou diretores cadastrados!");
            mostrarMenu();
        }

        String nome, genero, ano, nota;
        do{
            System.out.print("Digite o título não vazio: ");
            nome = new Scanner(System.in).nextLine();

            System.out.print("Digite o gênero compsoto somente por letras: ");
            genero  = new Scanner(System.in).nextLine();

            System.out.print("Digite o ano no formato (2000): ");
            ano = new Scanner(System.in).nextLine();

            System.out.print("Digite a nota no formato (9,5): ");
            nota = new Scanner(System.in).nextLine();

            System.out.println();

        }while(!filme.setFilme(nome, genero, ano, nota));

        adicionarAtor(filme);
        adicionarDiretor(filme);

        filmesCadastrados.add(filme);

        for(Ator ator : filme.getAtores())
            ator.adicionaFilme(filme);

        for(Diretor diretor : filme.getDiretores())
            diretor.adicionaFilme(filme);

        mostrarMenu();
    }

    public void cadastrarAtor() {
        Ator ator = new Ator();
        String nome;
        String dataNascimento;

        boolean adicionadoComSucesso;

        do {
            System.out.print("Digite um nome não vazio: ");
            nome = new Scanner(System.in).nextLine();

            System.out.print("Digite a data de nascimento (dia/mes/ano): ");
            dataNascimento = new Scanner(System.in).nextLine();


            adicionadoComSucesso = ator.setAtor(nome, dataNascimento) && !atoresCadastrados.contains(ator);

            if (!adicionadoComSucesso) {
                System.out.println("\nErro: Nome inválido, data de nascimento inválida ou ator já cadastrado.");
            }

        } while (!adicionadoComSucesso);

        atoresCadastrados.add(ator);

        mostrarMenu();
    }


    public void cadastrarDiretor(){

        Diretor diretor = new Diretor();
        String nome;
        String dataNascimento;

        do{
            System.out.print("Digite um nome não vazio: ");
            nome = new Scanner(System.in).nextLine();

            System.out.print("Digite a data de nascimento (dia/mes/ano): ");
            dataNascimento = new Scanner(System.in).nextLine();

            System.out.println();

        }while (!diretor.setDiretor(nome, dataNascimento) || diretoresCadastrados.contains(diretor));

        diretoresCadastrados.add(diretor);

        mostrarMenu();
    }

    public void cosultarFilme(){

        String nome = new Scanner(System.in).nextLine();
        nome = nome.toLowerCase();

        boolean encontrado = false;

        for (Filme filme : filmesCadastrados){
            if (filme.getNome().toLowerCase().equals(nome)){
                filme.mostrarFilme();
                encontrado = true;
                break;
            }
        }

        if(!encontrado)
            System.out.println("\u001B[3m\u001B[31mFilme não foi encontrado\u001B[0m");

        mostrarMenu();
    }

    public void adicionarAtor(Filme filme){

        boolean adicionar = true;
        int id;
        String escolha;

        do{
            int i = 1;
            for(Ator ator : atoresCadastrados){
                System.out.print(i);
                System.out.println(ator.getNome());
                i++;
            }

            System.out.print("Escolha o número do ator para adicionar: ");
            id = new Scanner(System.in).nextInt();
            System.out.println();

            filme.adicionarAtor(atoresCadastrados.get(id - 1));

            do{
                System.out.println("Deseja adicionar mais atores? (0 para não e 1 para sim): ");
                escolha = new Scanner(System.in).nextLine();
                System.out.println();

            }while (!escolha.equals("0") && !escolha.equals("1"));

            if(escolha.equals("0"))
                adicionar = false;

        }while (adicionar);
    }

    public void adicionarDiretor(Filme filme){

        boolean adicionar = true;
        int id;
        String escolha;

        do{ int i = 1;
            for(Diretor diretor : diretoresCadastrados){
                System.out.print(i);
                System.out.println(diretor.getNome());
                i++;
            }

            System.out.print("Escolha o número do diretor para adicionar: ");
            id = new Scanner(System.in).nextInt();
            System.out.println();

            filme.adicionarDiretor(diretoresCadastrados.get(id - 1));

            do{
                System.out.println("Deseja adicionar mais diretores? (0 para não e 1 para sim): ");
                escolha = new Scanner(System.in).nextLine();
                System.out.println();

            }while (!escolha.equals("0") && !escolha.equals("1"));

            if(escolha.equals("0"))
                adicionar = false;

        }while (adicionar);
    }

}
