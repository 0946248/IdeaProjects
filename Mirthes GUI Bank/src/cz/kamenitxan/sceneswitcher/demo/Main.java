package cz.kamenitxan.sceneswitcher.demo;

import cz.kamenitxan.sceneswitcher.SceneSwitcher;
import cz.kamenitxan.sceneswitcher.demo.gui.scenes.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import sun.management.Sensor;

import java.io.IOException;
import java.rmi.server.UID;

import static java.lang.Integer.parseInt;


/**
 * Main application class.
 */

public class Main extends Application implements ArduinoInputInterface {
    private final SceneSwitcher sceneSwitcher = SceneSwitcher.getInstance();



    int currentscreen = 1; //1 = Sensor, 2 = Login, 3 = Start, 4 = Rekeningen, 5 = Saldo, 6 = Opnemen, 7 = Groeten
    String PincodeString = "";
    String Input;
    int InputCount = 0;
    String Invoer = "_ _ _ _";
    boolean Ingelogd = false;
    String PasID;

    static Start Start;
    static cz.kamenitxan.sceneswitcher.demo.gui.scenes.Sensor Sensor;
    static Login Login;
    static Rekeningen Rekeningen;
    static SaldoMenu Saldo;
    static Opnemen Opnemen;
    static Groeten Groeten;


    @Override
public void start(Stage stage) throws Exception
{
    sceneSwitcher.addScene("Sensor", "Sensor.fxml");
    sceneSwitcher.addScene("Login", "Login.fxml");
    sceneSwitcher.addScene("Start", "Start.fxml");
    sceneSwitcher.addScene("Rekeningen", "Rekeningen.fxml");
    sceneSwitcher.addScene("Saldo", "Saldo.fxml");
    sceneSwitcher.addScene("Opnemen", "Opnemen.fxml");
    sceneSwitcher.addScene("Groeten", "Groeten.fxml");


    for (int i = 0; i <= 8; i++) {
        if (i == 1) {
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("./gui/scenes/Sensor.fxml"));
            Parent root = loader1.load();
            Sensor = (cz.kamenitxan.sceneswitcher.demo.gui.scenes.Sensor)loader1.getController();
        } else if (i == 2) {
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("./gui/scenes/Login.fxml"));
            Parent root = loader2.load();
            Login = loader2.getController();
        } else if (i == 3) {
            FXMLLoader loader3 = new FXMLLoader(getClass().getResource("./gui/scenes/Start.fxml"));
            Parent root = loader3.load();
            Start =  loader3.getController();
        } else if (i == 4) {
            FXMLLoader loader4 = new FXMLLoader(getClass().getResource("./gui/scenes/Rekeningen.fxml"));
            Parent root = loader4.load();
            Rekeningen = loader4.getController();
        } else if (i == 5) {
            FXMLLoader loader5 = new FXMLLoader(getClass().getResource("./gui/scenes/Saldo.fxml"));
            Parent root = loader5.load();
            Saldo = loader5.getController();
        } else if (i == 6) {
            FXMLLoader loader6 = new FXMLLoader(getClass().getResource("./gui/scenes/Opnemen.fxml"));
            Parent root = loader6.load();
            Opnemen = loader6.getController();
        } else if (i == 7) {
            FXMLLoader loader7 = new FXMLLoader(getClass().getResource("./gui/scenes/Groeten.fxml"));
            Parent root = loader7.load();
            Groeten = loader7.getController();
        }
    }

    stage.setTitle("ByteGroep2 : Bank MT");
    stage.setScene(sceneSwitcher.createMainScene(this.getClass()));

    SerialTest arduino = new SerialTest();
    arduino.initialize(this);
    Thread t = new Thread() {
        public void run() {
            try {
                Thread.sleep(10000000);
            } catch(InterruptedException ie) {

            }
        }
    };


    sceneSwitcher.loadScene("Sensor");
    stage.show();
}

    public static void main(String[] args)
    {
        launch(args);
    }

    public DatabaseConnector DBC = new DatabaseConnector();


    @Override
    public synchronized void rfidLezer(String UID) {
        System.out.println("Kaart: "+UID);
        currentscreen = 2;
        PasID = UID;
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                if (Ingelogd == false){
                if(UID.equals("C342DE2B")){
                    Ingelogd = true;
                    sceneSwitcher.loadScene("Login");
                }
                }
            }
        });
    }
    public boolean stringContains(String contains, String base) {
        if (base.contains(contains)) {
            return true;
        } else return false;
    }

    @Override
    public synchronized void keypadLezer(String key)  {
        Input = key;
        System.out.println("Key:" +key);
        System.out.println("1 scherm: " + currentscreen);


               /* if(PincodeString.equals("6002") && currentscreen == 2){
                    currentscreen = 3;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            sceneSwitcher.loadScene("Start");
                        }
                    });
                }*/


                 if (key.contains("A")){
                     System.out.println("Ik klik op A");
                    switch (currentscreen) {
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            System.out.println("Ik ga naar mijn rekeningen");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Start.NaarRekeningen();
                                    currentscreen = 4;
                                }
                            });

                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            System.out.println("Ik wil 5 opnemen");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Opnemen.EuroOpnemen5();
                                    currentscreen = 7;
                                }
                            });

                            break;
                        case 7:
                            break;
                        default:
                            break;
                    }
                }

               else  if (key.contains("B")){
                     System.out.println("Ik klik op B");
                    switch (currentscreen){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            System.out.println("Ik wil geld opnemen");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Start.GeldOpnemen();
                                    currentscreen = 6;
                                }
                            });

                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            System.out.println("Ik wil 10 opnemen");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Opnemen.EuroOpnemen10();
                                    currentscreen = 7;
                                }
                            });

                            break;
                        case 7:
                            break;
                        default:
                            break;

                    }

                }

               else if (key.contains("C")){
                    System.out.println("Ik klik op C");
                    switch (currentscreen){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            System.out.println("Nu ben ik bij saldo");
                            Saldo.setGeld(DBC.getBankRekeningSaldo());
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Start.NaarSaldo();
                                    currentscreen = 5;
                                }
                            });

                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            System.out.println("Ik wil 25 opnemen");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Opnemen.EuroOpnemen25();
                                    currentscreen = 7;
                                }
                            });

                            break;
                        case 7:
                            break;
                        default:
                            break;

                    }

                }

               else  if (key.contains("D")){
                    System.out.println("Ik klik op D");
                    switch (currentscreen){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            System.out.println("Ik wil 50 opnemen");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Opnemen.EuroOpnemen50();
                                }
                            });
                            currentscreen = 7;
                            break;
                        case 7:
                            break;
                        default:
                            break;

                    }

                }

                else if (key.contains("*")){
                     System.out.println("Ik klik op *");
                    switch (currentscreen){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            break;
                        case 7:
                            break;default:
                                break;

                            }
                    }

               else  if (key.contains("#")){
                     System.out.println("# scherm: " + currentscreen);
                     System.out.println("Ik klik op #");
                    switch (currentscreen){
                        case 1:
                            break;
                        case 2:

                            try {
                                DBC.getVerification(PasID, parseInt(PincodeString), 4);
                            }
                            catch (IOException IOE){ IOE.printStackTrace();
                            }

                            if (DBC.isSuccesfulLogin() == true)
                            {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        Login.GaNaarStart();
                                    }
                                });
                                currentscreen = 3;
                            }
                            break;
                        case 3:
                            System.out.println("Nu ben ik hier1");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Start.SessieStoppen();
                                    currentscreen = 1;
                                }
                            });
                            break;
                        case 4:
                            System.out.println("Nu ben ik naar start vanaf rekeningen");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Rekeningen.TerugNaarStart();
                                    currentscreen = 3;
                                }
                            });

                            break;
                        case 5:
                            System.out.println("Nu ben ik naar start vanaf Saldo");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Saldo.TerugNaarStart2();
                                    currentscreen = 3;
                                }
                            });

                            break;
                        case 6:
                            System.out.println("Nu ben ik naar start vanaf Opnemen");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Opnemen.TerugNaarStart3();
                                    currentscreen = 3;
                                }
                            });
                            break;
                        case 7:
                            break;
                        default:
                            break;
                    }

                }
                else {
                    switch(currentscreen){
                        case 1:
                            break;
                        case 2:
                            PincodeString += Input;
                            InputCount++;

                            System.out.println(PincodeString);
                            System.out.println("Aantal keys:" + InputCount);
                            switch (InputCount){
                                case 1:
                                    String Invoer = "* _ _ _";
                                    Login.setInvoer(Invoer);
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            sceneSwitcher.loadScene("Login");
                                        }
                                    });
                                    break;
                                case 2:
                                    Invoer = "* * _ _";
                                    Login.setInvoer(Invoer);
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            sceneSwitcher.loadScene("Login");
                                        }
                                    });
                                    break;
                                case 3:
                                    Invoer = "* * * _";
                                    Login.setInvoer(Invoer);
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            sceneSwitcher.loadScene("Login");
                                        }
                                    });
                                    break;
                                case 4:
                                    Invoer = "* * * *";
                                    Login.setInvoer(Invoer);
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            sceneSwitcher.loadScene("Login");
                                        }
                                    });
                                    break;
                            }
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            break;
                        case 7:
                            break;
                        default:
                            break;

                    }
                }
            }





    }

