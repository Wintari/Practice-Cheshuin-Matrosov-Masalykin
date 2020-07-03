package application.gui;

import application.Application;
import application.algorithm.Graph;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NewFileController {
    private final Graph graph = new Graph();

    @FXML
    private Button firstTool;

    @FXML
    private Button secondTool;

    @FXML
    private Button thirdTool;

    @FXML
    private Button cancelButton;

    @FXML
    private Button okButton;

    @FXML
    private Pane workSpace;

    @FXML
    void Confirm(){
        Stage stage = (Stage)okButton.getScene().getWindow();

        Application.setNewGraph(graph);

        stage.close();
    }

    @FXML
    void Cancel(){
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }
}
