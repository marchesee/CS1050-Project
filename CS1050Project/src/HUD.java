public class HUD {

    // prints player stats and inventory
	public static void draw(Player player) {

        System.out.println("===== PLAYER STATUS =====");

        System.out.println("Health: " + player.getHealth() + "/" + player.getMaxHealth());
        System.out.println("Level: " + player.getLevel() + "   XP: " + player.getExperience());

        if (player.isShattered()) {
            System.out.println("Status: !! SHATTERED !! (" + player.getShatteredTurns() + " turns remaining)");
        } else if (player.isDefending()) {
            System.out.println("Status: [Defending] Guard is raised");
        } else {
            System.out.println("Status: Normal");
        }

        String weaponName = (player.getEquippedWeapon() != null) ? player.getEquippedWeapon().getName() : "Fists";
        int weaponDmg = (player.getEquippedWeapon() != null) ? player.getEquippedWeapon().getAttackDamage() : 0;
        int baseAtk = player.getAttackPower() + weaponDmg;

        System.out.println("Weapon: " + weaponName);
        System.out.println("Damage Potential: ");
        System.out.println("  > Standard: " + baseAtk);
        System.out.println("  > Heavy:    " + (int)(baseAtk * 1.5) + " (60% Accuracy)");
        System.out.println("  > Precise:  " + (int)(baseAtk * 0.8) + " (Armor Piercing)");
        
        System.out.println("Current Defense: " + player.getDefense());

        System.out.println("\nBag (" + player.getInventory().size() + "/10): ");
        if (player.getInventory().isEmpty()) {
            System.out.println("  [Empty]");
        } else {
            for (int i = 0; i < player.getInventory().size(); i++) {
                System.out.println("  " + (i + 1) + ". " + player.getInventory().get(i).getName());
            }
        }

        System.out.println("=========================\n");
    }

	public static void clearScreen() {
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }
    }
	
}