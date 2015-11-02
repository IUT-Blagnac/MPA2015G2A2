package controle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class casse {

	private casse() {
	}

	private static int countClient(String id) {
		int nbC = 0;

		String ligne;
		try {
			// Ouvre le fichier source à la lecture
			BufferedReader fichier = new BufferedReader(new FileReader(
					"../OPTIweb/test/projets2014_2015.csv"));
			// Tant que le fichier en lecture contient de l'information,
			// lecture du contenu dans un System.out.println
			while ((ligne = fichier.readLine()) != null) {

				String[] lignedecoupee = ligne.split(";");
				if (lignedecoupee[3].equals(id)) {
					nbC++;
				}
			}
			fichier.close();
		} catch (Exception e) {

			System.err.println("Erreur : " + e.toString());

		}

		return nbC;
	}

	private static int countSuperviseur(String id) {
		int nbS = 0;

		String ligne;
		try {
			// Ouvre le fichier source à la lecture
			BufferedReader fichier = new BufferedReader(new FileReader(
					"../OPTIweb/test/projets2014_2015.csv"));
			// Tant que le fichier en lecture contient de l'information,
			// lecture du contenu dans un System.out.println
			while ((ligne = fichier.readLine()) != null) {

				String[] lignedecoupee = ligne.split(";");
				if (lignedecoupee[4].equals(id)) {
					nbS++;
				}
			}
			fichier.close();
		} catch (Exception e) {

			System.err.println("Erreur : " + e.toString());

		}

		return nbS;
	}

	private static String getNomSuj(String id) {
		String nomSuj = "";

		String ligne;
		try {
			// Ouvre le fichier source à la lecture
			BufferedReader fichier = new BufferedReader(new FileReader(
					"../OPTIweb/test/sujets2014_2015.csv"));
			// Tant que le fichier en lecture contient de l'information,
			// lecture du contenu dans un System.out.println
			while ((ligne = fichier.readLine()) != null) {

				String[] lignedecoupee = ligne.split(";");
				if (lignedecoupee[0].equals(id)) {
					fichier.close();
					return lignedecoupee[1];
				}
			}
			fichier.close();
		} catch (Exception e) {

			System.err.println("Erreur : " + e.toString());

		}

		return nomSuj;
	}

	private static String getTitreSuj(String id) {
		String nomSuj = "";

		String ligne;
		try {
			// Ouvre le fichier source à la lecture
			BufferedReader fichier = new BufferedReader(new FileReader(
					"../OPTIweb/test/sujets2014_2015.csv"));
			// Tant que le fichier en lecture contient de l'information,
			// lecture du contenu dans un System.out.println
			while ((ligne = fichier.readLine()) != null) {

				String[] lignedecoupee = ligne.split(";");
				if (lignedecoupee[0].equals(id)) {
					fichier.close();
					return lignedecoupee[2];
				}
			}
			fichier.close();
		} catch (Exception e) {

			System.err.println("Erreur : " + e.toString());

		}

		return nomSuj;
	}

	private static String getNomC(String id) {
		String nomC = "";

		String ligne;
		try {
			// Ouvre le fichier source à la lecture
			BufferedReader fichier = new BufferedReader(new FileReader(
					"../OPTIweb/test/intervenants2014_2015.csv"));
			// Tant que le fichier en lecture contient de l'information,
			// lecture du contenu dans un System.out.println
			while ((ligne = fichier.readLine()) != null) {

				String[] lignedecoupee = ligne.split(";");
				if (lignedecoupee[0].equals(id)) {
					fichier.close();
					return lignedecoupee[2] + " " + lignedecoupee[1];
				}
			}
			fichier.close();
		} catch (Exception e) {

			System.err.println("Erreur : " + e.toString());

		}

		return nomC;
	}

	private static String getNomS(String id) {
		String nomS = "";

		String ligne;
		try {
			// Ouvre le fichier source à la lecture
			BufferedReader fichier = new BufferedReader(new FileReader(
					"../OPTIweb/test/intervenants2014_2015.csv"));
			// Tant que le fichier en lecture contient de l'information,
			// lecture du contenu dans un System.out.println
			while ((ligne = fichier.readLine()) != null) {

				String[] lignedecoupee = ligne.split(";");
				if (lignedecoupee[0].equals(id)) {
					fichier.close();
					return lignedecoupee[2] + " " + lignedecoupee[1];
				}
			}
			fichier.close();
		} catch (Exception e) {

			System.err.println("Erreur : " + e.toString());

		}

		return nomS;
	}

	private static String getEtus(String groupe) {
		String etus = "";

		String ligne;
		try {
			// Ouvre le fichier source à la lecture
			BufferedReader fichier = new BufferedReader(new FileReader(
					"../OPTIweb/test/etudiants2014_2015.csv"));
			// Tant que le fichier en lecture contient de l'information,
			// lecture du contenu dans un System.out.println
			while ((ligne = fichier.readLine()) != null) {

				String[] lignedecoupee = ligne.split(";");
				if (lignedecoupee[0].equals(groupe)) {
					etus += lignedecoupee[3] + " " + lignedecoupee[2] + " - ";
				}
			}
			fichier.close();
		} catch (Exception e) {

			System.err.println("Erreur : " + e.toString());

		}

		return etus;
	}

	private static String getGrp(String suj) {
		String grp = "";

		String ligne;
		try {
			// Ouvre le fichier source à la lecture
			BufferedReader fichier = new BufferedReader(new FileReader(
					"../OPTIweb/test/projets2014_2015.csv"));
			// Tant que le fichier en lecture contient de l'information,
			// lecture du contenu dans un System.out.println
			while ((ligne = fichier.readLine()) != null) {

				String[] lignedecoupee = ligne.split(";");
				if (lignedecoupee[2].equals(suj)) {
					grp += lignedecoupee[1] + " ";
				}
			}
			fichier.close();
		} catch (Exception e) {

			System.err.println("Erreur : " + e.toString());

		}

		return grp;
	}

	/**
	 * Méthode permettant de lire un fichier fourni en paramètre.
	 *
	 * @param nomFich
	 * @return String[]
	 * @exception printStackTrace
	 * 
	 */
	public static String contentProjet(String nomFich) {
		String chaine = "";
		ArrayList<String[]> alsPremLigne = new ArrayList<String[]>();
		String ligne;
		try {
			// Ouvre le fichier source à la lecture
			BufferedReader fichier = new BufferedReader(new FileReader(nomFich));
			// Tant que le fichier en lecture contient de l'information,
			// lecture du contenu dans un System.out.println
			while ((ligne = fichier.readLine()) != null) {

				String[] lignedecoupee = ligne.split(";");
				alsPremLigne.add(lignedecoupee);
			}

			for (int i = 1; i < alsPremLigne.size(); i++) {
				chaine += "<li><p><b>[" + getNomSuj(alsPremLigne.get(i)[2])
						+ "]</b> " + getTitreSuj(alsPremLigne.get(i)[2])
						+ "</p><p><b>Client :</b> "
						+ getNomC(alsPremLigne.get(i)[3])
						+ "</p><p><b>Superviseur :</b> "
						+ getNomS(alsPremLigne.get(i)[4]) + "</p><p><b>Groupe "
						+ alsPremLigne.get(i)[1] + " :</b> "
						+ getEtus(alsPremLigne.get(i)[1]) + "</p></li>";
			}
			fichier.close();
		} catch (Exception e) {

			System.err.println("Erreur : " + e.toString());

		}

		return chaine;

	}

	public static String contentSuj(String nomFich) {
		String chaine = "";
		ArrayList<String[]> alsPremLigne = new ArrayList<String[]>();
		String ligne;
		try {
			// Ouvre le fichier source à la lecture
			BufferedReader fichier = new BufferedReader(new FileReader(nomFich));
			// Tant que le fichier en lecture contient de l'information,
			// lecture du contenu dans un System.out.println
			while ((ligne = fichier.readLine()) != null) {

				String[] lignedecoupee = ligne.split(";");
				alsPremLigne.add(lignedecoupee);
			}

			for (int i = 1; i < alsPremLigne.size(); i++) {
				chaine += "<li data-find='[" + alsPremLigne.get(i)[1]
						+ "]'><a href='#projets'>[" + alsPremLigne.get(i)[1]
						+ "] <br/><div style='white-space:normal;'><span><b>"
						+ alsPremLigne.get(i)[2]
						+ "</b></span><span class='ui-li-count'>"
						+ getGrp(alsPremLigne.get(i)[0])
						+ "</span></div></a></li>";
			}
			fichier.close();
		} catch (Exception e) {

			System.err.println("Erreur : " + e.toString());

		}

		return chaine;

	}

	public static String contentEtu(String nomFich) {
		String chaine = "";
		ArrayList<Etudia> alsPremLigne = new ArrayList<Etudia>();
		String ligne;
		Etudia etu;
		try {
			// Ouvre le fichier source à la lecture
			BufferedReader fichier = new BufferedReader(new FileReader(nomFich));
			// Tant que le fichier en lecture contient de l'information,
			// lecture du contenu dans un System.out.println
			while ((ligne = fichier.readLine()) != null) {

				String[] lignedecoupee = ligne.split(";");
				if (!lignedecoupee[1].equals("id")) {
					etu = new Etudia(new Integer(lignedecoupee[1]),
							lignedecoupee[0], lignedecoupee[2],
							lignedecoupee[3]);
					alsPremLigne.add(etu);
				}

				Collections.sort(alsPremLigne, new Comparator<Etudia>() {

					@Override
					public int compare(Etudia o1, Etudia o2) {
						int nombre1 = o1.getId();
						int nombre2 = o2.getId();
						if (nombre1 < nombre2)
							return -1;
						else if (nombre1 == nombre2)
							return 0;
						else
							return 1;
					}
				});
			}

			for (int i = 0; i < alsPremLigne.size(); i++) {
				chaine += "<li data-find='" + alsPremLigne.get(i).getPrenom()
						+ " " + alsPremLigne.get(i).getNom()
						+ "'><a href='#projets'>"
						+ alsPremLigne.get(i).getNom() + " "
						+ alsPremLigne.get(i).getPrenom()
						+ "<span class='ui-li-count' title='Groupe'>Groupe "
						+ alsPremLigne.get(i).getGroupe() + "</span></a></li>";
			}
			fichier.close();
		} catch (Exception e) {

			System.err.println("Erreur : " + e.toString());

		}

		return chaine;

	}

	public static String contentInter(String nomFich) {
		String chaine = "";
		ArrayList<String[]> alsPremLigne = new ArrayList<String[]>();
		String ligne;
		try {
			// Ouvre le fichier source à la lecture
			BufferedReader fichier = new BufferedReader(new FileReader(nomFich));
			// Tant que le fichier en lecture contient de l'information,
			// lecture du contenu dans un System.out.println
			while ((ligne = fichier.readLine()) != null) {

				String[] lignedecoupee = ligne.split(";");
				alsPremLigne.add(lignedecoupee);
			}

			for (int i = 1; i < alsPremLigne.size(); i++) {
				chaine += "<li data-find='"
						+ alsPremLigne.get(i)[2]
						+ " "
						+ alsPremLigne.get(i)[1]
						+ "'><a href='#projets'>"
						+ alsPremLigne.get(i)[2]
						+ " "
						+ alsPremLigne.get(i)[1]
						+ "<span class='ui-li-count' style='right: 120px !important;' title='Client'>"
						+ countClient(alsPremLigne.get(i)[0])
						+ "</span><span class='ui-li-count' title='Superviseur'>"
						+ countSuperviseur(alsPremLigne.get(i)[0])
						+ "</span></a></li>";
			}
			fichier.close();
		} catch (Exception e) {

			System.err.println("Erreur : " + e.toString());

		}

		return chaine;

	}

	/**
	 * Methode prenant en paramètre le nom de fichier à écrire, et un tableau de
	 * String à écrire dans le fichier fourni en 1er paramètre.
	 * 
	 * @param nomFichier
	 * @param ligneFichier
	 * @param append
	 * @exception IOException
	 */
	public static void createJson(String nomFichier) {

		// on va chercher le chemin et le nom du fichier et on me tout ca dans
		// un String

		String texte = "";
		// on met try si jamais il y a une exception
		try {
			/**
			 * BufferedWriter a besoin d un FileWriter, les 2 vont ensemble, on
			 * donne comme argument le nom du fichier true signifie qu on ajoute
			 * dans le fichier (append), on ne marque pas par dessus
			 */
			FileWriter fw = new FileWriter(nomFichier);

			// le BufferedWriter output auquel on donne comme argument le
			// FileWriter fw cree juste au dessus
			BufferedWriter output = new BufferedWriter(fw);

			texte += " [\n";
			texte += "    { \"id\": \"projets\",\n";
			texte += "       \"label\": \"Projets\",\n";
			texte += "       \"header\": \"Projets 2014-2015\",\n";
			texte += "       \"before\": \"<form class='ui-filterable'><input id='autocomplete-input-projet' name='projet' data-type='search' placeholder='Vous cherchez ?...'></form><ol id='listeprojets' data-role='listview' data-inset='true' data-filter='true' data-filter-reveal='false' data-input='#autocomplete-input-projet'>\",\n";
			texte += "       \"content\": \""
					+ contentProjet("../OPTIweb/test/projets2014_2015.csv")
					+ "\"\n";
			texte += "	   \"after\": \"</ol>\",\n";
			texte += "       \"footer\": \"OPTIweb V<span class='landscape'>ersion </span>0.1\",\n";
			texte += "       \"logo\": \"tasks\"\n";
			texte += "    },\n";
			texte += "    { \"id\": \"sujets\",\n";
			texte += "       \"label\": \"Sujets\",\n";
			texte += "       \"header\": \"Sujets 2014-2015\",\n";
			texte += "	   \"before\": \"<form class='ui-filterable'><input id='autocomplete-input-sujet' name='sujet' data-type='search' placeholder='Vous cherchez ?'></form><ol id='listesujets' data-role='listview' data-inset='true' data-filter='true' data-filter-reveal='false' data-input='#autocomplete-input-sujet' data-divider-theme='b' data-count-theme='a'><li data-role='list-divider'>Sujet<span class='ui-li-count' title='Groupe' style='right: 40px !important;'>Groupe</span></li>\",\n";
			texte += "       \"content\": \""
					+ contentSuj("../OPTIweb/test/sujets2014_2015.csv")
					+ "\"\n";
			texte += "	   \"after\": \"</ol>\",\n";
			texte += "       \"footer\": \"OPTIweb V<span class='landscape'>ersion </span>0.1\",\n";
			texte += "       \"logo\": \"copy\"\n";
			texte += "    },\n";
			texte += "    { \"id\": \"etudiants\",\n";
			texte += "       \"label\": \"Etudiants\",\n";
			texte += "       \"header\": \"Etudiants 2014-2015\",\n";
			texte += "       \"before\": \"<form class='ui-filterable'><input id='autocomplete-input-etudiant' name='etudiant' data-type='search' placeholder='Etudiant ou Groupe X'></form><ol id='listeetudiants' data-role='listview' data-inset='true' data-filter='true' data-filter-reveal='false' data-input='#autocomplete-input-etudiant' data-divider-theme='b'><li data-role='list-divider'>Etudiant<span class='ui-li-count' title='Groupe' style='right: 40px !important;'>Groupe</span></li>\",\n";
			texte += "       \"content\": \""
					+ contentEtu("../OPTIweb/test/etudiants2014_2015.csv")
					+ "\"\n";
			texte += "       \"after\": \"</ol>\",\n";
			texte += "       \"footer\": \"OPTIweb V<span class='landscape'>ersion </span>0.1 \",\n";
			texte += "       \"logo\": \"group\"\n";
			texte += "    },\n";
			texte += "    { \"id\": \"intervenants\",\n";
			texte += "       \"label\": \"Intervenants\",\n";
			texte += "       \"header\": \"Intervenants 2014-2015\",\n";
			texte += "       \"before\": \"<form class='ui-filterable'><input id='autocomplete-input-intervenant' name='intervenant' data-type='search' placeholder='Intervenant'></form><ul id='listeintervenants' data-role='listview' data-inset='true' data-filter='true' data-filter-reveal='false' data-input='#autocomplete-input-intervenant' data-divider-theme='b'><li data-role='list-divider'>Intervenant<span class='ui-li-count' style='right: 110px !important;' title='Client'>Client</span><span class='ui-li-count' title='Superviseur'>Superviseur</span></li>\",\n";
			texte += "       \"content\": \""
					+ contentInter("../OPTIweb/test/intervenants2014_2015.csv")
					+ "\"\n";
			texte += "       \"after\": \"</ul>\",\n";
			texte += "       \"footer\": \"OPTIweb V<span class='landscape'>ersion </span>0.1\",\n";
			texte += "	   \"logo\": \"group\"\n";
			texte += "    }\n";
			texte += "]\n";

			// on marque dans le fichier ou plutot dans le BufferedWriter qui
			// sert comme un tampon(stream)
			output.write(texte);
			// on peut utiliser plusieurs fois methode write

			output.flush();
			// ensuite flush envoie dans le fichier, ne pas oublier cette
			// methode pour le BufferedWriter

			output.close();
			// et on le ferme
			System.out.println("fichier crÃ©Ã©");
		} catch (IOException ioe) {
			System.out.print("Erreur : ");
			ioe.printStackTrace();
		}

	}
}
