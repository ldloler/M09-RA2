public class PrincipalEstricte {
    public static void main(String[] args) {
        Fil pepe = new Fil("Pepe", 0, 1);
        Fil juan = new Fil("Juan", 0, 1);

        pepe.start();
        juan.start();

        System.out.println("Acaba fil: Main");
    }
}
