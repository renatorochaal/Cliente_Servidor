package com.example.restaurante.Controller;

import com.example.restaurante.DAO.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button bt_Entrar;

    @FXML
    private Button bt_NovoUsuario;

    @FXML
    private CheckBox ck_captchar;

    @FXML
    private ImageView img_captchar;

    @FXML
    private ImageView img_fundo;

    @FXML
    private TextField tf_Senha;

    @FXML
    private TextField tf_matricula;

    @FXML
    void abrirTelaCadastro(ActionEvent event) {
        try {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/restaurante/CadastroUsuario.fxml")));
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void abrirTelaCardapio(ActionEvent event) {
        if (validarCampos()) {
            String matricula = tf_matricula.getText();
            String senha = tf_Senha.getText();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            if (usuarioDAO.validarLogin(matricula, senha)) {
                try {
                    Node source = (Node) event.getSource();
                    Stage stage = (Stage) source.getScene().getWindow();
                    stage.close();

                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/restaurante/Cardapio.fxml")));
                    Scene scene = new Scene(root);
                    Stage newStage = new Stage();
                    newStage.setScene(scene);
                    newStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Matrícula ou senha inválidos!");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image imagemFundo;
        Image imagemCaptchar;
        try {
            imagemFundo = new Image(new FileInputStream("src/main/resources/Image/login.png"));
            imagemCaptchar = new Image(new FileInputStream("src/main/resources/Image/captchar.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        img_fundo.setImage(imagemFundo);
        img_captchar.setImage(imagemCaptchar);


    }

    private boolean validarCampos() {
        if (tf_matricula.getText().isEmpty() || tf_Senha.getText().isEmpty() || !ck_captchar.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os campos e marque o Captcha!");
            alert.showAndWait();
            return false;
        }
        return true;
    }
}
