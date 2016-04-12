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
		
		//Cr�ation des joueurs
		//if (choixAff == 'C'){
			int nbJoueur = 0;
			while (nbJoueur<2 || nbJoueur>4){
				System.out.println("Entrez le nombre de joueurs (entre 2 et 4): ");
				nbJoueur = scan.nextInt();
			}
			Joueur[] liste = new Joueur[nbJoueur];
			int[][] positionD�part = {{0,0},{tailleX-1,tailleY-1},{0,tailleY-1},{tailleX-1,0}};
			for (int i=0; i<nbJoueur; i++){
				liste[i] = new Joueur();
				creationJoueur(positionD�part[i][0], positionD�part[i][1], liste[i], test);
				liste[i].conquerir(liste[i].getCouleur(), test);
			}
		//} else {
			PanneauPlateau pan = new PanneauPlateau();
			Fenetre fen = new Fenetre(pan);	//1150 * 740
			
		//}
		
		
		pan.setListeJoueur(liste);
		test.afficher(pan, choixAff);


		menuPrincipal(fen);
		
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
	
	public static void menuPrincipal(Fenetre fen){
		PanneauMenuPrincipal menuPrincipal = new PanneauMenuPrincipal();
		String[] textMenu = {"Jouer","Jouer en r�seau","R�gles","Scores","Quitter"};
		int[][] posMenu = {{525,300},{440,375},{521,450},{520,525},{511,600}};
		menuPrincipal.setText(textMenu);
		menuPrincipal.setPosition(posMenu);
		menuPrincipal.logo();
		fen.setContentPane(menuPrincipal);
		fen.repaint();
		fen.revalidate();
		boolean menu = true;
		while (menu){
			fen.repaint();
			String resultatClique = menuPrincipal.souris();
			switch(resultatClique){
			case "Jouer":
				menuJouer(fen);
				menu = false;
				break;
			case "Jouer en r�seau":
				break;
			case "R�gles":
				break;
			case "Scores":
				break;
			case "Quitter":
				fen.dispose();
				System.exit(0);
				break;
			case " ":
				break;
			}
		}
	}
	
	public static void menuJouer(Fenetre fen){
		PanMenuJouer menuJouer = new PanMenuJouer();
		String[] textMenu = {"Retour","Charger un terrain","Charger une partie","Suivant"};
		int[][] posMenu = {{10,40},{440,375},{521,450},{520,525}};
		menuJouer.setText(textMenu);
		menuJouer.setPosition(posMenu);
		fen.setContentPane(menuJouer);
		fen.repaint();
		fen.revalidate();
		boolean menu = true;
		while (menu){
			fen.repaint();
			String resultatClique = menuJouer.souris();
			switch(resultatClique){
			case "Retour":
				menuPrincipal(fen);
				menu = false;
				break;
			case "Charger un terrain":
				break;
			case "Charger une partie":
				break;
			case "Suivant":
				break;
			case " ":
				break;
			}
			
		}
	}
}
