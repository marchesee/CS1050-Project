public class Golem extends Enemy {

    private boolean canAttack;

    public Golem(String type, int health, int attackPower, int defense, int experienceAward, String spritePrefix) {
        super(type, health, attackPower, defense, experienceAward, spritePrefix);
        this.canAttack = false; 
    }

    @Override
    public void attack(Player target) {
        if (canAttack) {
            int damage = attackPower + 10; 
            System.out.println(type + " slams the ground with massive force!");
            target.takeDamage(damage);
        } else {
            System.out.println(type + " is charging its attack...");
        }
        
        canAttack = !canAttack;
    }
}