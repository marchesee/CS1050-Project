public class Golem extends Enemy {

    private boolean canAttack;

    public Golem(int health, int attackPower, int defense, int experienceAward) {
        super("Golem", health, attackPower, defense * 2, experienceAward);
        this.canAttack = false; // starts charging
    }

    @Override
    public void attack(Player target) {

        if (canAttack) {
            int damage = getAttackPower() + 10; // stronger hit
            System.out.println(getType() + " slams the ground with massive force!");
            target.takeDamage(damage);
        } else {
            System.out.println(getType() + " is charging its attack...");
        }

        // Flip turn behavior
        canAttack = !canAttack;
    }
}