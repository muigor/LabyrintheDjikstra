package modele;

import java.awt.Point;
import java.util.ArrayList;

public class Porte extends Case {
	private boolean estOuverte = false;

	@Override
	public void action(Aventurier a) {
		boolean aCle = false;
		for (Objet objet : a.getInventaire()) {
			if (objet instanceof Cle) {
				aCle = true;
			}
		}
		if (this.isEstOuverte()) {
			System.out.println("La porte est ouverte ! Vous pouvez passer.");
		} else if (aCle) {
			ArrayList<Objet> cle = new ArrayList<Objet>();
			System.out.println("Votre clé vous permet d'ouvrir la porte ! Elle a été retirée de votre inventaire.");
			this.setEstOuverte(true);
			if (a.getInventaire().size() != 0) {
				for (Objet objet : a.getInventaire()) {
					if (objet instanceof Cle) {
						cle.add(objet);
						break;
					}
				}
				a.getInventaire().removeAll(cle);
			}
		} else {
			System.out.println("La porte est fermée ! Vous n'avez pas de clé ! ");
			int ancienX = a.getHistoriqueTrajet().get(a.getHistoriqueTrajet().size() - 2).x;
			int ancienY = a.getHistoriqueTrajet().get(a.getHistoriqueTrajet().size() - 2).y;
			a.setX(ancienX);
			a.setY(ancienY);
			a.addHistoriqueTrajet(new Point(ancienX, ancienY));
		}
	}

	@Override
	public double getCoutTemps(Aventurier a) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCoutPv(Aventurier a) {
		// TODO Auto-generated method stub
		return 0;
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

	public boolean isEstOuverte() {
		return estOuverte;
	}

	public void setEstOuverte(boolean estOuverte) {
		this.estOuverte = estOuverte;
	}

}
