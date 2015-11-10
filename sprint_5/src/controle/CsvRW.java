package controle;

import java.io.*;
import java.util.ArrayList;

/**
 * Classe CsvRW qui permet de lire et d'�crire dans un fichier d�fini
 * 
 * @author CRUBILLE-NGUYEN
 */
public class CsvRW {

	private CsvRW() {
	}

	/**
	 * M�thode permettant de lire un fichier fourni en param�tre.
	 *
	 * @param nomFich
	 * @return String[]
	 * @exception printStackTrace
	 * 
	 */
	public static String[] lecture(String nomFich) {
		ArrayList<String> als = new ArrayList<String>();
		String ligne;
		try {
			// Ouvre le fichier source � la lecture
			BufferedReader fichier = new BufferedReader(new FileReader(nomFich));
			// Tant que le fichier en lecture contient de l'information,
			// lecture du contenu dans un System.out.println
			while ((ligne = fichier.readLine()) != null) {

				als.add(ligne);

			}
			// Fermeture du Buffer
			fichier.close();

		} catch (Exception e) {

			System.err.println("Erreur : " + e.toString());

		}
		return als.toArray(new String[0]);
	}

	/**
	 * Methode prenant en param�tre le nom de fichier � �crire, et un tableau de
	 * String � �crire dans le fichier fourni en 1er param�tre.
	 * 
	 * @param nomFichier
	 * @param ligneFichier
	 * @param append
	 * @exception IOException
	 */
	public static void ecrire(String nomFichier, String[] ligneFichier,
			boolean append) {

		try {
			PrintWriter sortie = new PrintWriter(new FileWriter(nomFichier,
					append));

			// Tant que le tableau fourni en param�tre a une taille sup�rieur �
			// la valeur x,
			// �criture du tableau dans le fichier.
			for (int x = 0; x < ligneFichier.length; x++) {
				sortie.write(ligneFichier[x]);
				if (!(x == (ligneFichier.length - 1))) {
					sortie.write(";");
				}
			}
			if (append) {
				sortie.write("\n");
			}
			// flush force l'�criture des donn�es.
			sortie.flush();

			// Fermeture du fichier.
			sortie.close();

		} catch (IOException ioe) {
			System.err.println("Erreur : " + ioe.toString());
		}

	}

}