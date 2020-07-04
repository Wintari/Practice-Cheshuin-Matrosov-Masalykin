package application.gui;

        import application.Application;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextField;
        import javafx.stage.FileChooser;
        import javafx.stage.Stage;

        import java.io.File;

public class SaveFileController {

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField textField;

    @FXML
    void Confirm(){
        Stage stage = (Stage)confirmButton.getScene().getWindow();

        Application.saveGraphToFile(textField.getText());

        stage.close();
    }

    @FXML
    void Cancel(){
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void ChooseDirectory(){
        final FileChooser fileChooser = new FileChooser();
        textField.clear();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Graph", "*.graph"));

        File file = fileChooser.showSaveDialog((Stage)textField.getScene().getWindow());
        if (file != null) {
            textField.setText(file.getPath());
        }
    }
}

