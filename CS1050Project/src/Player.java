import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {

    private String name;
    private int health;
    private int maxHealth;
    private int attackPower;
    private int defense;
    private int level;
    private int experience;
    private List<Item> inventory;
    private final int maxInventorySize = 10;
    private Weapon equippedWeapon;
    private Random random = new Random();
    private boolean isDefending = false;
    private int shatteredTurns = 0;
    

    public Player(String name, int maxHealth, int attackPower, int defense) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackPower = attackPower;
        this.defense = defense;
        this.level = 1;
        this.experience = 0;
        this.inventory = new ArrayList<>();
        this.equippedWeapon = null;
    }

    // attack an enemy
    public void attack(Enemy target, int stanceChoice) {
        int weaponDamage = (equippedWeapon != null) ? equippedWeapon.getAttackDamage() : 0;
        int baseAttack = attackPower + weaponDamage;
        
        int totalAttack = baseAttack;
        int effectiveDefense = target.getDefense();
        int hitChance = 100;
        String stanceName = "Standard";

        if (stanceChoice == 2) { 
            totalAttack = (int)(baseAttack * 1.5); 
            hitChance = 60;                        
            stanceName = "Heavy";
            
            if (random.nextInt(100) < 10) { 
                totalAttack = totalAttack * 2; 
                stanceName = "Devastating Heavy"; 
                System.out.println("\n*** CRITICAL HIT! You find a vital weak point! ***");
            }
        } else if (stanceChoice == 3) { 
            totalAttack = (int)(baseAttack * 0.8); 
            effectiveDefense = effectiveDefense / 2; 
            stanceName = "Precise";
        }

        System.out.println("\n" + name + " readies a " + stanceName + " strike...");

        if (random.nextInt(100) >= hitChance) {
            System.out.println(name + " swung wide! The attack missed entirely.");
            return; 
        }

        int damage = Math.max(totalAttack - effectiveDefense, 0);
        System.out.println("Damage reduced by " + effectiveDefense + " (Enemy DEF).");
        System.out.println(name + " hits " + target.getType() + " for " + damage + " damage.");
        
        target.takeDamage(damage); 
    }

    public void setDefending(boolean defending) {
    	this.isDefending = defending;
    }
    
    
    // take damage (just updates health, no prints here)
    public void takeDamage(int amount) {
        int damageTaken = Math.max(amount - defense, 0);
        health -= damageTaken;
    }

    public void heal(int amount) {
        health = Math.min(health + amount, maxHealth);
        System.out.println(name + " healed " + amount + " HP.");
    }

    public void shatterDefense(int turns) {
    	this.shatteredTurns = turns;
    	System.out.println("\n!!! " + name + "'s armor is SHATTERED! Defense reduced to 0 for " + turns + " turns! !!!");
    }
    
    public void updateStatusEffects() {
    	if (shatteredTurns > 0) {
    		shatteredTurns--;
    		if (shatteredTurns == 0) {
    			System.out.println("\n" + name + "'s armor recovers! Defense restored.");
    		}
    	}
    }
    
    public void gainExperience(int xp) {
        experience += xp;
        System.out.println(name + " gained " + xp + " XP.");
        if (experience >= level * 100) levelUp();
    }

    public void levelUp() {
        level++;
        maxHealth += 10;
        attackPower += 2;
        defense += 1;
        health = maxHealth;
        System.out.println(name + " leveled up to level " + level + "!");
    }
    
    public void equip(Weapon newWeapon) {
    	if (equippedWeapon != null) {
    		System.out.println("Unequipped " + equippedWeapon.getName());
    		inventory.add(equippedWeapon);
    	}
    	
    	equippedWeapon = newWeapon;
    	inventory.remove(newWeapon);
    	System.out.println(name + " equipped " + newWeapon.getName());
    }

    public boolean addItem(Item item) {
        if (inventory.size() < maxInventorySize) {
        	inventory.add(item);
        	return true;
        } else {
        	System.out.println("Your inventory is full!");
        	return false;
        }
    }
    
    public boolean consumePotion() {
    	for (int i = 0; i < inventory.size(); i++) {
    		
    		Item item = inventory.get(i); // Check if item is a potion
    		
    		if (item instanceof Potion) {
    			item.use(this); // Heal the player
    			inventory.remove(i); // Discard the empty potion
    			return true; // Successfully heal
    		}
    	}
    	System.out.println(name + " has no potions left.");
    	return false;
    }
    

    // getters
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getAttackPower() { return attackPower; }
    public int getDefense() { if (shatteredTurns > 0) { return 0; } if (isDefending) { return defense * 2; } return defense; }
    public int getLevel() { return level; }
    public int getExperience() { return experience; }
    public Weapon getEquippedWeapon() { return equippedWeapon; }
    public List<Item> getInventory() { return inventory; }
    public int getShatteredTurns() { return shatteredTurns; }
    public boolean isAlive() { return health > 0; }
    public boolean isDefending() { return isDefending; }
    public boolean isShattered() { return shatteredTurns > 0; }
}