package SixCouleurs;

import java.awt.Color;
import java.awt.Font;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Joueur {
	protected char couleur;
	protected boolean[][] territoire;
	protected int score = 1;
	protected String nom;


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
		char[] listeCouleur = new char[liste.length];
		for (int i=0; i<liste.length; i++) listeCouleur[i]=liste[i].getCouleur();	//récupération des couleurs des joueurs adverses
		boolean bonneCouleur = false;
		char couleur='A';

		if (choix == 'C'){
			Scanner scan = new Scanner(System.in);

			

			while (bonneCouleur == false){
				bonneCouleur = true;
				System.out.println("Tour de " + this.couleur);
				System.out.println("Choisissez une couleur à jouer (initiale en majuscule):");

				couleur = scan.nextLine().charAt(0);		//Retourne le premier caractère
				for (int i=0; i<listeCouleur.length; i++){
					if(couleur == listeCouleur[i]) bonneCouleur = false;
				}

			}

			System.out.println("Score de " + this.couleur + ": " + score);

		} else {
			char[] listeCouleurJouable = tabCouleurJouable(listeCouleur);	//Création de la liste des couleurs jouables
			int[][] tabCoordonnees = tabCoordonnees(listeCouleurJouable);	//Création de la liste de leurs coordonnées



			while (couleur == 'A'){
				couleur = testCouleurGraphique(pan, bonneCouleur, listeCouleurJouable, tabCoordonnees, couleur);
				cliqueBoutonSauvegarder(liste, pan);
			}
			pan.setPosCliqueX(0);
			pan.setPosCliqueY(0);
			System.out.println(couleur);
			
		}
		conquerir(couleur, p);
		p.afficher();

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
					if(pan.getPosCliqueX() == X && pan.getPosCliqueY() == Y) couleur = listeCouleurJouable[i];
				}
			}
			
		}
		pan.repaint();
		return couleur;
	}

	protected char[] tabCouleurJouable(char[] listeCouleur){
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
	
	protected int[][] tabCoordonnees(char[] listeCouleur){
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
	
	private void cliqueBoutonSauvegarder(Joueur[] liste, PanneauPlateau pan){
		int X = pan.getPosCliqueX();
		int Y = pan.getPosCliqueY();

		if (935<X && X<943+153){
			if (657<Y && Y<689){
				EditeurFichier editFichier = new EditeurFichier();
				String nomFichier = editFichier.fenetreSauvegarder();
				if (nomFichier.equals("")) System.out.println("Pas d'entrée");
				else {
					if (editFichier.existanceFichierDossier("Sauvegardes", nomFichier + ".txt")){
						boolean choix = editFichier.fenetreBouton();
						if (choix){
							String[] fichierSauvegarde = adapterSauvegardeString(pan);
							String informations = nom + "\u00A0";			//nom du joueur jouant son tour
							for (int i=0; i<liste.length; i++){			//Liste de tous les joueurs
								informations = informations + liste[i].getNom() + "\u00A0" + liste[i].getCouleur() + "\u00A0";
							}
							
							editFichier.ecriture(informations, fichierSauvegarde, "Sauvegardes" + nomFichier);
						}
					}else {
						String[] fichierSauvegarde = adapterSauvegardeString(pan);
						String informations = nom + "≡";			//nom du joueur jouant son tour
						for (int i=0; i<liste.length; i++){			//Liste de tous les joueurs
							informations = informations + liste[i].getNom() + "‼" + liste[i].getCouleur() + "≡";
						}
						
						editFichier.ecriture(informations, fichierSauvegarde, "Sauvegardes" + nomFichier);
					}
					
				}
				
				pan.setPosCliqueX(0);
				pan.setPosCliqueY(0);
			}
		}
		
	}

	private String[] adapterSauvegardeString(PanneauPlateau pan){
		//TODO réécrire le tableau du terrain
		char[][] terrain = pan.getPlateau();
		String[] listeTableau = new String[terrain.length];
		for (int i=0; i<listeTableau.length; i++){
			for (int j=0; j<terrain[0].length; j++){
				listeTableau[i] = listeTableau[i] + terrain[i][j] + "≡";
			}
		}
		return listeTableau;
	}

}
