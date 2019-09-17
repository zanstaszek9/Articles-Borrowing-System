package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        LoginController login = new LoginController();
        Parent root = FXMLLoader.load(getClass().getResource("Views/login.fxml"));
        primaryStage.setTitle("RAMS Login");
        primaryStage.setScene(new Scene(root, 560, 310));
        primaryStage.show();
        setStartingIDs();

    }

        public static void main(String Arg[]){  launch(Arg);    }

    public void setStartingIDs(){
        //  Method to get read last created ID of created objects,
        //  so new one will not overwrite existing ones
        Researcher.setIdCounter(FileManager.readResearcherID()+1);
        Article.setIdCounter(FileManager.readArticleID()+1);
    }

}
