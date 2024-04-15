package modele;

public class Personnage {

	/**
	 * Méthode qui retourne le nom de la classe de l'objet.
	 */
	public final String getClassName() {
		return this.getClass().getSimpleName();
	}

	/**
	 * Méthode abstraite qui permettra de définir une action / un changement sur
	 * l'aventurier.
	 */
	public void action(Aventurier a) {
		System.out.println("Vous rencontrez un " + this.getClassName() + " ! ");
	}

}
