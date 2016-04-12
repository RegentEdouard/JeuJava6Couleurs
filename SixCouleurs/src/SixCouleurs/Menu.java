package SixCouleurs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Menu extends JPanel {
	public String titre;
	public String[] text;
	public int[][] position;
	public int posCliqueX = 0;
	public int posCliqueY = 0;
	public int posSourisX = 0;
	public int posSourisY = 0;
	
	
	public void paintComponent(Graphics g) {
		for (int i=0; i<text.length; i++){
			Font font = new Font("Cooper Black", Font.BOLD, 30);
			g.setFont(font);
			g.setColor(new Color(62, 67, 94));
			g.drawString(text[i], position[i][0], position[i][1]);
			int longueurText = getFontMetrics(font).stringWidth(text[i]);
			System.out.println(longueurText);
		    g.drawRect(position[i][0]-4, position[i][1]-30, longueurText + 6, 36 );
		    g.drawRect(position[i][0]-5, position[i][1]-30-1, longueurText + 8, 38 );
		}
	}
	
	public Menu(String titre, String[] text, int[][] position){
		this.titre = titre;
		this.text = text;
		this.position = position;
		
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				posCliqueX = e.getX();
				posCliqueY = e.getY();
			}
		});
		
		this.addMouseMotionListener(new MouseAdapter(){
			public void mouseMoved(MouseEvent e){
				posSourisX = e.getX();
				posSourisY = e.getY();
			}
		});
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

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
}
