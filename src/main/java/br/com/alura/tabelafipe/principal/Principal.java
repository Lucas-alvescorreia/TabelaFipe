package br.com.alura.tabelafipe.principal;

import br.com.alura.tabelafipe.service.ConsumoApi;


public class Principal {
    public void exibeMenu(){
        ConsumoApi consumoApi = new ConsumoApi();
        var json = consumoApi.obterDados("https://parallelum.com.br/fipe/api/v1/carros/marcas");
        System.out.println(json);
    }
}

