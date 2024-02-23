import java.util.*;

public class Menu {
    private final Stack<Filme> filmesCadastrados;
    private final Stack<Ator> atoresCadastrados;
    private final Stack<Diretor> diretoresCadastrados;
    private Filme ultimoFilme;

    public Menu(Stack<Filme> filmesCadastrados, Stack<Ator> atoresCadastrados, Stack<Diretor> diretoresCadastrados) {

        this.filmesCadastrados = filmesCadastrados;
        this.atoresCadastrados = atoresCadastrados;
        this.diretoresCadastrados = diretoresCadastrados;
        carregarItens();
    }

    public void mostrarMenu() {
        System.out.println();
        System.out.printf("      \u001B[38;2;%d;%d;%dmMenu:\u001B[0m%n", 64, 224, 208);
        System.out.printf("      1 - \u001B[34mCadastrar Filme\u001B[0m%n");
        System.out.printf("      2 - \u001B[34mCadastrar Ator\u001B[0m%n");
        System.out.printf("      3 - \u001B[34mCadastrar Diretor\u001B[0m%n");
        System.out.printf("      4 - \u001B[34mConsultar Filme\u001B[0m%n");
        System.out.printf("      5 - \u001B[34mMostrar Itens\u001B[0m%n");
        System.out.printf("      6 - \u001B[34mEditar Filme\u001B[0m%n");
        System.out.printf("      7 - \u001B[34mSair\u001B[0m%n");

        int opcao = verficaOpcaoMenu("7");

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
                consultarFilme();
                break;
            case 5:
                mostrarItens();
                break;
            case 6:
                editarFilme();
                break;
            case 7:
                System.out.printf("\n\u001B[38;2;%d;%d;%dm      Saindo do Sistema...\u001B[0m%n", 255, 215, 0);
                System.exit(0);
                break;
        }

    }

    private void carregarItens() {
        List<String> linhas = Arquivo.lerItens();

        for (String linha : linhas) {
            String[] partes = linha.split("\\$");
            if (partes.length >= 2) {
                String tipo = partes[0];
                String[] atributos = partes[1].split("%");
                switch (tipo) {
                    case "Filme":
                        ultimoFilme = new Filme();
                        ultimoFilme.setFilme(atributos[0], atributos[1], atributos[2], atributos[3]);
                        filmesCadastrados.push(ultimoFilme);
                        break;
                    case "Ator":
                        if (atributos.length == 2 && ultimoFilme != null) {
                            Ator ator = new Ator();
                            ator.setAtor(atributos[0], atributos[1]);

                            if(!atoresCadastrados.contains(ator))
                                atoresCadastrados.add(ator);

                            ultimoFilme.adicionarAtor(ator); // Adiciona o ator ao último filme lido
                        }
                        break;
                    case "Diretor":
                        if (atributos.length == 2 && ultimoFilme != null) {
                            Diretor diretor = new Diretor();
                            diretor.setDiretor(atributos[0], atributos[1]);

                            if(!diretoresCadastrados.contains(diretor))
                                diretoresCadastrados.add(diretor);

                            ultimoFilme.adicionarDiretor(diretor); // Adiciona o diretor ao último filme lido
                        }
                        break;
                }
            }
        }
    }

    private void salvarItens() {

        List<String> linhas = new ArrayList<>();

        for (Filme filme : filmesCadastrados){
            linhas.add("Filme$" + filme.getNome() + "%" + filme.getGenero() + "%" + filme.getAno() + "%" + filme.getNota());
            for(Ator ator : filme.getAtores())
                linhas.add("Ator$" + ator.getNome() + "%" + ator.getDataNascimento());
            for(Diretor diretor : filme.getDiretores())
                linhas.add("Diretor$" + diretor.getNome() + "%" + diretor.getDataNascimento());
        }




        Arquivo.escreverItens(linhas);
    }

    private void cadastrarFilme(){

        Filme filme = new Filme();

        if (atoresCadastrados.isEmpty() || diretoresCadastrados.isEmpty()){
            System.out.println("      \u001B[31mNão há atores ou diretores cadastrados!\u001B[0m");
            mostrarMenu();
        }

        editarDados(filme);

        adicionarAtor(filme);
        adicionarDiretor(filme);

        filmesCadastrados.add(filme);
        salvarItens();

        mostrarMenu();
    }

    private void cadastrarAtor() {
        Ator ator = new Ator();
        String nome;
        String dataNascimento;

        boolean adicionadoComSucesso;

        do {
            System.out.printf("      Digite o \u001B[38;2;%d;%d;%dmnome:\u001B[0m ", 64,224,208);
            nome = new Scanner(System.in).nextLine();

            System.out.printf("      Digite a \u001B[38;2;%d;%d;%dmdata de nascimento\u001B[0m (dia/mes/ano): ", 64,224,208);
            dataNascimento = new Scanner(System.in).nextLine();

            adicionadoComSucesso = ator.setAtor(nome, dataNascimento) && !atoresCadastrados.contains(ator);

            if (!adicionadoComSucesso) {
                System.out.println("\n\u001B[31m      Erro: Nome inválido, data de nascimento inválida ou ator já cadastrado\u001B[0m\n");
            }

        } while (!adicionadoComSucesso);

        atoresCadastrados.add(ator);
        salvarItens();

        mostrarMenu();
    }

    private void cadastrarDiretor(){

        Diretor diretor = new Diretor();
        String nome;
        String dataNascimento;

        boolean adicionadoComSucesso;

        do {
            System.out.printf("      Digite o \u001B[38;2;%d;%d;%dmnome:\u001B[0m ", 64,224,208);
            nome = new Scanner(System.in).nextLine();

            System.out.printf("      Digite a \u001B[38;2;%d;%d;%dmdata de nascimento\u001B[0m (dia/mes/ano): ", 64,224,208);
            dataNascimento = new Scanner(System.in).nextLine();

            adicionadoComSucesso = diretor.setDiretor(nome, dataNascimento) && !diretoresCadastrados.contains(diretor);

            if (!adicionadoComSucesso) {
                System.out.println("\n\u001B[31m      Erro: Nome inválido, data de nascimento inválida ou diretor já cadastrado.\u001B[0m\n");
            }

        } while (!adicionadoComSucesso);

        diretoresCadastrados.add(diretor);
        salvarItens();

        mostrarMenu();
    }

    private void consultarFilme(){

        if(filmesCadastrados.isEmpty()){
            System.out.println("\u001B[31m      Nenhum filme cadastrado ainda\u001B[0m");
            mostrarMenu();
        }

        System.out.printf("      \u001B[34mFilmes Adicionados:\u001B[0m%n");
        for(Filme filme : filmesCadastrados)
            System.out.print("        " + " - " + filme.getNome() + "\n");

        System.out.println();
        System.out.printf("      Digite o \u001B[38;2;%d;%d;%dmnome do filme\u001B[0m que deseja procurar: ", 64,224,208);

        String nome = new Scanner(System.in).nextLine();
        nome = nome.toLowerCase();

        boolean encontrado = false;

        for (Filme filme : filmesCadastrados){
            if (filme.getNome().toLowerCase().equals(nome)){
                filme.dadosFilme();
                encontrado = true;
                break;
            }
        }

        if(!encontrado)
            System.out.println("\u001B[3m\u001B[31m      Filme não foi encontrado\u001B[0m");

        mostrarMenu();
    }

    private void adicionarAtor(Filme filme){

        if(filme.atoresIguais(atoresCadastrados)){
            System.out.println("      \u001B[31mAtores já cadastrados\u001B[0m");
            return;
        }

        boolean adicionar = true;
        boolean escolhido = false;

        int id;
        String escolha;

        do{
            int i = 1;
            System.out.printf("      \u001B[34mAtores Disponíveis:\u001B[0m%n");
            for(Ator ator : atoresCadastrados){
                System.out.print("      " + i + " - " + ator.getNome() + "\n");
                i++;
            }

            do{
                System.out.printf("\n      Escolha o \u001B[38;2;%d;%d;%dmnúmero\u001B[0m do ator para adicionar: ", 64,224,208);

                try{
                    id = new Scanner(System.in).nextInt();
                    System.out.println();


                    if(id > i || id < 1)
                        System.out.println("      \u001B[31mOpção inválida\u001B[0m");

                    else{
                        if(filme.adicionarAtor(atoresCadastrados.get(id - 1)))
                            escolhido = true;
                        else
                            System.out.println("      \u001B[31mAtor cadastrado\u001B[0m\n");
                }

                }catch(Exception e) {
                    System.out.println("      \u001B[31mOpção inválida\u001B[0m\n");
                }
            }while (!escolhido);

            do{
                System.out.printf("      Deseja adicionar mais atores? (\u001B[38;2;%d;%d;%dm0\u001B[0m para não e \u001B[38;2;%d;%d;%dm1\u001B[0m para sim): ", 64,224,208, 64,224,208);
                escolha = new Scanner(System.in).nextLine();
                System.out.println();

            }while (!escolha.equals("0") && !escolha.equals("1"));

            if(escolha.equals("0"))
                adicionar = false;

        }while (adicionar);
        salvarItens();
    }

    private void adicionarDiretor(Filme filme){

        if(filme.diretoresIguais(diretoresCadastrados)){
            System.out.println("      \u001B[31mDiretores já cadastrados\u001B[0m");
            return;
        }

        boolean adicionar = true;
        boolean escolhido = false;

        int id;
        String escolha;

        do{
            int i = 1;
            System.out.printf("      \u001B[34mDiretores Disponíveis:\u001B[0m%n");
            for(Diretor diretor : diretoresCadastrados){
                System.out.print("      " + i + " - " + diretor.getNome() + "\n");
                i++;
            }

            do{
                System.out.printf("\n      Escolha o \u001B[38;2;%d;%d;%dmnúmero\u001B[0m do diretor para adicionar: ", 64,224,208);

                try{
                    id = new Scanner(System.in).nextInt();
                    System.out.println();


                    if(id > i || id < 0)
                        System.out.println("      \u001B[31mOpção inválida\u001B[0m");

                    else{
                        if(filme.adicionarDiretor(diretoresCadastrados.get(id - 1)))
                            escolhido = true;
                        else
                            System.out.println("      \u001B[31mDiretor cadastrado\u001B[0m");
                    }

                }catch(Exception e) {
                    System.out.println("      \u001B[31mOpção inválida\u001B[0m\n");
                }
            }while (!escolhido);

            do{
                System.out.printf("      Deseja adicionar mais diretores? (\u001B[38;2;%d;%d;%dm0\u001B[0m para não e \u001B[38;2;%d;%d;%dm1\u001B[0m para sim): ", 64,224,208, 64,224,208);
                escolha = new Scanner(System.in).nextLine();
                System.out.println();

            }while (!escolha.equals("0") && !escolha.equals("1"));

            if(escolha.equals("0"))
                adicionar = false;

        }while (adicionar);
        salvarItens();
    }

    private void mostrarItens(){

        System.out.printf("\u001B[38;2;%d;%d;%dm      Filmes: \u001B[0m%n", 255,215,0);
        for(Filme filme : filmesCadastrados){
            System.out.print("     - ");
            filme.mostrarFilme();
        }

        System.out.printf("\n\u001B[38;2;%d;%d;%dm      Atores: \u001B[0m%n", 255,215,0);
        for(Ator ator : atoresCadastrados){
            System.out.print("     - ");
            ator.mostrarAtor();
        }

        System.out.printf("\n\u001B[38;2;%d;%d;%dm      Diretores: \u001B[0m%n", 255,215,0);
        for(Diretor diretor : diretoresCadastrados){
            System.out.print("     - ");
            diretor.mostrarDiretor();
        }
        mostrarMenu();
    }

    private void editarFilme() {

        Filme filme = null;
        int id;
        boolean escolhido = false;

        int i = 1;
        System.out.printf("      \u001B[34mFilmes Disponíveis:\u001B[0m%n");
        for(Filme aux : filmesCadastrados){
            System.out.print("      " + i + " - " + aux.getNome() + "\n");
            i++;
        }

        do{
            System.out.printf("\n      Escolha o \u001B[38;2;%d;%d;%dmnúmero\u001B[0m do filme para editar: ", 64,224,208);

            try{
                id = new Scanner(System.in).nextInt();
                System.out.println();


                if(id < 1 || id > i)
                    System.out.println("      \u001B[31mOpção inválida\u001B[0m");

                else{
                    filme = filmesCadastrados.get(id-1);
                    escolhido = true;
                }

            }catch(Exception e) {
                System.out.println("      \u001B[31mOpção inválida \u001B[0m");
            }
        }while (!escolhido);


        System.out.println();
        System.out.printf("      \u001B[38;2;%d;%d;%dmEditar:\u001B[0m%n", 64, 224, 208);
        System.out.printf("      1 - \u001B[34mDados do Filme\u001B[0m%n");
        System.out.printf("      2 - \u001B[34mAdicionar Ator\u001B[0m%n");
        System.out.printf("      3 - \u001B[34mAdicionar Diretor\u001B[0m%n");
        System.out.printf("      4 - \u001B[34mRemover Ator\u001B[0m%n");
        System.out.printf("      5 - \u001B[34mRemover Diretor\u001B[0m%n");
        System.out.printf("      6 - \u001B[34mVoltar\u001B[0m%n");

        int opcao = verficaOpcaoMenu("6");

        switch (opcao) {
            case 1:
                editarDados(filme);
                mostrarMenu();
                break;
            case 2:
                adicionarAtor(filme);
                mostrarMenu();
                break;
            case 3:
                adicionarDiretor(filme);
                mostrarMenu();
                break;
            case 4:
                removerAtor(filme);
                mostrarMenu();
                break;
            case 5:
                removerDiretor(filme);
                mostrarMenu();
                break;
            case 6:
                mostrarMenu();
                break;
        }
    }

    private void removerAtor(Filme filme) {

        if(filme.getAtores().size() == 1){
            System.out.println("      \u001B[31mNão pode remover todos os atores\u001B[0m");
            mostrarMenu();
        }


        int id;

        int i = 1;
        System.out.printf("      \u001B[34mAtores Cadastrados:\u001B[0m%n");
        for(Ator ator : filme.getAtores()){
            System.out.print("      " + i + " - " + ator.getNome() + "\n");
            i++;
        }

        id = verificaOpcaoRemover(i);

        if (id != -1)
            filme.getAtores().remove(id - 1);

        salvarItens();
    }

    private void removerDiretor(Filme filme) {

        if(filme.getDiretores().size() == 1){
            System.out.println("      \u001B[31mNão pode remover todos os diretores\u001B[0m");
            mostrarMenu();
        }

        int id;

        int i = 1;
        System.out.printf("      \u001B[34mDiretores Cadastrados:\u001B[0m%n");
        for(Diretor diretor : filme.getDiretores()){
            System.out.print("      " + i + " - " + diretor.getNome() + "\n");
            i++;
        }

        id = verificaOpcaoRemover(i);

        if (id != -1)
            filme.getDiretores().remove(id-1);

        salvarItens();
    }

    private void editarDados(Filme filme) {
        String nome, genero, ano, nota;
        do{
            System.out.printf("      Digite o \u001B[38;2;%d;%d;%dmnome:\u001B[0m ", 64,224,208);
            nome = new Scanner(System.in).nextLine();

            System.out.printf("      Digite o \u001B[38;2;%d;%d;%dmgênero:\u001B[0m (composto somente por letras): ", 64,224,208);
            genero  = new Scanner(System.in).nextLine();

            System.out.printf("      Digite o \u001B[38;2;%d;%d;%dmano:\u001B[0m (no formato 2000): ", 64,224,208);
            ano = new Scanner(System.in).nextLine();

            System.out.printf("      Digite a \u001B[38;2;%d;%d;%dmnota:\u001B[0m (no formato 9.5): ", 64,224,208);
            nota = new Scanner(System.in).nextLine();

            System.out.println();

        }while(!filme.setFilme(nome, genero, ano, nota));
        salvarItens();
    }

    private int verficaOpcaoMenu(String limite){
        boolean opcaoInvalida = true;
        String escolha;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("\n      Escolha uma opção: ");
            escolha = scanner.nextLine();

            if (escolha.matches("[1-6]") || escolha.equals(limite)) {
                opcaoInvalida = false;
            } else {
                System.out.println("      \u001B[31mOpção inválida\u001B[0m");
            }

        } while (opcaoInvalida);

        System.out.println();

        return Integer.parseInt(escolha);
    }

    private int verificaOpcaoRemover(int i){

        boolean escolhido = false;
        int id = -1;

        do{
            System.out.printf("\n      Escolha o \u001B[38;2;%d;%d;%dmnúmero\u001B[0m para remover: ", 64,224,208);
            try{
                id = new Scanner(System.in).nextInt();
                System.out.println();


                if(id > i || id < 1)
                    System.out.println("      \u001B[31mOpção inválida\u001B[0m");

                else
                    escolhido = true;

            }catch(Exception e) {
                System.out.println("      \u001B[31mOpção inválida\u001B[0m\n");
            }

        }while (!escolhido);

        return id;
    }
}
