package application.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("Practice");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 850, 640));
        primaryStage.show();
    }

    public static void execute(String[] args) {
        launch(args);
    }
}
