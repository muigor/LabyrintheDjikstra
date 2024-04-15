package modele;

import java.awt.Point;

/**
 * Classe qui représente le labyrinthe.
 */
public class Labyrinthe implements Cloneable {
	/*------------*/
	/* Propriétés */
	/*------------*/

	/*----- Taille du labyrinthe -----*/
	private final int taille_du_labyrinthe;

	/*----- Liste des cases composant le labyrinthe -----*/
	private final Case[][] cases;

	/*----- Aventurier qui doit traverser le labyrinthe -----*/
	private Aventurier aventurier;

	/*--------------*/
	/* Constructeur */
	/*--------------*/

	public Labyrinthe(int taille) {
		this.taille_du_labyrinthe = taille;
		this.cases = new Case[this.taille_du_labyrinthe][this.taille_du_labyrinthe];
	}

	/*----------*/
	/* Méthodes */
	/*----------*/

	/**
	 * Retourne la taille du labyrinthe.
	 */
	public int getTaille() {
		return this.taille_du_labyrinthe;
	}

	/**
	 * Affecte / retourne la case en position i,j.
	 */
	public void setCase(int i, int j, Case c) {
		this.cases[i][j] = c;
	}

	public Case getCase(int i, int j) {
		return this.cases[i][j];
	}

	/**
	 * Retourne / affecte l'aventurier du labyrinthe.
	 */
	public Aventurier getAventurier() {
		return this.aventurier;
	}

	public void setAventurier(Aventurier aventurier) {
		this.aventurier = aventurier;
	}

	/**
	 * Déclenche l'action de la case sur laquelle est l'aventurier.
	 */
	public void action() {
		int posX = this.aventurier.getX();
		int posY = this.aventurier.getY();
		this.aventurier.addHistoriqueTrajet(new Point(this.aventurier.getX(), this.aventurier.getY()));
		this.cases[posX][posY].action(this.aventurier);
		System.out.println();
	}

	public void actionSimul(int posX, int posY) {
		this.aventurier.addHistoriqueTrajet(new Point(this.aventurier.getX(), this.aventurier.getY()));
		this.cases[posX][posY].action(this.aventurier);
	}

} /*----- Fin de la classe Labyrinthe -----*/
