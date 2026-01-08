package Coet;

public class Motor extends Thread {
    private int pot_objectiu;
    private int pot_actual = 0;
    private String estat;

    public Motor(String name) {
        super(name);
    }

    public int getPot_objectiu() {
        return pot_objectiu;
    }

    public void setPot_objectiu(int pot_objectiu) {
        this.pot_objectiu = pot_objectiu;
    }

    public int getPot_actual() {
        return pot_actual;
    }

    @Override
    public void run() {

        do {

            while (pot_actual != pot_objectiu) {

                // Dormim el thread entre 1 i 2 segons per creixer de forma gradual.
                try {
                    sleep((int) (Math.random() * 1000) + 1000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
    
                if (pot_objectiu < pot_actual) {
                    pot_actual--;
                    estat = "Decre.";
                }
                else if (pot_objectiu > pot_actual) {
                    pot_actual++;
                    estat = "Incre.";
                }
                else estat = "FesRes";
    
                System.out.println(
                    String.format("\n%s: %s Objectiu: %d Actual: %d", getName(), estat, pot_objectiu, pot_actual)
                );
                
            }
            // Esperar una estona a tornar a comprobar.
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (pot_actual != 0);

    }
}
