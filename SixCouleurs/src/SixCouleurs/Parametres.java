package SixCouleurs;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Parametres {

	public static String theme;
	public static int[] couleurR = new int[3];
	public static int[] couleurO = new int[3];
	public static int[] couleurJ = new int[3];
	public static int[] couleurV = new int[3];
	public static int[] couleurB = new int[3];
	public static int[] couleurI = new int[3];
	private static JSONObject jsonObject;
	
	public static void couleur(){
		JSONParser parser = new JSONParser();

		try {
			Path jdkPath = Paths.get("Système");
			DirectoryStream<Path> stream = Files.newDirectoryStream(jdkPath);
			try { 
				Iterator<Path> iterator = stream.iterator();
				while(iterator.hasNext()) {
					Path p = iterator.next();
					System.out.println(p.getFileName().toString() + "//");
				}
			} finally { 
				stream.close(); 
			} 
			
			Object obj = parser.parse(new FileReader("Système/Paramètres.json"));

			jsonObject = (JSONObject) obj;
			
			for (int i=0; i<3; i++)couleurR[i] = Integer.parseInt(((JSONArray) jsonObject.get("couleurR")).get(i).toString());
			for (int i=0; i<3; i++)couleurO[i] = Integer.parseInt(((JSONArray) jsonObject.get("couleurO")).get(i).toString());
			for (int i=0; i<3; i++)couleurJ[i] = Integer.parseInt(((JSONArray) jsonObject.get("couleurJ")).get(i).toString());
			for (int i=0; i<3; i++)couleurV[i] = Integer.parseInt(((JSONArray) jsonObject.get("couleurV")).get(i).toString());
			for (int i=0; i<3; i++)couleurB[i] = Integer.parseInt(((JSONArray) jsonObject.get("couleurB")).get(i).toString());
			for (int i=0; i<3; i++)couleurI[i] = Integer.parseInt(((JSONArray) jsonObject.get("couleurI")).get(i).toString());
			theme = jsonObject.get("theme").toString();
			
//			String name = (String) jsonObject.get("Name");
//			String author = (String) jsonObject.get("Author");
//			JSONArray companyList = (JSONArray) jsonObject.get("Company List");
//
//			System.out.println("Name: " + name);
//			System.out.println("Author: " + author);
//			System.out.println("\nCompany List:");
//			Iterator<String> iterator = companyList.iterator();
//			while (iterator.hasNext()) {
//				System.out.println(iterator.next());
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void changeTheme(String nomTheme, int[][] tabCouleur){
		JSONObject jsonObjet = new JSONObject();
		
		jsonObjet.put("theme", nomTheme);
		JSONArray couleursThemeR = new JSONArray();
		couleursThemeR.add(tabCouleur[0][0]);
		couleursThemeR.add(tabCouleur[0][1]);
		couleursThemeR.add(tabCouleur[0][2]);
		jsonObjet.put("couleurR", couleursThemeR);
		
		JSONArray couleursThemeO = new JSONArray();
		couleursThemeO.add(tabCouleur[1][0]);
		couleursThemeO.add(tabCouleur[1][1]);
		couleursThemeO.add(tabCouleur[1][2]);
		jsonObjet.put("couleurO", couleursThemeO);
		
		JSONArray couleursThemeJ = new JSONArray();
		couleursThemeJ.add(tabCouleur[2][0]);
		couleursThemeJ.add(tabCouleur[2][1]);
		couleursThemeJ.add(tabCouleur[2][2]);
		jsonObjet.put("couleurJ", couleursThemeJ);
		
		JSONArray couleursThemeV = new JSONArray();
		couleursThemeV.add(tabCouleur[3][0]);
		couleursThemeV.add(tabCouleur[3][1]);
		couleursThemeV.add(tabCouleur[3][2]);
		jsonObjet.put("couleurV", couleursThemeV);
		
		JSONArray couleursThemeB = new JSONArray();
		couleursThemeB.add(tabCouleur[4][0]);
		couleursThemeB.add(tabCouleur[4][1]);
		couleursThemeB.add(tabCouleur[4][2]);
		jsonObjet.put("couleurB", couleursThemeB);
		
		JSONArray couleursThemeI = new JSONArray();
		couleursThemeI.add(tabCouleur[5][0]);
		couleursThemeI.add(tabCouleur[5][1]);
		couleursThemeI.add(tabCouleur[5][2]);
		jsonObjet.put("couleurI", couleursThemeI);
		

		Path chemin = Paths.get("Système/Paramètres.json");
		Charset charset = Charset.forName("Cp1252");
		String sauvegarde = jsonObjet.toString();
		try (BufferedWriter writer= Files.newBufferedWriter(chemin, charset)) {
			writer.write(sauvegarde, 0, sauvegarde.length());
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}

		
	
		couleur();

		
	}
}
