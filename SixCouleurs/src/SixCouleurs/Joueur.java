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
							fin = false;
						}
						if (j<terrain.length-1 && terrain[i][j+1] == (char)((int)couleur+32)){
							terrain[i][j+1] = couleur;
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
							fin = false;
						}
						if (i<terrain[0].length-1 && terrain[i+1][j] == (char)((int)couleur+32)){
							terrain[i+1][j] = couleur;
							fin = false;
						}
					}
				}
			}
		}
		
		p.setPlateau(terrain);
	}
	
}
