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
		Case[][] terrain = p.getPlateau();	//récupération

		
		//Recherche de la position de départ
		int posDepartX = 0, posDepartY = 0;
		if (p.getPlateau()[territoire.length-1][0].getCouleur() == couleur) posDepartX = territoire.length-1;
		if (p.getPlateau()[0][territoire[0].length-1].getCouleur() == couleur) posDepartY = territoire[0].length-1;
		if (p.getPlateau()[territoire.length-1][territoire[0].length-1].getCouleur() == couleur) {
			posDepartX = territoire.length-1;
			posDepartY = territoire[0].length-1;
		}

		for(int i=0; i<territoire.length; i++){
			for(int j=0; j<territoire[0].length; j++){
				territoire[i][j] = false;
			}
		}

		parcoursVoisin(terrain, terrain[posDepartX][posDepartY], couleur);


		p.setPlateau(terrain);
	}

	private void parcoursVoisin(Case[][] terrain, Case caseVerif, char couleur){
		if (caseVerif.getCouleur() == couleur){
			territoire[caseVerif.getY()][caseVerif.getX()] = true;
			for (int i=0; i<caseVerif.getVoisin().length; i++){
				if (caseVerif.getVoisin()[i] != null && territoire[caseVerif.getVoisin()[i].getY()][caseVerif.getVoisin()[i].getX()] != true){
					parcoursVoisin(terrain, caseVerif.getVoisin()[i], couleur);
				}	
			}
		}
		else if(caseVerif.getCouleur() == (char)((int)couleur+32)){
			caseVerif.setCouleur(couleur);
			territoire[caseVerif.getY()][caseVerif.getX()] = true;
			score ++;
			for (int i=0; i<caseVerif.getVoisin().length; i++){
				if (caseVerif.getVoisin()[i] != null)
					parcoursVoisin(terrain, caseVerif.getVoisin()[i], couleur);
			}
		}
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
				cliqueBoutonSauvegarder(liste, p);
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
	
	private void cliqueBoutonSauvegarder(Joueur[] liste, Plateau plat){
		int X = plat.getPanneau().getPosCliqueX();
		int Y = plat.getPanneau().getPosCliqueY();

		if (935<X && X<943+153){
			if (657<Y && Y<689){
				EditeurFichier editFichier = new EditeurFichier();
				String nomFichier = editFichier.fenetreSauvegarder();
				if (nomFichier == null || nomFichier.equals("")) System.out.println("Pas d'entrée");
				else {
					if (editFichier.existanceFichierDossier("Sauvegardes/", nomFichier + ".txt")){
						boolean choix = editFichier.fenetreBouton();
						if (choix){
							String[] fichierSauvegarde = adapterSauvegardeString(plat.getPanneau());
							String informations = nom + System.getProperty("line.separator");	//nom du joueur jouant son tour
							for (int i=0; i<liste.length; i++){			//Liste de tous les joueurs
								informations = informations + liste[i].getNom() + "\u00A0" + liste[i].getCouleur() + "\u00A0"
										+ liste[i].getClass().getSimpleName() + System.getProperty("line.separator");
							}
							informations += plat.getPanneau().getFormeCase() + System.getProperty("line.separator") +
									plat.getNbObstacles() + System.getProperty("line.separator");
							
							editFichier.ecriture(informations, fichierSauvegarde, "Sauvegardes/" + nomFichier);
						}
					}else {
						String[] fichierSauvegarde = adapterSauvegardeString(plat.getPanneau());
						String informations = nom + System.getProperty("line.separator");	//nom du joueur jouant son tour
						for (int i=0; i<liste.length; i++){			//Liste de tous les joueurs
							informations = informations + liste[i].getNom() + "\u00A0" + liste[i].getCouleur() + "\u00A0"
									+ liste[i].getClass().getSimpleName() + System.getProperty("line.separator");
						}
						informations += plat.getPanneau().getFormeCase() + System.getProperty("line.separator") +
								plat.getNbObstacles() + System.getProperty("line.separator");
						
						editFichier.ecriture(informations, fichierSauvegarde, "Sauvegardes/" + nomFichier);
					}
					
				}
				
				plat.getPanneau().setPosCliqueX(0);
				plat.getPanneau().setPosCliqueY(0);
			}
		}
		
	}

	private String[] adapterSauvegardeString(PanneauPlateau pan){
		//TODO réécrire le tableau du terrain
		Case[][] terrain = pan.getPlateau();
		String[] listeTableau = new String[terrain.length];
		for (int i=0; i<listeTableau.length; i++){
			for (int j=0; j<terrain[0].length; j++){
				if ((int) terrain[i][j].getCouleur()<97)
					listeTableau[i] = listeTableau[i] + (char)((int)terrain[i][j].getCouleur()+32)+ "\u00A0";
				else listeTableau[i] = listeTableau[i] + terrain[i][j].getCouleur()+ "\u00A0";
			}
		}
		return listeTableau;
	}

}
