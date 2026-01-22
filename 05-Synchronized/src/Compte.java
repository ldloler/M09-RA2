public class Compte {
    private static Compte instance;
    private float saldo;

    public Compte() throws Exception {
        if (instance != null)
            throw new Exception("Ja hi ha una instancia creada.");

        saldo = 0f;
    }

    public static Compte getInstance() {
        try {
            return new Compte();
        } catch (Exception e) {
            return instance;
        }
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

}
