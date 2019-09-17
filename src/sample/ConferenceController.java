package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ConferenceController extends ArticleController {

    private Conference article = new Conference();

    //  Constructors
    public ConferenceController(){}
    public ConferenceController(Conference conference){
        this.article = conference;
    }
    public ConferenceController(Article conference){
        this.article = (Conference) conference;
    }

    //  Adding needed textfields
    @FXML private TextField editors = new TextField();
    @FXML private TextField conferenceLocation = new TextField();

    @Override
    protected final void saveArticle(ActionEvent event) throws IOException {
        //  Creating new object of given data
        Conference c = new Conference(title.getText(), author.getText(), yearOfPublication.getText(),
                pageNumber.getText(), id.getText(), available.isSelected(),
                editors.getText(), conferenceLocation.getText()
        );
        System.out.println("CC saveArticle Conference created");

        //  Saving to file
        System.out.println(c.getConferenceLocation());

        articleStorage.put(article.getID(), c);
        FileManager.saveArticles(articleStorage);

        loadMainView(event);
    }

    @Override
    protected final void setTextFields() {
        super.setTextFields(article);
        editors.setText(article.getEditors());
        conferenceLocation.setText((article.getConferenceLocation()));
    }

    @Override
    protected final void clearTextFields() {
        super.clearTextFields();
        editors.clear();
        conferenceLocation.clear();
    }
}
