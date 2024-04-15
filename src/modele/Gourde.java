package modele;

public class Gourde extends Objet {

	private static final double BONUS_TEMPS = 1;
	private static final double BONUS_PDV = 0.5;
	private static final String BONUS_EMPLACEMENT = Desert.class.getSimpleName();
	private static final String DESCRIPTION = "Elle permet de parcourir les espaces de type " + BONUS_EMPLACEMENT
			+ " en ne perdant que " + (BONUS_PDV * 100) + " des points que vie que vous auriez normalement perdus ! ";

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
			System.out.println("Votre " + this.getClassName() + " vous avantage ! Vous ne perdez que "
					+ (BONUS_PDV * 100) + "% des points de vie que vous auriez dû perdre ! ");
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

	public String getDescription() {
		return DESCRIPTION;
	}

}
