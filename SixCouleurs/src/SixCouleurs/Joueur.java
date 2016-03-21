package SixCouleurs;

public class Joueur {
	public boolean ia = false;
	public char couleur;
	public boolean[][] territoire;

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
	
}
