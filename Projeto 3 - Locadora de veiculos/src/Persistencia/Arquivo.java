package Persistencia;

import Veiculo.*;
import Cliente.*;

import java.io.*;
import java.util.*;

public class Arquivo {
    private static HashSet<Veiculo> veiculos = new HashSet<>();
    private static HashSet<ClienteCPF> clientesCPF = new HashSet<>();
    private static HashSet<ClienteCNPJ> clientesCNPJ = new HashSet<>();

    Arquivo() {
        leArquivo();
    }

    public void leArquivo() {
        try (BufferedReader br = new BufferedReader(new FileReader("Arquivo.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {

                String[] partes = linha.split(":");

                if (partes.length == 2) {
                    String tipo = partes[0].trim();
                    String valor = partes[1].trim();

                    switch (tipo) {
                        case "CPF":
                            clientesCPF.add(new ClienteCPF(valor));
                            break;

                        case "CNPJ":
                            clientesCNPJ.add(new ClienteCNPJ(valor));
                            break;

                        case "Veiculos":
                            String[] veiculoPartes = valor.split(":");

                            if (veiculoPartes.length == 2) {
                                String tipoVeiculo = veiculoPartes[0].trim();
                                String placa = veiculoPartes[1].trim();

                                switch (tipoVeiculo) {
                                    case "Pequeno":
                                        veiculos.add(new VeiculoPequeno(placa));
                                        break;

                                    case "Médio":
                                        veiculos.add(new VeiculoMedio(placa));
                                        break;

                                    case "SUV":
                                        veiculos.add(new VeiculoSUV(placa));
                                        break;

                                    default:
                                        System.out.println("Tipo de veículo inválido: " + tipoVeiculo);
                                        break;
                                }
                            } else {
                                System.out.println("Formato inválido para veículo: " + valor);
                            }
                            break;
                        default:
                            System.out.println("Tipo inválido: " + tipo);
                            break;
                    }
                } else {
                    System.out.println("Linha inválida: " + linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro de arquivo leitura");
        }
    }

    public void escreveArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Arquivo.txt"))) {

            bw.write("Clientes:");

            for (ClienteCPF cliente : clientesCPF) {
                bw.write(cliente.mostrarDados());
                bw.newLine();
            }

            for (ClienteCNPJ cliente : clientesCNPJ) {
                bw.write(cliente.mostrarDados());
                bw.newLine();
            }

            bw.write("Veiculos:");

            for (Veiculo veiculo : veiculos) {

                if (veiculo instanceof VeiculoPequeno) {
                    bw.write(veiculo.mostrarDados());
                    bw.newLine();
                } else if (veiculo instanceof VeiculoMedio) {
                    bw.write(veiculo.mostrarDados());
                    bw.newLine();
                } else if (veiculo instanceof VeiculoSUV) {
                    bw.write(veiculo.mostrarDados());
                    bw.newLine();
                }
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
