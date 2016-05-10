package SixCouleurs;

import java.util.Scanner;

public class Plateau {
	private char[][] plateau;
	private Joueur[] listeJoueurs;
	private PanneauPlateau panneau;
	private char choixAffichage;
	private int nbObstacles = 0;
	

	public char[][] getPlateau() {
		return plateau;
	}

	public void setPlateau(char[][] plateau) {
		this.plateau = plateau;
	}

	public char getChoixAffichage() {
		return choixAffichage;
	}

	public void setChoixAffichage(char choixAffichage) {
		this.choixAffichage = choixAffichage;
	}

	public Joueur[] getListeJoueurs() {
		return listeJoueurs;
	}

	public void setListeJoueurs(Joueur[] listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}

	public PanneauPlateau getPanneau() {
		return panneau;
	}

	public void setPanneau(PanneauPlateau panneau) {
		this.panneau = panneau;
	}


	public void initPlateau(int colonne, int ligne, boolean obstacles) {
		char[][] plateau = new char[ligne][colonne];
		char[] couleur = {'r','o','j','v','b','i'};
		for (int i=0; i<ligne; i++){
			for(int j=0; j<colonne; j++){
				plateau[i][j] = couleur[alea(0,6)];
			}
		}

		//Chaque coin a une couleur différente pour éviter que deux joueurs aient la méme couleur
		while (plateau[ligne-1][colonne-1] == plateau[0][0]){
			plateau[ligne-1][colonne-1] = couleur[alea(0,6)];
		}
		while (plateau[0][colonne-1] == plateau[0][0] || plateau[0][colonne-1] == plateau[ligne-1][colonne-1]){
			plateau[0][colonne-1] = couleur[alea(0,6)];
		}
		while (plateau[ligne-1][0] == plateau[0][0] || plateau[ligne-1][0] == plateau[ligne-1][colonne-1] || plateau[ligne-1][0] == plateau[0][colonne-1]){
			plateau[ligne-1][0] = couleur[alea(0,6)];
		}
		this.plateau = plateau;
		
		//Mise en place des obstacles
		if (obstacles){
			for (int j=plateau[0].length/3; j<plateau[0].length*2/3; j++){
				if (j<=plateau[0].length/2) plateau[(plateau.length-1)/2][j]='N';
				if (j>=plateau[0].length/2) plateau[(plateau.length-1)/2][j]='N';
				nbObstacles++;
			}
			for (int i=plateau.length/3; i<plateau.length*2/3; i++){
				if (i<=plateau.length/2) plateau[i][(plateau[0].length-1)/2]='N';
				if (i>=plateau.length/2) plateau[i][(plateau[0].length-1)/2]='N';
				nbObstacles++;
			}
		}
		
	}


	public void afficher(){
		if (choixAffichage == 'C'){
			for (int i=0; i<plateau.length; i++){
				System.out.print("| ");
				for (int j=0; j<plateau[0].length; j++){
					System.out.print(plateau[i][j] + " | ");
				}
				System.out.println("");
			}
		} else {
			panneau.setPlateau(plateau);
			panneau.repaint();
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
	
	public void afficherScore(Joueur[] listeJ){
		String[] scoreMax = {"0",""};
		for (int i=0; i<listeJ.length; ++i){
			if (Integer.parseInt(scoreMax[0]) < listeJ[i].getScore()){
				scoreMax[0] = Integer.toString(listeJ[i].getScore());
				scoreMax[1] = Character.toString(listeJ[i].getCouleur());
				if (choixAffichage == 'G') panneau.setJoueurActif(i);
			}
		}
		System.out.println(scoreMax[1] + " est vainqueur avec un score de: " + scoreMax[0]);
		if (choixAffichage == 'G') panneau.repaint();
		while (true){
			
		}
	}
	
	public void creationJoueur(int positionX, int positionY, Joueur j){
		char[][] terrain = plateau;
		terrain[positionY][positionX] = (char)((int)plateau[positionY][positionX]-32);	//Passage de la case en majuscule
		plateau = terrain;
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

	public void debutPartie(){
		if (choixAffichage == 'C'){
			Scanner scan = new Scanner(System.in);
			int tailleX = 0;//30
			int tailleY = 0;//40
			while (tailleX<5 || tailleX>30){
				System.out.println("Entrez la hauteur du plateau (entre 5 et 30): ");
				tailleX = scan.nextInt();
			}
			while (tailleY<5 || tailleY>40){
				System.out.println("Entrez la largeur du plateau (entre 5 et 40): ");
				tailleY = scan.nextInt();
			}
			initPlateau(tailleX, tailleY, false);
			int nbJoueur = 0;
			while (nbJoueur<2 || nbJoueur>4){
				System.out.println("Entrez le nombre de joueurs (entre 2 et 4): ");
				nbJoueur = scan.nextInt();
			}
			listeJoueurs = new Joueur[nbJoueur];
			int[][] positionDepart = {{0,0},{tailleX-1,tailleY-1},{0,tailleY-1},{tailleX-1,0}};
			for (int i=0; i<nbJoueur; i++){
				listeJoueurs[i] = new Joueur();
				creationJoueur(positionDepart[i][0], positionDepart[i][1], listeJoueurs[i]);
				listeJoueurs[i].conquerir(listeJoueurs[i].getCouleur(), this);
			}
			afficher();
			partie();
		} else {
			PanneauPlateau nouveauPanneau = new PanneauPlateau();
			Fenetre fen = new Fenetre(nouveauPanneau);	//1150 * 740

			PanneauMenuPrincipal menuPrincipal = new PanneauMenuPrincipal();
			menuPrincipal.menu(fen);
		}

	}
	
	public void partie(){
		boolean partie = true;
		while (partie){
			for (int i=0; i<listeJoueurs.length; i++){
				int scoreTotal = 0;
				for (int j=0; j<listeJoueurs.length; j++){
					scoreTotal += listeJoueurs[j].getScore();
					if(listeJoueurs[j].getScore() > plateau.length*plateau[0].length/2) partie = false;
				}
				if (scoreTotal + nbObstacles >= plateau.length*plateau[0].length) partie = false;
				if (partie == false) break;
				if (choixAffichage == 'G') panneau.setJoueurActif(i);
				listeJoueurs[i].jouer(listeJoueurs, this, panneau, choixAffichage);
			}
		}
		
		if (choixAffichage == 'G') panneau.setFin(true);
		afficherScore(listeJoueurs);
	}


}
