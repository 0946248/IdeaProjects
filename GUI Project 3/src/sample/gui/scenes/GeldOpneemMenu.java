package sample;

import cz.kamenitxan.sceneswitcher.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

public class GeldOpneemMenu implements Initializable{
    DebugModus debug = new DebugModus();
   @FXML
    private Label OnvoldoendeSaldo;

    public static void setText(String text) {
        Text = text;
    }

    private static String Text="";
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (debug.isDEBUG())  System.out.println("Geld Opneem Menu Loaded");
        OnvoldoendeSaldo.setText(Text);

    }
    public void TerugNaarGebruikersMenu(){
        SceneSwitcher.getInstance().loadScene("GebruikersMenu","cs");
    }
}
