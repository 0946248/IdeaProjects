package sample;


import cz.kamenitxan.sceneswitcher.SceneSwitcher;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Enumeration;

import static java.lang.Integer.parseInt;

public class Main extends Application implements SerialPortEventListener {


    /***********De poorten die normaal gebruikt kunnen worden**********/
    private static final String PORT_NAMES[] = {
            "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9" // Windows poorten
    };

    /**************Millisecondes om te wachten op een open poort******/
    private static final int TIME_OUT = 2000;
    /****************Bits per seconde voor de poort********************/
    private static final int DATA_RATE = 9600;
    /************************Controllers*******************************/
    private static sample.gui.scenes.StartMenu StartM; //Screen 1
    private static sample.LogInController LIC; //Screen 2
    private static sample.GebruikersMenuController GMC; //Screen 3
    private static sample.PinCodeMenu PCM; //Screen 4
    private static sample.PinCodeMenu2 PCM2; //Screen 5
    private static sample.SaldoMenu SaldoM; //Screen 6
    private static sample.GeldOpneemMenu GOM; //Screen 7

    private final SceneSwitcher sceneSwitcher = SceneSwitcher.getInstance();
    String Invoer = "_ _ _ _ _ _";
    Connection conn = null;
    /************************Variabelen******************************/

    private Stage window;
    private SerialPort serialPort;
    private String RFID_String;
    private String PincodeString = "";
    private int CurrentScreen = 1; // 1 - Start, 2 - Log In, 3 - GebruikersMenu, 4 - Pin Code Menu, 5 - Pin Code Menu 2, 6 - Saldo Menu, 7 - Geld Opname
    private int Pogingen = 0;
    private int savedPin = 0;
    private DatabaseConnector DBC = new DatabaseConnector();
    private DebugModus debug = new DebugModus();

    /************************Arduino Input******************************/

    private int Pincode = 0;
    private int InputCount = 0;

    /*****Reader om de inkomende bytes naar characters om te zetten****/

    private BufferedReader input;

    /*******************De Output naar de Com Poort*********************/

    private OutputStream output;

    public void initialize() {
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            System.out.println("Geen open COM poort gevonden");
            return;
        }

        try {
            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            // open the streams
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();

            // add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    /***********Functie om de gebruikte COM Poort te sluiten************/

    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    /********Lees de data van de COM poort en verwerk het***************/

    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String inputLine = input.readLine();
                ArduinoInput(inputLine);

            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }

    /************************Main Methode********************/

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Maak alle Objecten en Schermposities voor JavaFX aan
        window = primaryStage;
        sceneSwitcher.addScene("LogIn", "LogIn.fxml");
        sceneSwitcher.addScene("GebruikersMenu", "GebruikersMenu.fxml");
        sceneSwitcher.addScene("Startscherm", "Start Menu.fxml");
        sceneSwitcher.addScene("PinCodeValidatie", "Pin Code Menu.fxml");
        sceneSwitcher.addScene("PinCodeInvoer", "Pin Code Menu 2.fxml");
        sceneSwitcher.addScene("SaldoMenu", "Saldo Menu.fxml");
        sceneSwitcher.addScene("GeldOpneemMenu", "Geld Opneem Menu.fxml");
        //Maak de GUI aan
        window.setTitle("Bank GUI");
        window.setScene(sceneSwitcher.createMainScene(this.getClass()));

        //Maak alle JavaFX Controllers aan
        for (int i = 0; i <= 8; i++) {
            if (i == 1) {
                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("./gui/scenes/Start Menu.fxml"));
                Parent root = loader1.load();
                StartM = loader1.getController();
            } else if (i == 2) {
                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("./gui/scenes/LogIn.fxml"));
                Parent root = loader2.load();
                LIC = loader2.getController();
            } else if (i == 3) {
                FXMLLoader loader3 = new FXMLLoader(getClass().getResource("./gui/scenes/GebruikersMenu.fxml"));
                Parent root = loader3.load();
                GMC = loader3.getController();
            } else if (i == 4) {
                FXMLLoader loader4 = new FXMLLoader(getClass().getResource("./gui/scenes/Pin Code Menu.fxml"));
                Parent root = loader4.load();
                PCM = loader4.getController();
            } else if (i == 5) {
                FXMLLoader loader5 = new FXMLLoader(getClass().getResource("./gui/scenes/Pin Code Menu 2.fxml"));
                Parent root = loader5.load();
                PCM2 = loader5.getController();
            } else if (i == 6) {
                FXMLLoader loader6 = new FXMLLoader(getClass().getResource("./gui/scenes/Saldo Menu.fxml"));
                Parent root = loader6.load();
                SaldoM = loader6.getController();
            } else if (i == 7) {
                FXMLLoader loader7 = new FXMLLoader(getClass().getResource("./gui/scenes/Geld Opneem Menu.fxml"));
                Parent root = loader7.load();
                GOM = loader7.getController();
            }
        }
        //Zet de GUI op Scherm 1
        sceneSwitcher.loadScene("Startscherm");
        CurrentScreen = 1;

        //Laat de GUI op het scherm zien
        window.show();

        //Start de communicatie tussen Java en Arduino
        Main main = new Main();
        main.initialize();

        Thread t = new Thread() {
            public void run() {
                //the following line will keep this app alive for 1000 seconds,
                //waiting for events to occur and responding to them (printing incoming messages to console).
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ie) {
                    System.out.println("ERROR");
                }
            }
        };
        t.start();

        if (debug.isDEBUG()) System.out.println("Started");
    }

    private void ArduinoInput(String Input) throws IOException {

        if (debug.isDEBUG()) System.out.println("Received Input: " + Input);
        //Als de Input "ID:" bevat is het een pinpas
        if (stringContains("ID:", Input)) {
            RFID_String = stringRemove("ID:", Input);
            if (debug.isDEBUG()) System.out.println(RFID_String);
            if (CurrentScreen == 1) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        sceneSwitcher.loadScene("LogIn");
                    }
                });
                CurrentScreen = 2;
            } else if (CurrentScreen == 2) {
                LIC.setText("Kaart Gewisseld");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        sceneSwitcher.loadScene("LogIn");
                    }
                });
            }
            //Check of de Input * is
        } else if (stringContains("*", Input)) {
            switch (CurrentScreen) {
                case 2:
                    //Valideer Pin + Ga naar scherm 3 (GebruikersMenu)
                    if (debug.isDEBUG()) System.out.println("Aantal Inputs: " + InputCount);
                    Pincode = parseInt(PincodeString);
                    if (debug.isDEBUG()) System.out.println("Ingevoerde Pincode = " + Pincode);
                    //Haal gegevens op
                    DBC.getVerification(RFID_String, Pincode, 1);

                    if (DBC.isSuccesfulLogin()) {
                        LIC.setText("");
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                sceneSwitcher.loadScene("GebruikersMenu");
                            }
                        });
                        CurrentScreen = 3;
                    } else {
                        System.out.println("Kaart is geblokkeerd:" + DBC.isBlocked());
                        System.out.println("Aantal Pogingen:" + DBC.getAttempts());
                        if (DBC.isBlocked()) LIC.setText("Pas Geblokkeerd");
                        else LIC.setText("Incorrecte Pin");

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                sceneSwitcher.loadScene("LogIn");
                            }
                        });
                    }
                    //Reset Pincode
                    savedPin = Pincode;
                    Pincode = 0;
                    PincodeString = "";
                    InputCount = 0;
                    break;
                case 3:
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            GMC.TerugNaarLogIn();
                        }

                    });
                    String Invoer = "_ _ _ _ _ _";
                    LIC.setInvoer(Invoer);
                    CurrentScreen = 1;
                    break;
                case 5:
                    //Valideer Pincode en ga naar scherm 3 (PinCodeInvoer)
                    if (debug.isDEBUG()) System.out.println("Aantal Inputs: " + InputCount);
                    Pincode = parseInt(PincodeString);
                    if (debug.isDEBUG()) System.out.println("Ingevoerde Pincode = " + Pincode);
                    //Verander Pincode
                    Invoer = "_ _ _ _ _ _";
                    PCM2.setInvoer(Invoer);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            sceneSwitcher.loadScene("PinCodeInvoer");
                        }
                    });
                    savedPin = Pincode;
                    Pincode = 0;
                    PincodeString = "";
                    InputCount = 0;
                    CurrentScreen = 3;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            PCM2.TerugNaarGebruikersMenu();
                        }
                    });
                    break;
                default:
                    break;
            }
            //Check of de Input A is
        } else if (stringContains("A", Input)) {
            switch (CurrentScreen) {
                case 3:
                    //Ga naar scherm 7 (GeldOpneemMenu)
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            GMC.NaarOpneemMenu();
                        }
                    });
                    CurrentScreen = 7;
                    break;
                case 4:
                    //Ga naar scherm 5 (PinCodeInput)
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            PCM.NaarPinMenu();
                        }
                    });
                    CurrentScreen = 5;
                    break;
                case 7:
                    //Test of er voldoende saldo is om er €10 van af te kunnen halen
                    DBC.veranderSaldo(RFID_String, savedPin, 1, -10);
                    updateOpnameMenu();
                    break;
                default:
                    break;
            }
            //Check of de Input B is
        } else if (stringContains("B", Input)) {
            switch (CurrentScreen) {
                case 3:
                    //Ga naar scherm 4 (PinCodeValidatie)
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            GMC.NaarPinMenu();
                        }
                    });
                    CurrentScreen = 4;
                    break;
                case 4:
                    //Ga naar scherm 3 (GebruikersMenu)
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            PCM.TerugNaarGebruikersMenu();
                        }
                    });
                    CurrentScreen = 3;
                    break;
                case 7:
                    //Test of er voldoende saldo is om er €20 van af te kunnen halen
                    DBC.veranderSaldo(RFID_String, savedPin, 1, -20);
                    updateOpnameMenu();
                    break;
                default:
                    break;
            }
            //Check of de Input C is
        } else if (stringContains("C", Input)) {
            switch (CurrentScreen) {
                case 7:
                    //Test of er voldoende saldo is om er €50 van af te kunnen halen
                    DBC.veranderSaldo(RFID_String, savedPin, 1, -50);
                    System.out.println(RFID_String);
                    System.out.println(savedPin);
                    System.out.println(DBC.getStringOutput());
                    System.out.println(DBC.getBankRekeningSaldo());
                    System.out.println(DBC.getSpaarRekeningSaldo());
                    updateOpnameMenu();
                    break;
                default:
                    break;
            }
            //Check of de Input D is
        } else if (stringContains("D", Input)) {
            switch (CurrentScreen) {
                case 3:
                    //Ga naar scherm (SaldoMenu)
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            GMC.NaarSaldoMenu();
                        }
                    });
                    SaldoM.setGeld(DBC.getBankRekeningSaldo());
                    System.out.println(DBC.getBankRekeningSaldo());
                    CurrentScreen = 6;
                    break;
                case 7:
                    //Test of er voldoende saldo is om er €100 van af te kunnen halen
                    DBC.veranderSaldo(RFID_String, savedPin, 1, -100);
                    updateOpnameMenu();
                    break;
                default:
                    break;
            }
            //Check of de Input # is
        } else if (stringContains("#", Input)) {
            switch (CurrentScreen) {
                case 1:
                    break;
                case 2:
                    //Verwijder alle opgeslagen data over de Pincode
                    Pincode = 0;
                    PincodeString = "";
                    InputCount = 0;
                    //Pas GUI aan op invoer
                    String Invoer = "_ _ _ _ _ _";
                    LIC.setInvoer(Invoer);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            sceneSwitcher.loadScene("LogIn");
                        }
                    });
                    break;
                case 5:
                    //Ga naar scherm 3 (GebrukersMenu)
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            PCM2.TerugNaarGebruikersMenu();
                        }
                    });
                    CurrentScreen = 3;
                    //Verwijder alle opgeslagen data over de Pincode
                    Pincode = 0;
                    PincodeString = "";
                    InputCount = 0;
                    break;
                case 6:
                    //Ga naar scherm 3 (GebruikersMenu)
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            SaldoM.TerugGebruikersMenu();
                        }
                    });
                    CurrentScreen = 3;
                    break;
                case 7:
                    //Ga naar scherm 3 (GebruikersMenu)
                    GOM.setText("");
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            GOM.TerugNaarGebruikersMenu();
                        }
                    });
                    CurrentScreen = 3;
                    break;
                default:
                    break;
            }
            //Check of de Input een cijfer is
        } else if (Input.length() == 1) {
            switch (CurrentScreen) {
                case 2:
                    //Voeg getal aan Pincode toe
                    PincodeString += Input;
                    InputCount++;

                    if (debug.isDEBUG()) System.out.println(PincodeString);
                    if (debug.isDEBUG()) System.out.println("Aantal Inputs: " + InputCount);
                    //Pas GUI aan op invoer
                    switch (InputCount) {
                        case 1:
                            String Invoer = "X _ _ _ _ _";
                            LIC.setInvoer(Invoer);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    sceneSwitcher.loadScene("LogIn");
                                }
                            });
                            break;
                        case 2:
                            Invoer = "X X _ _ _ _";
                            LIC.setInvoer(Invoer);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    sceneSwitcher.loadScene("LogIn");
                                }
                            });
                            break;
                        case 3:
                            Invoer = "X X X _ _ _";
                            LIC.setInvoer(Invoer);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    sceneSwitcher.loadScene("LogIn");
                                }
                            });
                            break;
                        case 4:
                            Invoer = "X X X X _ _";
                            LIC.setInvoer(Invoer);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    sceneSwitcher.loadScene("LogIn");
                                }
                            });
                            break;
                        case 5:
                            Invoer = "X X X X X _";
                            LIC.setInvoer(Invoer);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    sceneSwitcher.loadScene("LogIn");
                                }
                            });
                            break;
                        case 6:
                            Invoer = "X X X X X X";
                            LIC.setInvoer(Invoer);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    sceneSwitcher.loadScene("LogIn");
                                }
                            });
                            break;
                    }
                    break;
                case 5:
                    //Voeg Input aan Pincode toe
                    PincodeString += Input;
                    InputCount++;

                    if (debug.isDEBUG()) System.out.println(PincodeString);
                    if (debug.isDEBUG()) System.out.println("Aantal Inputs: " + InputCount);
                    //Pas GUI aan op invoer
                    switch (InputCount) {
                        case 1:
                            String Invoer = "X _ _ _ _ _";
                            PCM2.setInvoer(Invoer);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    sceneSwitcher.loadScene("PinCodeInvoer");
                                }
                            });
                            break;
                        case 2:
                            Invoer = "X X _ _ _ _";
                            PCM2.setInvoer(Invoer);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    sceneSwitcher.loadScene("PinCodeInvoer");
                                }
                            });
                            break;
                        case 3:
                            Invoer = "X X X _ _ _";
                            PCM2.setInvoer(Invoer);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    sceneSwitcher.loadScene("PinCodeInvoer");
                                }
                            });
                            break;
                        case 4:
                            Invoer = "X X X X _ _";
                            PCM2.setInvoer(Invoer);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    sceneSwitcher.loadScene("PinCodeInvoer");
                                }
                            });
                            break;
                        case 5:
                            Invoer = "X X X X X _";
                            PCM2.setInvoer(Invoer);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    sceneSwitcher.loadScene("PinCodeInvoer");
                                }
                            });
                            break;
                        case 6:
                            Invoer = "X X X X X X";
                            PCM2.setInvoer(Invoer);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    sceneSwitcher.loadScene("PinCodeInvoer");
                                }
                            });
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }


    }

    private boolean stringContains(String contains, String base) {
        return base.contains(contains);
    }

    private String stringRemove(String toBeRemoved, String base) {
        base = base.replace(toBeRemoved, "");
        return base;
    }

    private void updateOpnameMenu() {
        if (!DBC.isNietGenoegSaldo()) {

            GOM.setText("");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    GOM.TerugNaarGebruikersMenu();
                }
            });
            CurrentScreen = 3;
        } else {
            GOM.setText("Onvoldoende Saldo");
            if (debug.isDEBUG()) System.out.println("Niet Genoeg Geld!");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    sceneSwitcher.loadScene("GeldOpneemMenu");
                }
            });
        }

    }

}



