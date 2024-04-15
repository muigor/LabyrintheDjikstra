package modele;

/**
 * Classe qui représente une case du labyrinthe.
 */
public abstract class Case {
	/**
	 * Méthode qui retourne le nom de la classe de l'objet.
	 */
	public final String getClassName() {
		return this.getClass().getSimpleName();
	}

	public abstract double getCoutTemps(Aventurier a);

	public abstract double getCoutPv(Aventurier a);

	/**
	 * Méthode abstraite qui permettra de définir une action / un changement sur
	 * l'aventurier.
	 */
	public abstract void action(Aventurier a);

	public abstract Objet getObjet();

	public abstract void setObjet(Objet objet);

	public abstract Personnage getPersonnage();

	public abstract void setPersonnage(Personnage personnage);

	public abstract Piege getPiege();

	public abstract void setPiege(Piege piege);
} /*----- Fin de la classe Case -----*/
