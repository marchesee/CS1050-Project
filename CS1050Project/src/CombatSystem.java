import java.util.Scanner;

public class CombatSystem {

    private Scanner scanner = new Scanner(System.in);

public void startCombat(Player player, Enemy enemy) {
        
        System.out.println("\n[Press ENTER to engage " + enemy.getType() + "...]");
        scanner.nextLine();

        while (player.isAlive() && enemy.isAlive()) {
            
            HUD.clearScreen();
            enemy.setState("base");
            enemy.drawSprite();
            System.out.println("\nEnemy: " + enemy.getType() + " | HP: " + enemy.getHealth());
            HUD.draw(player);

            System.out.println("1) Attack");
            System.out.println("2) Use Potion");
            System.out.println("3) Defend");
            System.out.print("> ");
            String choice = scanner.nextLine().trim();
            boolean turnConsumed = false;

            if (choice.equals("1")) {
                System.out.println("\nChoose your stance:");
                System.out.println("1) Standard (Reliable damage)");
                System.out.println("2) Heavy (High damage, 60% accuracy)");
                System.out.println("3) Precise (Lower damage, pierces armor)");
                System.out.print("Stance> ");
                
                int stanceChoice = 1;
                try {
                    stanceChoice = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Defaulting to Standard.");
                }

                HUD.clearScreen();
                
                enemy.setState("hurt");
                enemy.drawSprite();
                System.out.println(); 
                
                player.attack(enemy, stanceChoice);
                turnConsumed = true;
                
                System.out.println("\n[Press ENTER to end your turn]");
                scanner.nextLine(); 
                
            } else if (choice.equals("2")) {
                HUD.clearScreen();
                enemy.setState("base");
                enemy.drawSprite();
                System.out.println();
                
                turnConsumed = player.consumePotion(); 
                
                System.out.println("\n[Press ENTER to continue]");
                scanner.nextLine();
                
            } else if (choice.equals("3")) {
                HUD.clearScreen();
                enemy.setState("base");
                enemy.drawSprite();
                System.out.println();
                
                System.out.println(player.getName() + " braces for impact, raising their guard!");
                player.setDefending(true);
                turnConsumed = true;
                
                System.out.println("\n[Press ENTER to continue]");
                scanner.nextLine();
                
            } else { 
                System.out.println("Invalid choice."); 
                continue; 
            }

            if (!enemy.isAlive()) {
                HUD.clearScreen();
                enemy.setState("dead");
                enemy.drawSprite();
                System.out.println("\n*** " + enemy.getType() + " has been defeated! ***");
                break; 
            }

            if (turnConsumed) {
                HUD.clearScreen();
                enemy.setState("attack");
                enemy.drawSprite();
                
                enemy.attack(player);
                
                System.out.println("\n[Press ENTER to continue]");
                scanner.nextLine(); 
            }
            
            player.setDefending(false);
            player.updateStatusEffects();
        }
    }

private void pause(int milliseconds) {
    try {
        Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}

}