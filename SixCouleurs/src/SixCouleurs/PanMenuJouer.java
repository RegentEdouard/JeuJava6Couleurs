package SixCouleurs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JOptionPane;

public class PanMenuJouer extends PanneauMenuPrincipal{
	private int nbJoueur = 2;
	private int largeurTerrain = 15;
	private int hauteurTerrain = 15;
	private String formeCase = "carré";
	private boolean obstacles = false;
	private boolean priseTerritoireAuto = false;
	private String[] listeNomJoueur = {"      ", "      ", "      ", "      "};
	private boolean[] choixIa = {false, false, false, false};
	private boolean page1 = true;


	public void paintComponent(Graphics g) {
		//Titre
		Font font = new Font("Cooper Black", Font.BOLD, 50);
		g.setFont(font);
		g.setColor(new Color(62, 67, 94));
		g.drawString("Jouer", 493, 55);

		//Affichage des boutons
		font = new Font("Cooper Black", Font.BOLD, 30);
		g.setFont(font);
		g.setColor(new Color(62, 67, 94));
		//Affichage du texte
		for (int i=0; i<text.length; i++){
			g.drawString(text[i], position[i][0], position[i][1]);
			int longueurText = getFontMetrics(font).stringWidth(text[i]);
			//System.out.println(longueurText);
			//Affichage des contours
			g.drawRect(position[i][0]-4, position[i][1]-30+2, longueurText+8, 36 );
			g.drawRect(position[i][0]-5, position[i][1]-30+1, longueurText+10, 38 );
		}
		//Surligner lorsque la souris survolle un bouton 
		for (int i=0; i<text.length; i++){
			int longueurText = getFontMetrics(font).stringWidth(text[i]);
			if (position[i][0]-5<posSourisX && posSourisX<position[i][0]+longueurText+5){
				if (position[i][1]-30+1<posSourisY && posSourisY<position[i][1]+9){
					g.drawRect(position[i][0]-6, position[i][1]-30, longueurText+12, 40);
					g.drawRect(position[i][0]-7, position[i][1]-30-1, longueurText+14, 42);
				}
			}
		}
		if (page1){
			//Texte de la page n°1
			g.drawString("Nombre de joueurs:", 300, 140);
			g.drawString(Integer.toString(nbJoueur), 685, 140);
			g.drawString("Taille du terrain:", 300, 230);
			g.drawString("Largeur:", 630, 230);
			g.drawString(Integer.toString(largeurTerrain), 790, 230);
			g.drawString("Hauteur:", 630, 280);
			g.drawString(Integer.toString(hauteurTerrain), 795, 280);
			g.drawString("Formes des cases:", 300, 360);
			g.drawString("Obstacles", 300, 450);
			g.drawString("Prise automatique", 300, 540);
			if (obstacles) g.fillRect(484, 450-30+5, 33, 31);			//Bouton obstacle cliqu�
			if (priseTerritoireAuto) g.fillRect(639, 540-30+5, 33, 31);	//Bouton prise automatique cliqu� //640 540
			
			g.drawRect(790-4, 230-30+2, getFontMetrics(font).stringWidth(Integer.toString(largeurTerrain))+8, 36);
			g.drawRect(790-5, 230-30+1, getFontMetrics(font).stringWidth(Integer.toString(largeurTerrain))+10, 38);
			g.drawRect(795-4, 280-30+2, getFontMetrics(font).stringWidth(Integer.toString(hauteurTerrain))+8, 36);
			g.drawRect(795-5, 280-30+1, getFontMetrics(font).stringWidth(Integer.toString(hauteurTerrain))+10, 38);
			//Surlignage des champs modifiables
			if(785<posSourisX && posSourisX<785+getFontMetrics(font).stringWidth(Integer.toString(largeurTerrain))+10){
				if(230-30+1<posSourisY && posSourisY<230+9){
					g.drawRect(790-6, 230-30, getFontMetrics(font).stringWidth(Integer.toString(largeurTerrain))+12, 40);
					g.drawRect(790-7, 230-31, getFontMetrics(font).stringWidth(Integer.toString(largeurTerrain))+14, 42);
				}
			}
			if(790<posSourisX && posSourisX<790+getFontMetrics(font).stringWidth(Integer.toString(largeurTerrain))+10){
				if(280-30+1<posSourisY && posSourisY<280+9){
					g.drawRect(795-6, 280-30, getFontMetrics(font).stringWidth(Integer.toString(hauteurTerrain))+12, 40);
					g.drawRect(795-7, 280-31, getFontMetrics(font).stringWidth(Integer.toString(hauteurTerrain))+14, 42);
				}
			}
			
			//Formes des cases
				//Carré
			g.drawRect(635, 360-18, 20, 20);
			g.drawRect(634, 360-19, 22, 22);
			if(formeCase.equals("carré")) g.fillRect(638, 363-18, 15, 15);
				//Losange
			int xLosange[] = {670, 683, 696, 683};
		    int yLosange[] = {352, 365, 352, 339};
		    g.drawPolygon(xLosange, yLosange, 4);
		    int xLosange2[] = {669, 683, 697, 683};
		    int yLosange2[] = {352, 366, 352, 338};
		    g.drawPolygon(xLosange2, yLosange2, 4);
		    if(formeCase.equals("losange")){
		    	int xLosangeSelection[] = {673, 683, 694, 683};
			    int yLosangeSelection[] = {352, 363, 352, 342};
			    g.fillPolygon(xLosangeSelection, yLosangeSelection, 4);
		    }
		    	//Hexagone
		    int cote = 16;	//En pixel
		    int demiCote = (int)(Math.sqrt(cote*cote-cote*cote/4));	//Pythagore
		    int xHexa[] = {710, 710, 710+demiCote, 710+2*demiCote, 710+2*demiCote, 710+demiCote};
		    int yHexa[] = {352-cote/2, 352+cote/2, 352+cote, 352+cote/2, 352-cote/2, 352-cote};
		    g.drawPolygon(xHexa, yHexa, 6);
		    int cote2 = 17;	//En pixel
		    int demiCote2 = (int)(Math.sqrt(cote2*cote2-cote2*cote2/4));	//Pythagore
		    int xHexa2[] = {709, 709, 709+demiCote2, 709+2*demiCote2, 709+2*demiCote2, 709+demiCote2};
		    int yHexa2[] = {352-cote2/2+1, 352+cote2/2-1, 352+cote2, 352+cote2/2-1, 352-cote2/2+1, 352-cote2};
		    g.drawPolygon(xHexa2, yHexa2, 6);
		    if(formeCase.equals("hexagone")){
		    	int coteSelection = 13;	//En pixel
			    int demiCoteSelection = (int)(Math.sqrt(coteSelection*coteSelection-coteSelection*coteSelection/4));
			    int xHexaSelection[] = {712+1, 712+1, 712+demiCoteSelection, 712+2*demiCoteSelection, 
			    		712+2*demiCoteSelection, 712+demiCoteSelection};
			    int yHexaSelection[] = {352-coteSelection/2, 352+coteSelection/2, 352+coteSelection, 352+coteSelection/2, 
			    		352-coteSelection/2, 352-coteSelection};
			    g.fillPolygon(xHexaSelection, yHexaSelection, 6);
		    }
		} else {
			//Texte de la page n°2
			g.drawString("Joueur 1:", 300, 180);
			g.drawString("Joueur 2:", 300, 280);
			if (nbJoueur > 2)g.drawString("Joueur 3:", 300, 380);
			if (nbJoueur > 3)g.drawString("Joueur 4:", 300, 480);
			
			for (int i=0; i<nbJoueur; i++){
				//Champs de saisie des noms des joueurs
				g.drawString(listeNomJoueur[i], 480, 180+i*100);
				g.drawRect(480-4, 180-30+2+i*100, getFontMetrics(font).stringWidth(listeNomJoueur[i])+8, 36);
				g.drawRect(480-5, 180-30+1+i*100, getFontMetrics(font).stringWidth(listeNomJoueur[i])+10, 38);
				//Surlignage des champs modifiables
				if(475<posSourisX && posSourisX<475+getFontMetrics(font).stringWidth(listeNomJoueur[i])+10){
					if(180-30+1+i*100<posSourisY && posSourisY<180+9+i*100){
						g.drawRect(480-6, 180-30+i*100, getFontMetrics(font).stringWidth(listeNomJoueur[i])+12, 40);
						g.drawRect(480-7, 180-31+i*100, getFontMetrics(font).stringWidth(listeNomJoueur[i])+14, 42);
					}
				}
			}
			
			g.drawString("Ordinateur", 625, 140);
			//Champs IA sélectionnés
			if (choixIa[0]) g.fillRect(709, 180-30+5, 33, 31);
			if (choixIa[1]) g.fillRect(709, 280-30+5, 33, 31);
			if (nbJoueur > 2){
				if (choixIa[2]) g.fillRect(709, 380-30+5, 33, 31);
				if (nbJoueur > 3){
					if (choixIa[3]) g.fillRect(709, 480-30+5, 33, 31);
				}
			}
		}
	}


	public String souris(){
		Font font = new Font("Cooper Black", Font.BOLD, 30);
		for (int i=0; i<text.length; i++){
			int longueurText = getFontMetrics(font).stringWidth(text[i]);
			if (position[i][0]-5<posCliqueX && posCliqueX<position[i][0]+longueurText+3){
				if (position[i][1]-30+1<posCliqueY && posCliqueY<position[i][1]+9){
					caseCocher(text[i]);
					return text[i];
				}
			}
		}
		
		if (page1){
			if(785<posCliqueX && posCliqueX<785+getFontMetrics(font).stringWidth(Integer.toString(largeurTerrain))+3){
				if(230-30+1<posCliqueY && posCliqueY<230+9)return "intLargeur";
			}
			if(790<posCliqueX && posCliqueX<790+getFontMetrics(font).stringWidth(Integer.toString(largeurTerrain))+3){
				if(280-30+1<posCliqueY && posCliqueY<280+9)return "intHauteur";
			}
		} else {
			for (int i=0; i<nbJoueur; i++){
				if(475<posCliqueX && posCliqueX<475+getFontMetrics(font).stringWidth(listeNomJoueur[i])+10){
					if(180-30+1+i*100<posCliqueY && posCliqueY<180+9+i*100) return "Joueur"+i;
				}
			}
		}
		
		return " ";
	}

	private void caseCocher(String text){
		if (text == "   "){
			if (page1){
				if (480<posCliqueX && posCliqueX>518){
					if (priseTerritoireAuto){
						priseTerritoireAuto = false;
						zeroPosClique();
					} else {
						priseTerritoireAuto = true;
						zeroPosClique();
					}
				} else {
					if (obstacles){
						obstacles = false;
						zeroPosClique();
					} else {
						obstacles = true;
						zeroPosClique();
					}
				}

			} else {
				//Boutons IA:
				
				if (posCliqueX>705 && posCliqueX<745){
					if (posCliqueY>150 && posCliqueY<190){
						if (choixIa[0]){
							choixIa[0] = false;
							zeroPosClique();
						} else {
							choixIa[0] = true;
							listeNomJoueur[0] = "Timmy";
							zeroPosClique();
						}
					}
					if (posCliqueY>250 && posCliqueY<290){
						if (choixIa[1]){
							choixIa[1] = false;
							zeroPosClique();
						} else {
							choixIa[1] = true;
							listeNomJoueur[1] = "Timmy";
							zeroPosClique();
						}
					}
					if (nbJoueur > 2){
						if (posCliqueY>350 && posCliqueY<390){
							if (choixIa[2]){
								choixIa[2] = false;
								zeroPosClique();
							} else {
								choixIa[2] = true;
								listeNomJoueur[2] = "Timmy";
								zeroPosClique();
							}
						}
						if (posCliqueY>450 && posCliqueY<490 && nbJoueur == 4){
							if (choixIa[3]){
								choixIa[3] = false;
								zeroPosClique();
							} else {
								choixIa[3] = true;
								listeNomJoueur[3] = "Timmy";
								zeroPosClique();
							}
						}
					}	
				}
			}
			
		}
	}


	public void menuJouer(Fenetre fen){
		String[] textMenu = {"Retour", "-", "+", "<", ">", "Charger un terrain", "Charger une partie", "   ", "Suivant", "   "};
		int[][] posMenu = {{20,50},{660,140},{720,140},{610,360},{745,360},{261,625},{261,675},{485, 450},{701,675},{640,540}};
		text = textMenu;
		position = posMenu;
		fen.setContentPane(this);
		fen.repaint();
		fen.revalidate();
		boolean menu = true;
		while (menu){
			fen.repaint();
			String resultatClique = souris();
			switch(resultatClique){
			case "Retour":
				if (page1){
					PanneauMenuPrincipal menuPrincipal = new PanneauMenuPrincipal();
					menuPrincipal.menu(fen);
					menu = false;
					zeroPosClique();
				} else {
					text = textMenu;
					position = posMenu;
					page1 = true;
					zeroPosClique();
				}
				
				break;
			case "-":
				if (nbJoueur>2) nbJoueur = nbJoueur-1;
				zeroPosClique();
				break;
			case "+":
				if (nbJoueur<4) nbJoueur = nbJoueur+1;
				zeroPosClique();
				break;
			case "<":
				switch (formeCase){
				case "losange":
					formeCase = "carré";
					break;
				case "hexagone":
					formeCase = "losange";
					break;
				}
				zeroPosClique();
				break;
			case ">":
				switch (formeCase){
				case "carré":
					formeCase = "losange";
					break;
				case "losange":
					formeCase = "hexagone";
					break;
				}
				zeroPosClique();
				break;
			case "Charger un terrain":
				break;
			case "Charger une partie":
				String nomFichier = questionSauvegarde();
				if (!nomFichier.equals("Pas de partie")){
					EditeurFichier edit = new EditeurFichier();
					String[] fichierCharge = edit.lecture("Sauvegardes/" + nomFichier);
					//System.out.println(fichierCharge[0]);
					
					chargementPartie(fichierCharge, fen);
					
				}
				zeroPosClique();
				break;
			case "Suivant":
				String[] textMenu2 = new String[nbJoueur+2];	//le premier joueur ne peut pas �tre un ordinateur
				int[][] posMenu2 = new int[nbJoueur+2][2];
				textMenu2[0] = "Retour";
				textMenu2[1] = "Commencer";
				posMenu2[0][0] = 20;
				posMenu2[0][1] = 50;
				posMenu2[1][0] = 701;
				posMenu2[1][1] = 675;
				for (int i=2; i<nbJoueur+2; i++){
					textMenu2[i] = "   ";
					posMenu2[i][0] = 710;
				}
				switch (nbJoueur){
				case 2:
					posMenu2[2][1] = 180;
					posMenu2[3][1] = 280;
					break;
				case 3:
					posMenu2[2][1] = 180;
					posMenu2[3][1] = 280;
					posMenu2[4][1] = 380;
					break;
				case 4:
					posMenu2[2][1] = 180;
					posMenu2[3][1] = 280;
					posMenu2[4][1] = 380;
					posMenu2[5][1] = 480;
					break;
				}
				text = textMenu2;
				position = posMenu2;

				page1 = false;
				zeroPosClique();
				break;
			case "Commencer":
				zeroPosClique();
				Plateau test = new Plateau();
				PanneauPlateau pan = new PanneauPlateau();
				test.setPanneau(pan);
				pan.setFormeCase(formeCase);
				test.initPlateau(hauteurTerrain, largeurTerrain, obstacles);	//TODO faire obstacles losanges
				test.setChoixAffichage('G');
				Joueur[] liste = new Joueur[nbJoueur];
				int[][] positionDepart = {{0,0},{hauteurTerrain-1,largeurTerrain-1},{0,largeurTerrain-1},{hauteurTerrain-1,0}};
				for (int i=0; i<nbJoueur; i++){
					if (choixIa[i]) liste[i] = new IA();
					else liste[i] = new Joueur();
					liste[i].setNom(listeNomJoueur[i]);
					test.creationJoueur(positionDepart[i][0], positionDepart[i][1], liste[i]);
					liste[i].conquerir(liste[i].getCouleur(), test);
				}
				test.setListeJoueurs(liste);
				fen.setContentPane(pan);
				pan.setPlateau(test.getPlateau());
				pan.setListeJoueur(test.getListeJoueurs());
				fen.repaint();
				fen.revalidate();
				test.partie();
				break;
			case "   ":
				if (480<posCliqueX && posCliqueX>518);
				break;
			case "intLargeur":
				int largeur = questionInt("Largeur");
				largeurTerrain = largeur;
				zeroPosClique();
				break;
			case "intHauteur":
				int hauteur = questionInt("Hauteur");
				hauteurTerrain = hauteur;
				zeroPosClique();
				break;
			case "Joueur0":
				String joueur1 = "";
				if (choixIa[0]) joueur1 = questionIa(0);
				else joueur1 = questionStr("Joueur 1", 0);
				setListeNomJoueur(joueur1, 0);
				System.out.println(joueur1);
				zeroPosClique();
				break;
			case "Joueur1":
				String joueur2 = "";
				if (choixIa[1]) joueur2 = questionIa(1);
				else joueur2 = questionStr("Joueur 2", 1);
				setListeNomJoueur(joueur2, 1);
				System.out.println(joueur2);
				zeroPosClique();
				break;
			case "Joueur2":
				String joueur3 = "";
				if (choixIa[2]) joueur3 = questionIa(2);
				else joueur3 = questionStr("Joueur 3", 2);
				
				setListeNomJoueur(joueur3, 2);
				System.out.println(joueur3);
				zeroPosClique();
				break;
			case "Joueur3":
				String joueur4 = "";
				if (choixIa[3]) joueur4 = questionIa(3);
				else joueur4 = questionStr("Joueur 4", 3);
				setListeNomJoueur(joueur4, 3);
				System.out.println(joueur4);
				zeroPosClique();
				break;
			}
			
		}
	}
	
	public int questionInt(String text){
		// TODO modifier la taille max en fonction de la forme des cases
		int max = 30;
		if (text.compareTo("Largeur") == 0) max = 40;
		int reponse = 15;
		do {
			String rep = JOptionPane.showInputDialog(null, text + " (comprise entre 5 et " + max + "):", text, JOptionPane.QUESTION_MESSAGE);
			try {
				reponse = Integer.parseInt(rep);
			} catch (NumberFormatException e){
				if (rep == null){
					if (text == "Largeur") reponse = largeurTerrain;
					else reponse = hauteurTerrain;
				}
				reponse = 0;
			}
		}while(reponse<5 || reponse>max);
		return reponse;
	}
	public String questionStr(String text, int numeroJoueur){
		String reponse = "";
		do {
			 reponse = JOptionPane.showInputDialog(null,"Nom du " + text + " (avec moins de 8 caractères):", text, JOptionPane.QUESTION_MESSAGE);
			 if(reponse == null) reponse = listeNomJoueur[numeroJoueur];
		}while(reponse.length()>8 || reponse.length()<0);
		return reponse;
	}
	public String questionIa(int numeroIa){
		String[] nomOrdi = {"Timmy", "John", "Resetti", "Chuck Norris"};
		String nom = (String)JOptionPane.showInputDialog(null, 
				"Choisissez l'ordinateur",
				"Choix ordinateur",
				JOptionPane.QUESTION_MESSAGE,
				null,
				nomOrdi,
				nomOrdi[2]);
		if(nom == null) nom = listeNomJoueur[numeroIa];
		return nom;
	}
	public String questionSauvegarde(){
		String[] nomOrdi = EditeurFichier.nomFichierDossier("Sauvegardes");;
		String nom = (String)JOptionPane.showInputDialog(null, 
				"Choisissez la partie à charger:",
				"Chargement de partie",
				JOptionPane.QUESTION_MESSAGE,
				null,
				nomOrdi,
				nomOrdi[0]);
		if(nom == null) nom = "Pas de partie";
		return nom;
	}
	
	public boolean isPage1() {
		return page1;
	}
	public void setPage1(boolean page1) {
		this.page1 = page1;
	}

	public int getNbJoueur() {
		return nbJoueur;
	}
	public void setNbJoueur(int nbJoueur) {
		this.nbJoueur = nbJoueur;
	}
	

	public String[] getListeNomJoueur() {
		return listeNomJoueur;
	}
	public void setListeNomJoueur(String listeNomJoueur, int i) {
		this.listeNomJoueur[i] = listeNomJoueur;
	}


	public int getLargeurTerrain() {
		return largeurTerrain;
	}
	public void setLargeurTerrain(int largeurTerrain) {
		this.largeurTerrain = largeurTerrain;
	}
	public int getHauteurTerrain() {
		return hauteurTerrain;
	}
	public void setHauteurTerrain(int hauteurTerrain) {
		this.hauteurTerrain = hauteurTerrain;
	}



	public String getFormeCase() {
		return formeCase;
	}


	public void setFormeCase(String formeCase) {
		this.formeCase = formeCase;
	}


	public boolean isObstacles() {
		return obstacles;
	}


	public void setObstacles(boolean obstacles) {
		this.obstacles = obstacles;
	}


	public boolean isPriseTerritoireAuto() {
		return priseTerritoireAuto;
	}


	public void setPriseTerritoireAuto(boolean priseTerritoireAuto) {
		this.priseTerritoireAuto = priseTerritoireAuto;
	}


	public boolean[] getChoixIa() {
		return choixIa;
	}


	public void setChoixIa(boolean[] choixIa) {
		this.choixIa = choixIa;
	}


	public void setListeNomJoueur(String[] listeNomJoueur) {
		this.listeNomJoueur = listeNomJoueur;
	}

	

	private void chargementPartie(String[] donnees, Fenetre fen){
		for (String t: donnees) System.out.println("+++++++++++++++++++++"+t);
		//String[] test = donnees[5].split("null"); sépare quand même en deux chaîne
		//System.out.println(donnees[1].split(" ")[1]);		Différencie bien les espaces insécables des autres
		
		int indiceFinListeJoueur = 0;
		for (int i=2; i<6; i++){
			if (donnees[i].equals("carré") || donnees[i].equals("losange") || donnees[i].equals("hexagone"))
				indiceFinListeJoueur = i-1;
		}
		
		
		zeroPosClique();
		Plateau test = new Plateau();
		PanneauPlateau pan = new PanneauPlateau();
		test.setPanneau(pan);
		pan.setFormeCase(donnees[indiceFinListeJoueur+1]);
		test.initPlateau(donnees[8].split("\u00A0").length, donnees.length-(indiceFinListeJoueur+2), 
				(Integer.parseInt(donnees[indiceFinListeJoueur+2])==0?false:true));
		for (int i=0; i<test.getPlateau().length; i++){
			for (int j=0; j<test.getPlateau()[0].length; j++){
				System.out.print(test.getPlateau()[i][j].getCouleur());
			}
			System.out.println();
		}
		fichierATableau(donnees, indiceFinListeJoueur, test);
		test.setChoixAffichage('G');
		Joueur[] liste = new Joueur[indiceFinListeJoueur];
		boolean[] listeIa = creerListeIa(donnees, indiceFinListeJoueur);
		int[][] positionDepart = {{0,0},{hauteurTerrain-1,largeurTerrain-1},{0,largeurTerrain-1},{hauteurTerrain-1,0}};
		String[] listeNomJoueurTemp = creerListeNomJoueur(donnees, indiceFinListeJoueur);
		for (int i=0; i<indiceFinListeJoueur; i++){
			if (listeIa[i]) liste[i] = new IA();
			else liste[i] = new Joueur();
			liste[i].setNom(listeNomJoueurTemp[i]);
			test.creationJoueur(positionDepart[i][0], positionDepart[i][1], liste[i]);
			liste[i].conquerir(liste[i].getCouleur(), test);
		}
		test.setListeJoueurs(liste);
		fen.setContentPane(pan);
		pan.setPlateau(test.getPlateau());
		pan.setListeJoueur(test.getListeJoueurs());
		int indiceDepart = 0;
		for (int i=0; i<indiceFinListeJoueur; i++){
			if (donnees[0].equals(donnees[i+1].split("\u00A0")[0])) indiceDepart = i+1;
		}
		fen.repaint();
		fen.revalidate();
		test.partie(indiceDepart);
	}

	private void fichierATableau(String[] donnees, int indiceListe, Plateau plat){
		Case[][] plateauTemp = plat.getPlateau();
		for (int i=0; i<donnees.length-(indiceListe+2); i++){
			for (int j=0; j<donnees[8].split("\u00A0").length; j++){
				String tempS = (donnees[i+indiceListe+3].split("null"))[1];
				char tempC = (tempS.split("\u00A0")[j]).charAt(0);
				plateauTemp[i][j].setCouleur(tempC);
			}
		}
		plat.setPlateau(plateauTemp);
	}
	
	private String[] creerListeNomJoueur(String[] donnees, int indiceListe){
		String[] nomJoueur = new String[indiceListe];
		for(int i=0; i<indiceListe; i++){
			nomJoueur[i] = donnees[i+1].split("\u00A0")[0];
		}
		return nomJoueur;
	}

	private boolean[] creerListeIa(String[] donnees, int indiceListe){
		boolean[] listeIa = choixIa;
		for (int i=0; i<indiceListe; i++){
			if (donnees[i+1].split("\u00A0")[1].equals("IA"))listeIa[i] = true;
		}
		return listeIa;
	}
}
