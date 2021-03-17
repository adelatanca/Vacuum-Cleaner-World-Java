public class Vacuum {

    int[] pos;
    int[][] room;
    int dir = 0; // up: 0, right: 1, down: 2, left: 3
    int performance = 0; // Masura de performanta

    public Vacuum(int[][] room, int[] pos) {
        this.room = room;
        this.pos = pos;
    }

    public boolean move() {
        int x = 0, y = 0;
        switch (dir) {
        case 0:
        // up
            y = -1;
            break;
        case 1:
        // right
            x = 1;
            break;
        case 2:
        // down 
            y = 1;
            break;
        case 3:
        // left
            x = -1;
            break;
        }
        // Noile coordonate in functie de cum se va misca vacuum cleaner
        int newX = pos[0] + x;
        int newY = pos[1] + y;
        // Se verifica daca vacuum a depasit limita camerei
        boolean outOfbounds = (newX < 0 || newY < 0 || newX > room[0].length - 1 || newY > room[1].length - 1);
        if (outOfbounds || room[newX][newY] == 0) {
            return false;
        } else {
            pos = new int[] { newX, newY };
            System.out.println("Vacuum is now at position (x,y): " + pos[0] + " , " + pos[1]);
            return true;
        }
    }

    public void turnLeft() {
        dir -= 1;
        if (dir < 0)
            // Merge la cea mai din capat directie, pentru turnLeft() este 3
            dir = 3;
    }

    public void turnRight() {
        dir += 1;
        if (dir > 3)
            // Merge la cea mai din capat directie, pentru turnRight() este 0
            dir = 0;
    }

    // Variabila performance va retine masura de performanta, acorda un punct pentru fiecare patrat curatat
    public void clean() {
        performance++;
        System.out.println("Sucking at position (x,y) " + pos[0] + " , " + pos[1] + " and performance is: " + performance);
    }
}