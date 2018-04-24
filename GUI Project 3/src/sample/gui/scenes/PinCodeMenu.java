package sample;
import cz.kamenitxan.sceneswitcher.SceneSwitcher;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;


public class PinCodeMenu implements Initializable {
    DebugModus debug = new DebugModus();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (debug.isDEBUG()) System.out.println("Pin Validatie Menu Loaded");

    }


    public void NaarPinMenu(){
        SceneSwitcher.getInstance().loadScene("PinCodeInvoer","cs");
    }
    public void TerugNaarGebruikersMenu(){
        SceneSwitcher.getInstance().loadScene("GebruikersMenu","cs");
    }
}


