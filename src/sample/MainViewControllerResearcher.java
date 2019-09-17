package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewControllerResearcher implements Initializable {
    // Creating all needed buttons

    @FXML private Button viewResearcherButton = new Button();
    @FXML private Button viewBorrowedButton = new Button();
    @FXML private Button seeArticlesButton = new Button();

    @FXML private Button searchButton = new Button();

    @FXML private AnchorPane contentPane = new AnchorPane();    // Pane that is updating to show different tabs
    Researcher researcherLogged;
    MainViewControllerResearcher(Researcher researcherLogged){
        this.researcherLogged = researcherLogged;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Event handlers for buttons
        viewResearcherButton.setOnAction(event -> {
            try {
                loadAddResearcher(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        viewBorrowedButton.setOnAction(event -> {
            try {
                viewListArticles(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        seeArticlesButton.setOnAction(event -> {
            try {
                viewListArticles(event, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        searchButton.setOnAction(event -> {
            try {
                viewSearchMenu(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    // Buttons' method called
    private void loadAddResearcher(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("Views/AddResearcherView.fxml")
        );
        fxmlLoader.setController(new ResearcherController(researcherLogged, "Researcher"));

        loadPanel(fxmlLoader);
    }

    private void viewListArticles(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("Views/ArticleTableView.fxml")
        );
        fxmlLoader.setController(new ArticleListController(researcherLogged));

        loadPanel(fxmlLoader);
    }

    private void viewListArticles(ActionEvent event, boolean available) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("Views/ArticleTableView.fxml")
        );
        fxmlLoader.setController(new ArticleListController(available));

        loadPanel(fxmlLoader);
    }


    private void viewSearchMenu(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("Views/SearchView.fxml")
        );
        fxmlLoader.setController(new SearchController("Researcher"));

        loadPanel(fxmlLoader);
    }


    private void loadPanel(FXMLLoader fxmlLoader) throws IOException {
        contentPane.getChildren().clear();
        AnchorPane panel = fxmlLoader.load();
        contentPane.getChildren().add(panel);
    }

}
