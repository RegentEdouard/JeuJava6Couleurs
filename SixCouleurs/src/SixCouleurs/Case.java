package SixCouleurs;

public class Case {
	private int x, y;
	private char couleur;
	private int possesseur;
	private Case[] voisin;

	
	public Case (int x, int y, char couleur){
		this.x = x;
		this.y = y;
		this.couleur = couleur;
	}
	
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public char getCouleur() {
		return couleur;
	}

	public void setCouleur(char couleur) {
		this.couleur = couleur;
	}

	public int getPossesseur() {
		return possesseur;
	}

	public void setPossesseur(int possesseur) {
		this.possesseur = possesseur;
	}


	public Case[] getVoisin() {
		return voisin;
	}

	public void setVoisin(Case[] voisin) {
		this.voisin = voisin;
	}
}
