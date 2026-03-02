import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private int health;
    private int maxHealth;
    private int attackPower;
    private int defense;
    private int level;
    private int experience;
    private List<Item> inventory;

    public Player(String name, int maxHealth, int attackPower, int defense) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackPower = attackPower;
        this.defense = defense;
        this.level = 1;
        this.experience = 0;
        this.inventory = new ArrayList<>();
    }

    public void attack(Enemy target) {
        System.out.println(name + " attacks " + target.getType());
        target.takeDamage(attackPower);
    }

    public void takeDamage(int amount) {
        int damageTaken = Math.max(amount - defense, 0);
        health -= damageTaken;
        System.out.println(name + " takes " + damageTaken + " damage.");
    }

    public void heal(int amount) {
        health = Math.min(health + amount, maxHealth);
        System.out.println(name + " heals for " + amount);
    }

    public void gainExperience(int xp) {
        experience += xp;
        System.out.println(name + " gained " + xp + " XP.");
        if (experience >= level * 100) {
            levelUp();
        }
    }

    public void levelUp() {
        level++;
        maxHealth += 10;
        attackPower += 2;
        defense += 1;
        health = maxHealth;
        System.out.println(name + " leveled up to level " + level + "!");
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }
}
