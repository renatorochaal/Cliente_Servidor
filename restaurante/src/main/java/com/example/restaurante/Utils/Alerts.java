package com.example.restaurante.Utils;

import javafx.scene.control.Alert;

public class Alerts {

    public void mostrarMensagem(String titulo, String cabecalho, String conteudo) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(conteudo);
        alert.show();
    }

    public void mostrarMensagemDeErro(String titulo, String cabecalho, String conteudo) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(conteudo);
        alert.show();
    }
}
