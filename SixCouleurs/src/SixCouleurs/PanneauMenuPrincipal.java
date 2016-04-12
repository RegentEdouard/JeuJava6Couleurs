package SixCouleurs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanneauMenuPrincipal extends JPanel{
	public String[] text;
	public int[][] position;
	private int posCliqueX = 0;
	private int posCliqueY = 0;
	protected int posSourisX = 0;
	protected int posSourisY = 0;
	private boolean logo = false;
	
	
	public void paintComponent(Graphics g) {
		if (logo){
			try {
			      Image img = ImageIO.read(new File("Titre.png"));
			      g.drawImage(img, 15, 10, this);
			      //Pour une image de fond
			      //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			    } catch (IOException e) {
			      e.printStackTrace();

			    }
		}

		//Affichage des boutons
		Font font = new Font("Cooper Black", Font.BOLD, 30);
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
	}
	
	public PanneauMenuPrincipal(){
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				posCliqueX = e.getX();
				posCliqueY = e.getY();
				System.out.println(posCliqueX + " " + posCliqueY);
			}
		});
		
		this.addMouseMotionListener(new MouseAdapter(){
			public void mouseMoved(MouseEvent e){
				posSourisX = e.getX();
				posSourisY = e.getY();
			}
		});
	}
	
	public String souris(){
		Font font = new Font("Cooper Black", Font.BOLD, 30);
		for (int i=0; i<text.length; i++){
			int longueurText = getFontMetrics(font).stringWidth(text[i]);
			if (position[i][0]-5<posCliqueX && posCliqueX<position[i][0]+longueurText+3){
				if (position[i][1]-30+1<posCliqueY && posCliqueY<position[i][1]+9){
					return text[i];
				}
			}
		}
		return " ";
	}
	
	public void logo(){
		logo = true;
	}

	public String[] getText() {
		return text;
	}

	public void setText(String[] text) {
		this.text = text;
	}

	public int[][] getPosition() {
		return position;
	}

	public void setPosition(int[][] position) {
		this.position = position;
	}

}
