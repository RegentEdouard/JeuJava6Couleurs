package SixCouleurs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class PanMenuJouer extends PanneauMenuPrincipal{
	private int nbJoueur = 2;
	private int largeurTerrain;
	private int hauteurTerrain;
	private String formeCase;
	private boolean obstacles = false;
	private boolean priseTerritoireAuto = false;
	private String[] listeNomJoueur;
	private boolean page1 = true;
	
	
	public void paintComponent(Graphics g) {
		//Titre
		Font font = new Font("Cooper Black", Font.BOLD, 50);
		g.setFont(font);
		g.setColor(new Color(62, 67, 94));
		g.drawString("Jouer", 493, 55);
		//System.out.println(getFontMetrics(font).stringWidth("Jouer"));

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
		    g.drawRect(position[i][0]-4, position[i][1]-30+2, longueurText + 6, 36 );
		    g.drawRect(position[i][0]-5, position[i][1]-30+1, longueurText + 8, 38 );
		}
		//Surligner lorsque la souris survolle un bouton 
		for (int i=0; i<text.length; i++){
			int longueurText = getFontMetrics(font).stringWidth(text[i]);
			if (position[i][0]-5<posSourisX && posSourisX<position[i][0]+longueurText+3){
				if (position[i][1]-30+1<posSourisY && posSourisY<position[i][1]+9){
					g.drawRect(position[i][0]-6, position[i][1]-30, longueurText + 10, 40 );
					g.drawRect(position[i][0]-7, position[i][1]-30-1, longueurText + 12, 42 );
				}
			}
		}
		if (page1){
			
		} else {
			
		}
	}
	


}
