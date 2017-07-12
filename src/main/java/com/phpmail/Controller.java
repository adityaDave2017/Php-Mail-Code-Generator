package com.phpmail;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


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
    public void initialize() {

        txtFieldDisplayName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                txtFieldDisplayName.setStyle(null);
            }
        });


        txtFieldFormFieldName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                txtFieldFormFieldName.setStyle(null);
            }
        });

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

        System.out.println("File Created...!");
    }


    public void aboutClicked() {
        System.out.println("Application to help designers. \u00A9 Aditya Dave.");
    }


    public void closeClicked() {
        // Todo: Look for unsaved work

        // afterwards exit
        Platform.exit();
    }


}
