import java.util.Random;

public class Assistent extends Thread {
    private float probabilitat = 50; // Probavilitat de fer la reserva sobre 100%
    private Esdeveniment esdeveniment;
    private Random r = new Random();

    public Assistent(String name, Esdeveniment esdeveniment) {
        super(name);
        this.esdeveniment = esdeveniment;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {

                // Si el numero es mes petit a probabilitat
                // fara la reserva, sino cancela.
                if (r.nextInt(100) < probabilitat) {
                    try {
                        esdeveniment.ferReserva(this);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    esdeveniment.cancelaReserva(this);
                }
            }

            try {
                sleep(r.nextLong(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
