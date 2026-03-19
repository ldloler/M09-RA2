import java.util.Random;

public class Barber extends Thread {
    private Random r = new Random();
    private Barberia barberia;

    public Barber(String name, Barberia barberia) {
        super(name);
        this.barberia = barberia;
    }

    @Override
    public void run() {
        Client client;
        while (true) {
            synchronized (barberia.condBarber) {
                while ((client = barberia.seguentClient()) == null) {
                    System.out.println("Ningí en espera");
                    System.out.println("Barber " + getName() + " dormint");
                    try {
                        barberia.condBarber.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.printf("Li toca al client %s\n", client.getNom());
            client.tallarseElsCabells();

            try {
                sleep(900 + r.nextInt(0, 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
