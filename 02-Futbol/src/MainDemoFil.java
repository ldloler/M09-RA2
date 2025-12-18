public class MainDemoFil {

    public static void main(String[] args) {
        Thread t = Thread.currentThread();

        System.out.println(
                String.format("""
                        Nom -> %s
                        Prioritat -> %d
                        toString() -> %s
                        """, t.getName(), t.getPriority(), t));
    }

}
