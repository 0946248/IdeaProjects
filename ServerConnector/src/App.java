import java.io.IOException;

public class App {

    public static void main(String[] args) {
        String Pas = "71090885";
        int Pin = 1234;
        int Terminal = 1;
        DatabaseConnector DBC = new DatabaseConnector();
        try {
            DBC.getVerification(Pas, Pin, Terminal);
            if (DBC.isSuccesfullLogin()) {
                System.out.println("Achternaam: " + DBC.getAchternaam());
                System.out.println("Saldo: " + DBC.getBankRekeningSaldo());
                System.out.println("Blocked: " + DBC.isBlocked());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}