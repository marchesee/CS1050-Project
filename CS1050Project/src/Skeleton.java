import java.util.Random;

public class Skeleton extends Enemy {

    private boolean revived;
    private Random random = new Random(); // Instantiate once at the class level

    // Added spritePrefix to the constructor
    public Skeleton(String type, int health, int attackPower, int defense, int experienceAward, String spritePrefix) {
        super(type, health, attackPower, defense, experienceAward, spritePrefix);
        this.revived = false;
    }

    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);

        if (health <= 0 && !revived) {
            if (random.nextInt(100) < 50) { 
                revived = true;
                System.out.println("\n*** The Skeleton's bones rattle and reassemble! ***");
                
                // Health is protected, modify it directly
                health = 20; 
                
                // Force the visual state back from 'dead' to 'base'
                setState("base"); 
            }
        }
    }
}