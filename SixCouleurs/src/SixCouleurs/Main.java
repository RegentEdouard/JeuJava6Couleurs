package SixCouleurs;

import java.awt.Font;
import edu.princeton.cs.introcs.StdDraw;

public class Main {

	public static void main(String[] args) {
		int tailleX = 4;
		int tailleY = 2;
		Plateau test = new Plateau(tailleX, tailleY);
		//StdDraw.setCanvasSize(900, 700);	//512*512 pardéfaut
		//StdDraw.setXscale(900, 0);
		//StdDraw.setYscale(700, 0);
		//StdDraw.clear(StdDraw.GRAY);
		//menuPrincipal();
		//Création des joueurs
		Joueur joueur1 = new Joueur();
		creationJoueur(0, 0, joueur1, test);
		Joueur joueur2 = new Joueur();
		creationJoueur(tailleX-1, tailleY-1, joueur2, test);
		Joueur[] liste = {joueur1,joueur2};
		joueur1.conquerir(joueur1.getCouleur(), test);
		joueur2.conquerir(joueur2.getCouleur(), test);
		test.afficher();
		
		boolean partie = true;
		while (partie){
			joueur1.jouer(liste, test);
			for (int i=0; i<liste.length; i++){
				if(liste[i].getScore() > tailleX*tailleY/2) partie = false;
			}
			if (partie == false) break;
			joueur2.jouer(liste, test);
			for (int i=0; i<liste.length; i++){
				if(liste[i].getScore() > tailleX*tailleY/2) partie = false;
			}
			
		}
		afficherScore(liste);
		//System.exit (0);
		
	}
	
	
	public static void afficherScore(Joueur[] listeJ){
		String[] scoreMax = {"0",""};
		for (int i=0; i<listeJ.length; ++i){
			if (Integer.parseInt(scoreMax[0]) < listeJ[i].getScore()){
				scoreMax[0] = Integer.toString(listeJ[i].getScore());
				scoreMax[1] = Character.toString(listeJ[i].getCouleur());
			}
		}
		System.out.println(scoreMax[1] + " est vainqueur avec un score de: " + scoreMax[0]);
	}
	
	public static void creationJoueur(int positionX, int positionY, Joueur j, Plateau p){
		char[][] terrain = p.getPlateau();
		terrain[positionY][positionX] = (char)((int)p.plateau[positionY][positionX]-32);	//Passage de la case en majuscule
		p.setPlateau(terrain);
		j.setTerritoire(tabDebut(positionX, positionY, new boolean[terrain.length][terrain[0].length]));
		j.setCouleur(terrain[positionY][positionX]);
	}
	
	public static boolean[][] tabDebut(int x, int y, boolean[][] tab){	//Retourne un tableau de territoire
		for (int i=0; i<tab.length; i++){
			for (int j=0; j<tab[0].length; j++){
				tab[i][j] = false;
			}
		}
		tab[y][x] = true;
		return tab;
	}
	
	public static void menuPrincipal(){
		String[] text = {"Nouvelle partie", "Quitter"};
		double[][] position = {{450,250},{450,400}};
		Font font = new Font("Cooper Black", Font.BOLD, 60);
		StdDraw.setFont(font);
		StdDraw.text(position[0][0], position[0][1], text[0]);
		StdDraw.text(position[1][0], position[1][1], text[1]);
	}
}
