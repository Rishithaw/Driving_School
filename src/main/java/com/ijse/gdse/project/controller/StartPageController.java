package com.ijse.gdse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartPageController implements Initializable {

    @FXML
    private AnchorPane ancPane;

    @FXML
    private Button btnNext;

    @FXML
    private ImageView imgBackground;

    @FXML
    private ImageView imgDriver;

    @FXML
    void NextPageOnAction(ActionEvent event) throws IOException {
        ancPane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/SignUpPageView.fxml"));
        ancPane.getChildren().add(load);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        imgBackground.setPreserveRatio(true);  // Maintain aspect ratio
//        imgBackground.fitWidthProperty().bind(ancPane.widthProperty());  // Bind width to the root pane width
//        imgBackground.fitHeightProperty().bind(ancPane.heightProperty());

    }
}
