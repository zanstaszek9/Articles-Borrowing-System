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

public class MainViewController implements Initializable {
    // Creating all needed buttons
    @FXML private Button addResearcherButton = new Button();

    @FXML private Button addJournalArticleButton = new Button();
    @FXML private Button addConferenceArticleButton = new Button();
    @FXML private Button addBookChapterButton = new Button();

    @FXML private Button seeResearchersButton = new Button();
    @FXML private Button seeArticlesButton = new Button();

    @FXML private Button searchButton = new Button();

    @FXML private AnchorPane contentPane = new AnchorPane();    // Pane that is updating to show different tabs


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Event handlers for buttons
        addResearcherButton.setOnAction(event -> {
            try {
                loadAddResearcher(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addJournalArticleButton.setOnAction(event -> {
            try {
                System.out.println("Add Journal button");
                loadAddJournal(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addConferenceArticleButton.setOnAction(event -> {
            try {
                System.out.println("Add Conference button");
                loadAddConference(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addBookChapterButton.setOnAction(event -> {
            try {
                System.out.println("Add Book button");
                loadAddBook(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        seeResearchersButton.setOnAction(event -> {
            try {
                viewListResearchers(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        seeArticlesButton.setOnAction(event -> {
            try {
                System.out.println("List Articles button");
                viewListArticles(event);
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
        fxmlLoader.setController(new ResearcherController());

        loadPanel(fxmlLoader);
    }

    private void loadAddJournal(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("Views/AddJournalArticleView.fxml")
        );
        fxmlLoader.setController(new JournalController());

        loadPanel(fxmlLoader);
    }

    private void loadAddConference(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("Views/AddConferenceArticleView.fxml")
        );
        fxmlLoader.setController(new ConferenceController());

        loadPanel(fxmlLoader);
    }
    private void loadAddBook(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("Views/AddBookChapterView.fxml")
        );
        fxmlLoader.setController(new BookController());

        loadPanel(fxmlLoader);
    }

    private void viewListResearchers(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("Views/ResearcherTableView.fxml")
        );
        fxmlLoader.setController(new ResearcherListController());

        loadPanel(fxmlLoader);
    }

    private void viewListArticles(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("Views/ArticleTableView.fxml")
        );
        fxmlLoader.setController(new ArticleListController());

        loadPanel(fxmlLoader);
    }

    private void viewSearchMenu(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("Views/SearchView.fxml")
        );
        fxmlLoader.setController(new SearchController());

        loadPanel(fxmlLoader);
    }


    private void loadPanel(FXMLLoader fxmlLoader) throws IOException {
        contentPane.getChildren().clear();
        AnchorPane panel = fxmlLoader.load();
        contentPane.getChildren().add(panel);
    }

}
