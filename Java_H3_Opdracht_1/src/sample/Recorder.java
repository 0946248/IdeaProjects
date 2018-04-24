package sample;

public class Recorder {
int CrashedPlanes = 0;
    public void simulate(int amount) {
        Airplane[] airplanes = new Airplane[amount];
        for (int i = 0; i < amount; i++) {
            System.out.println("----------------------------------------------------------------------------------------");
            airplanes[i] = new Airplane(2, 4, 3);
            try {
                airplanes[i].flight();
            }
            catch (CrashException Crash){
                CrashedPlanes++;
            }



        }
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Van de "+amount+" vliegtuigen zijn er "+CrashedPlanes+" neer gestort."  );
    }
}
