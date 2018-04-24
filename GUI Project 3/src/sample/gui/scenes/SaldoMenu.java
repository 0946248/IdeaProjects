package sample;

import cz.kamenitxan.sceneswitcher.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

public class SaldoMenu implements Initializable{

    public static void setGeld(double geld) {
        Geld = geld;
    }

    @FXML
    private Label SaldoHoeveelheid;


    private static double Geld = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Saldo Menu Loaded");
        SaldoHoeveelheid.setText("€"+Geld);

    }

    public static void TerugGebruikersMenu() {
        SceneSwitcher.getInstance().loadScene("GebruikersMenu", "cs");}


}
