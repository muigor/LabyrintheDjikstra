package modele;

public class Pioche extends Objet {

	private static final double BONUS_TEMPS = 1;
	private static final double BONUS_PDV = 1;
	private static final String BONUS_EMPLACEMENT = Mur.class.getSimpleName();
	private static final String DESCRIPTION = " Elle vous permet de traverser les murs mais en perdant énormément de temps !";

	public double[] action(Aventurier a, Espace e) {
		double effetsObjet[] = new double[2];
		effetsObjet[0] = 1;
		effetsObjet[1] = 1;
		return effetsObjet;
	}

	public double getBonusTemps() {
		return BONUS_TEMPS;
	}

	public String getBonusEmplacement() {
		return BONUS_EMPLACEMENT;
	}

	public double getBonusPdv() {
		return BONUS_PDV;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

}
