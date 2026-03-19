public class Client {
    private String nom;
    
    public Client(int id) {
        this.nom = "Client-" + id;
    }

    public String getNom() {
        return nom;
    }

    public void tallarseElsCabells() {
        System.out.println("Tallant cabell a " + nom);
    }
}