package modele;

public class Depart extends Case {

	@Override
	public void action(Aventurier a) {
		System.out.println("Ceci est le départ !");
	}

	@Override
	public Objet getObjet() {
		return null;
	}

	@Override
	public void setObjet(Objet objet) {

	}

	@Override
	public Personnage getPersonnage() {
		return null;
	}

	@Override
	public void setPersonnage(Personnage personnage) {

	}

	@Override
	public Piege getPiege() {
		return null;
	}

	@Override
	public void setPiege(Piege piege) {

	}

	@Override
	public double getCoutTemps(Aventurier a) {
		return 0;
	}

	@Override
	public double getCoutPv(Aventurier a) {
		return 0;
	}

}
