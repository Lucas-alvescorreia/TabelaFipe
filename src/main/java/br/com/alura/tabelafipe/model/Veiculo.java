package br.com.alura.tabelafipe.model;

import java.math.BigDecimal;

public class Veiculo {
    private BigDecimal valorVeiculo;
    private String marcaVeiculo;
    private String modeloVeiculo;
    private Integer anoVeiculo;
    private String tipoCombustivel;

    public Veiculo(DadosVeiculo dadosVeiculo) {
        this.valorVeiculo = new BigDecimal(dadosVeiculo.valor());
        this.marcaVeiculo = dadosVeiculo.marca();
        this.modeloVeiculo = dadosVeiculo.modelo();
        this.anoVeiculo = dadosVeiculo.anoModelo();
        this.tipoCombustivel = dadosVeiculo.combustivel();
    }

    public BigDecimal getValorVeiculo() {
        return valorVeiculo;
    }

    public void setValorVeiculo(BigDecimal valorVeiculo) {
        this.valorVeiculo = valorVeiculo;
    }

    public String getMarcaVeiculo() {
        return marcaVeiculo;
    }

    public void setMarcaVeiculo(String marcaVeiculo) {
        this.marcaVeiculo = marcaVeiculo;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public Integer getAnoVeiculo() {
        return anoVeiculo;
    }

    public void setAnoVeiculo(Integer anoVeiculo) {
        this.anoVeiculo = anoVeiculo;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }
}
