package com.phpmail.custom;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class NewTemplateDialog extends Dialog {

    public NewTemplateDialog() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/NewTemplateUi.fxml"));

        Pane pane = fxmlLoader.load();

        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.initStyle(StageStyle.UTILITY);

        getDialogPane().setContent(pane);
    }

}
