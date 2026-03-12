public class Barri {
    private Estanc estanc;
    private Fumador[] fumadors;

    public Barri() {
        this.estanc = new Estanc("Mollet");
        this.fumadors = new Fumador[3];
        initFumadors();
    }

    private void initFumadors() {
        for (int i = 0; i < 3; i++) {
            fumadors[i] = new Fumador(estanc, i);
        }
    }

    private void startFumadors() {
        for (Fumador fumador : fumadors) {
            fumador.start();
        }
    }

    private void startEstanc() {
        estanc.start();
        System.out.println("Estanc obert");
    }

    private void esperaFumadors() throws InterruptedException {
        for (Fumador fumador : fumadors) {
            fumador.join();
        }
    }

    private void tancaEstanc() {
        estanc.tancarEstanc();
    }

    public static void main(String[] args) throws InterruptedException {
        Barri b = new Barri();

        b.initFumadors();

        b.startFumadors();

        b.startEstanc();

        b.esperaFumadors();

        b.tancaEstanc();
    }

}
