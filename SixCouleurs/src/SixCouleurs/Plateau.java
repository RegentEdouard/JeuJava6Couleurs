package SixCouleurs;

public class Plateau {
	public char[][] plateau;

	public char[][] getPlateau() {
		return plateau;
	}

	public void setPlateau(char[][] plateau) {
		this.plateau = plateau;
	}
	
	public Plateau(int ligne, int colonne) {
		char[][] plateau = new char[ligne][colonne];
		char[] couleur = {'r','o','j','v','b','i'};
		for (int i=0; i<ligne; i++){
			for(int j=0; j<colonne; j++){
				plateau[i][j] = couleur[alea(0,5)];
			}
		}
		
		//pour deux joueurs
		plateau[0][0] = (char)((int)plateau[0][0]-32);	//passage de la case en haut à gauche en majuscule (joueur1)
		plateau[ligne-1][colonne-1] = (char)((int)plateau[ligne-1][colonne-1]-32);	////passage de la case en bas à droite en majuscule (joueur2)
		this.plateau = plateau;
		
		//création des joueurs
		Joueur joueur1 = new Joueur();
		Joueur joueur2 = new Joueur();
		joueur1.setTerritoire(tabDebut(0, 0, new boolean[ligne][colonne]));
		joueur1.setCouleur(plateau[0][0]);
		joueur2.setTerritoire(tabDebut(ligne-1, colonne-1, new boolean[ligne][colonne]));
		joueur1.setCouleur(plateau[ligne-1][colonne-1]);
	}
	
	public void afficher(){
		for (int i=0; i<plateau.length; i++){
			System.out.print("| ");
			for (int j=0; j<plateau[0].length; j++){
				System.out.print(plateau[i][j] + " | ");
			}
			System.out.println("");
		}
	}
	
	public static int alea(int min, int max){
		int Nb =(int)( min + (Math.random() * (max - min)));
		return Nb;
	}
	
	public static boolean[][] tabDebut(int x, int y, boolean[][] tab){
		for (int i=0; i<tab.length; i++){
			for (int j=0; j<tab[0].length; j++){
				tab[i][j] = false;
			}
		}
		tab[y][x] = true;
		return tab;
	}
}
