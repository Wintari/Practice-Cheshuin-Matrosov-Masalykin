package application.gui;

import application.Application;
import application.algorithm.Graph;
import application.graphviz.Proba;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

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
    private ImageView Image;

    @FXML
    private TextField node1;

    @FXML
    private TextField node2;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField nodeField;

    @FXML
    private Button addNodeButton;

    @FXML
    private Button deleteNodeButton;

    private static boolean isOnlyDigits(String str) {
        return str.matches("[a-zа-я]");
    }

    @FXML
    void addNode() {
        String node = nodeField.getText();
        if(isOnlyDigits(node)){
            graph.addNode(node);
        }
        try {
            printImg();
        }catch (IOException e){
            //do nothing
        }
        nodeField.clear();
    }

    @FXML
    void deleteNodeEdge() {
        String node = nodeField.getText();
        if(isOnlyDigits(node)){
            graph.deleteNode(node);
        }
        try {
            printImg();
        }catch (IOException e){
            //do nothing
        }
        nodeField.clear();
    }

    @FXML
    void addEdge() {
        String node = node1.getText();
        String parrent = node2.getText();
        boolean f1 = isOnlyDigits(node);
        boolean f2 = isOnlyDigits(parrent);
        if(f1 && f2){
            graph.addNode(node);
            graph.addNode(parrent);
            graph.addEdge(node, parrent);
        }
        try {
            printImg();
        }catch (IOException e){
            //do nothing
        }
        node1.clear();
        node2.clear();
    }

    @FXML
    void deleteEdge() {
        String node = node1.getText();
        String parrent = node2.getText();
        if(isOnlyDigits(node) && isOnlyDigits(parrent)){
            graph.deleteEdge(node, parrent);

        }
        try {
            printImg();
        }catch (IOException e){
            //do nothing
        }
        node1.clear();
        node2.clear();
    }

    public void printImg() throws IOException {
        Proba.makeGraph(graph.toStringList());
        FileInputStream inputstream;
        inputstream = new FileInputStream("C:\\temp\\img249.png");
        javafx.scene.image.Image img = new Image(inputstream);
        Image.setImage(img);
    }

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
