package br.com.alura.tabelafipe.principal;

import br.com.alura.tabelafipe.model.*;
import br.com.alura.tabelafipe.service.ConsumoApi;
import br.com.alura.tabelafipe.service.ConverteDados;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;
import java.util.stream.Collectors;


public class Principal {
    private Scanner scan = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu() throws JsonProcessingException {
        System.out.println("""
                *** OPÇÕES ***
                Carro
                Moto
                Caminhão
                                
                Digite uma das opções para consultar valores:""");

        var tipoVeiculo = scan.next();
        String endereco = null;

        if (tipoVeiculo.toLowerCase().contains("carr")) {
            endereco = URL_BASE + "carros/marcas";
        } else if (tipoVeiculo.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motos/marcas";
        } else if (tipoVeiculo.toLowerCase().contains("camin")) {
            endereco = URL_BASE + "caminhoes/marcas";
        } else {
            System.out.println("Tipo de veiculo Incorreto!");
        }
        BuscaMarca(endereco);

        System.out.println("Informe o código da marca para consulta:");
        var leitorMarca = scan.nextInt();
        scan.nextLine();
        ListarModelos(endereco, leitorMarca);

        System.out.println("Digite o código do modelo para buscar os valores do Veiculo: ");
        var codigoModelo = scan.next();
        ListarVeiculos(endereco, leitorMarca, codigoModelo);
    }

    public void BuscaMarca(String endereco) {
        var json = consumoApi.obterDados(endereco);

        var marcas = conversor.obterLista(json, Dados.class);
        marcas.stream().sorted(Comparator.comparing(Dados::codigo));
        marcas.forEach(m -> System.out.println("Cód: " + m.codigo() + "    " + "Descrição: " + m.nome()));
    }

    public void ListarModelos(String endereco, Integer codigoMarca) {
        endereco = endereco + "/" + codigoMarca + "/modelos";
        var json = consumoApi.obterDados(endereco);
        var modelosLista = conversor.obterDados(json, Modelo.class);
        System.out.println("\nModelos dessa marca: ");
        modelosLista.modelos().forEach(l -> System.out.println("Cód: " + l.codigo() + "    " + "Descrição: " + l.nome()));

        System.out.println("Digite um trecho do nome do veículo para consulta:");
        var trechoCarro = scan.next();
        List<Dados> modelosBuscados = modelosLista.modelos()
                .stream().filter(b -> b.nome().toLowerCase().contains(trechoCarro.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("Modelos Filtrados: ");
        modelosBuscados.forEach(l -> System.out.println("Cód: " + l.codigo() + "    " + "Descrição: " + l.nome()));
    }

    public void ListarVeiculos(String endereco, Integer codigoMarca, String codigoModelo) {
        endereco = endereco + "/" + codigoMarca + "/modelos/" + codigoModelo + "/anos";
        var json = consumoApi.obterDados(endereco);


        System.out.println("----------------------------------------------------------------");
        List<Dados> anos = conversor.obterLista(json, Dados.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            json = consumoApi.obterDados(enderecoAnos);
            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }
        System.out.println("Todos os Veiculos filtrados");
        veiculos.forEach(System.out::println);
    }
}

