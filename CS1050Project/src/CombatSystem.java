import java.util.Scanner;

public class CombatSystem {

    private Scanner scanner = new Scanner(System.in);

    public void startCombat(Player player, Enemy enemy) {

        System.out.println("\nYou encountered " + enemy.getType() + "!\n");

        while (player.isAlive() && enemy.isAlive()) {

            HUD.draw(player);   // always show HUD

            // enemy info
            System.out.println("Enemy: " + enemy.getType());
            System.out.println("Enemy Health: " + enemy.getHealth());
            System.out.println("Enemy Defense: " + enemy.getDefense());
            System.out.println();

            // options
            System.out.println("1) Attack");
            System.out.println("2) Use Potion");
            System.out.print("> ");

            String choice = scanner.nextLine().trim();
            boolean turnUsed = false;

            if (choice.equals("1")) {
            	player.attack(enemy);  // attack
            	turnUsed = true;
            } else if (choice.equals("2")) {
            	turnUsed = player.consumePotion();
            	if (!turnUsed) { continue; }
            } else { System.out.println("Invalid choice."); continue; }

            if (enemy.isAlive() && turnUsed) enemy.attack(player); // enemy turn

            System.out.println(); // separate turns
        }

        // after combat
        if (player.isAlive()) {
            System.out.println(enemy.getType() + " defeated!");
            player.gainExperience(enemy.getExperienceAward());

            Item loot = enemy.dropLoot();
            if (loot != null) {
            	player.addItem(loot);
            	System.out.println("You received: " + loot.getName() + "\n");
            } else {
            	System.out.println("Nothing of value dropped.");
            }    
        }
    }
}