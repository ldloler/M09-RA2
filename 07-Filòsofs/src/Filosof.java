import java.util.Random;

public class Filosof extends Thread {
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private int gana;
    private Random r = new Random();

    public Filosof(String name, Forquilla forquillaEsquerra, Forquilla forquillaDreta, int gana) {
        super(name);
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

            // si menja
            // intenta agafar forquilla esquerra
            boolean teEsquerra = agafarForquilla(forquillaEsquerra);
            if (!teEsquerra) {
                // si al final no espera [0.5, 1]s i torna a probar
                try {
                    sleep(r.nextLong(500, 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            System.out.println(
                    String.format("Filòsof: %s agafa la forquilla esquerra %d", this.getName(),
                            forquillaEsquerra.getNum()));

            // si agafa esquerra,
            // intenta agafat forquilla dreta
            boolean teDreta = agafarForquilla(forquillaDreta);
            if (!teDreta) {

                forquillaEsquerra.setEnUs(false);
                System.out.println(
                        String.format("Filòsof: %s deixa la forquilla esquerra(%d) i espera (dreta ocupara)",
                                this.getName(), forquillaEsquerra.getNum()));
                gana++;
                // si al final no espera [0.5, 1]s i torna a probar
                try {
                    sleep(r.nextLong(500, 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            System.out.println(
                    String.format("Filòsof: %s agafa la forquilla dreta %d", this.getName(),
                            forquillaDreta.getNum()));

            // te les dos forquilles, menja
            menjar();

            forquillaEsquerra.setEnUs(false);
            forquillaDreta.setEnUs(false);

        }
    }

    private boolean agafarForquilla(Forquilla forquilla) {

        boolean teForquilla;

        if (forquilla.isEnUs()) {
            teForquilla = false;

            // si no pot segueix intentant per [0.5, 1]s
            long start = System.currentTimeMillis();
            long now = System.currentTimeMillis();
            long espera = r.nextLong(500, 1000);

            while (!teForquilla ^ (start - now) < espera) {
                teForquilla = forquilla.isEnUs();

                // Una mica d'espera per no saturar la cpu
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

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