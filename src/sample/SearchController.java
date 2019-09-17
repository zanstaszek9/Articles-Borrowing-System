package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    @FXML private Button searchResearcherSubmit = new Button();
    @FXML private Button searchArticleSubmit = new Button();

    @FXML private TextField IDSearch = new TextField();

    @FXML private AnchorPane contentPane = new AnchorPane();

    SearchController(){}
    SearchController(String logged){    this.logged = logged;   }

    String logged = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!logged.isEmpty())
            lockFields();

        //  Buttons pressing
        searchResearcherSubmit.setOnAction(event -> {
            try {
                searchResearcher(event, Integer.parseInt(IDSearch.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (NumberFormatException e) {
                IDSearch.setText("Invalid ID. Enter integer.");
            }
        });

        searchArticleSubmit.setOnAction(event -> {
            try {
                searchArticle(event, IDSearch.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (NumberFormatException e) {
                IDSearch.setText("Invalid title. Enter string.");
            }
        });
    }

    private void searchResearcher(ActionEvent event, int id) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("Views/ResearcherTableView.fxml")
        );
        fxmlLoader.setController(new ResearcherListController(id));

        loadPanel(fxmlLoader);
    }

    private void searchArticle(ActionEvent event, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("Views/ArticleTableView.fxml")
        );
        fxmlLoader.setController(new ArticleListController(title));

        loadPanel(fxmlLoader);
    }

    private void loadPanel(FXMLLoader fxmlLoader) throws IOException {
        contentPane.getChildren().clear();
        AnchorPane panel = fxmlLoader.load();
        contentPane.getChildren().add(panel);
    }

    private void lockFields(){
        searchResearcherSubmit.setDisable(true);
        searchResearcherSubmit.setVisible(false);
    }
}
