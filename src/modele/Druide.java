package modele;

public class Druide extends Personnage {

	private Antidote antidote = new Antidote();

	@Override
	public void action(Aventurier a) {
		super.action(a);
		System.out
				.println("Il vous donne un " + antidote.getClassName() + " ! Il a été placé dans votre inventaire ! ");
		a.addInventaire(antidote);
	}

}
