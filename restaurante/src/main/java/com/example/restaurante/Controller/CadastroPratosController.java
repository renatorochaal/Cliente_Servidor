package com.example.restaurante.Controller;

import com.example.restaurante.DAO.PratoDAO;
import com.example.restaurante.Model.Prato;
import com.example.restaurante.Utils.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class CadastroPratosController implements Initializable {

    private PratoDAO pratoDAO = new PratoDAO();

    public void setPratoDAO(PratoDAO pratoDAO){
        this.pratoDAO = pratoDAO;
    }

    @FXML
    private Button bt_Cadastrar;

    @FXML
    private Button bt_Sair;
    @FXML
    private ImageView img_CadastroPratos;

    @FXML
    private TextField tf_Ingrediente;

    @FXML
    private TextField tf_Valor;

    @FXML
    private TextField tf_nomePrato;

    @FXML
    private TextField tf_servePessoa;

    @FXML
    void CadastraOsPratos(ActionEvent event) {
        PratoDAO pratoDAO = new PratoDAO();
        Alerts alerts = new Alerts();
        String nomePrato = tf_nomePrato.getText().trim();
        String valorStr = tf_Valor.getText().trim();
        String servePessoaStr = tf_servePessoa.getText().trim();
        String ingrediente = tf_Ingrediente.getText().trim();

        if (nomePrato.isEmpty() || valorStr.isEmpty() || servePessoaStr.isEmpty() || ingrediente.isEmpty()) {
            alerts.mostrarMensagemDeErro("Erro", null, "Todos os campos são obrigatórios.");
            return;
        }

        double valor = Double.parseDouble(valorStr);
        int servePessoa = Integer.parseInt(servePessoaStr);

        Prato prato = new Prato(nomePrato, valor, servePessoa, ingrediente);

        Prato cadastradoComSucesso = pratoDAO.create(prato);
        if (cadastradoComSucesso != null) {
            alerts.mostrarMensagem("Information", null, "Cadastro de prato bem sucedido!");
            // Aqui você pode atualizar a tabela de pratos na tela de exibir pratos ou fazer outra ação, se desejar
        } else {
            alerts.mostrarMensagemDeErro("Erro", null, "Cadastro de prato falhou!");
        }
    }


    @FXML
    void VoltaParaCardapio(ActionEvent event) {
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

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        PratoDAO pratoDAO = new PratoDAO();
        setPratoDAO(pratoDAO);

        Image imagemCadastro = null;
        try {
            imagemCadastro = new Image(new FileInputStream("src/main/resources/Image/CadastroPratos.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        img_CadastroPratos.setImage(imagemCadastro);
    }
}
