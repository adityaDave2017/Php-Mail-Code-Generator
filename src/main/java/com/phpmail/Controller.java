package com.phpmail;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;


@SuppressWarnings({"unused"})
public class Controller {

    @FXML
    public Button btnAddField;

    @FXML
    private TextField txtFieldEmailTo;

    @FXML
    private TextField txtFieldEmailSubject;

    @FXML
    private TextField txtFieldLogoUrl;

    @FXML
    private TextField txtFieldHomePage;

    @FXML
    private TextField txtFieldGreetings;

    @FXML
    private TextField txtFieldFromText;

    @FXML
    private TextField txtFieldSuccessUrl;

    @FXML
    private TextField txtFieldFailureUrl;

    @FXML
    private ListView<Field> lvFields;

    @FXML
    private TextField txtFieldDisplayName;

    @FXML
    private TextField txtFieldFormFieldName;

    @FXML
    private MenuItem menuItemGeneratePhp;

    @FXML
    private MenuItem menuItemClose;

    @FXML
    private MenuItem menuItemAbout;

    @FXML
    private Button btnClear;


    @FXML
    public void initialize() {

        Platform.runLater(() -> (txtFieldDisplayName.getScene()).getRoot().requestFocus());

        txtFieldDisplayName.textProperty().addListener((observable, oldValue, newValue) -> txtFieldDisplayName.setStyle(null));

        txtFieldFormFieldName.textProperty().addListener((observable, oldValue, newValue) -> txtFieldFormFieldName.setStyle(null));

    }


    public void addField() {

        if (txtFieldDisplayName.getText().isEmpty()) {
            txtFieldDisplayName.setStyle("-fx-border-color: red");
            txtFieldDisplayName.setPromptText("Enter display name...");
            return;
        }

        if (txtFieldFormFieldName.getText().isEmpty()) {
            txtFieldFormFieldName.setStyle("-fx-border-color: red");
            txtFieldFormFieldName.setPromptText("Enter form field name...");
            return;
        }

        Field newField = new Field();
        newField.setDisplayName(txtFieldDisplayName.getText());
        newField.setFormFieldName(txtFieldFormFieldName.getText());

        ObservableList<Field> observableList = lvFields.getItems();
        for (Field field : observableList) {
            if (field.compareTo(newField) == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Field Error");
                alert.setContentText("Entered field already exists!");
                alert.showAndWait();
                txtFieldDisplayName.clear();
                txtFieldFormFieldName.clear();
                return;
            }
        }

        lvFields.getItems().add(newField);
    }


    public void generatePhpCode() {
        // Todo: Show dialog to select save location and file name

        // Todo: create and save file on different thread

        System.out.println(lvFields.getItems());
        System.out.println("File Created...!");
    }


    public void aboutClicked() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(null);
        alert.setHeaderText(null);
        alert.setContentText("Application to help designers.\n\u00A9 Aditya Dave.");
        alert.show();
    }


    public void closeClicked() {
        if (lvFields.getItems().size() != 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().setAll(ButtonType.NO, ButtonType.YES);
            alert.setContentText("Do you want to save before exit?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                generatePhpCode();
            }
            lvFields.getItems().clear();
            Platform.exit();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().setAll(ButtonType.NO, ButtonType.YES);
            alert.setContentText("Do you really want to exit?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                Platform.exit();
            }
        }
    }


    public void clearClicked() {
        txtFieldDisplayName.clear();
        txtFieldFormFieldName.clear();
        txtFieldEmailSubject.clear();
        txtFieldEmailTo.clear();
        txtFieldFailureUrl.clear();
        txtFieldFromText.clear();
        txtFieldGreetings.clear();
        txtFieldHomePage.clear();
        txtFieldLogoUrl.clear();
        txtFieldSuccessUrl.clear();
    }


}
