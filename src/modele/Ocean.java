package modele;

public class Ocean extends Espace {

	private static final double COUT_PV = 0.15;
	private static final double TEMPS = 10;

	public Ocean(Objet objet, Personnage personnage, Piege piege) {
		super(objet, personnage, piege);
	}

	@Override
	public void action(Aventurier a) {
		super.action(a);
	}

	public double getCoutPv() {
		return COUT_PV;
	}

	public double getTemps() {
		return TEMPS;
	}

}
