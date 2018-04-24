//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cz.kamenitxan.sceneswitcher.demo.gui.scenes;

import java.io.IOException;

import cz.kamenitxan.sceneswitcher.demo.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;


public class SwapScene {
    public SwapScene() {
    }

    public static void swapScene(String bestandsnaam, Window window) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(bestandsnaam + ".fxml"));
            GridPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage stage = (Stage)window;
            stage.setScene(scene);
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }
}
