package sample;

import gnu.io.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.Enumeration;

import static java.lang.Integer.parseInt;


public class Main extends Application implements SerialPortEventListener {
    /************************Variabelen********************************/
    String RFID_String;
    String PincodeString = "";
    int Pincode = 0;
    int InputCount = 0;
    int RFID;
    SerialPort serialPort;
    BorderPane LogIn = new BorderPane();
    BorderPane GebruikersMenu = new BorderPane();

    Stage window = new Stage();
    Scene LogInScherm = new Scene(LogIn, 800,600);
    Scene GebruikersMenuScherm = new Scene (GebruikersMenu, 800, 600);


/************************Arduino Input******************************/
    /**
     * The port we're normally going to use.
     */
    private static final String PORT_NAMES[] = {
            "COM5", "COM3", "COM4", "COM6" // Windows
    };
    /**
     * A BufferedReader which will be fed by a InputStreamReader
     * converting the bytes into characters
     * making the displayed results codepage independent
     */
    private BufferedReader input;
    /**
     * The output stream to the port
     */
    private OutputStream output;
    /**
     * Milliseconds to block while waiting for port open
     */
    private static final int TIME_OUT = 2000;
    /**
     * Default bits per second for COM port.
     */
    private static final int DATA_RATE = 9600;

    public void initialize() {
        // the next line is for Raspberry Pi and
        // gets us into the while loop and was suggested here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
        //System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");

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
            System.out.println("Could not find COM port.");
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

    /**
     * This should be called when you stop using the port.
     * This will prevent port locking on platforms like Linux.
     */
    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    /**
     * Handle an event on the serial port. Read the data and print it.
     */
    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String inputLine = input.readLine();
                ArduinoInput(inputLine);

            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
        // Ignore all the other eventTypes, but you should consider the other ones.
    }



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Bank GUI");

        Label WelkomTekst = new Label("Welkom bij de Bank");
        Button LogInButton = new Button();
        LogInButton.setText("Log In");

        LogIn.setTop(WelkomTekst);
        LogIn.setBottom(LogInButton);
        //LogIn.getChildren().addAll(WelkomTekst, LogInButton);
        LogIn.setAlignment(WelkomTekst, Pos.CENTER);
        LogIn.setAlignment(LogInButton, Pos.CENTER);


        Label GMtekst = new Label("GebruikersMenu");
        Label GMsaldo = new Label("Saldo:$0");
        Button GMexit = new Button("Terug naar Log In Scherm");
        GebruikersMenu.setTop(GMtekst);
        GebruikersMenu.setCenter(GMsaldo);
        GebruikersMenu.setLeft(GMexit);
        GebruikersMenu.setAlignment(GMtekst, Pos.CENTER);
        GebruikersMenu.setAlignment(GMsaldo, Pos.CENTER);
        GebruikersMenu.setAlignment(GMexit, Pos.CENTER);


        LogInButton.setOnAction(e-> {
            window.setScene(GebruikersMenuScherm);
        });
        GMexit.setOnAction(e-> {
            window.setScene(LogInScherm);
        });

        window.setScene(LogInScherm);
        window.show();
        Main main = new Main();
        main.initialize();
        Thread t = new Thread() {
            public void run() {
                //the following line will keep this app alive for 1000 seconds,
                //waiting for events to occur and responding to them (printing incoming messages to console).
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ie) {
                }
            }
        };
        t.start();


    }

    public void ArduinoInput(String Input) {
        System.out.println("Received Input: " + Input);
        if (stringContains("ID:", Input)) {
            RFID_String = stringRemove("ID:", Input);
            System.out.println(RFID_String);

        } else if (stringContains("*", Input)) {
            System.out.println(Input);
            InputCount = 0;
            System.out.println("Aantal Inputs: " + InputCount);
            Pincode = parseInt(PincodeString);
            RFID = parseInt(RFID_String);
            System.out.println("Ingevoerde Pincode = " + Pincode);
            if (RFID == 71090885 && Pincode == 1234) {

            ForceLogIn();

            }

        } else if (Input.length() == 1) {
            PincodeString += Input;
            InputCount++;

            System.out.println(PincodeString);
            System.out.println("Aantal Inputs: " + InputCount);
        }

    }
    public boolean stringContains(String contains, String base) {
        if (base.contains(contains)) {
            return true;
        } else return false;
    }

    public String stringRemove(String toBeRemoved, String base) {
        String newBase = base.replace(toBeRemoved, "");
        return newBase;
    }


    public void ForceLogIn(){
        window.setScene(GebruikersMenuScherm);
    }



}