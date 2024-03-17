package Persistencia;

import Veiculo.*;
import Cliente.*;

import java.io.*;
import java.util.*;

public class Arquivo {
    private static final HashSet<Veiculo> veiculos = new HashSet<>();
    private static final HashSet<ClienteCPF> clientesCPF = new HashSet<>();
    private static final HashSet<ClienteCNPJ> clientesCNPJ = new HashSet<>();

    public static void leArquivo() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/Persistencia/Arquivo.txt"))) {
            String linha;
            String tipoAtual = "";
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Clientes") || linha.startsWith("Veiculos")) {
                    tipoAtual = linha.trim();
                } else if (linha.startsWith("-")) {
                    String[] partes = linha.split("-");
                    if (partes.length >= 2) {
                        String tipo = tipoAtual.trim();
                        Map<String, String> atributos = new HashMap<>();
                        for (int i = 1; i < partes.length; i++) {
                            String[] atributo = partes[i].split(":");
                            if (atributo.length == 2) {
                                atributos.put(atributo[0].trim(), atributo[1].trim());
                            }
                        }
                        switch (tipo) {
                            case "Clientes":
                                if (atributos.containsKey("CPF") && atributos.containsKey("Nome")) {
                                    clientesCPF.add(new ClienteCPF(atributos.get("CPF"), atributos.get("Nome")));
                                } else if (atributos.containsKey("CNPJ") && atributos.containsKey("Empresa")) {
                                    clientesCNPJ.add(new ClienteCNPJ(atributos.get("CNPJ"), atributos.get("Empresa")));
                                } else {
                                    System.out.println("Informações de cliente incompletas.");
                                }
                                break;
                            case "Veiculos":
                                if (atributos.containsKey("Tipo") && atributos.containsKey("Placa") && atributos.containsKey("Cor")) {
                                    String tipoVeiculo = atributos.get("Tipo");
                                    String placa = atributos.get("Placa");
                                    String cor = atributos.get("Cor");
                                    switch (tipoVeiculo) {
                                        case "Médio":
                                            veiculos.add(new VeiculoMedio(placa, cor));
                                            break;
                                        case "Pequeno":
                                            veiculos.add(new VeiculoPequeno(placa, cor));
                                            break;
                                        case "SUV":
                                            veiculos.add(new VeiculoSUV(placa, cor));
                                            break;
                                        default:
                                            System.out.println("Tipo de veículo inválido: " + tipoVeiculo);
                                            break;
                                    }
                                } else {
                                    System.out.println("Informações de veículo incompletas.");
                                }
                                break;
                            default:
                                System.out.println("Seção inválida: " + tipo);
                                break;
                        }
                    } else {
                        System.out.println("Linha inválida: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro de arquivo leitura");
        }
    }

    public static void escreveArquivo() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/Persistencia/Arquivo.txt"))) {
            bw.write("Clientes");
            bw.newLine();
            for (Cliente cliente : clientesCPF) {
                bw.write("- " + cliente.mostrarDados());
                bw.newLine();
            }
            for (Cliente cliente : clientesCNPJ) {
                bw.write("- " + cliente.mostrarDados());
                bw.newLine();
            }

            bw.write("Veiculos");
            bw.newLine();
            for (Veiculo veiculo : veiculos) {
                bw.write("- " + veiculo.mostrarDados());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro de arquivo escrita");
        }
    }

    public static HashSet<String> getCPFs() {
        HashSet<String> CPFs = new HashSet<>();
        for (ClienteCPF clienteCPF : clientesCPF)
            CPFs.add(clienteCPF.getCPF());
        return CPFs;
    }

    public static HashSet<String> getCNPJs() {
        HashSet<String> CNPJs = new HashSet<>();
        for (ClienteCNPJ clienteCNPJ : clientesCNPJ)
            CNPJs.add(clienteCNPJ.getCNPJ());
        return CNPJs;
    }

    public static HashSet<String> getPlacas() {
        HashSet<String> placas = new HashSet<>();
        for (Veiculo veiculo : veiculos)
            placas.add(veiculo.getPlaca());
        return placas;
    }

    public static ArrayList<Veiculo> getVeiculos() {
        return new ArrayList<>(veiculos);
    }

    public static ArrayList<Cliente> getClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.addAll(clientesCPF);
        clientes.addAll(clientesCNPJ);
        return clientes;
    }
}
