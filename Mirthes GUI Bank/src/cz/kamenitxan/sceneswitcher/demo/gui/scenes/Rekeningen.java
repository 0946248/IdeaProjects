package cz.kamenitxan.sceneswitcher.demo.gui.scenes;

import cz.kamenitxan.sceneswitcher.SceneSwitcher;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;

public class Rekeningen implements Initializable{

    @Override
    public void initialize (URL location, ResourceBundle resrouces){
        System.out.println("Rekeningen geladen");
    }


    public static void TerugNaarStart() {
        SceneSwitcher.getInstance().loadScene("Start", "cs");}
}




