package SixCouleurs;

public class IA extends Joueur{
	
	private boolean[][] territoireLocal;

	public void setTerritoire(boolean[][] territoire) {
		this.territoire = territoire;
		this.territoireLocal = new boolean[territoire.length][territoire[0].length];
		for (int i=0; i<territoireLocal.length; i++){
			for (int j=0; j<territoireLocal[0].length; j++){
				territoireLocal[i][j] = false;
			}
		}
	}

	public void jouer(Joueur[] liste, Plateau p, PanneauPlateau pan, char choix){
		for (int i=0; i<territoireLocal.length; i++){
			for (int j=0; j<territoireLocal[0].length; j++){
				territoireLocal[i][j] = false;
			}
		}
		char[] listeCouleur = new char[liste.length];
		for (int i=0; i<liste.length; i++) listeCouleur[i]=liste[i].getCouleur();	//r�cup�ration des couleurs des joueurs adverses
		char couleur = 'A';

		if (choix == 'C'){
			System.out.println("Tour de " + this.couleur);
			couleur = iaAlea(p,listeCouleur);		//Retourne le premier caract�re
			System.out.println("Score de " + this.couleur + ": " + score);

		} else {
			switch (nom){
			case "Timmy":
				couleur = iaAlea(p, listeCouleur);		//Retourne le premier caract�re
				break;
			case "John":
				couleur = iaMaxPointPerso(p, listeCouleur);		//Retourne le premier caract�re
				break;
			case "Resetti":
				couleur = iaMinPointAutre(p, listeCouleur);		//Retourne le premier caract�re
				break;
			case "Marvin":
				couleur = iaArbre(p, listeCouleur);		//Retourne le premier caract�re
				break;
			case "Chuck Norris":
				couleur = iaChuckNorris(p, listeCouleur);
			}
			
		}
		System.out.println("//"+couleur);
		conquerir(couleur, p);
		
		p.afficher();

	}


	private char iaAlea(Plateau plat, char[] listeCouleurNonJouable){
		char[] listeCouleurJouable = tabCouleurJouable(listeCouleurNonJouable);	//Création de la liste des couleurs jouables
		for (int i=0; i<listeCouleurJouable.length; i++) System.out.print(listeCouleurJouable[i] + " ");
		System.out.println();
		char couleur = 'A';
		int[] listeCouleurChoix = listeCouleursAdjacentes(plat,'N');

		//Création d'un tableau contenant les couleurs adjacentes
		int nbCouleurAdjacentes = 0;
		for (int i=0; i<listeCouleurChoix.length; i++){
			if (listeCouleurChoix[i] == 0) nbCouleurAdjacentes++;
		}
		char[] listeCouleurA = new char[6-nbCouleurAdjacentes];
		int compt = 0;
		for (int i=0; i<6; i++){
			if (listeCouleurChoix[i] != 0){
				switch (i){
				case 0:
					listeCouleurA[compt] = 'r';
					compt++;
					break;
				case 1:
					listeCouleurA[compt] = 'o';
					compt++;
					break;
				case 2:
					listeCouleurA[compt] = 'j';
					compt++;
					break;
				case 3:
					listeCouleurA[compt] = 'v';
					compt++;
					break;
				case 4:
					listeCouleurA[compt] = 'b';
					compt++;
					break;
				case 5:
					listeCouleurA[compt] = 'i';
					compt++;
					break;
				}
			}
		}
		
		System.out.println("---------------");
		for (int i=0; i<listeCouleurA.length; i++)System.out.println(listeCouleurA[i]);
		System.out.println("///////////////");
		for (int i=0; i<listeCouleurJouable.length; i++)System.out.println(listeCouleurJouable[i]);
		System.out.println("---------------");
		
		for (int j=0; j<10; j++){
			try {
				couleur = listeCouleurA[Plateau.alea(0,listeCouleurA.length)];
				for (int i=0; i<listeCouleurNonJouable.length; i++){
					if((char)((int)couleur-32) == listeCouleurNonJouable[i]) couleur = 'A';
				}
				if (couleur != 'A') break;
			} catch (ArrayIndexOutOfBoundsException e){
				break;
			}
		}
		if (couleur == 'A') {
			couleur = (char)((int)listeCouleurJouable[Plateau.alea(0,listeCouleurJouable.length)]+32);
		}
		couleur = (char)((int)couleur-32);	//Passage de la couleur en majuscule
		System.out.println(couleur);
		return couleur;
	}

	private char iaMaxPointPerso(Plateau plat, char[] listeCouleurNonJouable){
		char couleur = 'A';
		int[] listeCouleurChoix = listeCouleursAdjacentes(plat,'N');
		for (int i=0; i<listeCouleurChoix.length; i++) System.out.print(listeCouleurChoix[i]);
		System.out.println();
		char[] listeCouleurA = triCouleurDecroissant(listeCouleurChoix);
		for (int i=0; i<listeCouleurA.length; i++) System.out.print(listeCouleurA[i]);
		System.out.println();
		for (int i=0; i<7; i++){
			couleur = listeCouleurA[i];
			for (int j=0; j<listeCouleurNonJouable.length; j++){
				if((char)((int)couleur-32) == listeCouleurNonJouable[j]) couleur = 'A';
			}
			if (couleur != 'A') break;
		}

		couleur = (char)((int)couleur-32);	//Passage de la couleur en majuscule
		System.out.println(couleur);
		return couleur;
	}

	private char iaMinPointAutre(Plateau plat, char[] listeCouleurNonJouable){
		char couleur = 'A';
		int[] listeCouleurChoix = {0, 0, 0, 0, 0, 0};
		for (char coul : listeCouleurNonJouable){
			int[] listeCouleurChoixJoueur = listeCouleursAdjacentes(plat, coul);
			for (int i=0; i<6; i++)
				listeCouleurChoix[i] += listeCouleurChoixJoueur[i];
		}
		
		
		char[] listeCouleurA = triCouleurDecroissant(listeCouleurChoix);

		for (int i=0; i<6; i++){
			couleur = listeCouleurA[i];
			for (int j=0; j<listeCouleurNonJouable.length; j++){
				if((char)((int)couleur-32) == listeCouleurNonJouable[j]) couleur = 'A';
			}
			if (couleur != 'A') break;
		}

		couleur = (char)((int)couleur-32);	//Passage de la couleur en majuscule
		System.out.println(couleur);
		return couleur;
	}

	private char iaArbre(Plateau plat, char[] listeCouleurNonJouable){
		return iaMaxPointPerso(plat, listeCouleurNonJouable);
	}

	private char iaChuckNorris(Plateau plat, char[] listeCouleurNonJouable){
		char[] listeCouleurJouable = tabCouleurJouable(listeCouleurNonJouable);	//Création de la liste des couleurs jouables
		char couleur = (char)((int)listeCouleurJouable[Plateau.alea(0,listeCouleurJouable.length)]+32);
		Case[][] nouveauPlateau = plat.getPlateau();
		for (int i=0; i<plat.getPlateau().length; i++){
			for (int j=0; j<plat.getPlateau()[0].length; j++){
				nouveauPlateau[i][j].setCouleur(couleur);
				score++;
			}
		}
		score--;
		plat.setPlateau(nouveauPlateau);
		return couleur;
	}

	private int[] listeCouleursAdjacentes(Plateau plat, char couleur){
		//TODO Bug couleur adjacente?
		int[] listeCouleur = {0, 0, 0, 0, 0, 0};		//Rouge, Orange, Jaune, Vert, Bleu, Violet
		Case[][] terrain = plat.getPlateau();	//récupération
		
		int curseurX = 0;
		int curseurY = 0;
		if (couleur == 'N'){
			if (territoire[territoire.length-1][0]) curseurX = territoire.length-1;
			if (territoire[0][territoire[0].length-1]) curseurY = territoire[0].length-1;
			if (territoire[territoire.length-1][territoire[0].length-1]) {
				curseurX = territoire.length-1;
				curseurY = territoire[0].length-1;
			}
		} else {
			if (plat.getPlateau()[territoire.length-1][0].getCouleur() == couleur) curseurX = territoire.length-1;
			if (plat.getPlateau()[0][territoire[0].length-1].getCouleur() == couleur) curseurY = territoire[0].length-1;
			if (plat.getPlateau()[territoire.length-1][territoire[0].length-1].getCouleur() == couleur) {
				curseurX = territoire.length-1;
				curseurY = territoire[0].length-1;
			}
		}
		
		listeCouleurAdjacent(terrain[curseurX][curseurY], terrain[curseurX][curseurY].getCouleur(), listeCouleur);
		for (int coul : listeCouleur){
			System.out.print(coul + ", ");
		}
		System.out.println();
		return listeCouleur;
	}
	
	private void listeCouleurAdjacent(Case caseVerif, char couleur, int[] listeCouleur){
		if ((int)caseVerif.getCouleur()>97){	//Vérifie que la case ne soit pas prise (minuscule)
			territoireLocal[caseVerif.getY()][caseVerif.getX()] = true;
			if (caseVerif.getCouleur() == couleur || (int)couleur < 97){
				switch (caseVerif.getCouleur()){
				case 'r':
					listeCouleur[0] ++;
					break;
				case 'o':
					listeCouleur[1] ++;
					break;
				case 'j':
					listeCouleur[2] ++;
					break;
				case 'v':
					listeCouleur[3] ++;
					break;
				case 'b':
					listeCouleur[4] ++;
					break;
				case 'i':
					listeCouleur[5] ++;
					break;
				}
				for (int i=0; i<caseVerif.getVoisin().length; i++){
					if (caseVerif.getVoisin()[i] != null && 
							!territoireLocal[caseVerif.getVoisin()[i].getY()][caseVerif.getVoisin()[i].getX()]){
						listeCouleurAdjacent(caseVerif.getVoisin()[i], caseVerif.getCouleur(), listeCouleur);
					}	
				}
			}
			
		}
		else if(caseVerif.getCouleur() == couleur){
			territoireLocal[caseVerif.getY()][caseVerif.getX()] = true;
			for (int i=0; i<caseVerif.getVoisin().length; i++){
				if (caseVerif.getVoisin()[i] != null && 
						!territoireLocal[caseVerif.getVoisin()[i].getY()][caseVerif.getVoisin()[i].getX()])
					listeCouleurAdjacent(caseVerif.getVoisin()[i], couleur, listeCouleur);
			}
			
		}
	}

	
	private char[] triCouleurDecroissant(int[] tableau){
		//Rouge, Orange, Jaune, Vert, Bleu, Violet
		int indiceMax = 0;
		int indiceListe = 0;
		char[] liste = new char[6];
		for (int i=0; i<6; i++){
			indiceMax = 0;
			for (int j=1; j<6; j++){
				if (tableau[j] > tableau[indiceMax]) indiceMax = j;
			}
			switch (indiceMax){
			case 0:
				liste[indiceListe] = 'r';
				break;
			case 1:
				liste[indiceListe] = 'o';
				break;
			case 2:
				liste[indiceListe] = 'j';
				break;
			case 3:
				liste[indiceListe] = 'v';
				break;
			case 4:
				liste[indiceListe] = 'b';
				break;
			case 5:
				liste[indiceListe] = 'i';
				break;
			}
			indiceListe ++;
			tableau[indiceMax] = -1;
		}
		return liste;
	}

}
