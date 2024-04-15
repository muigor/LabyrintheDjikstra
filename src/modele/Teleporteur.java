package modele;

import java.awt.Point;

public class Teleporteur extends Piege {
	private final String NOM = "Téléporteur";
	private final double COUT_PDV = 0;
	private final double MALUS_TEMPS = 0;
	private int duree_malus_temps = 0;
	private int duree_malus_pv = 0;
	private final String DESCRIPTION = "Il vous téléporte à un endroit aléatoire !";
	private final int randomX;
	private final int randomY;

	public Teleporteur(int randomX, int randomY) {
		this.randomX = randomX;
		this.randomY = randomY;
	}

	@Override
	public void action(Aventurier a) {
		System.out.println("Vous déclenchez un " + NOM + " ! " + DESCRIPTION);
		Point p = new Point(randomX, randomY);
		a.setX(randomX);
		a.setY(randomY);
		a.addHistoriqueTrajet(p);
	}

	public String getNom() {
		return NOM;
	}

	public double getCoutPdv() {
		return COUT_PDV;
	}

	public double getMalusTemps() {
		return MALUS_TEMPS;
	}

	public int getDuree_malus_temps() {
		return duree_malus_temps;
	}

	public void setDuree_malus_temps(int duree_malus_temps) {
		this.duree_malus_temps = duree_malus_temps;
	}

	public int getDuree_malus_pv() {
		return duree_malus_pv;
	}

	public void setDuree_malus_pv(int duree_malus_pv) {
		this.duree_malus_pv = duree_malus_pv;
	}

	public int getDureeMalusTemps() {
		return duree_malus_temps;
	}

	public int getDureeMalusPv() {
		return duree_malus_pv;
	}

	public String getDescription() {
		return DESCRIPTION;
	}

	public int getRandomX() {
		return randomX;
	}

	public int getRandomY() {
		return randomY;
	}

}
