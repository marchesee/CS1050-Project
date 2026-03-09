
public class Potion extends Item {
	private int healingAmount;
	
	public Potion (String name, String description, int healingAmount) {
		super(name, description);
		this.healingAmount = healingAmount;
	}
	
	@Override
	public void use (Player target) {
		target.heal(healingAmount);
	}
}