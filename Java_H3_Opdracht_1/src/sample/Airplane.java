package sample;

public class Airplane {
    boolean PlaneisCrashing = false;


    public Airplane(int Flaps, int Engines, int Pilots) {
        System.out.println("Airplane Created");
        Flap[] Flap = new Flap[Flaps];
        Engine[] Engine = new Engine[Engines];
        Pilot[] Pilot = new Pilot[Pilots];
        for (int i = 0; i < Flaps; i++) {
            Flap[i] = new Flap();
        }
        for (int i = 0; i < Engines; i++) {
            Engine[i] = new Engine();
        }
        for (int i = 0; i < Pilots; i++) {
            Pilot[i] = new Pilot();
        }
    }

    public void flight() throws CrashException {
       if(PlaneisCrashing) {
           throw new CrashException();
       }
    }
}
