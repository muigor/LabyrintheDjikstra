package modele;

public abstract class Objet {

	/**
	 * Méthode qui retourne le nom de la classe de l'objet.
	 */
	public final String getClassName() {
		return this.getClass().getSimpleName();
	}

	public abstract double[] action(Aventurier a, Espace e);

	public abstract double getBonusTemps();

	public abstract String getBonusEmplacement();

	public abstract double getBonusPdv();

	public abstract String getDescription();

}
