import java.util.Random;

public class Filosof extends Thread {
    private Long iniciGana;
    private Long finalGana;
    private int gana;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private Random r = new Random();

    public Filosof(String name, Forquilla forquillaEsquerra, Forquilla forquillaDreta) {
        super(name);
        this.forquillaEsquerra = forquillaEsquerra;
        this.forquillaDreta = forquillaDreta;
        resetGana();
    }

    public void menjar() {
        if (!agafarForquilles())
            return;

        gana = calcularGana();

        System.out.println(this.getName() + " menja amb gana " + gana);

        try {
            sleep(r.nextLong(1000, 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(this.getName() + " ha acabat de menjar");
        resetGana();

        deixarForquilles();
    }

    public boolean agafarForquilles() {
        // Primer esquerra dps dreta
        agafarForquillaEsquerra();
        agafarForquillaDreta();
        System.out.println(
                String.format("%s tè forquilles esq(%d) dret(%d)", getName(), forquillaEsquerra.getNum(),
                        forquillaDreta.getNum()));
        return true;
    }

    public void agafarForquillaEsquerra() {
        forquillaEsquerra.agafar();
    }

    public void agafarForquillaDreta() {
        forquillaDreta.agafar();
    }

    public void deixarForquilles() {
        System.out.println(getName() + " deixa les forquilles");
        forquillaDreta.deixar();
        forquillaEsquerra.deixar();
    }

    public void pensar() {
        System.out.println(this.getName() + " pensant");

        // Inici gana
        iniciGana = System.currentTimeMillis();

        // Pensar
        try {
            sleep(r.nextLong(1000, 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int calcularGana() {
        finalGana = System.currentTimeMillis();

        return (int) (finalGana - iniciGana) / 1000;
    }

    public void resetGana() {
        iniciGana = System.currentTimeMillis();
        gana = 0;
    }

    // -------------------
    // Setters i Getters
    // -------------------

    public Long getIniciGana() {
        return iniciGana;
    }

    public void setIniciGana(Long iniciGana) {
        this.iniciGana = iniciGana;
    }

    public Long getFinalGana() {
        return finalGana;
    }

    public void setFinalGana(Long finalGana) {
        this.finalGana = finalGana;
    }

    public Forquilla getForquillaEsquerra() {
        return forquillaEsquerra;
    }

    public void setForquillaEsquerra(Forquilla forquillaEsquerra) {
        this.forquillaEsquerra = forquillaEsquerra;
    }

    public Forquilla getForquillaDreta() {
        return forquillaDreta;
    }

    public void setForquillaDreta(Forquilla forquillaDreta) {
        this.forquillaDreta = forquillaDreta;
    }

    @Override
    public void run() {
        while (true) {

            // menaj o pensa

            // Probabilitat  del 50 %
            if (r.nextLong(0, 10) < 5)
                pensar();
            else
                menjar();
        }

    }
}