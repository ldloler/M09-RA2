import java.util.Random;

public class Fumador extends Thread {
    private Estanc estanc;
    private int id;
    private Tabac tabac;
    private Llumi llumi;
    private Paper paper;
    private int fumades;

    private Random r = new Random();

    public Fumador(Estanc estanc, int id) {
        super();
        this.estanc = estanc;
        this.id = id;
        this.tabac = null;
        this.llumi = null;
        this.paper = null;
        this.fumades = 0;
    }

    private void fuma() {
        tabac = null;
        llumi = null;
        paper = null;
        System.out.println("Fumador " + id + " fumant");
        try {
            sleep(r.nextInt(500, 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fumades++;
        System.out.println("Fumador " + id + " ha fumat " + fumades + " vegades");
    }

    private void compraTabac() {
        try {
            this.tabac = estanc.venTabac();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void compraLlumi() {
        try {
            this.llumi = estanc.venLlumi();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void compraPaper() {
        try {
            this.paper = estanc.venPaper();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (fumades != 3) {

            if (tabac == null) {
                compraTabac();
                printCompre(id, "Tabac");
            }

            if (llumi == null) {
                compraLlumi();
                printCompre(id, "Llumi");
            }

            if (paper == null) {
                compraPaper();
                printCompre(id, "Paper");
            }

            if (potFumar()) {
                fuma();
            }

        }
    }

    private void printCompre(int fumador, String compra) {
        System.out.println(
                String.format("Fumador %d comprant %s", fumador, compra));
    }

    private boolean potFumar() {
        return (paper != null && llumi != null && tabac != null);
    }

}
