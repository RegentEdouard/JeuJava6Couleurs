package SixCouleurs;

import java.util.LinkedList;

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
			}
			
		}
		conquerir(couleur, p);
		p.afficher();

	}


	private char iaAlea(Plateau plat, char[] listeCouleurNonJouable){
		for (int i=0; i<territoireLocal.length; i++){
			for (int j=0; j<territoireLocal[0].length; j++){
				territoireLocal[i][j] = false;
			}
		}
		char[] listeCouleurJouable = tabCouleurJouable(listeCouleurNonJouable);	//Cr�ation de la liste des couleurs jouables
		for (int i=0; i<listeCouleurJouable.length; i++) System.out.print(listeCouleurJouable[i] + " ");
		System.out.println();
		char couleur = 'A';
		int[] listeCouleurChoix = listeCouleursAdjacentes(plat,'N');

		//Cr�ation d'un tableau contenant les couleurs adjacentes
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
		for (int i=0; i<territoireLocal.length; i++){
			for (int j=0; j<territoireLocal[0].length; j++){
				territoireLocal[i][j] = false;
			}
		}
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
		for (int i=0; i<territoireLocal.length; i++){
			for (int j=0; j<territoireLocal[0].length; j++){
				territoireLocal[i][j] = false;
			}
		}
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


	private int[] listeCouleursAdjacentes(Plateau plat, char couleur){
		int[] listeCouleur = {0, 0, 0, 0, 0, 0};		//Rouge, Orange, Jaune, Vert, Bleu, Violet
		boolean boucle = true;
		int curseurX = 0;
		int curseurY = 0;
		
		//Recherche du coin de départ
		if (couleur == 'N'){
			if (territoire[territoire.length-1][0]) curseurX = territoire.length-1;
			if (territoire[0][territoire[0].length-1]) curseurY = territoire[0].length-1;
			if (territoire[territoire.length-1][territoire[0].length-1]) {
				curseurX = territoire.length-1;
				curseurY = territoire[0].length-1;
			}
		} else {
			if (plat.getPlateau()[territoire.length-1][0] == couleur) curseurX = territoire.length-1;
			if (plat.getPlateau()[0][territoire[0].length-1] == couleur) curseurY = territoire[0].length-1;
			if (plat.getPlateau()[territoire.length-1][territoire[0].length-1] == couleur) {
				curseurX = territoire.length-1;
				curseurY = territoire[0].length-1;
			}
		}
		

//		char direction = 'N'; //4 directionos: Nord, Est, Sud, Ouest
		LinkedList<int[]> listeBordureCouleur = territoireAdjacent(plat, curseurX, curseurY);
		while (boucle){
			
			//Compte des couleurs voisines
			if (curseurX != 0 && (int)plat.getPlateau()[curseurX-1][curseurY]>97 && !territoireLocal[curseurX-1][curseurY]){	//V�rification si le caract�re est en minuscule
				LinkedList<int[]> listeTerritoireAdjacent = territoireAdjacent(plat, curseurX-1, curseurY);
				switch (plat.getPlateau()[listeTerritoireAdjacent.peek()[0]][listeTerritoireAdjacent.peek()[1]]){
				case 'r':
					listeCouleur[0] += listeTerritoireAdjacent.size();
					break;
				case 'o':
					listeCouleur[1] += listeTerritoireAdjacent.size();
					break;
				case 'j':
					listeCouleur[2] += listeTerritoireAdjacent.size();
					break;
				case 'v':
					listeCouleur[3] += listeTerritoireAdjacent.size();
					break;
				case 'b':
					listeCouleur[4] += listeTerritoireAdjacent.size();
					break;
				case 'i':
					listeCouleur[5] += listeTerritoireAdjacent.size();
					break;
				}
				for (int i=0; i<listeTerritoireAdjacent.size(); i++){
					territoireLocal[listeTerritoireAdjacent.peek()[0]][listeTerritoireAdjacent.peek()[1]] = true;
					listeTerritoireAdjacent.remove();
				}

			}
			if (curseurX != plat.getPlateau().length-1 && (int)plat.getPlateau()[curseurX+1][curseurY]>97 && !territoireLocal[curseurX+1][curseurY]){	//V�rification minuscule
				LinkedList<int[]> listeTerritoireAdjacent = territoireAdjacent(plat, curseurX+1, curseurY);
				switch (plat.getPlateau()[listeTerritoireAdjacent.peek()[0]][listeTerritoireAdjacent.peek()[1]]){
				case 'r':
					listeCouleur[0] += listeTerritoireAdjacent.size();
					break;
				case 'o':
					listeCouleur[1] += listeTerritoireAdjacent.size();
					break;
				case 'j':
					listeCouleur[2] += listeTerritoireAdjacent.size();
					break;
				case 'v':
					listeCouleur[3] += listeTerritoireAdjacent.size();
					break;
				case 'b':
					listeCouleur[4] += listeTerritoireAdjacent.size();
					break;
				case 'i':
					listeCouleur[5] += listeTerritoireAdjacent.size();
					break;
				}
				for (int i=0; i<listeTerritoireAdjacent.size(); i++){
					territoireLocal[listeTerritoireAdjacent.peek()[0]][listeTerritoireAdjacent.peek()[1]] = true;
					listeTerritoireAdjacent.remove();
				}
				
			}
			if (curseurY != 0 && (int)plat.getPlateau()[curseurX][curseurY-1]>97 && !territoireLocal[curseurX][curseurY-1]){	//V�rification si le caract�re est en minuscule
				LinkedList<int[]> listeTerritoireAdjacent = territoireAdjacent(plat, curseurX, curseurY-1);
				switch (plat.getPlateau()[listeTerritoireAdjacent.peek()[0]][listeTerritoireAdjacent.peek()[1]]){
				case 'r':
					listeCouleur[0] += listeTerritoireAdjacent.size();
					break;
				case 'o':
					listeCouleur[1] += listeTerritoireAdjacent.size();
					break;
				case 'j':
					listeCouleur[2] += listeTerritoireAdjacent.size();
					break;
				case 'v':
					listeCouleur[3] += listeTerritoireAdjacent.size();
					break;
				case 'b':
					listeCouleur[4] += listeTerritoireAdjacent.size();
					break;
				case 'i':
					listeCouleur[5] += listeTerritoireAdjacent.size();
					break;
				}
				for (int i=0; i<listeTerritoireAdjacent.size(); i++){
					territoireLocal[listeTerritoireAdjacent.peek()[0]][listeTerritoireAdjacent.peek()[1]] = true;
					listeTerritoireAdjacent.remove();
				}
				
			}
			if (curseurY != plat.getPlateau()[0].length-1 && (int)plat.getPlateau()[curseurX][curseurY+1]>97 && !territoireLocal[curseurX][curseurY+1]){	//V�rification minuscule
				LinkedList<int[]> listeTerritoireAdjacent = territoireAdjacent(plat, curseurX, curseurY+1);
				switch (plat.getPlateau()[listeTerritoireAdjacent.peek()[0]][listeTerritoireAdjacent.peek()[1]]){
				case 'r':
					listeCouleur[0] += listeTerritoireAdjacent.size();
					break;
				case 'o':
					listeCouleur[1] += listeTerritoireAdjacent.size();
					break;
				case 'j':
					listeCouleur[2] += listeTerritoireAdjacent.size();
					break;
				case 'v':
					listeCouleur[3] += listeTerritoireAdjacent.size();
					break;
				case 'b':
					listeCouleur[4] += listeTerritoireAdjacent.size();
					break;
				case 'i':
					listeCouleur[5] += listeTerritoireAdjacent.size();
					break;
				}
				for (int i=0; i<listeTerritoireAdjacent.size(); i++){
					territoireLocal[listeTerritoireAdjacent.peek()[0]][listeTerritoireAdjacent.peek()[1]] = true;
					listeTerritoireAdjacent.remove();
				}
				
			}
			//Fin de la vérif

			
			//Déplacement à la frontière du territoire
			curseurX = listeBordureCouleur.peek()[0];
			curseurY = listeBordureCouleur.peek()[1];
			listeBordureCouleur.remove();
			if (listeBordureCouleur.peek() == null) boucle = false;
			
		}
		
		return listeCouleur;
	}
	
	private int[] deplacementCurseur(int curseurX, int curseurY, char direction, Plateau plat, char couleurTerritoire){
		int[] result = {curseurX,curseurY,0};	//3 composantes: curseurX, curseurY, direction (1:N,2:E,3:S,4:O)
		switch (direction){
		case 'N':
			if (curseurY != 0 && plat.getPlateau()[curseurX][curseurY-1] == couleurTerritoire){
				result[1] = curseurY-1;
				result[2] = 4;	//O
			}
			else if (curseurX != 0 && plat.getPlateau()[curseurX-1][curseurY] == couleurTerritoire){
				result[0] = curseurX-1;
				result[2] = 1;	//N
			}
			else if (curseurY != plat.getPlateau()[0].length-1 && plat.getPlateau()[curseurX][curseurY+1] == couleurTerritoire){
				result[1] = curseurY+1;
				result[2]= 2;	//E
			}
			else if (curseurX != plat.getPlateau().length-1 && plat.getPlateau()[curseurX+1][curseurY] == couleurTerritoire){
				result[0] = curseurX+1;
				result[2] = 3;	//S
			}
			break;
		case 'E':
			if (curseurX != 0 && plat.getPlateau()[curseurX-1][curseurY] == couleurTerritoire){
				result[0] = curseurX-1;
				result[2] = 1;	//N
			}
			else if (curseurY != plat.getPlateau()[0].length-1 && plat.getPlateau()[curseurX][curseurY+1] == couleurTerritoire){
				result[1] = curseurY+1;
				result[2] = 2;	//E
			}
			else if (curseurX != plat.getPlateau().length-1 && plat.getPlateau()[curseurX+1][curseurY] == couleurTerritoire){
				result[0] = curseurX+1;
				result[2] = 3;	//S
			}
			else if (curseurY != 0 && plat.getPlateau()[curseurX][curseurY-1] == couleurTerritoire){
				result[1] = curseurY-1;
				result[2] = 4;	//O
			}
			break;
		case 'S':
			if (curseurY != plat.getPlateau()[0].length-1 && plat.getPlateau()[curseurX][curseurY+1] == couleurTerritoire){
				result[1] = curseurY+1;
				result[2] = 2;	//E
			}
			else if (curseurX != plat.getPlateau().length-1 && plat.getPlateau()[curseurX+1][curseurY] == couleurTerritoire){
				result[0] = curseurX+1;
				result[2] = 3;	//S
			}
			else if (curseurY != 0 && plat.getPlateau()[curseurX][curseurY-1] == couleurTerritoire){
				result[1] = curseurY-1;
				result[2] = 4;	//O
			}
			else if (curseurX != 0 && plat.getPlateau()[curseurX-1][curseurY] == couleurTerritoire){
				result[0] = curseurX-1;
				result[2] = 1;	//N
			}
			break;
		case 'O':
			if (curseurX != plat.getPlateau().length-1 && plat.getPlateau()[curseurX+1][curseurY] == couleurTerritoire){
				result[0] = curseurX+1;
				result[2] = 3;	//S
			}
			else if (curseurY != 0 && plat.getPlateau()[curseurX][curseurY-1] == couleurTerritoire){
				result[1] = curseurY-1;
				result[2] = 4;	//O
			}
			else if (curseurX != 0 && plat.getPlateau()[curseurX-1][curseurY] == couleurTerritoire){
				result[0] = curseurX-1;
				result[2] =	1;	//N
			}
			else if (curseurY != plat.getPlateau()[0].length-1 && plat.getPlateau()[curseurX][curseurY+1] == couleurTerritoire){
				result[1] = curseurY+1;
				result[2] = 2;	//E
			}
			break;
		}
		return result;
	}
	
	private LinkedList<int[]> territoireAdjacent(Plateau plat, int X, int Y){
		//TODO faire le cas en L
		LinkedList<int[]> listeCoordonnees= new LinkedList<int[]>();
		int curseurX = X; int curseurY = Y;
		char direction = 'N';
		do{
			int[] tabCurseur = deplacementCurseur(curseurX, curseurY, direction, plat, plat.getPlateau()[X][Y]);
			curseurX = tabCurseur[0];
			curseurY = tabCurseur[1];
			switch(tabCurseur[2]){
			case 1:
				direction = 'N';
				break;
			case 2:
				direction = 'E';
				break;
			case 3:
				direction = 'S';
				break;
			case 4:
				direction = 'O';
				break;
			}
			int[] temp = {curseurX, curseurY};
			listeCoordonnees.add(temp);
			if (X == curseurX && Y == curseurY)break;
		} while (true);
		return listeCoordonnees;
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
