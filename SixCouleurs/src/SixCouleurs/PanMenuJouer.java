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
			if (obstacles) g.fillRect(484, 450-30+5, 33, 31);			//Bouton obstacle cliqué
			if (priseTerritoireAuto) g.fillRect(639, 540-30+5, 33, 31);	//Bouton prise automatique cliqué //640 540
			
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
			g.drawRect(610, 360-16, 15, 15);
			g.drawRect(609, 360-17, 17, 17);
				//Losange
			int xLosange[] = {636, 643, 651, 643};
		    int yLosange[] = {352, 365, 352, 339};
		    g.drawPolygon(xLosange, yLosange, 4);
		    int xLosange2[] = {635, 643, 652, 643};
		    int yLosange2[] = {352, 366, 352, 338};
		    g.drawPolygon(xLosange2, yLosange2, 4);

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
				//TODO ajouter IA joueur 1
				
				if (posCliqueX>705 && posCliqueX<745){
					if (posCliqueY>150 && posCliqueY<190){
						if (choixIa[0]){
							choixIa[0] = false;
							zeroPosClique();
						} else {
							choixIa[0] = true;
							zeroPosClique();
						}
					}
					if (posCliqueY>250 && posCliqueY<290){
						if (choixIa[1]){
							choixIa[1] = false;
							zeroPosClique();
						} else {
							choixIa[1] = true;
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
								zeroPosClique();
							}
						}
						if (posCliqueY>450 && posCliqueY<490 && nbJoueur == 4){
							if (choixIa[3]){
								choixIa[3] = false;
								zeroPosClique();
							} else {
								choixIa[3] = true;
								zeroPosClique();
							}
						}
					}	
				}
			}
			
		}
	}


	public void menuJouer(Fenetre fen){
		String[] textMenu = {"Retour", "-", "+","Charger un terrain","Charger une partie", "   ","Suivant", "   "};
		int[][] posMenu = {{20,50},{660,140},{720,140},{261,625},{261,675},{485, 450},{701,675},{640,540}};
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
			case "Charger un terrain":
				break;
			case "Charger une partie":
				break;
			case "Suivant":
				String[] textMenu2 = new String[nbJoueur+2];	//le premier joueur ne peut pas être un ordinateur
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
				test.initPlateau(hauteurTerrain, largeurTerrain);
				test.setChoixAffichage('G');
				Joueur[] liste = new Joueur[nbJoueur];
				int[][] positionDépart = {{0,0},{hauteurTerrain-1,largeurTerrain-1},{0,largeurTerrain-1},{hauteurTerrain-1,0}};
				for (int i=0; i<nbJoueur; i++){
					if (choixIa[i]) liste[i] = new IA();
					else liste[i] = new Joueur();
					liste[i].setNom(listeNomJoueur[i]);
					test.creationJoueur(positionDépart[i][0], positionDépart[i][1], liste[i]);
					liste[i].conquerir(liste[i].getCouleur(), test);
				}
				test.setListeJoueurs(liste);
				PanneauPlateau pan = new PanneauPlateau();
				fen.setContentPane(pan);
				pan.setPlateau(test.getPlateau());
				pan.setListeJoueur(test.getListeJoueurs());
				test.setPanneau(pan);
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
				String joueur1 = questionStr("Joueur 1");
				setListeNomJoueur(joueur1, 0);
				System.out.println(joueur1);
				zeroPosClique();
				break;
			case "Joueur1":
				String joueur2 = questionStr("Joueur 2");
				setListeNomJoueur(joueur2, 1);
				System.out.println(joueur2);
				zeroPosClique();
				break;
			case "Joueur2":
				String joueur3 = questionStr("Joueur 3");
				setListeNomJoueur(joueur3, 2);
				System.out.println(joueur3);
				zeroPosClique();
				break;
			case "Joueur3":
				String joueur4 = questionStr("Joueur 4");
				setListeNomJoueur(joueur4, 3);
				System.out.println(joueur4);
				zeroPosClique();
				break;
			}
			
		}
	}
	
	public static int questionInt(String text){
		int max = 30;
		if (text.compareTo("Largeur") == 0) max = 40;
		int reponse = 15;
		do {
			JOptionPane jop = new JOptionPane();
			String rep = jop.showInputDialog(null, text + " (comprise entre 5 et " + max + "):", text, JOptionPane.QUESTION_MESSAGE);
			try {
				reponse = Integer.parseInt(rep);
			} catch (NumberFormatException e){
				reponse = 0;
			}
		}while(reponse<5 || reponse>max);
		return reponse;
	}
	public static String questionStr(String text){
		String reponse = "";
		do {
			JOptionPane jop = new JOptionPane();
			 reponse = jop.showInputDialog(null,"Nom du " + text + " (avec moins de 8 caractères):", text, JOptionPane.QUESTION_MESSAGE);
		}while(reponse.length()>8 || reponse.length()<0);
		return reponse;
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




}
