package Matchr_UI;

import Matchr_App.Inventory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// USE ONLY WHEN RUNNING APP WITH UI
public class JavaFX_App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // loads the UI from the fxml file
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        primaryStage.setTitle("Matchr");
        primaryStage.setScene(new Scene(root, 700, 750));
        primaryStage.show();

        // populates inventory with data (change after implementing database)
        Inventory.updateInventory();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
