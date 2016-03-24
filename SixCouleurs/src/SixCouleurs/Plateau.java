package SixCouleurs;

public class Plateau {
	public char[][] plateau;

	
	public char[][] getPlateau() {
		return plateau;
	}

	public void setPlateau(char[][] plateau) {
		this.plateau = plateau;
	}
	
	
	public Plateau(int colonne, int ligne) {
		char[][] plateau = new char[ligne][colonne];
		char[] couleur = {'r','o','j','v','b','i'};
		for (int i=0; i<ligne; i++){
			for(int j=0; j<colonne; j++){
				plateau[i][j] = couleur[alea(0,5)];
			}
		}
		this.plateau = plateau;
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
	
	public void modification(boolean[][] territoire, char modif){
		for (int i=0; i<territoire.length; i++){
			for (int j=0; j<territoire[0].length; j++){
				if (territoire[i][j] == true) plateau[i][j] = modif;
			}
		}
	}
	
	public static int alea(int min, int max){
		int Nb =(int)( min + (Math.random() * (max - min)));
		return Nb;
	}
	
}
