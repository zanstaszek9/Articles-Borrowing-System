package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class ResearcherController implements Initializable {
    //  FXML layout
    @FXML private TextField firstname = new TextField();
    @FXML private TextField surname = new TextField();
    @FXML private TextField street = new TextField();
    @FXML private TextField town = new TextField();
    @FXML private TextField postcode = new TextField();
    @FXML private TextField phoneNumber = new TextField();
    @FXML private TextField email = new TextField();
    @FXML private TextField id = new TextField();

    @FXML private Button addResearcherSubmit = new Button();

    @FXML private Label researcherTitleLabel = new Label();


    //  Map to use with binary file
    Map<Integer, Researcher> researcherStorage = FileManager.readResearchers(-2);
    Researcher researcher = new Researcher();
    boolean loggedR = false;

    //  Constructors
    public ResearcherController(){}
    public ResearcherController(Researcher res){
        this.researcher = res;
    }
    public ResearcherController(Researcher res, String s) {
        this.researcher = res;
        if (s.equals("Researcher"))
            loggedR = true;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTextFields();
        if (loggedR)
            lockFields();

        addResearcherSubmit.setOnAction(event -> {
                try {
                      saveResearcher(event);
                } catch (IOException e) {
                    e.printStackTrace();
                    firstname.setText("Something went wrong");
                    surname.setText("Please try again");
                }
        });
    }

    private void saveResearcher(ActionEvent event) throws IOException {
        //  Creating new object of given data
        Researcher res = new Researcher(firstname.getText(), surname.getText(),
                            street.getText(), town.getText(), postcode.getText(),
                            phoneNumber.getText(), email.getText(), id.getText()
        );

        //  Saving to file
        researcherStorage.put(researcher.getID(), res);
        FileManager.saveResearchers(researcherStorage);

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

    private void clearTextFields() {
        firstname.clear();
        surname.clear();
        street.clear();
        town.clear();
        postcode.clear();
        phoneNumber.clear();
        email.clear();
    }

    private void setTextFields() {
        // Same Controller is used for editing Researcher
        // and this method gets the values
        firstname.setText(researcher.getFirstname());
        surname.setText(researcher.getSurname());
        street.setText(researcher.getStreet());
        town.setText(researcher.getTown());
        postcode.setText(researcher.getPostcode());
        phoneNumber.setText(researcher.getPhoneNum());
        email.setText(researcher.getEmail());
        id.setText(Integer.toString(researcher.getID()));
    }

    private void lockFields(){
        firstname.setEditable(false);
        surname.setEditable(false);
        street.setEditable(false);
        town.setEditable(false);
        postcode.setEditable(false);
        phoneNumber.setEditable(false);
        email.setEditable(false);
        id.setEditable(false);
        addResearcherSubmit.setDisable(true);
        addResearcherSubmit.setVisible(false);
        researcherTitleLabel.setText("Researcher details");
    }
}
