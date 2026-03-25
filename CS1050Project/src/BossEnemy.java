public class BossEnemy extends Enemy {
    
    private int ultimateCooldown;
    private boolean isCharging;

    public BossEnemy(String type, int health, int attackPower, int defense, int experienceAward, String spritePrefix) {
        super(type, health, attackPower, defense, experienceAward, spritePrefix);
        this.ultimateCooldown = 3; // number of normal attacks before it can charge
        this.isCharging = false;
    }

    @Override
    public void attack(Player target) {
        if (isCharging) {
            System.out.println("\n*** " + type + " unleashes its devastating ULTIMATE attack! ***");
            
            int effectiveDefense = target.getDefense() / 2;
            int damage = Math.max((attackPower * 2) - effectiveDefense, 0);
            
            System.out.println(type + " crushes " + target.getName() + " for " + damage + " damage!");
            target.takeDamage(damage);
            
            if (target.isDefending()) {
                System.out.println(type + "'s sheer power crushes right through your guard!");
                target.shatterDefense(3);
            } else {
                System.out.println("You took the hit, but your armor remains intact.");
            }
            
            isCharging = false;
            ultimateCooldown = 3;
            
        } else if (ultimateCooldown <= 0) {
            // warn about incoming ultimate attack
            System.out.println("\n*** " + type + " steps back and begins gathering massive energy... ***");
            System.out.println("(WARNING: A devastating attack is coming next turn!)");
            
            isCharging = true; 
            
        } else {
            // normal attack
            super.attack(target);
            ultimateCooldown--;
        }
    }
}