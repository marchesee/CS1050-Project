public class HUD {

    // prints player stats and inventory
    public static void draw(Player player) {

        System.out.println("===== PLAYER STATUS =====");

        System.out.println("Health: " + player.getHealth() + "/" + player.getMaxHealth());
        System.out.println("Level: " + player.getLevel());
        System.out.println("XP: " + player.getExperience());

        String weaponName = (player.getEquippedWeapon() != null) ? player.getEquippedWeapon().getName() : "Fists";
        int weaponDmg = (player.getEquippedWeapon() != null) ? player.getEquippedWeapon().getAttackDamage() : 0;

        System.out.println("Weapon: " + weaponName + " (Total ATK: " + (player.getAttackPower() + weaponDmg) + ")");
        System.out.println("Bag (" + player.getInventory().size() + "/10): ");
        
        if (player.getInventory().isEmpty()) {
            System.out.println("  [Empty]");
        } else {
            for (int i = 0; i < player.getInventory().size(); i++) {
                System.out.println("  " + (i + 1) + ". " + player.getInventory().get(i).getName());
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