package modele;

public class PiegeLoup extends Piege {

	private final String NOM = "Piège à loup";
	private final double COUT_PDV = 0.1;
	private final double MALUS_TEMPS = 0.25;
	private int duree_malus_temps = (int) (Math.random() * 11);
	private int duree_malus_pv = 0;
	private final String DESCRIPTION = "Il vous fait perdre " + COUT_PDV * 100
			+ "% de vos points de vie maximum. De plus, Vous mettrez " + MALUS_TEMPS * 100
			+ "% de temps en plus pour voyager durant " + duree_malus_temps + " tour(s).";

	@Override
	public void action(Aventurier a) {
		System.out.println("Vous déclenchez un " + NOM + " ! " + DESCRIPTION);
		a.setPdv(a.getPdv() - COUT_PDV * 100);

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


}
