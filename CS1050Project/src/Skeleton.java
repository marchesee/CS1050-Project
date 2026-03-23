import java.util.Random;

public class Skeleton extends Enemy {

    private boolean revived;

    public Skeleton(int health, int attackPower, int defense, int experienceAward) {
        super("Skeleton", health, attackPower, defense, experienceAward);
        this.revived = false;
    }

    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);

        // If "dead" and hasn't revived yet
        if (!isAlive() && !revived) {
            Random rand = new Random();

            if (rand.nextInt(100) < 50) { // 50% chance to revive
                revived = true;
                System.out.println("💀 The Skeleton reassembles itself!");
                
                // bring it back with some health
                revive();
            }
        }
    }

    private void revive() {
        // since health is private, this simulates healing
        super.takeDamage(-20); 
    }
}
