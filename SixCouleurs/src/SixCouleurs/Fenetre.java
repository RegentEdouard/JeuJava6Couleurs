package SixCouleurs;

import javax.swing.JFrame;

public class Fenetre extends JFrame {
	public Fenetre(PanneauPlateau pan){
	    this.setTitle("Jeu des six couleurs");
	    this.setSize(1150, 740);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setContentPane(pan);
	    this.setVisible(true);
	  }
}
