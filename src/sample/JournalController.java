package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class JournalController extends ArticleController {

    private Journal article = new Journal();

    //  Constructors
    public JournalController(){}
    public JournalController(Journal journal){
        this.article = journal;
    }
    public JournalController(Article article){
        this.article = (Journal) article;
    }

    @FXML private TextField journalTitle = new TextField();
    @FXML private TextField volumeNumber = new TextField();
    @FXML private TextField partNumber = new TextField();

    @Override
    protected final void saveArticle(ActionEvent event) throws IOException {
        //  Creating new object of given data
        Journal j = new Journal(title.getText(), author.getText(), yearOfPublication.getText(),
                pageNumber.getText(), id.getText(), available.isSelected(),
                journalTitle.getText(), volumeNumber.getText(), partNumber.getText()
        );

        //  Saving to file
        articleStorage.put(article.getID(), j);
        FileManager.saveArticles(articleStorage);

        loadMainView(event);
    }

    @Override
    protected final void setTextFields() {
        super.setTextFields(article);
        journalTitle.setText(article.getTitleOfJournal());
        volumeNumber.setText(Integer.toString(article.getVolumeNum()));
        partNumber.setText(Integer.toString(article.getPartNum()));
    }

    @Override
    protected final void clearTextFields() {
        super.clearTextFields();
        journalTitle.clear();
        volumeNumber.clear();
        partNumber.clear();
    }
}
