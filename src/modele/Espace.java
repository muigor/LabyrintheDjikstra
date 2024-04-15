package modele;

/**
 * Classe qui représente une zone d'espace libre.
 */
public abstract class Espace extends Case {
	private Objet objet;
	private Personnage personnage;
	private Piege piege;

	public Espace(Objet objet, Personnage personnage, Piege piege) {
		this.objet = objet;
		this.personnage = personnage;
		this.piege = piege;
	}

	/**
	 * Méthode qui définit une action / un changement sur l'aventurier.
	 */
	@Override
	public void action(Aventurier a) {
		if (this.objet != null) {
			a.addInventaire(this.objet);
			System.out.println("Vous trouvez un(e) " + this.objet.getClassName() + " ! " + this.objet.getDescription());
			this.objet = null;
		}

		if (this.piege != null) {
			this.piege.action(a);
			a.addEffetsPieges(this.piege);
			this.piege = null;
		}
		if (this.personnage != null) {
			this.personnage.action(a);
			this.personnage = null;
		}

		double malus[] = a.getMalusPieges();
		double malusTemps = malus[0];
		double malusPdv = malus[1];

		double bonus[] = a.activerEffetsObjets(this);
		double bonusTemps = bonus[0];
		double bonusPdv = bonus[1];

		double coutTemps = (this.getTemps() + (this.getTemps() * malusTemps)) * bonusTemps;
		double coutPdv = (100 * this.getCoutPv() + (this.getCoutPv() * malusPdv)) * bonusPdv;

		System.out.println("Vous traversez un(e) " + this.getClassName() + " ! Traverser vous coûte " + coutPdv
				+ "% de vos PV max ! Vous mettez " + coutTemps + " jours à traverser !");

		a.setChrono(a.getChrono() + coutTemps);
		a.setPdv(a.getPdv() - coutPdv);

		System.out.println("Vous possédez dorénavant " + a.getPdv()
				+ "/100 points de vie et vous avez commencé votre aventure il y a " + a.getChrono() + " jours ! ");
	}

	public double getCoutTemps(Aventurier a) {
		double malus[] = a.getMalusPieges();
		double malusTemps = malus[0];
		double bonus[] = a.activerEffetsObjets(this);
		double bonusTemps = bonus[0];
		double coutTemps = (this.getTemps() + (this.getTemps() * malusTemps)) * bonusTemps;
		return coutTemps;
	}

	public double getCoutPv(Aventurier a) {
		double malus[] = a.getMalusPieges();
		double malusPdv = malus[1];
		double bonus[] = a.activerEffetsObjets(this);
		double bonusPdv = bonus[1];
		double coutPdv = (100 * this.getCoutPv() + (this.getCoutPv() * malusPdv)) * bonusPdv;
		return coutPdv;
	}

	public Objet getObjet() {
		return objet;
	}

	public void setObjet(Objet objet) {
		this.objet = objet;
	}

	public Personnage getPersonnage() {
		return personnage;
	}

	public void setPersonnage(Personnage personnage) {
		this.personnage = personnage;
	}

	public Piege getPiege() {
		return piege;
	}

	public void setPiege(Piege piege) {
		this.piege = piege;
	}

	public abstract double getCoutPv();

	public abstract double getTemps();

} /*----- Fin de la classe Espace -----*/
