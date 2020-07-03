package application.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    int CountOfStep = 10;
    int CurrentStep = 1;

    @FXML
    public MenuItem NewButton;

    @FXML
    public MenuItem OpenFileButton;

    @FXML
    public MenuItem SaveButton;

    @FXML
    public MenuItem QuitButton;

    @FXML
    public MenuItem HelpButton;

    @FXML
    public AnchorPane AnchorPane;

    @FXML
    public Button NextStepButton;

    @FXML
    public Button PrevStepButton;

    @FXML
    public Label StepNumberField;

    @FXML
    public ListView<?> StackList;

    @FXML
    public Pane GraphPane;

    @FXML
    void initNewFileWindow(){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("NewFile.fxml"));
            Stage stage = new Stage();
            stage.setTitle("New file");
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner((Stage)GraphPane.getScene().getWindow());
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initNewHelpWindow(){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("help.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Help");
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner((Stage)GraphPane.getScene().getWindow());
            stage.setScene(new Scene(root, 600, 425));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initOpenFileWindow(){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("OpenFile.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Open file");
            stage.setResizable(true);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner((Stage)GraphPane.getScene().getWindow());
            stage.setScene(new Scene(root, 400, 150));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initSaveFileWindow(){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("SaveFile.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Open file");
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner((Stage)GraphPane.getScene().getWindow());
            stage.setScene(new Scene(root, 400, 150));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleCloseButtonAction(){
        Stage stage = (Stage)GraphPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void nextstep(){
        if (CurrentStep < CountOfStep){
            CurrentStep = CurrentStep + 1;
        }
        StepNumberField.setText("   Шаг " + CurrentStep + "/" + CountOfStep);
    }

    @FXML
    void prevstep(){
        if (CurrentStep > 1){
            CurrentStep = CurrentStep - 1;
        }
        StepNumberField.setText("   Шаг " + CurrentStep + "/" + CountOfStep);
    }
}