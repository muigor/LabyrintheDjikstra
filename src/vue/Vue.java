package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modele.Aventurier;
import modele.Labyrinthe;
import modele.Objet;
import modele.PointTrouverChemin;
import modele.Porte;

/**
 * Fenêtre de visualisation du labyrinthe.
 */
public class Vue extends JFrame {
	/*------------*/
	/* Propriétés */
	/*------------*/

	private static final long serialVersionUID = 1L;
	/**
	 * Référence vers le labyrinthe que la classe Vue va visualiser.
	 */
	private Labyrinthe labyrinthe;
	List<PointTrouverChemin> chemin;
	private static final Color PURPLE = new Color(102, 0, 153);
	private static final Color LIGHT_RED = new Color(255, 102, 102);
	private static final Color BROWN = new Color(153, 102, 0);

	public static Image desert = Toolkit.getDefaultToolkit().getImage("images/desert.jpg");
	public static Image ocean = Toolkit.getDefaultToolkit().getImage("images/ocean.jpg");
	public static Image prairie = Toolkit.getDefaultToolkit().getImage("images/prairie.jpg");
	public static Image mur = Toolkit.getDefaultToolkit().getImage("images/mur.jpg");
	public static Image porteferme = Toolkit.getDefaultToolkit().getImage("images/porteferme.png");
	public static Image porteouverte = Toolkit.getDefaultToolkit().getImage("images/porteouverte.png");
	public static Image aventurierI = Toolkit.getDefaultToolkit().getImage("images/aventurier.png");
	public static Image depart = Toolkit.getDefaultToolkit().getImage("images/depart.png");
	public static Image arrivee = Toolkit.getDefaultToolkit().getImage("images/arrivee.png");

	// public static Image desert = ImageIO.read(new
	// File("C:\\Users\\johan\\eclipse-workspace\\ProjetLabyrinthe\\images\\desert.jpg"));
	/*----- Barre d'état de la fenêtre -----*/
	private final JLabel barre_etat;

	/*----- Zone de dessin -----*/
	Dessin dessin;

	/*--------------*/
	/* Constructeur */
	/*--------------*/

	public Vue(int x, int y, Labyrinthe labyrinthe, List<PointTrouverChemin> chemin) {
		/*----- Lien avec le labyrinthe -----*/
		this.labyrinthe = labyrinthe;
		this.chemin = chemin;
		/*----- Paramètres de la fenêtre -----*/
		this.setTitle("Labyrinthe");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(x, y);
		this.setLayout(new BorderLayout());

		/*----- Zone de dessin -----*/
		this.dessin = new Dessin(600);
		this.dessin.setFocusable(true);

		/*----- Attachement des écouteurs des évènements souris et clavier -----*/
		this.dessin.addMouseListener(this.dessin);
		this.dessin.addMouseMotionListener(this.dessin);
		this.dessin.addKeyListener(this.dessin);
		this.add(this.dessin, BorderLayout.CENTER);

		/*----- Barre d'état de la fenêtre -----*/
		this.barre_etat = new JLabel("Barre d'état");
		this.add(this.barre_etat, BorderLayout.SOUTH);

		/*----- Pour ajuster la fenêtre à son contenu et la rendre visible -----*/
		this.pack();
		this.setVisible(true);
	}

	/*----------------*/
	/* Classe interne */
	/*----------------*/

	class Dessin extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
		private static final long serialVersionUID = 1L;
		/*----- Propriétés de la classe interne -----*/
		int largeur;
		int taille_case;

		/*----- Constructeur de la classe interne -----*/
		public Dessin(int larg) {
			/*----- Initialisation des données -----*/
			this.taille_case = larg / labyrinthe.getTaille();
			this.largeur = this.taille_case * labyrinthe.getTaille();

			/*----- Paramètre du JPanel -----*/
			this.setPreferredSize(new Dimension(this.largeur, this.largeur));
		}

		/**
		 * Méthode qui permet de dessiner ou redessinner le labyrinthe lorsque la
		 * méthode repaint() est appelée sur la classe Dessin.
		 */
		@Override
		public void paint(Graphics g) {

			/*----- On efface le dessin en entier -----*/
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, this.largeur, this.largeur);
			// g.drawImage(desert, this.largeur, this.largeur, null);
			/*----- Affichage des cases du labyrinthe -----*/
			for (int i = 0; i < labyrinthe.getTaille(); i++)
				for (int j = 0; j < labyrinthe.getTaille(); j++) {
					/*----- Couleur de la case -----*/
					if (labyrinthe.getCase(i, j).getClassName().equals("Mur"))
						g.drawImage(mur, this.taille_case * j, i * taille_case, this.taille_case, this.taille_case,
								this);
					if (labyrinthe.getCase(i, j).getClassName().equals("Depart"))
						g.drawImage(depart, this.taille_case * j, i * taille_case, this.taille_case, this.taille_case,
								this);
					if (labyrinthe.getCase(i, j).getClassName().equals("Ocean"))
						g.drawImage(ocean, this.taille_case * j, i * taille_case, this.taille_case, this.taille_case,
								this);
					if (labyrinthe.getCase(i, j).getClassName().equals("Desert"))
						g.drawImage(desert, this.taille_case * j, i * taille_case, this.taille_case, this.taille_case,
								this);
					if (labyrinthe.getCase(i, j).getClassName().equals("Prairie"))
						g.drawImage(prairie, this.taille_case * j, i * taille_case, this.taille_case, this.taille_case,
								this);
					if (labyrinthe.getCase(i, j).getClassName().equals("Arrivee"))
						g.drawImage(arrivee, this.taille_case * j, i * taille_case, this.taille_case, this.taille_case,
								this);
					if (labyrinthe.getCase(i, j).getClassName().equals("Porte")) {
						Porte p = (Porte) labyrinthe.getCase(i, j);
						if (p.isEstOuverte() == false)
							g.drawImage(porteferme, this.taille_case * j, i * taille_case, this.taille_case,
									this.taille_case, this);
						else
							g.drawImage(porteouverte, this.taille_case * j, i * taille_case, this.taille_case,
									this.taille_case, this);
					}

					/*----- Affichage de la case sous forme d'un rectangle plein -----*/
					// g.fillRect(taille_case * j, taille_case * i, taille_case, taille_case);

					if (labyrinthe.getCase(i, j).getObjet() != null) {
						if (labyrinthe.getCase(i, j).getObjet().getClassName().equals("Bateau")) {
							g.setColor(Color.CYAN);
							g.fillOval(taille_case * j + taille_case / 2, taille_case * i + taille_case / 4,
									taille_case / 4, taille_case / 4);
						} else if (labyrinthe.getCase(i, j).getObjet().getClassName().equals("Gourde")) {
							g.setColor(BROWN);
							g.fillOval(taille_case * j + taille_case / 2, taille_case * i + taille_case / 4,
									taille_case / 4, taille_case / 4);
						} else if (labyrinthe.getCase(i, j).getObjet().getClassName().equals("Pioche")) {
							g.setColor(Color.DARK_GRAY);
							g.fillOval(taille_case * j + taille_case / 2, taille_case * i + taille_case / 4,
									taille_case / 4, taille_case / 4);
						} else if (labyrinthe.getCase(i, j).getObjet().getClassName().equals("Cle")) {
							g.setColor(Color.RED);
							g.fillOval(taille_case * j + taille_case / 2, taille_case * i + taille_case / 4,
									taille_case / 4, taille_case / 4);
						}
					}

					if (labyrinthe.getCase(i, j).getPersonnage() != null) {
						if (labyrinthe.getCase(i, j).getPersonnage().getClassName().equals("Druide")) {
							g.setColor(Color.PINK);
							g.fillRect(taille_case * j + taille_case / 4, taille_case * i + taille_case / 4,
									taille_case / 4, taille_case / 4);
						} else if (labyrinthe.getCase(i, j).getPersonnage().getClassName().equals("Voleur")) {
							g.setColor(Color.ORANGE);
							g.fillRect(taille_case * j + taille_case / 4, taille_case * i + taille_case / 4,
									taille_case / 4, taille_case / 4);
						} else if (labyrinthe.getCase(i, j).getPersonnage().getClassName().equals("Soigneur")) {
							g.setColor(Color.BLACK);
							g.fillRect(taille_case * j + taille_case / 4, taille_case * i + taille_case / 4,
									taille_case / 4, taille_case / 4);
						} else if (labyrinthe.getCase(i, j).getPersonnage().getClassName().equals("Mage")) {
							g.setColor(Color.WHITE);
							g.fillRect(taille_case * j + taille_case / 4, taille_case * i + taille_case / 4,
									taille_case / 4, taille_case / 4);
						}
					}

					if (labyrinthe.getCase(i, j).getPiege() != null) {
						if (labyrinthe.getCase(i, j).getPiege().getClassName().equals("Poison")) {
							g.setColor(PURPLE);
							g.fillRoundRect(taille_case * j + taille_case / 4, taille_case * i + taille_case / 2,
									taille_case / 4, taille_case / 4, 20, 20);
						} else if (labyrinthe.getCase(i, j).getPiege().getClassName().equals("PiegeLoup")) {
							g.setColor(LIGHT_RED);
							g.fillRoundRect(taille_case * j + taille_case / 4, taille_case * i + taille_case / 2,
									taille_case / 4, taille_case / 4, 20, 20);
						} else if (labyrinthe.getCase(i, j).getPiege().getClassName().equals("Teleporteur")) {
							g.setColor(Color.BLUE);
							g.fillRoundRect(taille_case * j + taille_case / 4, taille_case * i + taille_case / 2,
									taille_case / 4, taille_case / 4, 20, 20);
						}
					}
				}

			if (chemin != null) {
				for (PointTrouverChemin point : chemin) {
					g.setColor(Color.MAGENTA);
					g.fillOval(taille_case * point.getY() + taille_case / 4,
							taille_case * point.getX() + taille_case / 4, taille_case / 2, taille_case / 2);
				}
			}

			/*----- Affichage de l'aventurier -----*/
			Aventurier aventurier = labyrinthe.getAventurier();

			g.drawImage(aventurierI, this.taille_case * aventurier.getY(), this.taille_case * aventurier.getX(),
					this.taille_case, this.taille_case, this);

			/**
			 * g.setColor(Color.MAGENTA); g.fillOval(taille_case * aventurier.getY() +
			 * taille_case / 4, taille_case * aventurier.getX() + taille_case / 4,
			 * taille_case / 2, taille_case / 2);
			 **/
		}

		/**
		 * Gestion des interactions souris et clavier sur le labyrinthe.
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			/*----- Lecture de la position de la souris lors du clic sur l'objet Dessin -----*/
			int x = e.getX();
			int y = e.getY();

			/*----- Recherche des coordonnées de la case du labyrinthe sur laquelle le joueur a cliqué -----*/
			int ligne = y / this.taille_case;
			int colonne = x / this.taille_case;

			/*----- On regarde si l'aventurier est sur la case sur laquelle on vient de cliquer -----*/
			String msgAventurier = "";
			if (labyrinthe.getAventurier().getX() == ligne && labyrinthe.getAventurier().getY() == colonne)
				msgAventurier = "\nL'aventurier est sur cette case.";

			String msgObjet = "";
			if (labyrinthe.getCase(ligne, colonne).getObjet() != null)
				msgObjet = "\nUn(e) " + labyrinthe.getCase(ligne, colonne).getObjet().getClassName()
						+ " se trouve ici ! ";

			String msgPiege = "";
			if (labyrinthe.getCase(ligne, colonne).getPiege() != null)
				msgPiege = "\nUn(e) " + labyrinthe.getCase(ligne, colonne).getPiege().getNom() + " se trouve ici ! ";

			String msgPersonnage = "";
			if (labyrinthe.getCase(ligne, colonne).getPersonnage() != null)
				msgPersonnage = "\nUn(e) " + labyrinthe.getCase(ligne, colonne).getPersonnage().getClassName()
						+ " se trouve ici ! ";
			/*----- Etat de la case -----*/
			JOptionPane.showMessageDialog(this,
					"Vous avez cliqué sur la case (" + ligne + "," + colonne + ").\n C'est un(e) "
							+ labyrinthe.getCase(ligne, colonne).getClassName() + "." + msgAventurier + msgObjet
							+ msgPiege + msgPersonnage);
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			barre_etat.setText("Points de vie : " + labyrinthe.getAventurier().getPdv() + "/100 Durée du voyage : "
					+ labyrinthe.getAventurier().getChrono() + " jours");
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			/**
			 * Déplacement de l'aventurier dans le labyrinthe.
			 */
			int pos;

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			}

			/*----- Vers le bas -----*/
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				pos = labyrinthe.getAventurier().getX();
				labyrinthe.getAventurier().setX(pos + 1);
			}

			/*----- Vers le haut -----*/
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				pos = labyrinthe.getAventurier().getX();
				labyrinthe.getAventurier().setX(pos - 1);
			}

			/*----- Vers la droite -----*/
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				pos = labyrinthe.getAventurier().getY();
				labyrinthe.getAventurier().setY(pos + 1);
			}

			/*----- Vers la gauche -----*/
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				pos = labyrinthe.getAventurier().getY();
				labyrinthe.getAventurier().setY(pos - 1);
			}

			/*----- On déclenche l'action de la case sur laquelle est l'aventurier -----*/
			labyrinthe.action();
			String inventaire = "";
			for(Objet o : labyrinthe.getAventurier().getInventaire()) {
				inventaire = inventaire + " / " + o.getClassName();
			}

			barre_etat.setText("Points de vie : " + labyrinthe.getAventurier().getPdv() + "/100 Durée du voyage : "
					+ labyrinthe.getAventurier().getChrono() + " jours Votre inventaire : " + inventaire);

			/*----- On refait le dessin -----*/
			if (labyrinthe.getAventurier().estMort()) {
				System.out.println("Vous êtes mort ! ");

			} else if (labyrinthe.getAventurier().isArrive()) {
				System.out.println("Vous êtes arrivé ! Vous terminez votre aventure avec "
						+ labyrinthe.getAventurier().getPdv() + "/100 points de vie ! Votre aventure a duré "
						+ labyrinthe.getAventurier().getChrono() + " jours !");
				System.out.println("Appuyez sur entrée pour voir le chemin le plus court ! ");
				repaint();
			} else {
				repaint();
			}
		}

	} /*----- Fin de la classe interne Dessin -----*/

} /*----- Fin de la classe Vue -----*/