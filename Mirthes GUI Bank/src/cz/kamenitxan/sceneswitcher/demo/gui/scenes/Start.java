package cz.kamenitxan.sceneswitcher.demo.gui.scenes;

import cz.kamenitxan.sceneswitcher.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;


public class Start implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Startscherm geladen");
    }

    public static void SessieStoppen() {
        SceneSwitcher.getInstance().loadScene("Sensor", "cs"); }
    public static void NaarRekeningen(){
        SceneSwitcher.getInstance().loadScene("Rekeningen", "cs");}
    public static void GeldOpnemen(){
        SceneSwitcher.getInstance().loadScene("Opnemen", "cs"); }
    public static void NaarSaldo(){
        SceneSwitcher.getInstance().loadScene("Saldo", "cs");
    }
}
