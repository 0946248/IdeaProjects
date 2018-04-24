package sample;

import cz.kamenitxan.sceneswitcher.SceneSwitcher;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;


public class GebruikersMenuController implements Initializable {
    DebugModus debug = new DebugModus();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (debug.isDEBUG()) System.out.println("Gebruikers Menu Loaded");


    }

    public void NaarPinMenu() {
        SceneSwitcher.getInstance().loadScene("PinCodeValidatie", "cs");
    }

    public void TerugNaarLogIn() {
        SceneSwitcher.getInstance().loadScene("Startscherm", "cs");
    }

    public void NaarOpneemMenu() {
        SceneSwitcher.getInstance().loadScene("GeldOpneemMenu", "cs");
    }

    public void NaarSaldoMenu() {
        SceneSwitcher.getInstance().loadScene("SaldoMenu", "cs");
    }


}