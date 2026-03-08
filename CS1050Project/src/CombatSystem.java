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
            System.out.println();

            // options
            System.out.println("1) Attack");
            System.out.println("2) Heal");
            System.out.print("> ");

            int choice = scanner.nextInt();

            if (choice == 1) player.attack(enemy);  // attack
            else if (choice == 2) player.heal(10);  // heal
            else { System.out.println("Invalid choice."); continue; }

            if (enemy.isAlive()) enemy.attack(player); // enemy turn

            System.out.println(); // separate turns
        }

        // after combat
        if (player.isAlive()) {
            System.out.println(enemy.getType() + " defeated!");
            player.gainExperience(enemy.getExperienceAward());

            Item loot = enemy.dropLoot();
            player.addItem(loot);
            System.out.println("You received: " + loot.getName() + "\n");
        }
    }
}