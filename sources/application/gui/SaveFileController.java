package application.gui;

        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextField;
        import javafx.stage.FileChooser;
        import javafx.stage.Stage;

        import java.io.File;

public class SaveFileController {

    @FXML
    private Button CancelButton;

    @FXML
    private Button ConfirmButton;

    @FXML
    private TextField TextField;

    @FXML
    void Confirm(){
        Stage stage = (Stage)ConfirmButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Cancel(){
        Stage stage = (Stage)CancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void ChooseDirectory(){
        final FileChooser fileChooser = new FileChooser();
        TextField.clear();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Graph", "*.graph"));

        File file = fileChooser.showSaveDialog((Stage)TextField.getScene().getWindow());
        if (file != null) {
            TextField.setText(file.getPath());
        }
    }
}

