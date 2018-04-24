

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene logIn, gMenu;
    //controllers opzetten voor FXML files
    static Inlogscherm inlogschermController;
    //static GebruikersMenu gMenuController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Inlogscherm.fxml"));

        //Importeren van FXML files als Parents
        Parent inlogscherm = FXMLLoader.load(getClass().getResource("/Inlogscherm.fxml"));
        Parent gebruikersMenu = FXMLLoader.load(getClass().getResource("/Gebruikers Menu.fxml"));
        Parent test = FXMLLoader.load(getClass().getResource("/Test.fxml"));

        inlogschermController= (Inlogscherm) loader.getController();

        //Scenes maken van de Parents (Een scene is het gedeelte dat binnen het venster te zien is)
        logIn = new Scene(inlogscherm, 600, 400);
        gMenu = new Scene(gebruikersMenu, 600, 400);
        Scene testScene = new Scene(test, 600, 400);

        //Venster instellingen voor tijdens het opstarten
        window.setTitle("Bank GUI");
        window.setScene(testScene);
        window.show();
    }


}