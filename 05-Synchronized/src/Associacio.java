public class Associacio {

    private int numSocis;
    private Soci[] socis;

    public Associacio() throws Exception {
        this.numSocis = 1000;
        this.socis = new Soci[numSocis];

        for (int i = 0; i < socis.length; i++) {
            socis[i] = new Soci("soci " + i);
        }
    }

    public void instanciaCompteTempsSocis() {
        for (Soci soci : socis) {
            soci.start();
        }
    }

    public void esperaPeriodeSocis() throws InterruptedException {
        for (Soci soci : socis) {
            soci.join();
        }
    }

    public void mostraBalancComptes(Compte compte) {
        System.out.println("El compte es: " + compte.getSaldo());
    }

    public static void main(String[] args) throws Exception {
        Associacio associacio = new Associacio();

        associacio.instanciaCompteTempsSocis();
        associacio.esperaPeriodeSocis();
        associacio.mostraBalancComptes(associacio.socis[0].getCOMPTE());

    }
}
