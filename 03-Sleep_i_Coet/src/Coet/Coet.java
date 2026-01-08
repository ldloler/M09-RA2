package Coet;

import java.util.Scanner;

public class Coet {

    private Motor[] motors = {
            new Motor("Motor 0"),
            new Motor("Motor 1"),
            new Motor("Motor 2"),
            new Motor("Motor 3")
    };

    public void passarAPotencia(int p) {
        if (p < 0 || p > 10) {
            System.out.println("Error, la potencia no pot ser: " + p);
            return;
        }

        System.out.println("Passant a potencia " + p);

        for (Motor motor : motors) {
            motor.setPot_objectiu(p);
        }

        arranca();

    }

    private void arranca() {
        for (Motor motor : motors) {
            if (motor.getState() == Thread.State.NEW) {
                motor.start();
            }
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Coet c = new Coet();

        int p;
        do {
            p = s.nextInt();
    
            c.passarAPotencia(p);
        } while (p != 0);

        s.close();
    }
}
