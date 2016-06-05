package SixCouleurs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JOptionPane;

public class PanneauRegle extends PanneauMenuPrincipal {

	private String[] texte;
	private int tailleZoneTexte = 750;
	
	public PanneauRegle(){
		EditeurFichier edit = new EditeurFichier();
		texte = edit.lecture("Système/Texte_Règles.txt");
	}

	public void paintComponent(Graphics g) {
		//Titre
		Font font = new Font("Cooper Black", Font.BOLD, 50);
		g.setFont(font);
		g.setColor(new Color(62, 67, 94));
		g.drawString("Règles", 493, 55);

		//Affichage des boutons
		font = new Font("Cooper Black", Font.BOLD, 30);
		g.setFont(font);
		
		
		g.drawString("Retour", 20, 50);
		int longueurText = getFontMetrics(font).stringWidth("Retour");
		//System.out.println(longueurText);
		//Affichage des contours
		g.drawRect(20-4, 50-30+2, longueurText+8, 36 );
		g.drawRect(20-5, 50-30+1, longueurText+10, 38 );
		
		//Surligner lorsque la souris survolle un bouton 
		
		if (20-5<posSourisX && posSourisX<20+longueurText+5){
			if (50-30+1<posSourisY && posSourisY<50+9){
				g.drawRect(20-6, 50-30, longueurText+12, 40);
				g.drawRect(20-7, 50-30-1, longueurText+14, 42);
			}
		}
		
		
		
		
		
		//Champs de saisie des thèmes
		g.drawString("Thème:", 910, 100);
		g.drawString(Parametres.theme, 900, 150);
		g.drawRect(900-4, 150-30+2, getFontMetrics(font).stringWidth(Parametres.theme)+8, 36);
		g.drawRect(900-5, 150-30+1, getFontMetrics(font).stringWidth(Parametres.theme)+10, 38);
		//Surlignage des champs modifiables
		if(895<posSourisX && posSourisX<895+getFontMetrics(font).stringWidth(Parametres.theme)+10){
			if(150-30+1<posSourisY && posSourisY<150+9){
				g.drawRect(900-6, 150-30, getFontMetrics(font).stringWidth(Parametres.theme)+12, 40);
				g.drawRect(900-7, 150-31, getFontMetrics(font).stringWidth(Parametres.theme)+14, 42);
				if (posCliqueX !=0 && posCliqueY !=0){
					zeroPosClique();
					questionTheme();
				}
			}
		}
		
		//separationTexte(texte[0],g);
		font = new Font("Cooper Black", Font.BOLD, 20);
		g.setFont(font);
		g.drawString("      Le jeu des six couleurs est un jeu de stratégie par la conquête de territoires. La partie se "
				, 20, 220);
		g.drawString("déroule sur un plateau découpé en cases de 6 couleurs différentes (rouge, orange, jaune, vert, bleu, ", 
				20, 220+30);
		g.drawString("ou violet). Le but du jeu est de contrôler plus de cases que les adversaires à la fin de la partie.", 
				20, 220+2*30);
		g.drawString("Les joueurs (de 2 à 4) commencent la partie en contrôlant chacun une case de la grille, ces cases ", 
				20, 220+3*30);
		g.drawString("doivent être de couleurs différentes. Les joueurs jouent chacun leur tour. À son tour, un joueur ", 
				20, 220+4*30);
		g.drawString("choisit une couleur différente de celle qu'il a actuellement, et non utilisée par ses adversaires.", 
				20, 220+5*30);
		g.drawString("-Toutes les cases contrôlées par le joueur deviennent alors de la couleur choisie.", 
				20, 220+6*30);
		g.drawString("-Toutes les cases de la couleur choisie et qui touchent une case contrôlée par le joueur passent sous ", 
				20, 220+7*30);
		g.drawString("son contrôle.", 
				20, 220+8*30);
		g.drawString("La partie s'arrête lorsqu'il n'y a plus de case non contrôlée, ou lorsqu'un joueur contrôle plus de la ", 
				20, 220+9*30);
		g.drawString("moitié des cases. Le joueur gagnant est celui qui contrôle le plus de cases.", 
				20, 220+10*30);

		g.drawString("Difficulté des ordinateurs :", 
				20, 220+12*30);
		g.drawString("Timmy va choisir une couleur aléatoire dans les territoires adjacents, John va choisir la couleur qui lui ", 
				20, 220+13*30);
		g.drawString("rapporte le plus de point, Resetti va choisir la couleur qui embête le plus ses adversaires et Chuck ", 
				20, 220+14*30);
		g.drawString("Norris… Comme son nom l’indique, personne ne peut le battre.", 
				20, 220+15*30);
	}

	private void separationTexte(String chaine, Graphics g){	//Ne fonctionne pas
		Font font = new Font("Cooper Black", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(new Color(62, 67, 94));
		String[] chaineCoupee = chaine.split(" ");
		int indice = 0;
		int indicePrecedent = 0;
		int taillechaineCoupee = 0;
		int numeroLigne = 0;
		while (indice < chaineCoupee.length){
			taillechaineCoupee += getFontMetrics(font).stringWidth(chaineCoupee[indice]);
			if (taillechaineCoupee < tailleZoneTexte){
				indice ++;
			}else{
				String truc = "";
				for (int i=indicePrecedent; i< indice; i++)truc+=chaineCoupee[i] + " ";
				g.drawString(truc, 20, 300+numeroLigne*23);
				//g.drawString("test", 20, 100+numeroLigne*23);
				numeroLigne ++;
				taillechaineCoupee = 0;
				indicePrecedent = indice;
			}
		}
	}
	

	private void questionTheme(){
		String[] nomTheme = {"Protanopie", "Deutéranopie", "Barbie", "Standard"};
		String nom = (String)JOptionPane.showInputDialog(null, 
				"Choisissez le thème qui vous convient",
				"Choix thème",
				JOptionPane.QUESTION_MESSAGE,
				null,
				nomTheme,
				nomTheme[3]);
		if(nom == null) nom = Parametres.theme;
		switch (nom){
		case "Standard":
			int[][] themeStandard = {{255, 0, 0},
					{255, 106, 0},
					{255, 216, 0},
					{0, 226, 0},
					{0, 0, 255},
					{193, 0, 184}};
			Parametres.changeTheme("Standard", themeStandard);
			break;
		case "Barbie":
			int[][] themeBarbie = {{253, 108, 158},
					{222, 49, 99},
					{199, 44, 72},
					{255, 0, 255},
					{219, 0, 115},
					{249, 66, 158}};
			Parametres.changeTheme("Barbie", themeBarbie);
			break;
		case "Deutéranopie":
			int[][] themeDeutéranopie = {{162, 152, 89},
					{190, 185, 162},
					{190, 175, 32},
					{243, 231, 156},
					{7, 117, 255},
					{128, 160, 255}};
			Parametres.changeTheme("Deutéranopie", themeDeutéranopie);
			break;
		case "Protanopie":
			int[][] themeProtanopie  = {{137, 130, 95},
					{169, 169, 165},
					{209, 191, 0},
					{255, 244, 153},
					{0, 109, 255},
					{121, 157, 255}};
			Parametres.changeTheme("Protanopie", themeProtanopie);
			break;
		}
	}
	
	public void menuRegle(Fenetre fen){
		fen.setContentPane(this);
		fen.repaint();
		fen.revalidate();
		boolean menu = true;
		while (menu){
			fen.repaint();
			String resultatClique = souris();
			switch(resultatClique){
			case "Retour":
				PanneauMenuPrincipal menuPrincipal = new PanneauMenuPrincipal();
				menuPrincipal.menu(fen);
				menu = false;
				zeroPosClique();
				break;
			}
		}
	}
	
	public String souris(){
		Font font = new Font("Cooper Black", Font.BOLD, 30);
		int longueurText = getFontMetrics(font).stringWidth("Retour");
		if (20-5<posCliqueX && posCliqueX<20+longueurText+3){
			if (50-30+1<posCliqueY && posCliqueY<50+9){
				return "Retour";
			}
		}
		return " ";
	}
}
