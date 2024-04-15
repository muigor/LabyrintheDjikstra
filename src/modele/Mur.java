package modele;

import java.awt.Point;

/**
 * Classe qui représente un mur.
 */
public class Mur extends Case {
	private static final int TEMPS = 20;

	/**
	 * Méthode qui définit une action / un changement sur l'aventurier.
	 */
	@Override
	public void action(Aventurier a) {
		boolean aPioche = false;
		boolean aMageBuff = false;
		for (Objet objet : a.getInventaire()) {
			if (objet instanceof MageBuff) {
				aMageBuff = true;
			}
			if (objet instanceof Pioche) {
				aPioche = true;
			}
		}
		if (aMageBuff) {
			System.out.println("L'enchantement du mage vous permet de traverser le mur sans perdre de temps ! ");
			System.out.println("Vous possédez dorénavant " + a.getPdv()
					+ "/100 points de vie et vous avez commencé votre aventure il y a " + a.getChrono() + " jours ! ");
		} else if (aPioche && !aMageBuff) {
			double malus[] = a.getMalusPieges();
			double malusTemps = malus[0];
			double coutTemps = TEMPS + TEMPS * malusTemps;
			System.out.println("Votre pioche vous permet de traverser le mur ! Vous mettez cependant " + coutTemps
					+ " jours à briser le mur !");
			a.setChrono(a.getChrono() + coutTemps);
			System.out.println("Vous possédez dorénavant " + a.getPdv()
					+ "/100 points de vie et vous avez commencé votre aventure il y a " + a.getChrono() + " jours ! ");
		} else {
			System.out.println("Vous vous êtes cogné contre un mur !");
			int ancienX = a.getHistoriqueTrajet().get(a.getHistoriqueTrajet().size() - 2).x;
			int ancienY = a.getHistoriqueTrajet().get(a.getHistoriqueTrajet().size() - 2).y;
			a.setX(ancienX);
			a.setY(ancienY);
			a.addHistoriqueTrajet(new Point(ancienX, ancienY));
		}

	}

	@Override
	public Objet getObjet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setObjet(Objet objet) {
		// TODO Auto-generated method stub

	}

	@Override
	public Personnage getPersonnage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPersonnage(Personnage personnage) {
		// TODO Auto-generated method stub

	}

	@Override
	public Piege getPiege() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPiege(Piege piege) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getCoutTemps(Aventurier a) {
		boolean aPioche = false;
		boolean aMageBuff = false;
		for (Objet objet : a.getInventaire()) {
			if (objet instanceof MageBuff) {
				aMageBuff = true;
			}
			if (objet instanceof Pioche) {
				aPioche = true;
			}
		}
		if (aMageBuff) {
			return 0;
		} else if (aPioche && !aMageBuff) {
			double malus[] = a.getMalusPieges();
			double malusTemps = malus[0];
			double coutTemps = TEMPS + TEMPS * malusTemps;
			return coutTemps;
		} else
			return 0;
	}

	@Override
	public double getCoutPv(Aventurier a) {
		// TODO Auto-generated method stub
		return 0;
	}

} /*----- Fin de la classe Mur -----*/
