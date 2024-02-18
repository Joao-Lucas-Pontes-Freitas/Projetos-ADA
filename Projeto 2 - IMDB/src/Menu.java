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
    public void mostrarMenu(){
        System.out.println("1 - Cadastrar Filme");
        System.out.println("2 - Cadastrar Ator");
        System.out.println("3 - Cadastrar Diretor");
        System.out.println("4 - Consultar Filme");
        System.out.println("5 - Sair");
        System.out.println("\nEscolha uma opção: ");

        int opcao;
        Scanner scanner = new Scanner(System.in);
        opcao = scanner.nextInt();

        switch (opcao){
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

        String titulo;
        String ano;
        String nota;
        String genero;

        Ator ator;
        Diretor diretor;

        ArrayList<Ator> atores = new ArrayList<>();
        ArrayList<Diretor> diretores = new ArrayList<>();

        boolean adicionarAtor = true;
        boolean adicionarDiretor = true;

        int indiceAtor;
        int indiceDiretor;

        do{
            int i = 1;
            for (Ator atorCadastrado : atoresCadastrados){
                System.out.println(i + atorCadastrado.getNome());
                i++;
            }

            do{
                System.out.print("Digite o número do ator já cadastrado: ");
                indiceAtor = new Scanner(System.in).nextInt();

                if (indiceAtor > 0 && indiceAtor <= atoresCadastrados.size()){
                    ator = atoresCadastrados.get(indiceAtor);
                    atores.add(ator);
                }
                else
                    break;

                System.out.println("Digite 1 para adicionar mais algum ator e 0 para parar: ");


            }while(adicionarAtor);

        }while (!filme.validarFilme() && !filmesCadastrados.contains(filme));

        mostrarMenu();
    }

    public void cadastrarAtor(){

        Ator ator = new Ator();
        String nome;
        String dataNascimento;

        do{
            System.out.print("Digite um nome válido: ");
            nome = new Scanner(System.in).nextLine();

            System.out.print("\nDigite a data de nascimento (dia/mes/ano): ");
            dataNascimento = new Scanner(System.in).nextLine();

            System.out.println();

        }while (!ator.setAtor(nome, dataNascimento) && !atoresCadastrados.contains(ator));

        atoresCadastrados.add(ator);

        mostrarMenu();
    }

    public void cadastrarDiretor(){

        Diretor diretor = new Diretor();
        String nome;
        String dataNascimento;

        do{
            System.out.print("Digite um nome válido: ");
            nome = new Scanner(System.in).nextLine();

            System.out.print("\nDigite a data de nascimento (dia/mes/ano): ");
            dataNascimento = new Scanner(System.in).nextLine();

            System.out.println();

        }while (!diretor.setDiretor(nome, dataNascimento) && !diretoresCadastrados.contains(diretor));

        diretoresCadastrados.add(diretor);

        mostrarMenu();
    }

    public void cosultarFilme(){

        String nome = new Scanner(System.in).nextLine();
        boolean encontrado = false;

        for (Filme filme : filmesCadastrados){
            if (filme.getNome().equals(nome)){
                filme.mostrarFilme();
                encontrado = true;
                break;
            }
        }

        if(!encontrado)
            System.out.println("\u001B[3m\u001B[31mFilme não foi encontrado\u001B[0m");

        mostrarMenu();
    }

    public void teste(){
        System.out.println("teste");
    }

}
