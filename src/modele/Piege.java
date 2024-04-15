package modele;

public abstract class Piege {

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
	public abstract void action(Aventurier a);

	public abstract String getNom();

	public abstract double getCoutPdv();

	public abstract double getMalusTemps();

	public abstract int getDureeMalusTemps();

	public abstract void setDuree_malus_temps(int m);

	public abstract int getDureeMalusPv();

	public abstract void setDuree_malus_pv(int m);

	public abstract String getDescription();
}
