package application.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelpController {

    @FXML
    private Button closeButton;

    @FXML
    void Close(){
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
    }
}
