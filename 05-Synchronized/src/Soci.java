import java.util.Random;

public class Soci extends Thread {

    private final Compte COMPTE;
    private float aportacio = 10f;
    private int esperaMax = 100;
    private Random random = new Random();
    private int maxAnys = 10;

    public Soci(String name) throws Exception {
        super(name);
        this.COMPTE = Compte.getInstance();
    }

    public Compte getCOMPTE() {
        return COMPTE;
    }

    @Override
    public void run() {

        for (int i = 0; i < maxAnys; i++) {
            for (int j = 0; j < 12; j++) {
                synchronized (this) {
                    COMPTE.setSaldo(COMPTE.getSaldo() + calcula_quota(i));
                }

                try {
                    sleep(random.nextLong(esperaMax));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        }

    }

    private float calcula_quota(int mes) {
        if ((mes % 2) == 1)
            return aportacio * (-1);
        else return aportacio;
    }

}
