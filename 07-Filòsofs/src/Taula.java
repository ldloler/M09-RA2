public class Taula {
    private Filosof[] comensals;
    private Forquilla[] forquilles;

    public Taula(int numCmensals) {
        asignarForquilles(numCmensals);
        asignarComensals(numCmensals);
    }

    private void asignarComensals(int numCmensals) {
        comensals = new Filosof[numCmensals];

        for (int i = 0; i < comensals.length; i++) {
            comensals[i] = new Filosof("fil" + i,
                    forquilles[i],
                    forquilles[(i+1) % forquilles.length],
                    4);
        }
    }

    private void asignarForquilles(int numCmensals) {
        forquilles = new Forquilla[numCmensals];

        for (int i = 0; i < forquilles.length; i++) {
            forquilles[i] = new Forquilla(i);
        }
    }

    public void showTaula() {

        for (Filosof filosof : comensals) {
            System.err.println(
                    String.format("Comensal: %s esq:%d dret:%d", filosof.getName(),
                            filosof.getForquillaEsquerra().getNum(),
                            filosof.getForquillaDreta().getNum()));
        }

        System.err.println("--------------------------");

    }

    public void cridarATaula() {
        for (Filosof filosof : comensals) {
            filosof.start();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(4);

        taula.showTaula();
        taula.cridarATaula();
    }

}
