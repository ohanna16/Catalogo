package br.senai.rn.catalogodeprodutos2.model;

import java.io.Serializable;
import java.util.Objects;

public class Produto implements Serializable, Comparable<Produto> {

    private Long id;
    private String nome;
    private String preco;
    private String fabricante;

    public Produto() {
    }

    public Produto( String nome, String preco, String fabricante) {
        this.nome = nome;
        this.preco = preco;
        this.fabricante = fabricante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int compareTo(Produto produto){
        return nome.compareTo(produto.nome);
    }

}
