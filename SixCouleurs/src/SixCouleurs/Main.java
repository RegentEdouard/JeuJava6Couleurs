package SixCouleurs;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int tailleX = 10;
		int tailleY = 5;
		Plateau test = new Plateau(tailleX, tailleY);
		
		//Création des joueurs
		Joueur joueur1 = new Joueur();
		creationJoueur(0, 0, joueur1, test);
		Joueur joueur2 = new Joueur();
		creationJoueur(tailleX-1, tailleY-1, joueur2, test);
		
		test.afficher();
		
		//Premier tour
		Scanner scan = new Scanner(System.in);
		System.out.println("couleur choisit:");
		char couleur = scan.next("[A-Z]").charAt(0);		//Retourne le premier caractère
		joueur1.conquerir(couleur, test);
		test.afficher();
		
		
		scan.close();
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

}
