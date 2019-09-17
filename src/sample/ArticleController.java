package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class ArticleController implements Initializable {

    protected Article article;
    Map<Integer, Article>articleStorage = FileManager.readArticles(-2);     //  Map to use with binary file

    //  Constructors
    public ArticleController(){}
    public ArticleController(Article article){
        this.article = article;
    }

    //  FXML layout
    @FXML protected TextField title = new TextField();
    @FXML protected TextField author = new TextField();
    @FXML protected TextField yearOfPublication = new TextField();
    @FXML protected TextField pageNumber = new TextField();
    @FXML protected CheckBox available = new CheckBox();
    @FXML protected TextField id = new TextField();

    @FXML protected Button addArticleSubmit = new Button();


    @Override
    public final void initialize(URL location, ResourceBundle resources) {
        setTextFields();

        //  Buttons pressing
        addArticleSubmit.setOnAction(event -> {
            try {
                System.out.println("Add Article Button was pressed");
                saveArticle(event);
            } catch (IOException e) {
                e.printStackTrace();
                title.setText("Something went wrong");
                author.setText("Please try again");
            }
        });
    }

    protected abstract void saveArticle(ActionEvent event) throws IOException;

    protected void clearTextFields() {
            title.clear();
            author.clear();
            yearOfPublication.clear();
            pageNumber.clear();
    }

    protected abstract void setTextFields();    // Children have to add their new attributes to be inserted into textfields

    protected void setTextFields(Article a) {
        // Same Controller is used for editing Article and
        // this method gets the values
         title.setText(a.getTitle());
         author.setText(a.getAuthor());
         yearOfPublication.setText(Integer.toString(a.getYearOfPublication()));
         pageNumber.setText(Integer.toString(a.getPageNumber()));
         available.setSelected(a.getAvailable());
         id.setText(Integer.toString(a.getID()));
    }

    protected final void loadMainView(ActionEvent event) throws IOException {
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
