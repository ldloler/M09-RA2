import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Estanc extends Thread {
    private List<Tabac> tabacs;
    private List<Llumi> llumins;
    private List<Paper> papers;
    private Random r = new Random();
    private boolean obert;

    public Estanc(String name) {
        super(name);
        this.tabacs = new LinkedList<Tabac>();
        this.llumins = new LinkedList<Llumi>();
        this.papers = new LinkedList<Paper>();
        this.obert = true;
    }

    synchronized public void nouSubministrament() {
        switch (r.nextInt(1, 4)) {
            case 1:
                addTabac();
                System.out.println("Afegint Tabac");
                break;
            case 2:
                addLlumi();
                System.out.println("Afegint Llumi");
                break;
            case 3:
                addPaper();
                System.out.println("Afegint Paper");
                break;
            default:
                break;
        }
        notifyAll();
    }

    private void addPaper() {
        this.papers.add(new Paper());
    }

    private void addLlumi() {
        this.llumins.add(new Llumi());
    }

    private void addTabac() {
        this.tabacs.add(new Tabac());
    }

    synchronized public Paper venPaper() throws InterruptedException {
        while (this.papers.size() == 0) {
            wait();
        }

        Paper p = this.papers.removeFirst();

        return p;
    }

    synchronized public Llumi venLlumi() throws InterruptedException {
        while (this.llumins.size() == 0) {
            wait();
        } 

        Llumi l = this.llumins.removeFirst();

        return l;
    }

    synchronized public Tabac venTabac() throws InterruptedException {
        while (this.tabacs.size() == 0) {
            wait();
        }

        Tabac t = this.tabacs.removeFirst();

        return t;
    }

    public void tancarEstanc() {
        this.obert = false;
    }

    @Override
    public void run() {
        while (obert) {
            nouSubministrament();

            try {
                sleep(r.nextInt(500, 1500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
