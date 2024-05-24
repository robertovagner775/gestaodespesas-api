package com.roberto.gestaodespesas.model;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public class Despesa {
    

    private int id;
    private String descricao;
    private Double valor;
    private Categoria categoria;
    private LocalDate data;

    public int getId() {
        return id;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = Categoria.valueOf(categoria);
    }


}
