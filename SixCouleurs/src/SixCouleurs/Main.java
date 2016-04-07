package SixCouleurs;

import java.util.Scanner;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		int tailleX = 30;//30
		int tailleY = 40;//40
		Plateau test = new Plateau(tailleX, tailleY);
		//Fenetre fen = new Fenetre();
		
		//choix du mode d'affichage
		Scanner scan = new Scanner(System.in);
		char choixAff = 'A';
		while (choixAff != 'C' && choixAff != 'G'){
			System.out.println("Quel mode d'affichage voulez vous? (console C, graphique G): ");
			choixAff = scan.nextLine().charAt(0);
		}
		
		//Création des joueurs
		//if (choixAff == 'C'){
			int nbJoueur = 0;
			while (nbJoueur<2 || nbJoueur>4){
				System.out.println("Entrez le nombre de joueurs (entre 2 et 4): ");
				nbJoueur = scan.nextInt();
			}
			Joueur[] liste = new Joueur[nbJoueur];
			int[][] positionDépart = {{0,0},{tailleX-1,tailleY-1},{0,tailleY-1},{tailleX-1,0}};
			for (int i=0; i<nbJoueur; i++){
				liste[i] = new Joueur();
				creationJoueur(positionDépart[i][0], positionDépart[i][1], liste[i], test);
				liste[i].conquerir(liste[i].getCouleur(), test);
			}
		//} else {
			PanneauPlateau pan = new PanneauPlateau();
			Fenetre fen = new Fenetre(pan);
			
		//}
		
		
		pan.setListeJoueur(liste);
		test.afficher(pan, choixAff);
		
		boolean partie = true;
		while (partie){
			for (int i=0; i<nbJoueur; i++){
				int scoreTotal = 0;
				for (int j=0; j<nbJoueur; j++){
					scoreTotal += liste[j].getScore();
					if(liste[j].getScore() > tailleX*tailleY/2) partie = false;
				}
				if (scoreTotal >= tailleX*tailleY) partie = false;
				if (partie == false) break;
				pan.setJoueurActif(i);
				liste[i].jouer(liste, test, pan, choixAff);
			}
		}
		
		pan.setFin(true);
		afficherScore(liste, pan);
		
		//System.exit (0);
		
	}
	
	
	
	public static void afficherScore(Joueur[] listeJ, PanneauPlateau pan){
		String[] scoreMax = {"0",""};
		for (int i=0; i<listeJ.length; ++i){
			if (Integer.parseInt(scoreMax[0]) < listeJ[i].getScore()){
				scoreMax[0] = Integer.toString(listeJ[i].getScore());
				scoreMax[1] = Character.toString(listeJ[i].getCouleur());
			}
		}
		System.out.println(scoreMax[1] + " est vainqueur avec un score de: " + scoreMax[0]);
		pan.repaint();
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
		String[] text = {"Nouvelle partie", "Comment jouer", "Quitter"};
		//double[][] position = {{450,250},{450,400}};
		//Font font = new Font("Cooper Black", Font.BOLD, 60);
		//StdDraw.setFont(font);
		//StdDraw.text(position[0][0], position[0][1], text[0]);
		//StdDraw.text(position[1][0], position[1][1], text[1]);
	}
	
}
