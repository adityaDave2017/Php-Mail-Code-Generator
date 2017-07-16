package com.phpmail.controller;

import com.phpmail.custom.EditingCell;
import com.phpmail.custom.NewTemplateDialog;
import com.phpmail.pojo.EmailData;
import com.phpmail.pojo.Field;
import com.phpmail.utils.Save;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;


@SuppressWarnings({"unused", "Duplicates"})
public class MainController {

    private static boolean isUnsaved = true;
    private static File saveFile = null;
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
    private TextField txtFieldSelectTemplate;
    @FXML
    private TextField txtFieldDisplayName;
    @FXML
    private TextField txtFieldFormFieldName;
    @FXML
    private MenuItem menuItemNewMailCode;
    @FXML
    private MenuItem menuItemNewTemplate;
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
    @FXML
    private TableView<Field> tableFields;
    @FXML
    private TableColumn<Field, String> tableColDisplayName;
    @FXML
    private TableColumn<Field, String> tableColFormFieldName;
    private File templateFile = null;

    @FXML
    public void initialize() {

        Platform.runLater(() -> {
            txtFieldDisplayName.getScene().getRoot().requestFocus();
            Stage primaryStage = (Stage) txtFieldSelectTemplate.getScene().getWindow();
            primaryStage.setOnCloseRequest(event -> {
                boolean trouble = tableFields.getItems().size() != 0;

                for (Node node : vBoxContainer.getChildren()) {
                    if (node instanceof TextField && !((TextField) node).getText().isEmpty()) {
                        node.setStyle("-fx-border-color: red");
                        trouble = true;
                    }
                }

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.getButtonTypes().clear();
                alert.getButtonTypes().setAll(ButtonType.NO, ButtonType.YES);
                if (tableFields.getItems().size() != 0 || trouble) {
                    alert.setContentText("Do you want to save before exit?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.YES) {
                        try {
                            if (!generatePhpCode()) {
                                Alert error = new Alert(Alert.AlertType.ERROR);
                                error.setContentText("Nothing to Save!");
                                error.setHeaderText(null);
                                error.showAndWait();
                            }
                        } catch (InterruptedException exc) {
                            exc.printStackTrace();
                        }
                    }
                } else {
                    alert.setContentText("Do you really want to exit?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.NO) {
                        event.consume();
                    }
                }
            });
        });

        txtFieldDisplayName.textProperty().addListener((observable, oldValue, newValue) -> {
            isUnsaved = true;
            txtFieldDisplayName.setStyle(null);
        });

        txtFieldFormFieldName.textProperty().addListener((observable, oldValue, newValue) -> {
            isUnsaved = true;
            txtFieldFormFieldName.setStyle(null);
        });

        txtFieldLogoUrl.textProperty().addListener(((observable, oldValue, newValue) -> {
            isUnsaved = true;
            txtFieldLogoUrl.setStyle(null);
        }));

        txtFieldSuccessUrl.textProperty().addListener((observable, oldValue, newValue) -> {
            isUnsaved = true;
            txtFieldSuccessUrl.setStyle(null);
        });

        txtFieldHomePage.textProperty().addListener((observable, oldValue, newValue) -> {
            isUnsaved = true;
            txtFieldHomePage.setStyle(null);
        });

        txtFieldGreetings.textProperty().addListener((observable, oldValue, newValue) -> {
            isUnsaved = true;
            txtFieldGreetings.setStyle(null);
        });

        txtFieldFromText.textProperty().addListener((observable, oldValue, newValue) -> {
            isUnsaved = true;
            txtFieldFromText.setStyle(null);
        });

        txtFieldFailureUrl.textProperty().addListener((observable, oldValue, newValue) -> {
            isUnsaved = true;
            txtFieldFailureUrl.setStyle(null);
        });

        txtFieldEmailTo.textProperty().addListener((observable, oldValue, newValue) -> {
            isUnsaved = true;
            if (!newValue.isEmpty()) {
                for (String emailId : newValue.split(",")) {
                    if (!emailId.matches("[\\w.\\d]+@(\\w)+\\.(\\w)+")) {
                        txtFieldEmailTo.setStyle("-fx-border-color: red");
                        break;
                    } else {
                        txtFieldEmailTo.setStyle(null);
                    }
                }
            } else {
                txtFieldEmailTo.setStyle(null);
            }
        });

        txtFieldEmailSubject.textProperty().addListener((observable, oldValue, newValue) -> {
            isUnsaved = true;
            txtFieldEmailSubject.setStyle(null);
        });

        txtFieldSelectTemplate.textProperty().addListener((observable, oldValue, newValue) -> txtFieldSelectTemplate.setStyle(null));

        txtFieldSelectTemplate.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                selectTemplate();
                (txtFieldDisplayName.getScene()).getRoot().requestFocus();
            }
        });

        tableColDisplayName.prefWidthProperty().bind(tableFields.widthProperty().divide(2).subtract(2));
        tableColDisplayName.setCellFactory(param -> new EditingCell());
        tableColDisplayName.setCellValueFactory(new PropertyValueFactory<>("displayName"));
        tableColDisplayName.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setDisplayName(event.getNewValue()));

        tableColFormFieldName.prefWidthProperty().bind(tableFields.widthProperty().divide(2));
        tableColFormFieldName.setCellFactory(param -> new EditingCell());
        tableColFormFieldName.setCellValueFactory(new PropertyValueFactory<>("formFieldName"));
        tableColFormFieldName.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setFormFieldName(event.getNewValue()));

        tableFields.getItems().addListener((ListChangeListener<Field>) c -> isUnsaved = true);
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

        ObservableList<Field> observableList = tableFields.getItems();
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

        tableFields.getItems().add(newField);
        txtFieldDisplayName.clear();
        txtFieldFormFieldName.clear();
    }


    @FXML
    public boolean generatePhpCode() throws InterruptedException {

        if (tableFields.getItems().size() == 0) {
            System.out.println("table fields");
            return false;
        }

        for (Node node : vBoxContainer.getChildren()) {
            if (node instanceof TextField && ((TextField) node).getText().isEmpty()) {
                node.setStyle("-fx-border-color: red");
                System.out.println(node.getId());
                return false;
            }
        }

        if (saveFile == null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PHP files (*.php)", "*.php");
            fileChooser.getExtensionFilters().add(extensionFilter);
            saveFile = fileChooser.showSaveDialog(null);
        }

        Thread saver = new Thread(() -> {

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

                Save.generatePhp(templateFile, saveFile, data, tableFields.getItems());
                isUnsaved = false;
            }
        });

        saver.start();
        saver.join();
        return true;
    }


    @FXML
    public void aboutClicked() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setGraphic(null);
        alert.setHeaderText(null);
        alert.setContentText("Application to help designers.\n\u00A9 Aditya Dave.");
        alert.show();
    }


    @FXML
    public void closeClicked() {
        boolean trouble = tableFields.getItems().size() != 0;
        for (Node node : vBoxContainer.getChildren()) {
            if (node instanceof TextField && !((TextField) node).getText().isEmpty()) {
                node.setStyle("-fx-border-color: red");
                trouble = true;
            }
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().setAll(ButtonType.NO, ButtonType.YES);
        if (tableFields.getItems().size() != 0 || trouble) {
            alert.setContentText("Do you want to save before exit?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                try {
                    if (!generatePhpCode()) {
                        Alert error = new Alert(Alert.AlertType.ERROR);
                        error.setContentText("Nothing to Save!");
                        error.setHeaderText(null);
                        error.showAndWait();
                    }
                } catch (InterruptedException exc) {
                    exc.printStackTrace();
                }
            }
            tableFields.getItems().clear();
            Platform.exit();
        } else {
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
                trouble = true;
            }
        }

        if (tableFields.getItems().size() != 0 || trouble) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to save work before clear?");
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(ButtonType.NO, ButtonType.YES);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {
                try {
                    if (!generatePhpCode()) {
                        Alert error = new Alert(Alert.AlertType.ERROR);
                        error.setContentText("Nothing to Save!");
                        error.setHeaderText(null);
                        error.showAndWait();
                    }
                } catch (InterruptedException exc) {
                    exc.printStackTrace();
                }
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
        tableFields.getItems().clear();
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


    @FXML
    void createNewTemplate() {
        try {
            NewTemplateDialog newTemplateDialog = new NewTemplateDialog();
            newTemplateDialog.showAndWait();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

}
