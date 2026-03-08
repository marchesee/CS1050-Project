public class HUD {

    // prints player stats and inventory
    public static void draw(Player player) {

        System.out.println("===== PLAYER STATUS =====");

        System.out.println("Health: " + player.getHealth() + "/" + player.getMaxHealth());
        System.out.println("Level: " + player.getLevel());
        System.out.println("XP: " + player.getExperience());

        System.out.print("Items: ");

        if (player.getInventory().isEmpty()) {
            System.out.println("None");
        } 
        else {

            for (Item item : player.getInventory()) {
                System.out.print(item.getName() + " ");
            }

            System.out.println();
        }

        System.out.println("=========================\n");
    }

    public static void clearScreen() {

        for (int i = 0; i < 40; i++) {
            System.out.println();
        }

    }
}