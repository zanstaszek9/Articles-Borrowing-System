package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ArticleBorrowController  implements Initializable {

    @FXML private TextArea articleDetails = new TextArea();
    @FXML private TextArea researcherDetails = new TextArea();
    @FXML private ChoiceBox researcherSelect = new ChoiceBox();

    @FXML private Button borrowArticleSubmit = new Button();

    private Article article;
    private Researcher researcher;
    Map<Integer, Article> articleStorage = FileManager.readArticles(-2);     //  Map to use with binary file
    Map<Integer, Researcher> researcherStorage = FileManager.readResearchers(-2);

    ArticleBorrowController(Article article){this.article = article;}


    @Override
    public final void initialize(URL location, ResourceBundle resources) {
        setChoiceBox();
        setTextFields();

        researcherSelect.setOnAction(event -> {
            System.out.println(researcherSelect.getValue());
            int index = researcherSelect.getValue().toString().indexOf(".");

            if (index > -1) {
                int resID = Integer.parseInt(researcherSelect.getValue().toString().substring(0, index));
                researcher = FileManager.readResearchers(resID).get(resID);
                researcherDetails.setText(researcher.toString());
            }
            else
                researcherDetails.clear();
        });

        borrowArticleSubmit.setOnAction(event -> {
            if (!researcherDetails.getText().isEmpty())
                try {
                    System.out.println("Borrow Button was pressed");
                    saveBorrow(event);
                    loadMainView(event);
                } catch (IOException e) {
                    e.printStackTrace();
                    articleDetails.setText("Something went wrong");
                }
            else
                researcherSelect.show();
        });
    }

    private void saveBorrow(ActionEvent event) throws IOException{
        article.setBorrower(researcher);
        researcher.borrowArticle(article);

        articleStorage.put(article.getID(), article);
        researcherStorage.put(researcher.getID(), researcher);

        FileManager.saveArticles(articleStorage);
        FileManager.saveResearchers(researcherStorage);
    }

    private void clearTextFields() {
        articleDetails.clear();
        researcherDetails.clear();
    }

    private void setTextFields(){
        articleDetails.setText(article.toString());
    }

    private void setChoiceBox() {
        ObservableList<String> researchers = FXCollections.observableArrayList();
        researchers.add("Select Researcher");

        for (HashMap.Entry<Integer, Researcher> item : researcherStorage.entrySet())
            researchers.add(item.getValue().getID() +". " + item.getValue().getFullName());

        researcherSelect.setItems(researchers);
        researcherSelect.getSelectionModel().selectFirst();
    }

    private final void loadMainView(ActionEvent event) throws IOException {
        MainViewController controller = new MainViewController();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("Views/MainView.fxml" )
        );
        loader.setController(controller);
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
    }
}
