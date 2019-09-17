package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    // Creating all needed buttons
    @FXML private TextField username = new TextField();
    @FXML private PasswordField password = new PasswordField();
    @FXML private Button loginSubmit = new Button();

    private Researcher researcherLogged = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginSubmit.setOnAction(event->{
            if(username.getText().equals("admin1") &&
               password.getText().equals("admin1")) {
                try {
                    load(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (loginResearcher()) {
                try {
                    load(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                password.clear();
                username.clear();
                username.setPromptText("Inputs not valid!");
            }
        });
    }

    public void load(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader;
        if (researcherLogged == null) {
            fxmlLoader = new FXMLLoader(
                    getClass().getResource("Views/MainView.fxml"));
            fxmlLoader.setController(new MainViewController());
        }
        else {
            fxmlLoader = new FXMLLoader(
                    getClass().getResource("Views/MainViewResearcher.fxml"));
            fxmlLoader.setController(new MainViewControllerResearcher(researcherLogged));
        }

        loadScene(event, fxmlLoader);
    }

    public void loadScene(ActionEvent event, FXMLLoader fxmlLoader) throws IOException {

        AnchorPane mainView = fxmlLoader.load();
        Scene scene = new Scene(mainView);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.setTitle("Research Article Management System RAMS");
        appStage.show();
    }

    public boolean loginResearcher (){
        Map<Integer, Researcher> researcherStorage = FileManager.readResearchers(-2);
        String usernameR = username.getText();
        String passwordR = password.getText();

        for (Researcher r : researcherStorage.values()) {
            System.out.println("LC loginResearcher: "+r.getFullName());
            System.out.println("LC loginResearcher: "+r.getUsername() + " : " + r.getPassword());
            System.out.println("LC loginResearcher tfs: "+username.getText() + " : " + password.getText());

            if (r.getUsername().equals(usernameR) && r.getPassword().equals(passwordR)) {
                System.out.println("Match!");
                researcherLogged = r;
                return true;
            }
        }

        return false;
    }
}
