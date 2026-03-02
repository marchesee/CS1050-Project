public class BossEnemy extends Enemy {

    private int ultimateCooldown;

    public BossEnemy(String type, int health, int attackPower, int defense, int experienceAward) {
        super(type, health, attackPower, defense, experienceAward);
        this.ultimateCooldown = 3; // example cooldown
    }

    public void useUltimate(Player player) {
        if (ultimateCooldown == 0) {
            System.out.println(type + " uses ULTIMATE ability!");
            player.takeDamage(attackPower * 2);
            ultimateCooldown = 3; // reset cooldown
        } else {
            System.out.println("Ultimate not ready. Cooldown: " + ultimateCooldown);
            ultimateCooldown--;
        }
    }
}
