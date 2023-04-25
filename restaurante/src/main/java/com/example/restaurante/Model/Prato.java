package com.example.restaurante.Model;

public class Prato {
    private int id;
    private String nome;
    private double preco;
    private int serveQuantasPessoas;
    private String ingredientes;

    public Prato(int id, String nome, double preco, int serveQuantasPessoas, String ingredientes) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.serveQuantasPessoas = serveQuantasPessoas;
        this.ingredientes = ingredientes;
    }

    public Prato() {
    }

    public Prato( String nome, double preco, int serveQuantasPessoas, String ingredientes) {
        this.nome = nome;
        this.preco = preco;
        this.serveQuantasPessoas = serveQuantasPessoas;
        this.ingredientes = ingredientes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getServeQuantasPessoas() {
        return serveQuantasPessoas;
    }

    public void setServeQuantasPessoas(int serveQuantasPessoas) {
        this.serveQuantasPessoas = serveQuantasPessoas;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public String toString() {
        return "Prato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", serveQuantasPessoas=" + serveQuantasPessoas +
                ", ingredientes='" + ingredientes + '\'' +
                '}';
    }
}
