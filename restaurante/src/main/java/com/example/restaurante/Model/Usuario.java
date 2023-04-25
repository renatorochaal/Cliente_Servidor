package com.example.restaurante.Model;

public class Usuario {
    private long id;
    private String usuario;
    private String senha;
    private String nome;
    private String cpf;
    private String cargo;
    private String matricula;
    private String area;

    public Usuario() {
    }

    public Usuario(String usuario, String senha, String nome, String cpf, String cargo, String matricula, String area) {
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.matricula = matricula;
        this.area = area;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", senha='" + senha + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", cargo='" + cargo + '\'' +
                ", matricula='" + matricula + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
