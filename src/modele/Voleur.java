package modele;

public class Voleur extends Personnage {

	@Override
	public void action(Aventurier a) {
		// TODO Auto-generated method stub
		super.action(a);
		if (a.getInventaire().size() == 0) {
			System.out.println(
					"Votre inventaire est vide ! Le voleur ne fait rien et vous laisse continuer votre route.");
		} else {
			int alea = (int) Math.random() * a.getInventaire().size();
			System.out.println("Il vous vole votre " + a.getInventaire().get(alea).getClassName() + " ! ");
			a.getInventaire().remove(alea);
		}
	}
}
