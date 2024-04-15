package modele;

public class Mage extends Personnage {

	@Override
	public void action(Aventurier a) {
		super.action(a);
		System.out
				.println("Il vous enchante et vous procurre la capacité de traverser les murs sans perdre de temps ! ");
		a.addInventaire(new MageBuff());
	}

}
