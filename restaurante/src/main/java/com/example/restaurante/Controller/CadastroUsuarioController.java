package com.example.restaurante.Controller;

import com.example.restaurante.DAO.UsuarioDAO;
import com.example.restaurante.Model.Usuario;
import com.example.restaurante.Utils.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CadastroUsuarioController implements Initializable {

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private Button bt_Voltar;

    @FXML
    private Button bt_cadastrarUsuario;

    @FXML
    private ImageView img_Cadastro;

    @FXML
    private TextField tf_Area;

    @FXML
    private TextField tf_CPF;

    @FXML
    private TextField tf_Cargo;

    @FXML
    private TextField tf_Matricula;

    @FXML
    private TextField tf_Nome;

    @FXML
    private TextField tf_Senha;

    @FXML
    void cadastraUsuario(ActionEvent event) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Alerts alerts = new Alerts();
        String nome = tf_Nome.getText().trim();
        String cpf = tf_CPF.getText().trim();
        String matricula = tf_Matricula.getText().trim();
        String cargo = tf_Cargo.getText().trim();
        String area = tf_Area.getText().trim();
        String senha = tf_Senha.getText().trim();

        if (nome.isEmpty() || cpf.isEmpty() || matricula.isEmpty() || cargo.isEmpty() || area.isEmpty() || senha.isEmpty()) {
            alerts.mostrarMensagemDeErro("Erro", null, "Todos os campos são obrigatórios.");
            return;
        }

        Usuario usuario = new Usuario("", senha, nome, cpf, cargo, matricula, area);

        Usuario cadastradoComSucesso = usuarioDAO.create(usuario);
        if (cadastradoComSucesso != null) {
            alerts.mostrarMensagem("Information", null, "Cadastro bem sucedido!");
            // Aqui você pode fechar a janela de cadastro ou fazer outra ação, se desejar
        } else {
            alerts.mostrarMensagemDeErro("Erro", null, "Cadastro falhou!");
        }
    }






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image imagemCadastro = null;
        try {
            imagemCadastro = new Image(new FileInputStream("src/main/resources/Image/cadastro.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        img_Cadastro.setImage(imagemCadastro);

    }
    @FXML
    private void abrirTelaLogin(){
            try {
                ((Stage) bt_Voltar.getScene().getWindow()).close(); // fecha a janela atual (Cadastro)
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/restaurante/Login.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}

