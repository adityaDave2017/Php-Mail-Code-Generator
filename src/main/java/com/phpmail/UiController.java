package com.phpmail;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;


@SuppressWarnings({"unused", "Duplicates"})
public class UiController {

    @FXML
    public Button btnAddField;
    @FXML
    public MenuItem menuItemNew;
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
    private TextField txtFieldSelectTemplate;
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
    private VBox vBoxContainer;


    private File templateFile;


    @FXML
    public void initialize() {

        Platform.runLater(() -> {
            (txtFieldDisplayName.getScene()).getRoot().requestFocus();
            Stage primaryStage = (Stage) txtFieldSelectTemplate.getScene().getWindow();
            primaryStage.setOnCloseRequest(event -> {
                if (lvFields.getItems().size() != 0) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.getButtonTypes().addAll(ButtonType.NO, ButtonType.YES);
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
            });
        });

        txtFieldDisplayName.textProperty().addListener((observable, oldValue, newValue) -> txtFieldDisplayName.setStyle(null));

        txtFieldFormFieldName.textProperty().addListener((observable, oldValue, newValue) -> txtFieldFormFieldName.setStyle(null));

        txtFieldLogoUrl.textProperty().addListener(((observable, oldValue, newValue) -> txtFieldLogoUrl.setStyle(null)));

        txtFieldSuccessUrl.textProperty().addListener((observable, oldValue, newValue) -> txtFieldSuccessUrl.setStyle(null));

        txtFieldHomePage.textProperty().addListener((observable, oldValue, newValue) -> txtFieldHomePage.setStyle(null));

        txtFieldGreetings.textProperty().addListener((observable, oldValue, newValue) -> txtFieldGreetings.setStyle(null));

        txtFieldFromText.textProperty().addListener((observable, oldValue, newValue) -> txtFieldFromText.setStyle(null));

        txtFieldFailureUrl.textProperty().addListener((observable, oldValue, newValue) -> txtFieldFailureUrl.setStyle(null));

        txtFieldEmailTo.textProperty().addListener((observable, oldValue, newValue) -> txtFieldEmailTo.setStyle(null));

        txtFieldEmailSubject.textProperty().addListener((observable, oldValue, newValue) -> txtFieldEmailSubject.setStyle(null));

        txtFieldSelectTemplate.textProperty().addListener((observable, oldValue, newValue) -> txtFieldSelectTemplate.setStyle(null));

        txtFieldSelectTemplate.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                selectTemplate();
                (txtFieldDisplayName.getScene()).getRoot().requestFocus();
            }
        });

    }


    @FXML
    public void addField() {

        boolean trouble = false;
        if (txtFieldDisplayName.getText().isEmpty()) {
            txtFieldDisplayName.setStyle("-fx-border-color: red");
            trouble = true;
        }

        if (txtFieldFormFieldName.getText().isEmpty()) {
            txtFieldFormFieldName.setStyle("-fx-border-color: red");
            trouble = true;
        }

        if (trouble) {
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


    @FXML
    public void generatePhpCode() {

        if (lvFields.getItems().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nothing to save!");
            alert.show();
            return;
        }

        boolean trouble = false;
        for (Node node : vBoxContainer.getChildren()) {
            if (node instanceof TextField && ((TextField) node).getText().isEmpty()) {
                node.setStyle("-fx-border-color: red");
                trouble = true;
            }
        }

        if (trouble) {
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PHP files (*.php)", "*.php");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File saveFile = fileChooser.showSaveDialog(null);

        if (saveFile != null) {

            if (!saveFile.getName().endsWith(".php")) {
                saveFile = new File(saveFile.getAbsolutePath() + ".php");
            }

            EmailData data = new EmailData();
            data.setEmailTo(txtFieldEmailTo.getText());
            data.setEmailSubject(txtFieldEmailSubject.getText());
            data.setCompanyLogoUrl(txtFieldLogoUrl.getText());
            data.setCompanyHomePage(txtFieldHomePage.getText());
            data.setGreetingsText(txtFieldGreetings.getText());
            data.setFromText(txtFieldFromText.getText());
            data.setSuccessUrl(txtFieldSuccessUrl.getText());
            data.setFailureUrl(txtFieldFailureUrl.getText());

            Util.generatePhp(templateFile, saveFile, data, lvFields.getItems());
        }

    }


    @FXML
    public void aboutClicked() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(null);
        alert.setHeaderText(null);
        alert.setContentText("Application to help designers.\n\u00A9 Aditya Dave.");
        alert.show();
    }


    @FXML
    public void closeClicked() {
        if (lvFields.getItems().size() != 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().addAll(ButtonType.NO, ButtonType.YES);
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


    @FXML
    public void clearClicked() {

        boolean trouble = false;
        for (Node node : vBoxContainer.getChildren()) {
            if (node instanceof TextField && !((TextField) node).getText().isEmpty()) {
                node.setStyle("-fx-border-color: red");
                trouble = true;
            }
        }

        if (lvFields.getItems().size() != 0 || trouble) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to save work before clear?");
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(ButtonType.NO, ButtonType.YES);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {
                generatePhpCode();
            }
        }

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
        txtFieldSelectTemplate.clear();
        lvFields.getItems().clear();
    }


    @FXML
    void selectTemplate() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a Template File");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Template File (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extensionFilter);
        templateFile = fileChooser.showOpenDialog(null);
        if (templateFile != null) {
            txtFieldSelectTemplate.setText(templateFile.getName());
        }
    }


}
