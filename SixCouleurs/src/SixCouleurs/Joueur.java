package SixCouleurs;

import java.util.Scanner;

public class Joueur {
	public boolean ia = false;
	public char couleur;
	public boolean[][] territoire;
	public int score = 1;

	
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
	
	public void jouer(Joueur[] liste, Plateau p){
		Scanner scan = new Scanner(System.in);
		
		char[] listeCouleur = new char[liste.length];
		for (int i=0; i<liste.length; i++) listeCouleur[i]=liste[i].getCouleur();	//récupération des couleurs des joueurs adverses
		
		boolean bonneCouleur = false;
		char couleur='B';
		
		while (bonneCouleur == false){
			bonneCouleur = true;
			System.out.println("Choisissez une couleur à jouer (initiale en majuscule):");
			
			couleur = scan.nextLine().charAt(0);		//Retourne le premier caractère
			for (int i=0; i<listeCouleur.length; i++){
				if(couleur == listeCouleur[i]) bonneCouleur = false;
			}
			
		}
		
		conquerir(couleur, p);
		System.out.println("Score:" + score);
		p.afficher();
	}

	
	
}
