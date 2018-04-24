package cz.kamenitxan.sceneswitcher.demo.gui.scenes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public void database(){
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://83.86.233.88.1337", "root", "usbw");
        }   catch (SQLException e){
            e.printStackTrace();
        }
    }
}