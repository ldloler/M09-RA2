import java.util.Random;

public class Filosof extends Thread {
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private int num;
    private int gana;
    private Random r = new Random();

    public Filosof(int num, Forquilla forquillaEsquerra, Forquilla forquillaDreta, int gana) {
        super("fil" + num);
        this.num = num;
        this.forquillaEsquerra = forquillaEsquerra;
        this.forquillaDreta = forquillaDreta;
        this.gana = gana;
    }

    public Forquilla getForquillaEsquerra() {
        return forquillaEsquerra;
    }

    public Forquilla getForquillaDreta() {
        return forquillaDreta;
    }

    public void menjar() {
        System.out.println("Filòsof: " + this.getName() + " menja");

        try {
            sleep(r.nextLong(1000, 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Filòsof: " + this.getName() + " ha acabat de menjar");
        gana--;
        System.out.println("Filòsof: " + this.getName() + " gana=" + gana);
    }

    public void pensar() {
        System.out.println("Filòsof: " + this.getName() + " pensant");
        try {
            sleep(r.nextLong(1000, 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (gana > 0) {

            // menaj o pensa

            // si pensa
            if (r.nextLong(0, 1000) < 500) {
                pensar();
                continue;
            }

            // te les dos forquilles, menja
            try {
                if (agafarForquilles()) {
                    menjar();
                    deixarForquilles();
                }
                try {
                    sleep(r.nextLong(500, 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    synchronized private void deixarForquilles() {
        // System.out.println(
        //         String.format("Filòsof: %s deixa les forquilles", this.getName()));
        forquillaEsquerra.setEnUs(false);
        forquillaDreta.setEnUs(false);
        this.notifyAll();
    }

    synchronized private boolean agafarForquilles() throws InterruptedException {
        // si menja
        // intenta agafar forquilla esquerra
        if (!agafarForquilla(forquillaEsquerra)) {
            return false;
        }

        System.out.println(
                String.format("Filòsof: %s agafa la forquilla esquerra %d", this.getName(),
                        forquillaEsquerra.getNum()));

        // si agafa esquerra,
        // intenta agafat forquilla dreta
        if (!agafarForquilla(forquillaDreta)) {

            System.out.println(
                    String.format("Filòsof: %s deixa la forquilla esquerra(%d) i espera (dreta ocupara)",
                            this.getName(), forquillaEsquerra.getNum()));
            forquillaEsquerra.setEnUs(false);
            this.notifyAll();
            gana++;
            return false;
        }

        System.out.println(
                String.format("Filòsof: %s agafa la forquilla dreta %d", this.getName(),
                        forquillaDreta.getNum()));
        return true;
    }

    private boolean agafarForquilla(Forquilla forquilla) throws InterruptedException {

        boolean teForquilla;

        if (forquilla.isEnUs()) {
            teForquilla = false;

            // si no pot segueix intentant per [0.5, 1]s
            long start = System.currentTimeMillis();
            long now = System.currentTimeMillis();
            long espera = r.nextLong(500, 1000);

            while (!teForquilla ^ (start - now) < espera) {
                this.wait(espera);
                teForquilla = forquilla.isEnUs();

                // Una mica d'espera per no saturar la cpu
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // si no l'ha pogut agafar
            if (!teForquilla) {
                return false;
            }

        } else {
            teForquilla = true;
            forquilla.setEnUs(true);
        }

        return teForquilla;

    }

}