import java.util.Scanner;

public class Game {

    private Player player;
    private Dungeon dungeon;
    private CombatSystem combat;
    private Scanner scanner;

    public Game() {
        scanner = new Scanner(System.in);
        combat = new CombatSystem();
        player = new Player("Hero", 100, 10, 2);
        dungeon = new Dungeon();
    }

    public void start() {
        boolean running = true;
        boolean isFirstTurn = true;

        while (running) {
        	if (!isFirstTurn) {
        		HUD.clearScreen();
        	} else {
        		isFirstTurn = false;
        	}
        	
        	Room currentRoom = dungeon.getCurrentRoom();
        	
            currentRoom.drawRoom();   // draw current room
            HUD.draw(player);  // always show player HUD

            System.out.println("Move with W/A/S/D, I to open inventory, or Q to quit:");
            System.out.print("> ");
            String input = scanner.nextLine().toUpperCase();

            int dx = 0, dy = 0;

            switch (input) {
                case "W": dy = -1; break;
                case "S": dy = 1; break;
                case "A": dx = -1; break;
                case "D": dx = 1; break;
                case "I": openInventory(); continue;
                case "Q": running = false; continue;
                default: System.out.println("Invalid input"); continue;
            }

            currentRoom.movePlayer(dx, dy);

            // check if next to enemy
            if (currentRoom.isNearEnemy()) {  
                Enemy currentEnemy = currentRoom.getEnemy();
                combat.startCombat(player, currentEnemy);

                //remove enemy and spawn door if dead
                if (!currentEnemy.isAlive()) {
                	HUD.clearScreen();
                	currentEnemy.setState("dead");
                    currentEnemy.drawSprite();
                    currentRoom.enemyDefeated();
                }	
                	if (currentEnemy.getType().equalsIgnoreCase("Ancient Dragon") || currentEnemy instanceof BossEnemy){
                    	System.out.println("\nWith a final, earth-shattering roar, the Ancient Dragon collapses.");
                    	System.out.println("You have cleared the dungeon. You win!");
                    	
                    	System.out.println("\nPress ENTER to exit the dungeon...");
                        scanner.nextLine();
                    	
                    	running = false;
                    	continue;
                }
            }

            // check if player reached doorway
            if (currentRoom.reachedDoor()) {
                System.out.println("You enter the next room!\n");
                dungeon.nextRoom();
            }

            if (!player.isAlive()) {
                System.out.println("GAME OVER.");
                running = false;
                
                
            }
        }
        
    	System.out.println("The End.");
      }
        
        private void openInventory() {
        	if (player.getInventory().isEmpty()) {
        		System.out.println("\nYour bag is empty. Press Enter to return to combat.");
        		scanner.nextLine();
        		return;
        }
        	
        	boolean managing = true;
        	while (managing) {
        		System.out.println("\n--- INVENTORY ---");
        		for (int i = 0; i < player.getInventory().size(); i++) {
        			System.out.println((i + 1) + ") " + player.getInventory().get(i).getName() + " - " + player.getInventory().get(i).getDescription());
        		}
        		System.out.println("Type the number of the item you want to use, or press 'B' to go back.");
        		System.out.print("> ");
        		
        		String inventoryInput = scanner.nextLine().trim().toUpperCase();
        		
        		if (inventoryInput.equals("B")) {
        			managing = false;
        			continue;
        		}
        		
        		try {
        			int choice = Integer.parseInt(inventoryInput) - 1;
        			
        			if (choice >= 0 && choice < player.getInventory().size()) {
        				Item selectedItem = player.getInventory().get(choice);
        				selectedItem.use(player);
        				if (selectedItem instanceof Potion) {
        					player.getInventory().remove(choice);
        				}
        				managing = false;
        			} else {
        				System.out.println("You don't have that many items. Enter a valid number.");
        			}
        		} catch (NumberFormatException e) {
        			System.out.println("Please enter a valid number or 'B'.");
        		}
        	}
       	}
}