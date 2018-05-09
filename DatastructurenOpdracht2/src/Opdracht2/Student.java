package Opdracht2;

/**
 * De Klasse die de Studentengegevens bevat
 */
public class Student
{

    private int studentnummer;
    private String naam;
    private int leeftijd;
    private String geslacht;
    /**
     * De Volgende Student op de Stack
     */
    private Student next;

    /**
     * Constructor voor een volledige Student
     *
     * @param nummer   Het studentnummer van de student
     * @param Naam     De naam van de student
     * @param Leeftijd De leeftijd van de student
     * @param Geslacht Het geslacht van de student (Mag alleen v,V,m of M zijn)
     */
    public Student(int nummer, String Naam, int Leeftijd, String Geslacht)
    {
        studentnummer = nummer;
        naam = Naam;
        leeftijd = Leeftijd;
        if (Geslacht.equals("m") || Geslacht.equals("v") || Geslacht.equals("M") || Geslacht.equals("V"))
        {
            geslacht = Geslacht;
        }
        else
        {
            System.out.println("Verkeerde Input, probeer opnieuw");
        }

    }

    /**
     * Een Constructor voor een lege Student
     */
    public Student()
    {
    }

    /**
     * Geef de volgende student op de stack
     *
     * @return De volgende student op de stack
     */
    public Student getNext()
    {
        return next;
    }

    /**
     * Geef aan welke student na deze student op de stack komt
     *
     * @param next De volgende student op de stack
     */
    public void setNext(Student next)
    {
        this.next = next;
    }

    /**
     * Geef het Studentnummer van de student
     *
     * @return Het Studentnummer
     */
    public int getStudentnummer()
    {
        return studentnummer;
    }

    /**
     * Pas het Studentnummer aan
     *
     * @param studentnummer Het nieuwe studentnummer van de student
     */
    public void setStudentnummer(int studentnummer)
    {
        this.studentnummer = studentnummer;
    }

    /**
     * Geef de naam van de student
     *
     * @return De naam van de student
     */
    public String getNaam()
    {
        return naam;
    }

    /**
     * Pas de naam van de student aan
     *
     * @param naam De nieuwe naam van de student
     */
    public void setNaam(String naam)
    {
        this.naam = naam;
    }

    /**
     * Geef de leeftijd van de student
     *
     * @return De leeftijd van de student
     */
    public int getLeeftijd()
    {
        return leeftijd;
    }

    /**
     * Pas de leeftijd van de student aan
     *
     * @param leeftijd De nieuwe leeftijd van de student
     */
    public void setLeeftijd(int leeftijd)
    {
        this.leeftijd = leeftijd;
    }

    /**
     * Geef het geslacht van de student
     *
     * @return Het geslacht van de student
     */
    public String getGeslacht()
    {
        return geslacht;
    }

    /**
     * Pas het geslacht van de student aan
     *
     * @param Geslacht Het geslacht van de student (Mag alleen m,v,V of M zijn)
     */
    public void setGeslacht(String Geslacht)
    {
        if (Geslacht.equals("m") || Geslacht.equals("v") || Geslacht.equals("M") || Geslacht.equals("V"))
        {
            geslacht = Geslacht;
        }
        else
        {
            System.out.println("Verkeerde Input, probeer opnieuw");
        }
    }

    /**
     * Print alle gegevens van de student
     */
    public void printStudent()
    {
        System.out.println("Studentnummer:" + studentnummer);
        System.out.println("Naam:" + naam);
        System.out.println("Leeftijd:" + leeftijd);
        System.out.println("Geslacht:" + geslacht);
    }

    /**
     * Geeft een string terug met alle gegevens van de student gescheiden door ':"
     *
     * @return Alle gegevens van de student in een string
     */
    public String toString()
    {
        String info = studentnummer + ":" + naam + ":" + leeftijd + ":" + geslacht;
        return info;
    }

}
