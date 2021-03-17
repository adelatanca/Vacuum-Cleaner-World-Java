import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class VacuumCleanerWorld {

    public static void main(String args[]) {
        int posX, posY, dir;
        Scanner positions = new Scanner(System.in);
       // int[][] room = new int[4][4];
        int[][] room = { { 0, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 } };
        System.out.print("Room: ");
        System.out.println();
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[i].length; j++) {
              //  room[i][j] = ((int) (Math.random() * 2));
                System.out.print(" " + room[i][j]);
            }
            System.out.println();
        }
        System.out.println("Introduceti x: ");
        posX = positions.nextInt();
        System.out.println("Introduceti y: ");
        posY = positions.nextInt();
        System.out.println("Introduceti directia (0-up, 1-right, 2-down, 3-left): ");
        dir = positions.nextInt();
        int[] pos = new int[] { posX, posY };
        Vacuum vacuum = new Vacuum(room, pos);
        VacuumCleanerWorld vacuumCleanerWorld = new VacuumCleanerWorld();
        vacuumCleanerWorld.suckDirt(vacuum, posX, posY, dir);
        positions.close();
    }

    public void suckDirt(Vacuum vacuum, int x, int y, int dir) {
        // Vacuum incepe de la pozitia {1,1} si directia = 0, adica se misca UP si va
        // face SUCK unde gaseste 1, 0 este considerat curat
        depthFirstSearch(vacuum, x, y, dir, new HashSet<>());
    }

    private void depthFirstSearch(Vacuum vacuum, int i, int j, int dir, Set<String> visited) {
        // Identificarea patratului in care se afla vacuum cleaner
        String pos = i + "_" + j;
        // Se verifica daca Hashsetul contine pozitia actuala, daca zona a fost deja
        // vizitata va face return;
        if (visited.contains(pos))
            return;
        else { // Daca pozitia nu exista in Hashset, vacuum va curata zona
                vacuum.clean();
        }
        visited.add(pos);
        for (int n = 0; n < 4; n++) { // Vacuum cleaner se misca in toate directiile, + 90 grade la fiecare iteratie
            if (vacuum.move()) {
                int row = i, col = j;
                switch (dir) {
                // go up
                case 0:
                    row = i - 1;
                    break;
                // go right
                case 90:
                    col = j + 1;
                    break;
                // go down
                case 180:
                    row = i + 1;
                    break;
                // go left
                case 270:
                    col = j - 1;
                    break;
                }
                depthFirstSearch(vacuum, row, col, dir, visited);
                // Se intoarce la pozitia initiala
                vacuum.turnLeft();
                vacuum.turnLeft();
                vacuum.move();
                vacuum.turnRight();
                vacuum.turnRight();
            }
            // Se duce la urmatoarea directie
            vacuum.turnRight();
            dir += 90;
            dir = dir % 360;
        }
    }
}
