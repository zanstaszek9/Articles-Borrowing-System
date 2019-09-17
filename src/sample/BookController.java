package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class BookController extends ArticleController {

    @FXML private TextField editor = new TextField();
    @FXML private TextField publicationPlace = new TextField();
    @FXML private TextField publisher = new TextField();

    private Book article = new Book();

    //  Constructors
    public BookController(){}
    public BookController(Book book){
        this.article = book;
    }
    public BookController(Article book){
        this.article = (Book) book;
    }

    @Override
    protected final void saveArticle(ActionEvent event) throws IOException {
        //  Creating new object of given data
        Book b = new Book(title.getText(), author.getText(), yearOfPublication.getText(),
                pageNumber.getText(), id.getText(), available.isSelected(),
                editor.getText(), publicationPlace.getText(), publisher.getText()
        );

        //  Saving to file
        articleStorage.put(article.getID(), b);
        FileManager.saveArticles(articleStorage);

        loadMainView(event);
    }

    @Override
    protected final void setTextFields() {
        super.setTextFields(article);
        editor.setText(article.getEditor());
        publicationPlace.setText((article.getPublicationPlace()));
        publisher.setText(article.getPublisher());
    }

    @Override
    protected final void clearTextFields() {
        super.clearTextFields();
        editor.clear();
        publicationPlace.clear();
        publisher.clear();
    }
}
