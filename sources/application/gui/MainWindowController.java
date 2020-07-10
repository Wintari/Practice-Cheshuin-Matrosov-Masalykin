package application.gui;

import application.Application;
import application.graphviz.Proba;

import application.stepper.EdgeAction;
import application.stepper.NodeAction;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainWindowController {

    int countOfStep = 10;
    int currentStep = 1;

    boolean timerFlag = false;

    @FXML
    public MenuItem newButton;

    @FXML
    public MenuItem openFileButton;

    @FXML
    public MenuItem saveButton;

    @FXML
    public MenuItem quitButton;

    @FXML
    public MenuItem helpButton;

    @FXML
    public AnchorPane anchorPane;

    @FXML
    public Button nextStepButton;

    @FXML
    public Button prevStepButton;

    @FXML
    public Label stepNumberField;

    @FXML
    public ListView<String> stackList;

    @FXML
    public Pane graphPane;

    @FXML
    private ImageView GraphView;


    @FXML
    private Button ToEndButton;

    @FXML
    private Button ToStartButton;

    @FXML
    private Button PlayButton;

    final AnimationTimer timer = new AnimationTimer() {

        private long lastUpdate = 0;

        @Override
        public void handle(long time) {
            if ((time - this.lastUpdate) > 750_000_000) {
                if(currentStep >= countOfStep - 1){
                    Play();
                }
                else{
                    nextStep();
                }
                this.lastUpdate = time;
            }
        }
    };

    @FXML
    void GoToEnd() {
        if(!timerFlag) {
            if (currentStep < countOfStep - 1) {
                currentStep = countOfStep - 1;
                ArrayList<String> stack = Application.stepper.lastStep();
                ObservableList<String> observableStack = FXCollections.observableArrayList(stack);
                stackList.setItems(observableStack);
                reInit();
            }
        }
    }

    @FXML
    void GoToStart() {
        if(!timerFlag) {
            if (currentStep > 1) {
                currentStep = 1;
                ArrayList<String> stack = Application.stepper.firstStep();
                ObservableList<String> observableStack = FXCollections.observableArrayList(stack);
                stackList.setItems(observableStack);
                reInit();
            }
        }
    }

    @FXML
    void Play() {
        if(!timerFlag){
            timer.start();
            PlayButton.setText("STOP");
        }
        else {
            timer.stop();
            PlayButton.setText("PLAY");
        }
        timerFlag = !timerFlag;
    }

    void setCountOfStep(){
        this.countOfStep = Application.stepper.getStepCount() + 1;
    }

    @FXML
    void initialize(){
        setCountOfStep();
        currentStep = 1;
        stepNumberField.setText("   Шаг " + currentStep + "/" + (countOfStep - 1));
        try {
            printImg();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    void reInit(){
        stepNumberField.setText("   Шаг " + currentStep + "/" +  (countOfStep - 1));
        try {
            printImg();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void initNewFileWindow(){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("NewFile.fxml"));
            Stage stage = new Stage();
            stage.setTitle("New file");
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner((Stage)graphPane.getScene().getWindow());
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
            stage.setOnHiding( event -> {
                initialize();
            });
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initNewHelpWindow(){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Help.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Help");
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner((Stage)graphPane.getScene().getWindow());
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
            stage.initOwner((Stage)graphPane.getScene().getWindow());
            stage.setScene(new Scene(root, 400, 150));
            stage.show();
            stage.setOnHiding( event -> {
                initialize();
            });
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
            stage.initOwner((Stage)graphPane.getScene().getWindow());
            stage.setScene(new Scene(root, 400, 150));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleCloseButtonAction(){
        Stage stage = (Stage)graphPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void nextstep(){
        if(!timerFlag) {
            nextStep();
        }
    }

    void nextStep(){
        if (currentStep <  (countOfStep - 1)){
            currentStep = currentStep + 1;
            ArrayList<String> stack = Application.stepper.nextStep();

            ObservableList<String> observableStack = FXCollections.observableArrayList(stack);
            stackList.setItems(observableStack);
        }
        reInit();
    }

    @FXML
    void prevstep() {
        if(!timerFlag) {
            prevStep();
        }
    }

    void prevStep() {

        if (currentStep > 1) {
            currentStep = currentStep - 1;
            ArrayList<String> stack = Application.stepper.prevStep();

            ObservableList<String> observableStack = FXCollections.observableArrayList(stack);
            stackList.setItems(observableStack);
        }
        reInit();

    }

    public void printImg() throws IOException{
        FileInputStream inputstream;
        inputstream = new FileInputStream("temp.png");
        Image img = new Image(inputstream);
        GraphView.setImage(img);
    }
}