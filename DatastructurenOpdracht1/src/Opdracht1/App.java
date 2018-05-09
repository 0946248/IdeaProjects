package Opdracht1;

/**
 * De Klasse die de opdracht uitvoert
 */
public class App {
    /**
     * De Main Methode voor Opdracht 1
     * @param args een ongebruikte String Array
     */
    public static void main(String[] args) {
        /**
         *Stap 1 - Maak de studenten aan
         */
        Student Janice = new Student(01, "Janice", 25, "v");
        Student Kermit = new Student(02, "Kermit", 18, "m");
        Student Gonzo = new Student(03, "Gonzo", 8, "m");
        Student Chef = new Student(04, "Swedish Chef", 35, "m");
        Student Piggy = new Student(05, "Miss Piggy", 28, "v");
        /**
         *Stap 2 - Maak een Stack
         */
        Stack s = new Stack();
        /**
         * Stap 3 - pop()
         */
        s.pop();
        /**
         * Stap 4 - Print de stack.
         */
        s.printStack();
        /**
         * Stap 5 - Print alle vrouwen in de stack.
         */
        s.printWomen();
        /**
         * Stap 6 - Push achtereenvolgens Janice, Kermit, Gonzo, Swedish Chef en Miss Piggy op de stack.
         */
        s.push(Janice);
        s.push(Kermit);
        s.push(Gonzo);
        s.push(Chef);
        s.push(Piggy);
        /**
         * Stap 7 - Print alle mannelijke studenten.
         */
        s.printMen();
        /**
         * Stap 8 - Print alle vrouwelijke studenten.
         */
        s.printWomen();
        /**
         * Stap 9 - Push Kermit nogmaals op de stack.
         */
        s.push(Kermit);
        /**
         * Stap 10 - Print de stack.
         */
        s.printStack();
        /**
         * Testing Other Functions
         */
        System.out.println("Is Swedish Chef on the stack? " + s.peek(Chef));

    }
}
