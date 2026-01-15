public class Administracio {

    private int num_poblacio_activa = 50;
    private Treballador[] poblacio_activa = new Treballador[num_poblacio_activa];

    public Administracio() {

        for (int i = 0; i < this.num_poblacio_activa; i++) {
            poblacio_activa[i] = new Treballador(i + "", 25000, 20, 65);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Administracio administracio = new Administracio();

        for (Treballador treballador : administracio.poblacio_activa) {
            treballador.start();
            treballador.join();
        }

        for (Treballador treballador : administracio.poblacio_activa) {
            System.out.println(
                    String.format("Ciutadà-%-2s -> edat: %d / total: %.2f", treballador.getName(),
                            treballador.getEdat_actual(), treballador.getCobrat()));

        }

    }

}
