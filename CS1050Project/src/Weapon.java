
public class Weapon extends Item {
	private int attackDamage;
	
	public Weapon(String name, String description, int attackDamage) {
		super(name, description);
		this.attackDamage = attackDamage;
	}
	
	public int getAttackDamage() {
		return attackDamage;
	}
	
	@Override
	public void use (Player target) {
		target.equip(this);
	}
}