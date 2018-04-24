package Opdracht1;

public class Student {
    private int studentnummer;
    private String naam;
    private int leeftijd;
    private String geslacht;

    public int getStudentnummer() {
        return studentnummer;
    }

    public void setStudentnummer(int studentnummer) {
        this.studentnummer = studentnummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public void setLeeftijd(int leeftijd) {
        this.leeftijd = leeftijd;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String Geslacht) {
        if (Geslacht.equals("m") || Geslacht.equals("v") || Geslacht.equals("M") || Geslacht.equals("V")) {
            geslacht = Geslacht;
        } else {
            System.out.println("Verkeerde Input, probeer opnieuw");
        }
    }

    public Student(int nummer, String Naam, int Leeftijd, String Geslacht) {
        studentnummer = nummer;
        naam = Naam;
        leeftijd = Leeftijd;
        if (Geslacht.equals("m") || Geslacht.equals("v") || Geslacht.equals("M") || Geslacht.equals("V")) {
            geslacht = Geslacht;
        } else {
            System.out.println("Verkeerde Input, probeer opnieuw");
        }

    }

    public Student() {
    }

    public void printStudent() {
        System.out.println("Studentnummer:" + studentnummer);
        System.out.println("Naam:" + naam);
        System.out.println("Leeftijd:" + leeftijd);
        System.out.println("Geslacht:" + geslacht);
    }

    public String toString() {
        String info = studentnummer + ":" + naam + ":" + leeftijd + ":" + geslacht;
        return info;
    }
}
