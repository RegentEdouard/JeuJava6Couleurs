package SixCouleurs;

import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		
		//choix du mode d'affichage
		Scanner scan = new Scanner(System.in);
		char choixAff = 'A';
		while (choixAff != 'C' && choixAff != 'G'){
			System.out.println("Quel mode d'affichage voulez vous? (console C, graphique G): ");
			choixAff = scan.nextLine().charAt(0);
		}
		

		Parametres.couleur();
		char[] tab = {'o','b','j','i','r','v','O','B','J','I','R','V'};
		for (int i=0; i<12; i++){
			System.out.print((int)tab[i] + " ");
		}
		Plateau test = new Plateau();
		test.setChoixAffichage(choixAff);
		test.debutPartie();
		//TODO Refaire le redemarrer partie
		
	}

}