package modele;

public class Antidote extends Objet {

	private static final double BONUS_TEMPS = 1;
	private static final double BONUS_PDV = 1;
	private static final String BONUS_EMPLACEMENT = null;
	private static final String EFFET_OBJET = Poison.class.getSimpleName();
	private static final String DESCRIPTION = "Il permettra de vous soigner si vous êtes empoisonné par un piège ! ";

	@Override
	public double[] action(Aventurier a, Espace e) {
		double effetsObjet[] = new double[2];
		effetsObjet[0] = 1;
		effetsObjet[1] = 1;
		for (Piege piege : a.getEffetsPieges()) {
			if (piege.getClassName().equals(EFFET_OBJET)) {
				System.out.println("Votre antidote vous guérit de votre poison ! ");
				a.getEffetsPieges().remove(piege);
				return effetsObjet;
			}
		}
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
