package modele;

public class Prairie extends Espace {

	private static final double COUT_PV = 0.03;
	private static final double TEMPS = 1;

	public Prairie(Objet objet, Personnage personnage, Piege piege) {
		super(objet, personnage, piege);
		// TODO Auto-generated constructor stub
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
