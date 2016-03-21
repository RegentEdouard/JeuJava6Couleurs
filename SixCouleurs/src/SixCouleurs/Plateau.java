package SixCouleurs;

public class Plateau {
	public String[][] plateau;

	public String[][] getPlateau() {
		return plateau;
	}

	public void setPlateau(String[][] plateau) {
		this.plateau = plateau;
	}
	
	public Plateau(int ligne, int colonne) {
		String[][] plateau = new String[ligne][colonne];
		String[] couleur = {"r","o","j","v","b","i"};
		for (int i=0; i<plateau.length; i++){
			for(int j=0; j<plateau[0].length; j++){
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
	
	public static int alea(int min, int max){
		int Nb =(int)( min + (Math.random() * (max - min)));
		return Nb;
	}
}
