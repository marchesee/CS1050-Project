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
        return new Item("Gold Coin", "A shiny gold coin dropped by " + type);
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