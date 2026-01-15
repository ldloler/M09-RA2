import java.util.Random;

public class Treballador extends Thread {

    private int sou_anual_brut;
    private int edat_inici_treball;
    private int edat_fi_treball;
    private int edat_actual;
    private float cobrat;
    private Random rnd;

    public Treballador(String name, int sou_anual_brut, int edat_inici_treball, int edat_fi_treball) {
        super(name);
        this.sou_anual_brut = sou_anual_brut;
        this.edat_inici_treball = edat_inici_treball;
        this.edat_fi_treball = edat_fi_treball;
        this.edat_actual = 0;
        this.cobrat = 0.0f;
        this.rnd = new Random();
    }

    public int getEdat_actual() {
        return edat_actual;
    }

    public float getCobrat() {
        return cobrat;
    }

    public void cobra() {
        cobrat += sou_anual_brut / 12.0;
    }

    public void pagaImpostos() {
        cobrat -= sou_anual_brut / 12.0 * 0.24;
    }

    @Override
    public void run() {

        // Bucle anys
        for (int i = 0; i < edat_fi_treball; i++) {

            // Bucle messos
            for (int j = 0; j < 12; j++) {

                if (edat_actual < edat_inici_treball)
                    break;

                cobra();
                pagaImpostos();

                try {
                    sleep(rnd.nextInt(3));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            edat_actual++;
        }

    }

}
