package SixCouleurs;

import java.awt.event.MouseEvent;
import java.util.Scanner;

public class Joueur {
	public boolean ia = false;
	public char couleur;
	public boolean[][] territoire;
	public int score = 1;
	public String nom;


	public boolean isIa() {
		return ia;
	}

	public void setIa(boolean ia) {
		this.ia = ia;
	}

	public char getCouleur() {
		return couleur;
	}

	public void setCouleur(char couleur) {
		this.couleur = couleur;
	}

	public boolean[][] getTerritoire() {
		return territoire;
	}

	public void setTerritoire(boolean[][] territoire) {
		this.territoire = territoire;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public void conquerir(char couleur, Plateau p){	//couleur est en majuscule
		this.couleur = couleur;
		p.modification(territoire, couleur);
		boolean fin = false;	//vérifie s'il y a eu des modifications sur le terrain
		char[][] terrain = p.getPlateau();	//récupération
		while (fin == false){
			fin = true;
			for (int i=0; i<terrain.length; i++){
				for (int j=0; j<terrain[0].length; j++){
					if (terrain[i][j] == couleur){
						if (j>0 && terrain[i][j-1] == (char)((int)couleur+32)){
							terrain[i][j-1] = couleur;
							territoire[i][j-1] = true;
							score ++;
							fin = false;
						}
						if (j<terrain[0].length-1 && terrain[i][j+1] == (char)((int)couleur+32)){
							terrain[i][j+1] = couleur;
							territoire[i][j+1] = true;
							score ++;
							fin = false;
						}
					}
				}
			}
			for (int j=0; j<terrain[0].length; j++){
				for (int i=0; i<terrain.length; i++){
					if (terrain[i][j] == couleur){
						if (i>0 && terrain[i-1][j] == (char)((int)couleur+32)){
							terrain[i-1][j] = couleur;
							territoire[i-1][j] = true;
							score ++;
							fin = false;
						}
						if (i<terrain.length-1 && terrain[i+1][j] == (char)((int)couleur+32)){
							terrain[i+1][j] = couleur;
							territoire[i+1][j] = true;
							score ++;
							fin = false;
						}
					}
				}
			}
		}

		p.setPlateau(terrain);
	}

	public void jouer(Joueur[] liste, Plateau p, PanneauPlateau pan, char choix){
		if (choix == 'C'){
			Scanner scan = new Scanner(System.in);

			char[] listeCouleur = new char[liste.length];
			for (int i=0; i<liste.length; i++) listeCouleur[i]=liste[i].getCouleur();	//récupération des couleurs des joueurs adverses

			boolean bonneCouleur = false;
			char couleur='A';

			while (bonneCouleur == false){
				bonneCouleur = true;
				System.out.println("Tour de " + this.couleur);
				System.out.println("Choisissez une couleur à jouer (initiale en majuscule):");

				couleur = scan.nextLine().charAt(0);		//Retourne le premier caractère
				for (int i=0; i<listeCouleur.length; i++){
					if(couleur == listeCouleur[i]) bonneCouleur = false;
				}

			}

			conquerir(couleur, p);
			System.out.println("Score de " + this.couleur + ": " + score);
			p.afficher(pan, choix);

		} else {
			char[] listeCouleur = new char[liste.length];
			for (int i=0; i<liste.length; i++) listeCouleur[i]=liste[i].getCouleur();	//Récupération des couleurs des joueurs adverses
			char[] listeCouleurJouable = tabCouleurJouable(listeCouleur);	//Création de la liste des couleurs jouables
			int[][] tabCoordonnees = tabCoordonnees(listeCouleurJouable);	//Création de la liste de leurs coordonnées
			
			
			boolean bonneCouleur = false;
			char couleur = 'A';


			while (couleur == 'A'){
				couleur = testCouleurGraphique(pan, bonneCouleur, listeCouleurJouable, tabCoordonnees, couleur);
			}
			pan.setPosCliqueX(0);
			pan.setPosCliqueY(0);
			System.out.println(couleur);
			conquerir(couleur, p);
			p.afficher(pan, choix);
			
		}

	}

	public char testCouleurGraphique(PanneauPlateau pan, boolean bonneCouleur, 
			char[] listeCouleurJouable, int[][] tabCoordonnees, char couleur){
		bonneCouleur = false;
		int X = pan.getPosSourisX();
		int Y = pan.getPosSourisY();
		pan.setCouleurSouris('A');
		couleur = 'A';
		
		for (int i=0; i<listeCouleurJouable.length; i++){
			X = pan.getPosSourisX();
			if (tabCoordonnees[i][0]<X && X<tabCoordonnees[i][1]){
				Y = pan.getPosSourisY();
				if (tabCoordonnees[i][2]<Y && Y<tabCoordonnees[i][3]){
					pan.setCouleurSouris(listeCouleurJouable[i]);
					if(pan.getPosCliqueX() == X && pan.getPosCliqueY() == Y) couleur = listeCouleurJouable[i];;
				}
			}
			
		}
		pan.repaint();
		return couleur;
	}

	private char[] tabCouleurJouable(char[] listeCouleur){
		char[] listeCouleurJouable = new char[6 - listeCouleur.length];
		int indiceTab = 0;
		char[] couleur = {'R','O','J','V','B','I'};
		boolean couleurOk = false;
		for (int i=0; i<6; i++){
			couleurOk = true;
			for (int j=0; j<listeCouleur.length; j++){
				if (listeCouleur[j] == couleur[i]){
					couleurOk = false;
					break;
				}
			}
			if (couleurOk){
				listeCouleurJouable[indiceTab] = couleur[i];
				indiceTab ++;
			}
		}
		return listeCouleurJouable;
	}
	
	private int[][] tabCoordonnees(char[] listeCouleur){
		int[][] tabCoordonnees = new int[listeCouleur.length][4];
		//'r','o','j','v','b','i'

		
		for (int i=0; i<listeCouleur.length; i++){
			tabCoordonnees[i][2] = 660;
			tabCoordonnees[i][3] = 681;
			switch(listeCouleur[i]){
			case 'R':
				tabCoordonnees[i][0] = 362;
				tabCoordonnees[i][1] = 383;
				break;
			case 'O':
				tabCoordonnees[i][0] = 393;
				tabCoordonnees[i][1] = 414;
				break;
			case 'J':
				tabCoordonnees[i][0] = 424;
				tabCoordonnees[i][1] = 445;
				break;
			case 'V':
				tabCoordonnees[i][0] = 455;
				tabCoordonnees[i][1] = 476;
				break;
			case 'B':
				tabCoordonnees[i][0] = 486;
				tabCoordonnees[i][1] = 507;
				break;
			case 'I':
				tabCoordonnees[i][0] = 517;
				tabCoordonnees[i][1] = 538;
				break;
			}
		}
		return tabCoordonnees;
	}
}
