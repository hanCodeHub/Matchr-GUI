package Matchr_UI;

import Matchr_App.Inventory;
import Matchr_App.ItemFinder;
import Matchr_App.Racquet;
import Matchr_App.User;
import Matchr_Models.RacquetModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

// handles events from UI and returns data to UI
public class Controller {

    // APP LOGIC FIELDS
    RacquetModel racquetModel;


    // UI OBJECTS
    // controls
    @FXML
    private TextField brand;
    @FXML
    private Spinner<Integer> weight;
    @FXML
    private Spinner<Integer> balance;
    @FXML
    private Spinner<Integer> stiffness;
    @FXML
    private Spinner<Integer> style;
    @FXML
    private Spinner<Integer> skill;
    @FXML
    private Spinner<Integer> strength;
    @FXML
    private Slider shaftDiameter;

    // control for displaying results
    @FXML
    private Text resultsText;

    // buttons
    @FXML
    private Button updateBtn;
    @FXML
    private Button recommendBtn;
    @FXML
    private Button resetBtn;

    public Controller() {
        this.racquetModel = new RacquetModel();
    }

    // EVENT HANDLERS
    // calls the event handler depending on the button clicked
    @FXML
    public void onSubmit(ActionEvent e) {

        // update inventory
        if (e.getSource().equals(updateBtn))
            handleUpdate();

        // recommend racquet
        else if (e.getSource().equals(recommendBtn))
            handleRec();

        // reset controls
        else if (e.getSource().equals(resetBtn))
            handleReset();

    }

    // updates the database with a new inventory from excel
    private void handleUpdate() {

        // read the excel source data into the database
        Inventory.readFromExcel();
        ArrayList<Racquet> racquetSrc = Inventory.getInventory();
        racquetModel.createRacquetTable();
        racquetModel.insertRacquets(racquetSrc);

        // display confirmation message on UI
        getResultsText().setText("Inventory successfully updated.");
    }

    // reset method for returning all the controls to default
    private void handleReset() {
        getBrand().clear();
        getWeight().getValueFactory().setValue(1);
        getBalance().getValueFactory().setValue(1);
        getStiffness().getValueFactory().setValue(1);
        getStyle().getValueFactory().setValue(1);
        getSkill().getValueFactory().setValue(1);
        getStrength().getValueFactory().setValue(1);
        getShaftDiameter().setValue(7.5);
        getResultsText().setText("");
    }

    // recommend method for processing user request
    private void handleRec() {
        User user = new User(
                getBrand().getText(),
                getWeight().getValue(),
                getBalance().getValue(),
                getStiffness().getValue(),
                getStyle().getValue(),
                getSkill().getValue(),
                getStrength().getValue(),
                (float) getShaftDiameter().getValue()
        );

        // feed racquets from database into itemfinder
        ItemFinder<Racquet> racquetFinder = new ItemFinder<>(user);
        List<Racquet> racquets = racquetModel.getAllRacquets();

        // rank and display top recommendation
        if (racquets.size() > 0) {
            racquetFinder.rankItems(racquets);
            Racquet recommended = racquetFinder.getTopResult();
            displayResult(recommended);

        } else { // if no data
            getResultsText().setText("No racquets found in database.");
        }

    }

    // displays recommendation to the results text control
    private void displayResult(Racquet rec) {
        String text = "Your recommended racquet is " + rec.getBrand() + " " + rec.getModel();
        resultsText.setText(text);
    }

    // getters
    public TextField getBrand() {
        return brand;
    }
    public Spinner<Integer> getWeight() {
        return weight;
    }
    public Spinner<Integer> getBalance() {
        return balance;
    }
    public Spinner<Integer> getStiffness() {
        return stiffness;
    }
    public Spinner<Integer> getStyle() {
        return style;
    }
    public Spinner<Integer> getSkill() {
        return skill;
    }
    public Spinner<Integer> getStrength() {
        return strength;
    }
    public Slider getShaftDiameter() {
        return shaftDiameter;
    }
    public Text getResultsText() {
        return resultsText;
    }
}
