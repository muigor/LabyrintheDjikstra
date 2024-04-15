package modele;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Classe qui représente l'aventurier qui traverse le labyrinthe.
 */
public class Aventurier {
	/*------------*/
	/* Propriétés */
	/*------------*/

	/*----- Sa position dans le labyrinthe -----*/
	private int x;
	private int y;
	private double pdv;
	private double chrono;
	private boolean arrive;
	private ArrayList<Objet> inventaire;
	private ArrayList<Piege> effetsPieges;
	private ArrayList<Point> historiqueTrajet;

	/*--------------*/
	/* Constructeur */
	/*--------------*/

	public Aventurier(int x, int y) {
		this.x = x;
		this.y = y;
		this.pdv = 100;
		this.chrono = 0;
		this.arrive = false;
		this.inventaire = new ArrayList<Objet>();
		this.effetsPieges = new ArrayList<Piege>();
		this.historiqueTrajet = new ArrayList<Point>();
	}

	/*----------*/
	/* Méthodes */
	/*----------*/

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getPdv() {
		return this.pdv;
	}

	public void setPdv(double pdv) {
		this.pdv = pdv;
		if (this.pdv < 0) {
			this.pdv = 0;
		}
	}

	public ArrayList<Objet> getInventaire() {
		return this.inventaire;
	}

	public void addInventaire(Objet objet) {
		this.inventaire.add(objet);
	}

	public double getChrono() {
		return chrono;
	}

	public void setChrono(double chrono) {
		this.chrono = chrono;
	}

	public ArrayList<Piege> getEffetsPieges() {
		return effetsPieges;
	}

	public void addEffetsPieges(Piege piege) {
		ArrayList<Piege> piegesARenouveler = new ArrayList<Piege>();
		for (Piege p : this.effetsPieges) {
			if (p.getClassName().equals(piege.getClassName())) {
				piegesARenouveler.add(p);
			}
		}
		this.effetsPieges.removeAll(piegesARenouveler);
		this.effetsPieges.add(piege);
	}

	public ArrayList<Point> getHistoriqueTrajet() {
		return historiqueTrajet;
	}

	public void addHistoriqueTrajet(Point point) {
		this.historiqueTrajet.add(point);
	}

	public double[] getMalusPieges() {
		double effetsPieges[] = new double[2];
		double effetTemps = 0;
		double effetPdv = 0;
		effetsPieges[0] = effetTemps;
		effetsPieges[1] = effetPdv;

		if (this.effetsPieges.size() <= 0) {
			return effetsPieges;
		}

		ArrayList<Piege> piegesExpires = new ArrayList<Piege>();
		for (Piege piege : this.effetsPieges) {
			if (piege.getDureeMalusPv() > 1) {
				effetPdv = effetPdv + piege.getCoutPdv();
			}

			if (piege.getDureeMalusTemps() > 1) {
				effetTemps = effetTemps + piege.getMalusTemps();
			}

			piege.setDuree_malus_temps(piege.getDureeMalusTemps() - 1);
			piege.setDuree_malus_pv(piege.getDureeMalusPv() - 1);

			if (piege.getDureeMalusPv() < 1 && piege.getDureeMalusTemps() < 1) {
				System.out.println("Les effets du " + piege.getNom() + " ont expiré !");
				piegesExpires.add(piege);
			}
		}
		this.effetsPieges.removeAll(piegesExpires);
		effetsPieges[0] = effetTemps;
		effetsPieges[1] = effetPdv;
		if (!this.effetsPieges.isEmpty()) {
			System.out.println("Les pièges que vous avez déclenchés vous pénalisent de " + (effetTemps * 100)
					+ "% au niveau du temps");
			System.out.println("Les pièges que vous avez déclenchés vous pénalisent de " + (effetPdv * 100)
					+ "% au niveau de vos points de vie");
		}
		return effetsPieges;
	}

	public double[] activerEffetsObjets(Espace e) {
		double effetsObjets[] = new double[2];
		double effetTemps = 1;
		double effetPdv = 1;
		effetsObjets[0] = effetTemps;
		effetsObjets[1] = effetPdv;
		if (this.inventaire.size() == 0) {
			return effetsObjets;
		} else {
			for (Objet objet : this.inventaire) {
				double effetsObjet[] = new double[2];
				effetsObjet = objet.action(this, e);
				effetsObjets[0] = effetsObjets[0] * effetsObjet[0];
				effetsObjets[1] = effetsObjets[1] * effetsObjet[1];
			}
		}
		return effetsObjets;
	}

	public boolean estMort() {
		return this.pdv <= 0;
	}

	public boolean isArrive() {
		return arrive;
	}

	public void setArrive(boolean arrive) {
		this.arrive = arrive;
	}

} /*----- Fin de la classe Aventurier -----*/
