import java.util.Random;

public class Enemy {

    protected String type;
    protected int health;
    protected int attackPower;
    protected int defense;
    protected int experienceAward;
    protected Random random = new Random();
    protected String spriteBase;
    protected String spriteAttack;
    protected String spriteHurt;
    protected String spriteDead;
    protected String currentState;

    public Enemy(String type, int health, int attackPower, int defense, int experienceAward, String spritePrefix) {
        this.type = type;
        this.health = health;
        this.attackPower = attackPower;
        this.defense = defense;
        this.experienceAward = experienceAward;
        
        this.currentState = "base";
    }

    public void setState(String state) {
        this.currentState = state;
    }
    
    public void drawSprite() {
        switch (currentState) {
            case "attack": System.out.println(spriteAttack); break;
            case "hurt":   System.out.println(spriteHurt); break;
            case "dead":   System.out.println(spriteDead); break;
            default:       System.out.println(spriteBase); break;
        }
    }
    // attack player
    public void attack(Player target) {
        int damage = Math.max(attackPower - target.getDefense(), 0);
        System.out.println(type + " attacks " + target.getName() + " for " + damage + " damage.");
        target.takeDamage(attackPower);
    }
    
    public int getAttackPower() {
        return attackPower;
    }

    public void takeDamage(int amount) {
        health -= amount;
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