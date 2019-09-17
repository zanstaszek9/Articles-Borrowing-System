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


public class ResearcherListController implements Initializable {

    @FXML private TableView<Researcher> researchersTable = new TableView<>();

    @FXML private Button editResearcherButton = new Button();
    @FXML private Button deleteResearcherButton = new Button();

    @FXML private AnchorPane contentPane = new AnchorPane();

    ResearcherListController (){}
    ResearcherListController (int id){
        this.id = id;
    }
    int id = -2;


    private final ObservableList<Researcher> showResearchers() {
        ObservableList<Researcher> res = FXCollections.observableArrayList();
        for (HashMap.Entry<Integer, Researcher> i :
                FileManager.readResearchers(id).entrySet() )
            res.add(i.getValue());

        return res;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listAll(Researcher.getFields());

        //  Buttons pressing
        deleteResearcherButton.setOnAction(event -> {
            Researcher researcher = researchersTable.getSelectionModel().getSelectedItem();

            Map<Integer, Researcher> researcherList = FileManager.readResearchers(-2);
            researcherList.remove(researcher.getID());

            FileManager.saveResearchers(researcherList);
            researchersTable.setItems(showResearchers());
        });

        editResearcherButton.setOnAction(event -> {
            Researcher researcher = researchersTable.getSelectionModel().getSelectedItem();
            try {
                loadAddResearcher(event, researcher);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void listAll(String[] fields){
        researchersTable.setItems(showResearchers());

        for (String f : fields){
            TableColumn<Researcher, String> colName = new TableColumn<>(f.substring(0,1).toUpperCase()
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
            researchersTable.getColumns().addAll(colName);
        }
    }

    private void loadAddResearcher(ActionEvent event, Researcher researcher) throws IOException {
        ResearcherController researcherController = new ResearcherController(researcher);
        System.out.println("RLC lAR Res ID: "+researcher.getID());

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("Views/AddResearcherView.fxml")
        );

        fxmlLoader.setController(researcherController);
        contentPane.getChildren().clear();
        AnchorPane panel = fxmlLoader.load();
        contentPane.getChildren().add(panel);
    }
}
