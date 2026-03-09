import java.util.Random;

public class Enemy {

    protected String type;
    protected int health;
    protected int attackPower;
    protected int defense;
    protected int experienceAward;
    protected Random random = new Random();

    public Enemy(String type, int health, int attackPower, int defense, int experienceAward) {
        this.type = type;
        this.health = health;
        this.attackPower = attackPower;
        this.defense = defense;
        this.experienceAward = experienceAward;
    }

    // attack player
    public void attack(Player target) {
        int damage = Math.max(attackPower - target.getDefense(), 0);
        System.out.println(type + " attacks " + target.getName() + " for " + damage + " damage.");
        target.takeDamage(attackPower);
    }

    public void takeDamage(int amount) {
        int damageTaken = Math.max(amount - defense, 0);
        health -= damageTaken;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public Item dropLoot() {
        int dropChance = random.nextInt(100);
        
        if (dropChance < 15) {
        	return new Potion("Greater Health Potion", "Restores 30 HP when consumed.", 30);
        } else if (dropChance < 20) {
        	return new Weapon ("Iron Dagger", "A sharp blade, doused in goblin blood.", 5);
        } else if (dropChance < 70) {
        	return new Potion ("Health Potion", "Restores 15 HP when consumed.", 15);
        } else {
        	return null;
        }
    }

    public int getExperienceAward() {
        return experienceAward;
    }

    public String getType() {
        return type;
    }

    public int getDefense() {
    	return defense; 
    }
    public int getHealth() {
    	return health; 
    }
}