package Opdracht1;

/**
 * De Klasse die de stack maakt
 */
public class Stack {
    /**
     * Het Student Object dat het begin van de Stack is
     */
    private Student start = new Student();
    /**
     * De grootte van de Stack
     */
    private int size = 0;

    /**
     * Vraag de grootte van de stack op
     *
     * @return De grootte van de stack
     */
    public int size() {
        return size;
    }

    /**
     * Gooi een Student op de Stack
     *
     * @param s Een Student Object
     */
    public void push(Student s) {
        //Plaatst Student op de stack
        if(!(peek(s))) {
            size++;
            Student temp;
            if (size == 0) {
                start.setNext(s);

            } else {
                temp = start.getNext();
                for (int i = 0; i <= size; i++) {
                    if (temp.getNext() != null) {
                        temp = temp.getNext();
                    } else temp.setNext(s);
                }
            }
        }else System.out.println(s.getNaam()+" staat al op de stack");
    }

    /**
     * Haal de laatste Student van de stack en return deze
     *
     * @return De laatste Student
     */
    public Student pop() {
        size--;
        Student temp;
        Student output = new Student();
        try {
            temp = start.getNext();
        } catch (NullPointerException NPE) {
            System.out.println("Er staat niks op de stack.");
            return null;
        }
        for (int i = 0; i <= size; i++) {
            if (i == size) {
                output = temp.getNext();
                temp.setNext(null);
            } else temp = temp.getNext();
        }
        return output;
    }

    /**
     * Check of een student op de stack staat
     *
     * @param s De student die gecheckt moet worden
     * @return Een boolean die 'true' is als de gegeven Student op de stack staat
     */
    public boolean peek(Student s) {
        //Return true als Student.studentnummer in de stack staat
        Student temp;
        temp = start.getNext();
        for (int i = 0; i <= size; i++) {
            if (temp.getStudentnummer() == s.getStudentnummer()) {
                return true;
            }
            temp = temp.getNext();

        }
        return false;
    }

    /**
     * Print de Stack
     */
    public void printStack() {
        Student temp = new Student();
        boolean succes = true;
        try {
            temp = start.getNext();
        } catch (NullPointerException NPE) {
            System.out.println("Er staat niks op de stack.");
            succes = false;
        }
        if (succes) {
            System.out.println("-------Alle Studenten Op De Stack------");

            for (int i = 0; i <= size; i++) {
                System.out.println(temp.toString());
                temp = temp.getNext();
            }
            System.out.println("-----------------END-------------------");

        }
    }

    /**
     * Print alle Mannelijke Studenten
     */
    public void printMen() {
        Student temp = new Student();
        boolean succes = true;
        try {
            temp = start.getNext();
        } catch (NullPointerException NPE) {
            System.out.println("Er staat niks op de stack.");
            succes = false;
        }
        if (succes) {
            System.out.println("-------Alle Mannen Op De Stack---------");

            for (int i = 0; i <= size; i++) {
                if (temp.getGeslacht().equals("m") || temp.getGeslacht().equals("M")) {
                    System.out.println(temp.toString());
                }
                temp = temp.getNext();
            }
            System.out.println("-----------------END-------------------");

        }
    }

    /**
     * Print alle Vrouwelijke Studenten
     */
    public void printWomen() {
        Student temp = new Student();
        boolean succes = true;
        try {
            temp = start.getNext();
        } catch (NullPointerException NPE) {
            System.out.println("Er staat niks op de stack.");
            succes = false;
        }
        if (succes) {
            System.out.println("-------Alle Vrouwen Op De Stack--------");
            for (int i = 0; i <= size; i++) {
                if (temp.getGeslacht().equals("v") || temp.getGeslacht().equals("V")) {
                    System.out.println(temp.toString());
                }
                temp = temp.getNext();

            }
            System.out.println("-----------------END-------------------");
        }
    }
}
