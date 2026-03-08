import java.util.Scanner;

public class Game {

    private Player player;
    private Room room;
    private CombatSystem combat;
    private Scanner scanner;

    public Game() {
        scanner = new Scanner(System.in);
        combat = new CombatSystem();
        player = new Player("Hero", 100, 10, 2);
        room = new Room();  // generate first room
    }

    public void start() {
        boolean running = true;

        while (running) {

            room.drawRoom();   // draw current room
            HUD.draw(player);  // always show player HUD

            System.out.println("Move with W/A/S/D or Q to quit:");
            System.out.print("> ");
            String input = scanner.nextLine().toUpperCase();

            int dx = 0, dy = 0;

            switch (input) {
                case "W": dy = -1; break;
                case "S": dy = 1; break;
                case "A": dx = -1; break;
                case "D": dx = 1; break;
                case "Q": running = false; continue;
                default: System.out.println("Invalid input"); continue;
            }

            room.movePlayer(dx, dy);

            // check if next to enemy
            if (room.isNearEnemy()) {
                combat.startCombat(player, room.getEnemy());

                //remove enemy and spawn door if dead
                if (!room.getEnemy().isAlive()) {
                    room.enemyDefeated();
                }
            }

            // check if player reached doorway
            if (room.reachedDoor()) {
                System.out.println("You enter the next room!\n");
                room = new Room();  // generate new room
            }

            if (!player.isAlive()) {
                System.out.println("GAME OVER.");
                running = false;
            }
        }

        System.out.println("The End.");
    }
}