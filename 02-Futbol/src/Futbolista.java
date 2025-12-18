public class Futbolista extends Thread {

    private int ngols;
    private int ntirades;

    private final int NUM_JUGADORS = 11;
    private final int NUM_TIRADES = 20;
    private final float PROBABILITAT = 0.5f;

    public Futbolista(String name) {
        super(name);
        this.ngols = 0;
        this.ntirades = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
            if (Math.random() >= PROBABILITAT)
                ngols++;
            ntirades++;
        }
    }

    public int getNgols() {
        return ngols;
    }

    public void setNgols(int ngols) {
        this.ngols = ngols;
    }

    public int getNtirades() {
        return ntirades;
    }

    public void setNtirades(int ntirades) {
        this.ntirades = ntirades;
    }

    @Override
    public String toString() {
        return String.format("%s   \t-> %2d gols", getName(), getNgols());
    }

    public static void main(String[] args) {
        Futbolista pique = new Futbolista("Piqué");
        Futbolista vinicius = new Futbolista("Vinicius");
        Futbolista torres = new Futbolista("Torres");
        Futbolista ramos = new Futbolista("Ramos");
        Futbolista ronaldo = new Futbolista("Ronaldo");
        Futbolista lewan = new Futbolista("Lewan");
        Futbolista belli = new Futbolista("Belli");
        Futbolista arnau = new Futbolista("Arnau");
        Futbolista aspas = new Futbolista("Aspas");
        Futbolista messi = new Futbolista("Messi");
        Futbolista mbape = new Futbolista("Mbapé");

        System.out.println("Inici dels xuts --------------");
        pique.start();
        vinicius.start();
        torres.start();
        ramos.start();
        ronaldo.start();
        lewan.start();
        belli.start();
        arnau.start();
        aspas.start();
        messi.start();
        mbape.start();
        System.out.println("Fi dels xuts------------------");

        System.out.println("----- Estadistiques ------");
        System.out.println(
                String.format("""
                        %s
                        %s
                        %s
                        %s
                        %s
                        %s
                        %s
                        %s
                        %s
                        %s
                        %s
                        """, pique, vinicius, torres, ramos, ronaldo, lewan, belli, arnau, aspas, messi, mbape));
    }
}