package modele;

public class Poison extends Piege {

	private final String NOM = "Piège de poison";
	private final double COUT_PDV = 0.1;
	private final double MALUS_TEMPS = 0;
	private int duree_malus_temps = 0;
	private int duree_malus_pv = Integer.MAX_VALUE;
	private final String DESCRIPTION = "Le " + NOM + " vous fait perdre " + COUT_PDV * 100
			+ "% de points de vie en plus par tour ! Pour vous en soigner il vous faut un antidote ! ";

	@Override
	public void action(Aventurier a) {

		System.out.println("Vous déclenchez un " + NOM + " ! " + DESCRIPTION);

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
