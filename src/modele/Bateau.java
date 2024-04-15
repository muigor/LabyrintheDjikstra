package modele;

public class Bateau extends Objet {

	private static final double BONUS_TEMPS = 0.25;
	private static final double BONUS_PDV = 0;
	private static final String BONUS_EMPLACEMENT = Ocean.class.getSimpleName();
	private static final String DESCRIPTION = " Il vous permet de parcourir les espaces de type " + BONUS_EMPLACEMENT
			+ " en " + (BONUS_TEMPS * 100)
			+ "% du temps qu'il vous aurait fallu normalement et sans perdre de point de vie !";

	@Override
	public double[] action(Aventurier a, Espace e) {
		double effetsObjet[] = new double[2];
		if (!e.getClassName().equals(BONUS_EMPLACEMENT)) {
			effetsObjet[0] = 1;
			effetsObjet[1] = 1;
			return effetsObjet;
		} else {
			effetsObjet[0] = BONUS_TEMPS;
			effetsObjet[1] = BONUS_PDV;
			System.out.println("Votre " + this.getClassName() + " vous avantage ! Vous traversez en "
					+ (BONUS_TEMPS * 100)
					+ "% du temps qu'il vous aurez normalement fallu ! De plus, vous ne perdez pas de points de vie ! ");
			return effetsObjet;
		}
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
