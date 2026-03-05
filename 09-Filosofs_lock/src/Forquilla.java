import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Forquilla {
    private int num;
    private Lock bloqueig = new ReentrantLock(true);

    public Forquilla(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void agafar() {
        bloqueig.lock();
    }

    public void deixar() {
        bloqueig.unlock();
    }
}
