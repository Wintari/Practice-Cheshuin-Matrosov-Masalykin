package application.gui;

import application.Application;
import application.graphviz.Proba;

import application.stepper.EdgeAction;
import application.stepper.NodeAction;
import application.stepper.Step;
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
import java.util.LinkedList;

public class MainWindowController {

    int countOfStep = 10;
    int currentStep = 1;

    LinkedList<LinkedList<String>> dotSources = new LinkedList<LinkedList<String>>();

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

    void setCountOfStep(){
        this.countOfStep = Application.stepper.getStepCount() + 1;
    }

    void setDotSource(){
        this.dotSources.clear();
        for(int i = 0; i < countOfStep; i++) {
            this.dotSources.add(Application.graph.toStringList());
        }
    }

    void setGraphView(int index){
        if(!dotSources.isEmpty()) {
            Proba.makeGraph(dotSources.get(index - 1));
        }
    }

    @FXML
    void initialize(){
        setCountOfStep();
        setDotSource();
        initSteps();
        stepNumberField.setText("   Шаг " + currentStep + "/" + (countOfStep - 1));
        setGraphView(1);
        try {
            printImg();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    void reInit(){
        stepNumberField.setText("   Шаг " + currentStep + "/" +  (countOfStep - 1));
        setGraphView(currentStep);
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

    void addToDotSource(int startIndex, String line, String newLine){
        for(int i = startIndex - 1; i < countOfStep; i++){
            if(dotSources.get(i).contains(line)) {
                dotSources.get(i).set(dotSources.get(i).indexOf(line), newLine);
            }
            else if(dotSources.get(i).contains(line + " [color=blue]")) {
                dotSources.get(i).set(dotSources.get(i).indexOf(line + " [color=blue]"), newLine);
            }
            else if(dotSources.get(i).contains(line + " [color=green]")) {
                dotSources.get(i).set(dotSources.get(i).indexOf(line + " [color=green]"), newLine);
            }
            else if(dotSources.get(i).contains(line + " [color=yellow]")) {
                dotSources.get(i).set(dotSources.get(i).indexOf(line + " [color=yellow]"), newLine);
            }
            else if(dotSources.get(i).contains(line + " [color=grey]")) {
                dotSources.get(i).set(dotSources.get(i).indexOf(line + " [color=grey]"), newLine);
            }
        }
    };

    public static String reverseString(String inputString) {
        int stringLength = inputString.length();
        String result = "";
        for (int i = 0; i < stringLength; i++) {
            result = inputString.charAt(i) + result;
        }
        return result;
    }

    void initSteps(){
        for(int i = 1; i < countOfStep; i++){
            Step step = Application.stepper.nextStep();

            EdgeAction edgeAction = step.getEdgeAction();
            NodeAction nodeAction = step.getNodeAction();

            if(edgeAction != null) {

                String tmp = edgeAction.getEdge();
                String reversTmp = reverseString(tmp);

                EdgeAction.Action edgeaction = edgeAction.getAction();

                switch (edgeaction) {
                    case EDGE_STARTED:
                        addToDotSource(i, tmp, tmp + " [color=blue]");
                        addToDotSource(i, reversTmp, reversTmp + " [color=blue]");
                        break;
                    case EDGE_FINISHED_COMMON:
                        addToDotSource(i, tmp, tmp + " [color=grey]");
                        addToDotSource(i, reversTmp, reversTmp + " [color=grey]");
                        break;
                    case EDGE_FINISHED_BRIDGE:
                        addToDotSource(i, tmp, tmp + " [color=green]");
                        addToDotSource(i, reversTmp, reversTmp + " [color=green]");
                        break;
                }
            }

            if(nodeAction != null){
                String tmp = nodeAction.getNode().getName();

                NodeAction.Action nodeacttion = nodeAction.getAction();

                switch (nodeacttion){
                    case NODE_STARTED:
                        addToDotSource(i, tmp, tmp + " [color=blue]");
                        break;
                    case NODE_FINISHED:
                        addToDotSource(i, tmp, tmp + " [color=grey]");
                        break;
                    case TIME_UPDATED:
                        addToDotSource(i, tmp, tmp + " [color=yellow]");
                        break;
                }
            }
        }
    }

    @FXML
    void nextstep(){
        if (currentStep <  (countOfStep - 1)){
            currentStep = currentStep + 1;
        }
        reInit();
    }

    @FXML
    void prevstep(){
        if (currentStep > 1){
            currentStep = currentStep - 1;
        }
        reInit();
    }

    public void printImg() throws IOException{
        FileInputStream inputstream;
        inputstream = new FileInputStream("C:\\temp\\img249.png");
        Image img = new Image(inputstream);
        GraphView.setImage(img);
    }
}