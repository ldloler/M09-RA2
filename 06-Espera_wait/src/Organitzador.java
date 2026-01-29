import java.util.ArrayList;
import java.util.List;

public class Organitzador {
    private static List<Assistent> assistents = new ArrayList<Assistent>();
    private static Esdeveniment esdeveniment;

    private static void initEsdeveniment(int maxim_places) throws Exception {
        esdeveniment = new Esdeveniment(maxim_places);
    }

    private static void initAssistents(int num_assistents) {
        for (int i = 0; i < num_assistents; i++) {
            assistents.add(new Assistent("Assistent-" + i, esdeveniment));
        }

        for (Assistent assistent : assistents) {
            assistent.start();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println();
        initEsdeveniment(5);
        initAssistents(10);
    }
}
