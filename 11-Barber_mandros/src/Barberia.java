import java.util.LinkedList;

public class Barberia extends Thread {
    private LinkedList<Client> salaEspera;
    private int maxCadires;
    public Object condBarber = 1;
    static private Barberia barberia;

    public Barberia(int maxCadires) {
        this.maxCadires = maxCadires;
        this.salaEspera = new LinkedList<Client>();
    }

    public Client seguentClient() {
        return salaEspera.pollFirst();
    }

    public void entrarClient(Client client) {
        if (salaEspera.size() < maxCadires) {
            synchronized (condBarber) {
                salaEspera.add(client);
                System.out.printf("Client %s en espera\n", client.getNom());
                condBarber.notifyAll();
            }
        } else {
            System.out.printf("No queden cadires, client %s s'en va\n", client.getNom());
        }
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            entrarClient(new Client(i));

            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 11; i <= 20; i++) {
            entrarClient(new Client(i));

            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        barberia = new Barberia(3);
        Barber barber = new Barber("Marc", barberia);
        barber.start();
        barberia.start();
    }

}
