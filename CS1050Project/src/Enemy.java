import java.util.Random;

public class Enemy {

    protected String type;
    protected int health;
    protected int attackPower;
    protected int defense;
    protected int experienceAward;

    public Enemy(String type, int health, int attackPower, int defense, int experienceAward) {
        this.type = type;
        this.health = health;
        this.attackPower = attackPower;
        this.defense = defense;
        this.experienceAward = experienceAward;
    }

    public void attack(Player target) {
        System.out.println(type + " attacks " + target);
        target.takeDamage(attackPower);
    }

    public void takeDamage(int amount) {
        int damageTaken = Math.max(amount - defense, 0);
        health -= damageTaken;
        System.out.println(type + " takes " + damageTaken + " damage.");
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
}


