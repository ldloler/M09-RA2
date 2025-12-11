public class Fil extends Thread {
    
    private int delay;
    private int headstart;

    public Fil(String nom) {
        super(nom);
    }

    public Fil(String nom, int headstart, int delay) {
        super(nom);
        this.headstart = headstart;
        this.delay = delay;
    }

    @Override
    public void run() {
        for (int i = 0; i < headstart; i++) {}

        for (int i = 0; i < 10; i++) {
            System.out.printf("%s %d\n", this.getName(), i);
            try {
                sleep(delay);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println("Acava el fil: " + this.getName());
    }
}
