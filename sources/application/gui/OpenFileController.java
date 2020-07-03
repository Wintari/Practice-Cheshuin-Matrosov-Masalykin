package application.gui;

import application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;

public class OpenFileController {

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField textField;

    @FXML
    void Confirm(){
        Stage stage = (Stage)confirmButton.getScene().getWindow();

        Application.loadGraphFromFile(textField.getText());

        stage.close();
    }

    @FXML
    void Cancel(){
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void ChooseFile(){
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Graph", "*.graph"));
        textField.clear();
        File file = fileChooser.showOpenDialog((Stage)textField.getScene().getWindow());
        if (file != null) {
            textField.setText(file.getAbsolutePath());
        }
    }
}
