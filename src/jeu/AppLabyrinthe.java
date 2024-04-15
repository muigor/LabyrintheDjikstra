package jeu;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import modele.Arrivee;
import modele.Aventurier;
import modele.Bateau;
import modele.Cle;
import modele.Depart;
import modele.Desert;
import modele.Druide;
import modele.Espace;
import modele.Gourde;
import modele.Labyrinthe;
import modele.Mage;
import modele.Mur;
import modele.Objet;
import modele.Ocean;
import modele.Personnage;
import modele.Piege;
import modele.PiegeLoup;
import modele.Pioche;
import modele.Poison;
import modele.Porte;
import modele.Prairie;
import modele.Soigneur;
import modele.Teleporteur;
import modele.TrouverChemin;
import modele.Voleur;
import modele.PointTrouverChemin;
import vue.Vue;

/**
 * Application de l'aventurier et du labyrinthe.
 * 
 * Cette classe permet de charger le jeu et de le lancer.
 */
public class AppLabyrinthe {

	/**
	 * Noms des fichiers contenant des labyrinthes.
	 */
	private static final String LAB_1 = "data" + File.separator + "labyrinthe_1.csv";
	private static final String LAB_2 = "data" + File.separator + "labyrinthe_2.csv";

	/**
	 * Chargement du labyrinthe et de l'aventurier.
	 *
	 * A partir d'un fichier csv, cette méthode crée le labyrinthe et l'aventurier
	 * sur la case de départ du labyrinthe.
	 */

	public static Labyrinthe chargeLabyrintheAlea(String fichier) {
		Labyrinthe laby = null;
		ArrayList<Objet> lesObjets = new ArrayList<>(Arrays.asList(new Bateau(), new Gourde(), new Pioche(), new Cle()));
		ArrayList<Piege> lesPieges = new ArrayList<>(Arrays.asList(new Poison(), new PiegeLoup()));
		ArrayList<Personnage> lesPersonnages = new ArrayList<>(
				Arrays.asList(new Druide(), new Soigneur(), new Voleur(), new Mage()));

		try (Scanner scanner = new Scanner(new FileInputStream(fichier))) {
			/*----- Lecture de la taille du labyrinthe -----*/
			int taille = Integer.valueOf(scanner.nextLine());

			/*----- Initialisation du labyrinthe -----*/
			laby = new Labyrinthe(taille);

			/*----- Lecture du fichier et des types de cases composant le labyrinthe -----*/
			for (int i = 0; i < taille; i++) {
				/*----- Lecture d'une ligne du fichier -----*/
				String[] liste = scanner.nextLine().trim().split(";");

				int type_case;
				for (int j = 0; j < taille; j++) {
					type_case = Integer.valueOf(liste[j]);

					/*----- Type 0 --> "Espace" -----*/
					if (type_case == 0) {
						int type_espace = (int) (Math.random() * 3);
						if (type_espace == 0)
							laby.setCase(i, j, new Ocean(null, null, null));
						if (type_espace == 1)
							laby.setCase(i, j, new Desert(null, null, null));
						if (type_espace == 2)
							laby.setCase(i, j, new Prairie(null, null, null));
					}

					/*----- Type 1 --> "Mur" -----*/
					if (type_case == 1) {
						laby.setCase(i, j, new Mur());
					}
					
					if (type_case == 1 && i == 4 && j == 5)
						laby.setCase(i, j, new Porte());
						
					/*----- Type 10 --> "Arrivée" -----*/
					if (type_case == 10)
						laby.setCase(i, j, new Arrivee());

					/*----- Type 9 --> "Espace de départ" et "Aventurier" -----*/
					if (type_case == 9) {
						laby.setCase(i, j, new Depart());
						laby.setAventurier(new Aventurier(i, j));
						laby.getAventurier().addHistoriqueTrajet(new Point(i, j));
					}
				}
			}

			for (Objet objet : lesObjets) {
				int randomX = (int) (Math.random() * taille);
				int randomY = (int) (Math.random() * taille);
				if (!(laby.getCase(randomX, randomY) instanceof Espace)
						&& laby.getCase(randomX, randomY).getObjet() == null) {
					while (!(laby.getCase(randomX, randomY) instanceof Espace)
							&& laby.getCase(randomX, randomY).getObjet() == null) {
						randomX = (int) (Math.random() * taille);
						randomY = (int) (Math.random() * taille);
					}
				}
				laby.getCase(randomX, randomY).setObjet(objet);
			}

			for (Personnage personnage : lesPersonnages) {
				int randomX = (int) (Math.random() * taille);
				int randomY = (int) (Math.random() * taille);
				if (!(laby.getCase(randomX, randomY) instanceof Espace)
						&& laby.getCase(randomX, randomY).getPersonnage() == null) {
					while (!(laby.getCase(randomX, randomY) instanceof Espace)
							&& laby.getCase(randomX, randomY).getPersonnage() == null) {
						randomX = (int) (Math.random() * taille);
						randomY = (int) (Math.random() * taille);
					}
				}
				laby.getCase(randomX, randomY).setPersonnage(personnage);
			}

			for (Piege piege : lesPieges) {
				int randomX = (int) (Math.random() * taille);
				int randomY = (int) (Math.random() * taille);
				if (!(laby.getCase(randomX, randomY) instanceof Espace)
						&& laby.getCase(randomX, randomY).getPiege() == null) {
					while (!(laby.getCase(randomX, randomY) instanceof Espace)
							&& laby.getCase(randomX, randomY).getPiege() == null) {
						randomX = (int) (Math.random() * taille);
						randomY = (int) (Math.random() * taille);
					}
				}
				laby.getCase(randomX, randomY).setPiege(piege);
			}

			int randomX = (int) (Math.random() * taille);
			int randomY = (int) (Math.random() * taille);
			if (!(laby.getCase(randomX, randomY) instanceof Espace) && laby.getCase(randomX, randomY).getObjet() == null
					&& laby.getCase(randomX, randomY).getPersonnage() == null
					&& laby.getCase(randomX, randomY).getPiege() == null) {
				while (!(laby.getCase(randomX, randomY) instanceof Espace)
						&& laby.getCase(randomX, randomY).getPiege() == null
						&& laby.getCase(randomX, randomY).getObjet() == null
						&& laby.getCase(randomX, randomY).getPersonnage() == null) {
					randomX = (int) (Math.random() * taille);
					randomY = (int) (Math.random() * taille);
				}
			}
			laby.getCase(1, 4).setPiege(new Teleporteur(randomX, randomY));
		} catch (FileNotFoundException ex) {
			System.err.println("Erreur lors de la lecture du fichier : " + fichier + " - " + ex.getMessage());
		}

		return laby;
	}

	public static Labyrinthe chargeLabyrinthe(String fichier) {
		Labyrinthe laby = null;

		try (Scanner scanner = new Scanner(new FileInputStream(fichier))) {
			/*----- Lecture de la taille du labyrinthe -----*/
			int taille = Integer.valueOf(scanner.nextLine());

			/*----- Initialisation du labyrinthe -----*/
			laby = new Labyrinthe(taille);

			/*----- Lecture du fichier et des types de cases composant le labyrinthe -----*/
			for (int i = 0; i < taille; i++) {
				/*----- Lecture d'une ligne du fichier -----*/
				String[] liste = scanner.nextLine().trim().split(";");

				int type_case;
				for (int j = 0; j < taille; j++) {
					type_case = Integer.valueOf(liste[j]);

					/*----- Type 0 --> "Espace" -----*/
					if (type_case == 0) {
						if (i < 4 && j < 4)
							laby.setCase(i, j, new Desert(null, null, null));
						else if (i < 7 && j < 7)
							laby.setCase(i, j, new Prairie(null, null, null));
						else
							laby.setCase(i, j, new Ocean(null, null, null));
					}

					/*----- Type 1 --> "Mur" -----*/
					if (type_case == 1)
						laby.setCase(i, j, new Mur());

					if (type_case == 1 && i == 4 && j == 5)
						laby.setCase(i, j, new Porte());

					/*----- Type 10 --> "Arrivée" -----*/
					if (type_case == 10)
						laby.setCase(i, j, new Arrivee());

					/*----- Type 9 --> "Espace de départ" et "Aventurier" -----*/
					if (type_case == 9) {
						laby.setCase(i, j, new Depart());
						laby.setAventurier(new Aventurier(i, j));
						laby.getAventurier().addHistoriqueTrajet(new Point(i, j));
					}
				}
			}
			int randomX = (int) (Math.random() * taille);
			int randomY = (int) (Math.random() * taille);
			if (!(laby.getCase(randomX, randomY) instanceof Espace) && laby.getCase(randomX, randomY).getObjet() == null
					&& laby.getCase(randomX, randomY).getPersonnage() == null
					&& laby.getCase(randomX, randomY).getPiege() == null) {
				while (!(laby.getCase(randomX, randomY) instanceof Espace)
						&& laby.getCase(randomX, randomY).getPiege() == null
						&& laby.getCase(randomX, randomY).getObjet() == null
						&& laby.getCase(randomX, randomY).getPersonnage() == null) {
					randomX = (int) (Math.random() * taille);
					randomY = (int) (Math.random() * taille);
				}
			}

			laby.getCase(5, 3).setObjet(new Pioche());
			laby.getCase(3, 1).setObjet(new Gourde());
			laby.getCase(4, 2).setObjet(new Bateau());

			laby.getCase(1, 1).setObjet(new Cle());
			laby.getCase(1, 1).setPersonnage(new Druide());
			laby.getCase(1, 1).setPiege(new Poison());

			laby.getCase(1, 9).setPersonnage(new Mage());
			laby.getCase(4, 7).setPersonnage(new Voleur());
			laby.getCase(9, 2).setPersonnage(new Soigneur());
			laby.getCase(6, 6).setPersonnage(new Druide());

			laby.getCase(7, 5).setPiege(new Poison());
			laby.getCase(9, 9).setPiege(new PiegeLoup());
			laby.getCase(9, 5).setPiege(new Teleporteur(randomX, randomY));

		} catch (FileNotFoundException ex) {
			System.err.println("Erreur lors de la lecture du fichier : " + fichier + " - " + ex.getMessage());
		}

		return laby;
	}
	
	public static Labyrinthe chargeLabyrinthe2(String fichier) {
		Labyrinthe laby = null;

		try (Scanner scanner = new Scanner(new FileInputStream(fichier))) {
			/*----- Lecture de la taille du labyrinthe -----*/
			int taille = Integer.valueOf(scanner.nextLine());

			/*----- Initialisation du labyrinthe -----*/
			laby = new Labyrinthe(taille);

			/*----- Lecture du fichier et des types de cases composant le labyrinthe -----*/
			for (int i = 0; i < taille; i++) {
				/*----- Lecture d'une ligne du fichier -----*/
				String[] liste = scanner.nextLine().trim().split(";");

				int type_case;
				for (int j = 0; j < taille; j++) {
					type_case = Integer.valueOf(liste[j]);

					/*----- Type 0 --> "Espace" -----*/
					if (type_case == 0) {
						if (i < 4 && j < 4)
							laby.setCase(i, j, new Prairie(null, null, null));
						else if (i < 7 && j < 7)
							laby.setCase(i, j, new Desert(null, null, null));
						else
							laby.setCase(i, j, new Ocean(null, null, null));
					}

					/*----- Type 1 --> "Mur" -----*/
					if (type_case == 1)
						laby.setCase(i, j, new Mur());

					if (type_case == 1 && i == 4 && j == 4)
						laby.setCase(i, j, new Porte());

					/*----- Type 10 --> "Arrivée" -----*/
					if (type_case == 10)
						laby.setCase(i, j, new Arrivee());

					/*----- Type 9 --> "Espace de départ" et "Aventurier" -----*/
					if (type_case == 9) {
						laby.setCase(i, j, new Depart());
						laby.setAventurier(new Aventurier(i, j));
						laby.getAventurier().addHistoriqueTrajet(new Point(i, j));
					}
				}
			}
			int randomX = (int) (Math.random() * taille);
			int randomY = (int) (Math.random() * taille);
			if (!(laby.getCase(randomX, randomY) instanceof Espace) && laby.getCase(randomX, randomY).getObjet() == null
					&& laby.getCase(randomX, randomY).getPersonnage() == null
					&& laby.getCase(randomX, randomY).getPiege() == null) {
				while (!(laby.getCase(randomX, randomY) instanceof Espace)
						&& laby.getCase(randomX, randomY).getPiege() == null
						&& laby.getCase(randomX, randomY).getObjet() == null
						&& laby.getCase(randomX, randomY).getPersonnage() == null) {
					randomX = (int) (Math.random() * taille);
					randomY = (int) (Math.random() * taille);
				}
			}

			laby.getCase(5, 3).setObjet(new Pioche());
			laby.getCase(3, 6).setObjet(new Gourde());
			laby.getCase(1, 7).setObjet(new Bateau());
			laby.getCase(6, 2).setObjet(new Bateau());
			laby.getCase(1, 4).setObjet(new Cle());
			laby.getCase(4, 1).setPersonnage(new Voleur());
			laby.getCase(4, 1).setPiege(new PiegeLoup());
			laby.getCase(4, 1).setObjet(new Gourde());
			laby.getCase(5, 9).setPersonnage(new Mage());
			laby.getCase(7, 6).setPersonnage(new Voleur());
			laby.getCase(7, 7).setPersonnage(new Druide());
			laby.getCase(5, 4).setPersonnage(new Soigneur());
			laby.getCase(5, 4).setObjet(new Pioche());
			laby.getCase(7, 5).setPiege(new Poison());
			laby.getCase(3, 3).setPiege(new PiegeLoup());
			laby.getCase(1, 9).setPiege(new PiegeLoup());
			laby.getCase(3, 3).setPersonnage(new Voleur());
			laby.getCase(7, 1).setPiege(new Teleporteur(randomX, randomY));

		} catch (FileNotFoundException ex) {
			System.err.println("Erreur lors de la lecture du fichier : " + fichier + " - " + ex.getMessage());
		}

		return laby;
	}

	public static PointTrouverChemin getArrivee(String fichier) {
		PointTrouverChemin arrivee = null;

		try (Scanner scanner = new Scanner(new FileInputStream(fichier))) {
			int taille = Integer.valueOf(scanner.nextLine());
			for (int i = 0; i < taille; i++) {
				String[] liste = scanner.nextLine().trim().split(";");

				int type_case;
				for (int j = 0; j < taille; j++) {
					type_case = Integer.valueOf(liste[j]);
					if (type_case == 10)
						arrivee = new PointTrouverChemin(i, j, null);
				}
			}
		} catch (FileNotFoundException ex) {
			System.err.println("Erreur lors de la lecture du fichier : " + fichier + " - " + ex.getMessage());
		}
		return arrivee;
	}

	public static PointTrouverChemin getDepart(String fichier) {
		PointTrouverChemin depart = null;

		try (Scanner scanner = new Scanner(new FileInputStream(fichier))) {
			int taille = Integer.valueOf(scanner.nextLine());
			for (int i = 0; i < taille; i++) {
				String[] liste = scanner.nextLine().trim().split(";");

				int type_case;
				for (int j = 0; j < taille; j++) {
					type_case = Integer.valueOf(liste[j]);
					if (type_case == 9)
						depart = new PointTrouverChemin(i, j, null);
				}
			}
		} catch (FileNotFoundException ex) {
			System.err.println("Erreur lors de la lecture du fichier : " + fichier + " - " + ex.getMessage());
		}
		return depart;
	}

	/*---------------------*/
	/* Programme principal */
	/*---------------------*/

	public static void main(String[] s) throws InterruptedException, CloneNotSupportedException {
		/*----- Chargement du labyrinthe -----*/
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String choix = "";
		String modeDeJeu = "";
		System.out.println(
				"Bienvenue dans le jeu de l'aventurier et du Labyrinthe ! \n développé par Johan Senac et Boubacar Diallo ™");
		System.out.println(
				"Veuillez choisir votre niveau : \ntapez 1 pour le premier niveau \ntapez 2 pour le second niveau \ntapez a pour générer un niveau aléatoire");
		choix = scanner.nextLine();
		if (!"1".equals(choix) && !"2".equals(choix) && !"a".equals(choix)) {
			while (!"1".equals(choix) && !"2".equals(choix) && !"a".equals(choix)) {
				System.out.println(
						"La valeur saisie n'est pas valide \ntapez 1 pour le premier niveau \ntapez 2 pour le second niveau \ntapez a pour générer un niveau aléatoire");
				choix = scanner.nextLine();
			}
		}

		if ("1".equals(choix)) {
			PointTrouverChemin pointDepart = getDepart(LAB_1);
			PointTrouverChemin pointArrivee = getArrivee(LAB_1);

			/*----- Création de la fenêtre de visualisation du labyrinthe et affichage -----*/
			Labyrinthe labyrinthe = chargeLabyrinthe(LAB_1);
			new Vue(100, 100, labyrinthe, null);
			scanner.nextLine();
			System.out.println();
			Labyrinthe copieLabyrinthePdv = chargeLabyrinthe(LAB_1);
			List<PointTrouverChemin> cheminCourtPdv = TrouverChemin.trouverChemin(copieLabyrinthePdv, pointDepart,
					pointArrivee);
			new Vue(100, 100, copieLabyrinthePdv, cheminCourtPdv);
		} else if ("2".equals(choix)) {
			PointTrouverChemin pointDepart = getDepart(LAB_2);
			PointTrouverChemin pointArrivee = getArrivee(LAB_2);


			Labyrinthe labyrinthe = chargeLabyrinthe2(LAB_2);
			new Vue(100, 100, labyrinthe, null);
			scanner.nextLine();
			System.out.println();
			Labyrinthe copieLabyrinthePdv = chargeLabyrinthe2(LAB_2);
			List<PointTrouverChemin> cheminCourtPdv = TrouverChemin.trouverChemin(copieLabyrinthePdv, pointDepart,
					pointArrivee);
			new Vue(100, 100, copieLabyrinthePdv, cheminCourtPdv);
		} else if ("a".equals(choix)) {
			PointTrouverChemin pointDepart = getDepart(LAB_1);
			PointTrouverChemin pointArrivee = getArrivee(LAB_1);


			Labyrinthe labyrinthe = chargeLabyrintheAlea(LAB_1);
			new Vue(100, 100, labyrinthe, null);
			scanner.nextLine();
			System.out.println();
			Labyrinthe copieLabyrinthePdv = chargeLabyrintheAlea(LAB_2);
			List<PointTrouverChemin> cheminCourtPdv = TrouverChemin.trouverChemin(copieLabyrinthePdv, pointDepart,
					pointArrivee);
			new Vue(100, 100, copieLabyrinthePdv, cheminCourtPdv);
		}

	}

} /*----- Fin de ma classe AppLabyrinthe -----*/
