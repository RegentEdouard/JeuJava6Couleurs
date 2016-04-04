package SixCouleurs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class PanneauPlateau extends JPanel {

	private char[][] plateau;
	public Joueur[] listeJoueur;
	public int joueurActif;
	public int sourisX = 0;
	public int sourisY = 0;
	public boolean fin = false;

	public void paintComponent(Graphics g) {

		// On décide d'une couleur de fond pour notre rectangle
		g.setColor(Color.white);
		// On dessine celui-ci afin qu'il prenne tout la surface
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		int hauteur = 740-80;	// Hauteur de la fenetre sans les bords
		int largeur = 900-40;	// Largeur de la fenêtre sans les bords

		//Création de la séparation entre le terrain et les données
		g.setColor(Color.lightGray);
		g.fillRect(880, 0, 1, 740);


		for (int i=0; i<plateau.length; i++){
			for (int j=0; j<plateau[0].length; j++){//'r','o','j','v','b','i'
				switch(plateau[i][j]){
				case 'r':
					g.setColor(Color.red);
					break;
				case 'R':
					g.setColor(Color.red);
					break;
				case 'o':
					g.setColor(new Color(255, 106, 0));	//Orange
					//g.setColor(Color.orange);
					break;
				case 'O':
					g.setColor(new Color(255, 106, 0));	//Orange
					//g.setColor(Color.orange);
					break;
				case 'j':
					g.setColor(Color.yellow);
					break;
				case 'J':
					g.setColor(Color.yellow);
					break;
				case 'v':
					g.setColor(Color.green);
					break;
				case 'V':
					g.setColor(Color.green);
					break;
				case 'b':
					g.setColor(Color.blue);
					break;
				case 'B':
					g.setColor(Color.blue);
					break;
				case 'i':
					g.setColor(Color.magenta);
					break;
				case 'I':
					g.setColor(Color.magenta);
					break;
				}
				int largeurCase = largeur/plateau.length;
				int hauteurCase = hauteur/plateau[0].length;
				largeurCase = Math.min(largeurCase, hauteurCase);
				largeurCase = Math.max(largeurCase, 21);
				hauteurCase = largeurCase;
				g.fillRect(i*largeurCase+20, j*hauteurCase+20, largeurCase, hauteurCase);
				//Contours blanc
				g.setColor(Color.white);
				g.drawRect(i*largeurCase+20, j*hauteurCase+20, largeurCase, hauteurCase);
				g.drawRect(i*largeurCase+21, j*hauteurCase+21, largeurCase-2, hauteurCase-2);


				//System.out.print(plateau[i][j] + " ");
			}
		}
		//Affichage de la sélection de couleur
		g.setColor(Color.red);
		g.fillRect(362, 660, 21, 21);
		g.setColor(new Color(255, 106, 0));	//Orange
		g.fillRect(393, 660, 21, 21);
		g.setColor(Color.yellow);
		g.fillRect(424, 660, 21, 21);
		g.setColor(Color.green);
		g.fillRect(455, 660, 21, 21);
		g.setColor(Color.blue);
		g.fillRect(486, 660, 21, 21);
		g.setColor(Color.magenta);
		g.fillRect(517, 660, 21, 21);

		//Afficher les couleurs que l'on ne peut pas utiliser
		g.setColor(Color.white);
		for (int i=0; i<listeJoueur.length; i++){
			switch(listeJoueur[i].getCouleur()){
			case 'R':
				g.fillRect(365, 663, 15, 15);
				break;
			case 'O':
				g.fillRect(396, 663, 15, 15);
				break;
			case 'J':
				g.fillRect(427, 663, 15, 15);
				break;
			case 'V':
				g.fillRect(458, 663, 15, 15);
				break;
			case 'B':
				g.fillRect(489, 663, 15, 15);
				break;
			case 'I':
				g.fillRect(520, 663, 15, 15);
				break;
			}
		}

		Font font = new Font("Cooper Black", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString("Score:", 930, 80);
		for (int i=0; i<listeJoueur.length; i++){
			if (listeJoueur[i].getNom() == null) listeJoueur[i].setNom("Joueur " + (i+1));
			g.drawString(listeJoueur[i].getNom() + ": " + listeJoueur[i].getScore(), 910, 110+i*25);
		}
		font = new Font("Cooper Black", Font.BOLD, 22);
		g.setFont(font);
		g.drawString("C'est au tour de ", 910, 260);
		g.drawString(listeJoueur[joueurActif].getNom(), 950, 287);
		g.drawString("de jouer", 910, 314);
		
		//Affichage du gagnant
		if (fin){
			g.drawString("Vainqueur :", 935, 500);
			g.drawString(listeJoueur[joueurActif].getNom() + " !", 945, 525);
			g.drawString("Félicitation", 935, 550);
		}
	}

	public PanneauPlateau(){
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				System.out.print("X = " + e.getX() + ", Y = " + e.getY());
				sourisX = e.getX();
				sourisY = e.getY();
				System.out.println("; X = " + sourisX + ", Y = " + sourisY);
			}
		});
	}

	public char[][] getPlateau() {
		return plateau;
	}

	public void setPlateau(char[][] plateau) {
		this.plateau = plateau;
	}

	public Joueur[] getListeJoueur() {
		return listeJoueur;
	}

	public void setListeJoueur(Joueur[] listeJoueur) {
		this.listeJoueur = listeJoueur;
	}

	public int getJoueurActif() {
		return joueurActif;
	}

	public void setJoueurActif(int joueurActif) {
		this.joueurActif = joueurActif;
	}

	public int getSourisX() {
		return sourisX;
	}

	public void setSourisX(int sourisX) {
		this.sourisX = sourisX;
	}

	public int getSourisY() {
		return sourisY;
	}

	public void setSourisY(int sourisY) {
		this.sourisY = sourisY;
	}

	public void setFin(boolean fin) {
		this.fin = fin;
	}

}
