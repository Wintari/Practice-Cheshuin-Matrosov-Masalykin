package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NewFileController {

    @FXML
    private Button FirstTool;

    @FXML
    private Button SecondTool;

    @FXML
    private Button ThirdTool;

    @FXML
    private Button CancelButton;

    @FXML
    private Button OkButton;

    @FXML
    private Pane WorkSpace;

    @FXML
    void Confirm(){
        Stage stage = (Stage)OkButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Cancel(){
        Stage stage = (Stage)CancelButton.getScene().getWindow();
        stage.close();
    }
}
