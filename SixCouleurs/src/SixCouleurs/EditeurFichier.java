package SixCouleurs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class EditeurFichier {

	public String[] lecture(String stringChemin){
		Path chemin = Paths.get(stringChemin);
		Charset charset = Charset.forName("Cp1252");
		LinkedList<String> listeTexte= new LinkedList<String>();
		try (BufferedReader reader = Files.newBufferedReader(chemin, charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				listeTexte.add(line);
				//System.out.println(line);
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
		String[] listeFichier = new String[listeTexte.size()];
		for (int i=0; i<listeFichier.length; i++){
			listeFichier[i] = listeTexte.peek();
			listeTexte.remove();
		}
		return listeFichier;
	}

	public void ecriture(String informations,String[] tableau, String stringChemin){
		Path chemin = Paths.get(stringChemin + ".txt");
		Charset charset = Charset.forName("Cp1252");
		String sauvegarde = tabStringToString(informations, tableau);
		try (BufferedWriter writer= Files.newBufferedWriter(chemin, charset)) {
			writer.write(sauvegarde, 0, sauvegarde.length());
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}
	
	private String tabStringToString(String informations, String[] liste){
		String resultat = informations;
		for (int i=0; i<liste.length; i++){
			resultat = resultat + liste[i] + System.getProperty("line.separator");
		}
		return resultat;
	}

	public boolean existanceFichierDossier(String stringChemin, String nomFichier){
		String[] listeFichier = null;

		try {
			listeFichier = fichierDossier(stringChemin);
		} catch (IOException x) {
			// TODO Auto-generated catch block
			System.err.format("IOException: %s%n", x);
		}
		for (int i=0; i<listeFichier.length; i++){
			if (listeFichier[i].equals(nomFichier)) return true;	//Le fichier est bien présent dans le dossier
		}
		return false;
	}
	
	public static String[] fichierDossier(String chemin) throws IOException {
		Path jdkPath = Paths.get(chemin);
		DirectoryStream<Path> stream = Files.newDirectoryStream(jdkPath);
		LinkedList<String> listeFichier= new LinkedList<String>();
		try { 
			Iterator<Path> iterator = stream.iterator();
			while(iterator.hasNext()) {
				Path p = iterator.next();
				listeFichier.add(p.getFileName().toString());
			}
		} finally { 
			stream.close(); 
		} 
		String[] liste = new String[listeFichier.size()];
		for (int i=0; i<liste.length; i++){
			liste[i] = listeFichier.peek();
			listeFichier.remove();
		}
		for (int i=0; i<liste.length; i++) System.out.println(liste[i]);
		return liste;
	}

	public String fenetreSauvegarder(){
		String reponse = "";
		reponse = JOptionPane.showInputDialog(null,"Nom:", "Sauvegarde", JOptionPane.QUESTION_MESSAGE);
		return reponse;
	}
	
	public boolean fenetreBouton(){
		int option = JOptionPane.showConfirmDialog(null, "Le fichier existe déjà,"+System.getProperty("line.separator")+
				"Voulez-vous remplacer ?", "Confirmer l'enregistrement", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if(option != JOptionPane.NO_OPTION && option != JOptionPane.CLOSED_OPTION){
			return true;
		} else {
			return false;
		}
		
	}
	
	public static String[] nomFichierDossier(String chemin){
		Path jdkPath = Paths.get(chemin);
		String[] liste = new String[0];
		try{
			DirectoryStream<Path> stream = Files.newDirectoryStream(jdkPath);
			LinkedList<String> listeFichier= new LinkedList<String>();
			try { 
				Iterator<Path> iterator = stream.iterator();
				while(iterator.hasNext()) {
					Path p = iterator.next();
					listeFichier.add(p.getFileName().toString());
				}
			} finally { 
				stream.close(); 
			} 
			liste = new String[listeFichier.size()];
			for (int i=0; i<liste.length; i++){
				liste[i] = listeFichier.peek();
				listeFichier.remove();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return liste;
	}
}
