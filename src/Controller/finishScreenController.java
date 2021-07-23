package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * The type Finish screen controller.
 */
public class finishScreenController {
    private String winlose;

    @FXML
    private Label textLabel;

    /**
     * Instantiates a new Finish screen controller.
     */
    public finishScreenController(){

    }

    /**
     * Initialize.
     */
    public void initialize(){
        textLabel.setText(Static_User.winloss.get(Static_User.winloss.size()-1));
    }
}
