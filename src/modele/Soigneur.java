package modele;

public class Soigneur extends Personnage {

	@Override
	public void action(Aventurier a) {
		// TODO Auto-generated method stub
		super.action(a);
		System.out.println("Il vous soigne de vos blessures ! Vos points de vie sont remis au maximum !");
		a.setPdv(100);
	}

}
