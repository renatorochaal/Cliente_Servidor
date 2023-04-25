package com.example.restaurante.Controller;

import com.example.restaurante.DAO.PratoDAO;
import com.example.restaurante.Model.Prato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CardapioController implements Initializable {

    @FXML
    private Button bt_CadastrarPratos;

    @FXML
    private Button bt_ExibirPratos;

    @FXML
    private Button bt_Sair;
    @FXML
    private Button bt_LimparList;

    @FXML
    private ImageView img_Direita;

    @FXML
    private ImageView img_Esqueda;

    @FXML
    private ListView<String> lt_ListaPratos;

    @FXML
    void LimparListView(ActionEvent event) {
        lt_ListaPratos.getItems().clear();

    }

    @FXML
    void abrirTelaCadastroPratos(ActionEvent event) {
        try {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(getClass().getResource("/com/example/restaurante/CadastroPratos.fxml"));
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exibirPratos(ActionEvent event) {
        lt_ListaPratos.getItems().clear(); // Limpa a lista de pratos para exibição atualizada
        PratoDAO pratoDAO = new PratoDAO();
        List<Prato> pratos = pratoDAO.findAll();
        for (Prato prato : pratos) {
            StringBuilder sb = new StringBuilder();
            sb.append(prato.getNome()).append(" - R$ ").append(prato.getPreco()).append(" - Serve ")
                    .append(prato.getServeQuantasPessoas()).append(" pessoas\nIngredientes: ").append(prato.getIngredientes());
            lt_ListaPratos.getItems().add(sb.toString());
        }
    }

    @FXML
    void fecharPrograma(ActionEvent event) {
        Stage stage = (Stage) bt_Sair.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Image imagemDireita = null;
        Image imagemEsquerda = null;
        try {
            imagemDireita = new Image(new FileInputStream("src/main/resources/Image/TelaDireita.png"));
            imagemEsquerda = new Image(new FileInputStream("src/main/resources/Image/TelaEsquerda.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        img_Direita.setImage(imagemDireita);
        img_Esqueda.setImage(imagemEsquerda);
        PratoDAO pratoDAO = new PratoDAO();
        List<Prato> pratos = pratoDAO.findAll();
        for (Prato prato : pratos) {
            lt_ListaPratos.getItems().add(prato.getNome());
        }

        bt_LimparList.setOnAction(event -> {
            lt_ListaPratos.getItems().clear();
        });
    }

}
