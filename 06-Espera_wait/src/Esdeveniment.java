import java.util.LinkedList;
import java.util.List;

public class Esdeveniment {
    private int placesDisponibles;
    private List<Assistent> reserves = new LinkedList<Assistent>();

    public Esdeveniment(int maxim_places) throws Exception {
        if (maxim_places > 10) {
            throw new Exception("L'esdeveniment no pot tenir més de 10 places.");
        } else if (maxim_places < 0) {
            throw new Exception("L'esdeveniment no pot tenir places negatives.");
        } else {
            this.placesDisponibles = maxim_places;
        }
    }

    // Si l'assistent no pot fer la reserva retorna false,
    // si pot l'apunta a la llista i retorna true.
    public void ferReserva(Assistent a) throws InterruptedException {
        synchronized (this) {
            while (placesDisponibles == 0) {
                a.wait();
            }
            reserves.add(a);
            placesDisponibles--;
            System.out.println(
                    String.format("%s ha fet una reserva. Places disponibles: %d", a.getName(), placesDisponibles));
        }
    }

    // Si l'assistent no pot treure la reserva retorna false,
    // si pot retorna true.
    public void cancelaReserva(Assistent a) {
        synchronized (this) {

            if (reserves.contains(a)) {
                reserves.remove(a);
                placesDisponibles++;
                System.out.println(
                        String.format("%s ha cancel·lat una reserva. Places disponibles: %d",
                                a.getName(), placesDisponibles));
                a.notifyAll();
            } else {
                System.out.println(
                        String.format("%s no ha pogut cancel·lar una reserva inexistent. Places disponibles: %d",
                                a.getName(), placesDisponibles));
            }
        }
    }
}
