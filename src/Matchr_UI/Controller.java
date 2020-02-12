package Matchr_UI;

import Matchr_App.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class Controller {

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

    // buttons
    @FXML
    private Button recommendBtn;
    @FXML
    private Button resetBtn;


    // calls the event handler depending on the button clicked
    @FXML
    public void onSubmit(ActionEvent e) {
        // recommend
        if (e.getSource().equals(recommendBtn))
            handleRec();
        // reset
        else if (e.getSource().equals(resetBtn))
            handleReset();
    }

    // Event handlers

    // reset method for clearing all the controls on reset
    private void handleReset() {
        getBrand().clear();
        getWeight().getValueFactory().setValue(1);
        getBalance().getValueFactory().setValue(1);
        getStiffness().getValueFactory().setValue(1);
        getStyle().getValueFactory().setValue(1);
        getSkill().getValueFactory().setValue(1);
        getStrength().getValueFactory().setValue(1);
        getShaftDiameter().setValue(7.5);
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
        System.out.println(user);
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
}
