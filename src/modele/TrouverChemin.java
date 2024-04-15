package modele;

import java.util.ArrayList;
import java.util.List;


public class TrouverChemin {

	public static boolean estPassable(Labyrinthe laby, PointTrouverChemin point) {
		if (point.getY() < 0 || point.getY() > laby.getTaille() - 1) {
			return false;
		}
		if (point.getX() < 0 || point.getX() > laby.getTaille() - 1) {
			return false;
		}
		if (laby.getCase(point.getX(), point.getY()).getClass() == Mur.class) {
			boolean murPassable = false;
			for (Objet objet : laby.getAventurier().getInventaire()) {
				if (objet.getClass() == MageBuff.class) {
					murPassable = true;
				} else if (objet.getClass() == Pioche.class) {
					murPassable = true;
				}
			}
			return murPassable;
		}
		if (laby.getCase(point.getX(), point.getY()).getClass() == Porte.class) {
			boolean portePassable = false;
			for (Objet objet : laby.getAventurier().getInventaire()) {
				if (objet.getClass() == Cle.class) {
					portePassable = true;
				}
			}
			return portePassable;
		}
		return true;
	}

	public static List<PointTrouverChemin> trouverVoisins(Labyrinthe laby, PointTrouverChemin point) {
		List<PointTrouverChemin> voisins = new ArrayList<>();
		PointTrouverChemin haut = point.decaler(0, 1);
		PointTrouverChemin bas = point.decaler(0, -1);
		PointTrouverChemin gauche = point.decaler(-1, 0);
		PointTrouverChemin droite = point.decaler(1, 0);
		if (estPassable(laby, haut))
			voisins.add(haut);
		if (estPassable(laby, bas))
			voisins.add(bas);
		if (estPassable(laby, gauche))
			voisins.add(gauche);
		if (estPassable(laby, droite))
			voisins.add(droite);
		return voisins;
	}

	public static List<PointTrouverChemin> trouverChemin(Labyrinthe laby, PointTrouverChemin depart,
			PointTrouverChemin arrivee) {
		boolean fini = false;
		List<PointTrouverChemin> passe = new ArrayList<>();
		passe.add(depart);
		while (!fini) {
			List<PointTrouverChemin> nouvelleOuverture = new ArrayList<>();
			for (int i = 0; i < passe.size(); ++i) {
				PointTrouverChemin point = passe.get(i);
				for (PointTrouverChemin voisin : trouverVoisins(laby, point)) {
					if (!passe.contains(voisin) && !nouvelleOuverture.contains(voisin)) {
						nouvelleOuverture.add(voisin);
					}
				}
			}

			for (PointTrouverChemin point : nouvelleOuverture) {
				passe.add(point);
				if (arrivee.equals(point)) {
					fini = true;
					break;
				}
			}

			if (!fini && nouvelleOuverture.isEmpty())
				return null;
		}

		List<PointTrouverChemin> chemin = new ArrayList<>();
		PointTrouverChemin point = passe.get(passe.size() - 1);
		while (point.getPrecedent() != null) {
			chemin.add(0, point);
			point = point.getPrecedent();
		}
		return chemin;
	}
}
