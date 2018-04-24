package cz.kamenitxan.sceneswitcher.demo.gui.scenes;

import cz.kamenitxan.sceneswitcher.SceneSwitcher;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.awt

import java.net.URL;
import java.util.ResourceBundle;

public class Saldo implements Initializable{

    @FXML
    public Label Saldo;

    @FXML
    public Button SaldoTerugButton;



    private static double Geld = 0;

    @Override
    public void initialize (URL location, ResourceBundle resrouces){
        System.out.println("Saldo geladen");
        Saldo.setText("€"+Geld);
    }

    public static void setGeld(double geld) {
        Geld = geld;
    }

}




