package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class ArticleListController implements Initializable {

    @FXML private TableView<Article> articlesTable = new TableView<>();

    @FXML private Button editArticleButton = new Button();
    @FXML private Button deleteArticleButton = new Button();
    @FXML private Button borrowArticleButton = new Button();

    @FXML private AnchorPane contentPane = new AnchorPane();

    Map<Integer, Article>articleStorage = FileManager.readArticles(-2);     //  Map to use with binary file
    String title = "";  // Used for Search Controller; When passed by it, search for title with this String in it
    Researcher researcher;
    int id = -2;
    boolean available = false;

    ArticleListController (){}
    ArticleListController (int id){
        this.id = id;
    }
    ArticleListController (String title){
        this.title = title;
    }
    ArticleListController (Researcher researcher){  this.researcher = researcher;   }
    ArticleListController (boolean available){  this.available = available; }

    private final ObservableList<Article> showArticles() {
        System.out.println("ALC: showArticles");
        ObservableList<Article> articles = FXCollections.observableArrayList();
        if (!title.isEmpty())
            for    (HashMap.Entry<Integer, Article> i :
                    FileManager.readArticles(title).entrySet() )
                articles.add(i.getValue());

        else if (researcher != null)
            for (Article a : researcher.getBorrowedList())
                articles.add(a);

        else if(available) {
            for (HashMap.Entry<Integer, Article> i :
                    FileManager.readArticles(-2).entrySet())
                if (i.getValue().getAvailable())
                    articles.add(i.getValue());
        }
        else
            for    (HashMap.Entry<Integer, Article> i :
                    FileManager.readArticles(id).entrySet())
                articles.add(i.getValue());

        return articles;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listAll();
        if (researcher != null || available != false)
            lockFields();

        //  Buttons pressing
        deleteArticleButton.setOnAction(event -> {
            try {
                Article article = articlesTable.getSelectionModel().getSelectedItem();
                returnArticle(event, article);  // Erasing borrowed article from Researcher objects
                Map<Integer, Article> articleList = FileManager.readArticles(id);
                articleList.remove(article.getID());


                FileManager.saveArticles(articleList);
                articlesTable.setItems(showArticles());
            }catch (NullPointerException e){
                System.out.println(event.getTarget() + " pressed without selection");
            }
        });

        editArticleButton.setOnAction(event -> {
            Article article = articlesTable.getSelectionModel().getSelectedItem();
                try {
                    loadEditArticles(event, article);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e){
                    System.out.println(event.getTarget() + " error");
                } catch (NullPointerException e){
                    System.out.println(event.getTarget() + " pressed without selection");
                }
        });

        borrowArticleButton.setOnAction(event -> {
            Article article = articlesTable.getSelectionModel().getSelectedItem();
            try {
                if (article.getAvailable())
                    try {
                        loadBorrowArticle(event, article);
                    } catch (IOException e) {}
                else
                    returnArticle(event, article);
            }  catch (NullPointerException e){}
        });

        //  Selecting any article in list should change text and logic in Borrow button to Return borrowed article
        articlesTable.getSelectionModel().selectedItemProperty().addListener(event->{
            try {
                if (!articlesTable.getSelectionModel().getSelectedItem().getAvailable())
                    borrowArticleButton.setText("Return");
                else
                    borrowArticleButton.setText("Borrow");
            } catch (NullPointerException e){
                borrowArticleButton.setText("Borrow");
            }
        });
    }

    public void listAll(){
        articlesTable.setItems(showArticles());
        for (String f : Article.getFields()){
            TableColumn<Article, String> colName = new TableColumn<>(f.substring(0,1).toUpperCase()
                    + f.substring(1).replaceAll(
                    String.format("%s|%s|%s",
                            "(?<=[A-Z])(?=[A-Z][a-z])",
                            "(?<=[^A-Z])(?=[A-Z])",
                            "(?<=[A-Za-z])(?=[^A-Za-z])"
                    ),
                    " "
            ));
            // Regular Expression above makes column list in appropriate naming conventions automatically
            colName.setCellValueFactory(new PropertyValueFactory<>(f));
            articlesTable.getColumns().addAll(colName);
        }
    }

    private void loadEditArticles(ActionEvent event, Article article) throws IOException {
        ArticleController articleController = null;
        FXMLLoader fxmlLoader = new FXMLLoader();

        if (article instanceof Journal){
            System.out.println("ALC: loadEditArticles Journal");
            articleController = new JournalController(article);
            fxmlLoader = new FXMLLoader(getClass().getResource("Views/AddJournalArticleView.fxml"));
        }
        else if (article instanceof Conference) {
            System.out.println("ALC: loadEditArticles Conference");
            articleController = new ConferenceController(article);
            fxmlLoader = new FXMLLoader(getClass().getResource("Views/AddConferenceArticleView.fxml"));
        }
        else if (article instanceof Book) {
            System.out.println("ALC: loadEditArticles Book");
            articleController = new BookController(article);
            fxmlLoader = new FXMLLoader(getClass().getResource("Views/AddBookChapterArticleView.fxml"));
        }

        fxmlLoader.setController(articleController);
        loadPanel(fxmlLoader);
    }

    private void loadBorrowArticle(ActionEvent event, Article article) throws IOException {
        ArticleBorrowController articleBorrowController = new ArticleBorrowController(article);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Views/BorrowArticleView.fxml"));

        fxmlLoader.setController(articleBorrowController);
        loadPanel(fxmlLoader);
    }

    private void returnArticle(ActionEvent event, Article article){
        Researcher researcher = article.getBorrowerObject();
        researcher.returnArticle(article);
        // Researcher assignment must be before setting article availability,
        // because setters erases borrower
        article.setAvailable(true);
        articleStorage.put(article.getID(), article);

        Map<Integer, Researcher> researcherStorage = FileManager.readResearchers(-2);
        researcherStorage.put(researcher.getID(), researcher);

        FileManager.saveResearchers(researcherStorage);
        FileManager.saveArticles(articleStorage);
        articlesTable.setItems(showArticles());
    }



    private void loadPanel(FXMLLoader fxmlLoader) throws IOException {
        contentPane.getChildren().clear();
        AnchorPane panel = fxmlLoader.load();
        contentPane.getChildren().add(panel);
    }

    private void lockFields(){
       editArticleButton.setDisable(true);
       deleteArticleButton.setDisable(true);
       borrowArticleButton.setDisable(true);

        editArticleButton.setVisible(false);
        deleteArticleButton.setVisible(false);
        borrowArticleButton.setVisible(false);
    }
}
