package cz.kamenitxan.sceneswitcher.demo.gui.scenes;

import cz.kamenitxan.sceneswitcher.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Login  implements Initializable{

    @FXML
    private Label PinInvoer;

    public void setText(String text) {
        Text = text;
    }
    public static void setInvoer(String invoer){ Invoer = invoer;}

    private static String Text = "";
    private static String Invoer ="_ _ _ _";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Log In geladen");

        PinInvoer(Invoer);


    }

    @FXML
    public void PinInvoer(String s){PinInvoer.setText(s);}

    public static void GaNaarStart() {
        SceneSwitcher.getInstance().loadScene("Start", "cs"); }


}


