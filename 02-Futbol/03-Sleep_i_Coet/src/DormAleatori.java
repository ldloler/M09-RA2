public class DormAleatori extends Thread {
    private final long CREATED;

    public DormAleatori(String name) {
        super(name);
        CREATED = System.currentTimeMillis();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            
            int interval_aleatori = (int) (Math.random() * 1000);

            long total = System.currentTimeMillis() - CREATED;

            String mostrar = String.format("%-4s(%d)\t a dormir %4dms total %6dms", this.getName(), i, interval_aleatori,
                    total);
            System.out.println(mostrar);

            try {
                sleep(interval_aleatori);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws Exception {
        DormAleatori pep = new DormAleatori("Pep");
        DormAleatori joan = new DormAleatori("Joan");
        pep.start();
        joan.start();
        System.out.println("---- Fi main ----");
    }
}
