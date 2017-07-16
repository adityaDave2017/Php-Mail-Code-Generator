package com.phpmail.controller;


import com.phpmail.pojo.FormatTemplate;
import com.phpmail.utils.Save;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;


@SuppressWarnings({"unused"})
public class NewTemplateController {

    private static boolean isUnsaved = true;
    private static File saveFile = null;
    @FXML
    private VBox vBoxContainer;
    @FXML
    private TextArea textAreaHead;
    @FXML
    private TextArea textAreaBody;
    @FXML
    private TextArea textAreaTail;
    @FXML
    private TextArea textAreaHeaders;
    @FXML
    private TextArea textAreaMailCode;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            textAreaHead.getScene().getRoot().requestFocus();

            /*Window window = textAreaHead.getScene().getWindow();
            window.setOnCloseRequest(event -> window.hide());*/

            Stage stage = (Stage) textAreaHead.getScene().getWindow();
            stage.setOnCloseRequest(event -> {
                if (isUnsaved) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("Do you want to save before exit?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.YES) {
                        saveClicked();
                    }
                } else {
                    stage.close();
                }
                event.consume();
            });
        });

        textAreaHead.textProperty().addListener((observable, oldValue, newValue) -> {
            isUnsaved = true;
            textAreaHead.setStyle(null);
        });

        textAreaBody.textProperty().addListener((observable, oldValue, newValue) -> {
            isUnsaved = true;
            if (!(newValue.contains("$$DISPLAY_NAME$$") && newValue.contains("$$FIELD_VAR_NAME$$"))) {
                textAreaBody.setStyle("-fx-border-color: red");
            } else {
                textAreaBody.setStyle(null);
            }
        });

        textAreaTail.textProperty().addListener((observable, oldValue, newValue) -> {
            isUnsaved = true;
            textAreaTail.setStyle(null);
        });

        textAreaHeaders.textProperty().addListener((observable, oldValue, newValue) -> {
            isUnsaved = true;
            textAreaHeaders.setStyle(null);
        });

        textAreaMailCode.textProperty().addListener((observable, oldValue, newValue) -> {
            isUnsaved = true;
            textAreaMailCode.setStyle(null);
        });
    }


    @FXML
    public void clearClicked() {
        textAreaHead.clear();
        textAreaBody.clear();
        textAreaTail.clear();
        textAreaHeaders.clear();
        textAreaMailCode.clear();
    }


    @FXML
    public void saveClicked() {

        boolean trouble = false;
        for (Node node : vBoxContainer.getChildren()) {
            if (node instanceof TextArea && ((TextArea) node).getText().isEmpty()) {
                node.setStyle("-fx-border-color: red");
                trouble = true;
            }
        }

        if (trouble) {
            return;
        }

        FormatTemplate formatTemplate = new FormatTemplate();
        formatTemplate.setHead(textAreaHead.getText());
        formatTemplate.setBody(textAreaBody.getText());
        formatTemplate.setTail(textAreaTail.getText());
        formatTemplate.setHeaders(textAreaHeaders.getText());
        formatTemplate.setMailCode(textAreaMailCode.getText());

        if (saveFile == null) {
            FileChooser saveFileChooser = new FileChooser();
            saveFileChooser.setTitle("Save Template");
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Template file (*.json)", "*.json");
            saveFileChooser.getExtensionFilters().add(extensionFilter);
            saveFile = saveFileChooser.showSaveDialog(null);

            if (!saveFile.getName().endsWith(".json")) {
                saveFile = new File(saveFile.getAbsolutePath() + ".json");
                ((Stage) textAreaHead.getScene().getWindow()).setTitle(textAreaHead.getScene().getWindow() + " - " + saveFile.getAbsolutePath());
            }

        }

        Thread thread = new Thread(() -> {
            Save.saveTemplate(saveFile, formatTemplate);
            isUnsaved = false;
        });

        try {
            thread.join();
            thread.start();
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }

    }

}
