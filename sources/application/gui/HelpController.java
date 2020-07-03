package application.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelpController {

    @FXML
    private Button CloseButton;

    @FXML
    void Close(){
        Stage stage = (Stage)CloseButton.getScene().getWindow();
        stage.close();
    }
}
