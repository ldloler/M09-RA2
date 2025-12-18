public class Futbolista extends Thread {

    private int ngols;
    private int ntirades;

    private final static int NUM_JUGADORS = 11;
    private final static int NUM_TIRADES = 20;
    private final static float PROBABILITAT = 0.5f;

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
        Futbolista[] futbolistas = new Futbolista[NUM_JUGADORS];
        String[] nom_jugadors = "Piqué,Vinicius,Torres,Ramos,Ronaldo,Lewan,Belli,Arnau,Aspas,Messi,Mbapé".split(",");

        for (int i = 0; i < futbolistas.length; i++) {
            futbolistas[i] = new Futbolista(nom_jugadors[i]);
        }

        System.out.println("Inici dels xuts --------------");
        for (Futbolista f : futbolistas) {
            f.start();
        }
        System.out.println("Fi dels xuts------------------");

        System.out.println("----- Estadistiques ------");
        StringBuilder sb = new StringBuilder();
        for (Futbolista f : futbolistas) {
            sb.append(String.format("%s\n", f));
        }

        System.out.println(sb.toString());
    } 
}