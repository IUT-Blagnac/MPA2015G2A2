package controle;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Application java qui permet à partir d'un .conf de créer un .html
 * 
 * @author groupe3B1
 *
 */
public class OPTIweb {

	private static String idp;
	private static String labelp;
	private static String headerp;
	private static String beforep;
	private static String contentp;
	private static String afterp;
	private static String footerp;
	private static String logop;

	private static String cheminConf;

	/**
	 * Constructeur de mywhatsonGenerateur afin de pouvoir l'utiliser par
	 * l'interface homme machine.
	 * 
	 * @param pChemin
	 *            pChemin lié à cheminConf
	 */
	public OPTIweb(String pChemin) {
		OPTIweb.cheminConf = pChemin;

		String nomFichier = conversionConfToHtml();

		System.out.println(nomFichier);
		String texte = "";

		texte += baliseHead();
		texte += baliseBody();

		ecrire(nomFichier, texte);

	}

	/**
	 * Remplace ".conf" par ".html" dans le nom du fichier
	 * 
	 * @return nomFichier nomFichier est le fichier qui sera créé finissant
	 *         par .html
	 */
	public static String conversionConfToHtml() {
		String nomFichier = "";
		String str = cheminConf;
		char[] charArray = str.toCharArray();
		int i = charArray.length - 1;
		while (i != 0 && charArray[i] != '/' && charArray[i] != '\\') {
			i--;
		}
		if (i != 0)
			i++;
		while (charArray[i] != '.' && i < charArray.length) {
			nomFichier += charArray[i];
			i++;
		}
		nomFichier += ".html";

		return (nomFichier);
	}

	/**
	 * Permet de tout écrire dans le fichier .html
	 * 
	 * @param nomFic
	 *            nom du fichier .html de destination, il s'écrase à chaque
	 *            fois, s'il n'existe pas il est généré
	 * @param texte
	 *            Chaine de caractère contenant ce qui sera écrit dans un
	 *            proche avenir dans le .html
	 */
	public static void ecrire(String nomFic, String texte) {
		// on va chercher le chemin et le nom du fichier et on me tout ca dans
		// un String

		// on met try si jamais il y a une exception
		try {
			/**
			 * BufferedWriter a besoin d un FileWriter, les 2 vont ensemble, on
			 * donne comme argument le nom du fichier true signifie qu on ajoute
			 * dans le fichier (append), on ne marque pas par dessus
			 */
			FileWriter fw = new FileWriter(nomFic);

			// le BufferedWriter output auquel on donne comme argument le
			// FileWriter fw cree juste au dessus
			BufferedWriter output = new BufferedWriter(fw);

			// on marque dans le fichier ou plutot dans le BufferedWriter qui
			// sert comme un tampon(stream)
			output.write(texte);
			// on peut utiliser plusieurs fois methode write

			output.flush();
			// ensuite flush envoie dans le fichier, ne pas oublier cette
			// methode pour le BufferedWriter

			output.close();
			// et on le ferme
			System.out.println("fichier créé");
		} catch (IOException ioe) {
			System.out.print("Erreur : ");
			ioe.printStackTrace();
		}

	}

	/**
	 * Permet de générer le header du .html
	 * 
	 * @return texte
	 */
	public static String baliseHead() {
		String texte = "";
		texte += "<!DOCTYPE html>\n";
		texte += "<html>\n";
		texte += "<head>\n";
		texte += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n";
		texte += "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n";
		texte += "<meta name=\"generator\" content=\"OPTIweb VOPTIweb\" />\n";
		texte += "<title>OPTIweb - V0.1</title>\n";
		texte += "<link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css\" />\n";
		texte += "<link rel=\"stylesheet\" href=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css\" />\n";
		texte += "<script src=\"http://code.jquery.com/jquery-1.9.1.min.js\">";
		texte += "$(document).bind('mobileinit',function(){$.mobile.changePage.defaults.changeHash = false;$.mobile.hashListeningEnabled = false;$.mobile.pushStateEnabled = false;});</script>";
		texte += "<script src=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js\"></script>\n";
		texte += "<style type='text/css'>    \n";
		texte += "@media all and (orientation:portrait) { .landscape {display: none;} }\n";
		texte += "@media all and (orientation:landscape) { .landscape {display: inline;} }\n";
		texte += "</style>\n";
		texte += "</head>\n";

		return texte;
	}

	/**
	 * Permet de générer le body du .html
	 * 
	 * @return texte
	 */
	public static String baliseBody() {

		int i = 0;

		String texte = "<body>\n";
		texte += "\n<!-- DEBUT page accueil -->\n";
		texte += "<div data-role=\"page\" id=\"accueil\" data-title=\"OPTIweb - V0.1\">\n";
		texte += "<div data-role=\"header\" data-add-back-btn=\"true\">\n";
		texte += "<h1 aria-level=\"1\" role=\"heading\" class=\"ui-title\">P<span class=\"landscape\">rojets </span>tut<span class=\"landscape\">orés</span> 2014-2015<br>Département INFO<span class=\"landscape\">RMATIQUE</span><br>IUT de Blagnac</h1>\n";
		texte += "<a href=\"#credits\" data-theme=\"b\" class=\"ui-btn-right\">Crédits</a>\n";
		texte += "</div>\n";
		texte += "<div data-role=\"content\">\n";
		texte += "<ul data-role=\"listview\" data-inset=\"true\" id=\"listeSources\">\n";

		try {
			// read the json file
			FileReader reader = new FileReader(cheminConf);

			JSONParser jsonParser = new JSONParser();
			// tableau des pages
			JSONArray pages = (JSONArray) jsonParser.parse(reader);

			// take the elements of the json array
			i = 0;
			while (i < pages.size()) {
				JSONObject p = (JSONObject) pages.get(i);
				idp = (String) p.get("id");
				logop = (String) p.get("logo");
				labelp = (String) p.get("label");
				if (p.get("up") == null)
					texte += "<li><a href=\"#" + idp + "\"><i class=\"fa fa-"
							+ logop + "\"></i> " + labelp + "</a></li>\n";
				i++;
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}

		texte += "</ul>\n";
		texte += "</div>\n";
		texte += "<div data-role=\"footer\">\n";
		texte += " <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4>\n";
		texte += "</div>\n";
		texte += "</div>\n";
		texte += "<!-- FIN page accueil -->\n";

		try {
			// read the json file
			FileReader reader = new FileReader(cheminConf);

			JSONParser jsonParser = new JSONParser();
			// tableau des pages
			JSONArray pages = (JSONArray) jsonParser.parse(reader);

			// take the elements of the json array
			int k = 0;
			while (k < pages.size()) {
				JSONObject p = (JSONObject) pages.get(k);
				idp = (String) p.get("id");
				labelp = (String) p.get("label");
				headerp = (String) p.get("header");
				beforep = (String) p.get("before");
				contentp = (String) p.get("content");
				afterp = (String) p.get("after");
				footerp = (String) p.get("footer");
				logop = (String) p.get("logo");

				texte += "\n<!-- DEBUT page " + idp + " -->\n";
				texte += "<div data-role=\"page\" id=\"" + idp
						+ "\" data-title=\"OPTIweb - V0.1\">\n";
				texte += "<div data-role=\"header\" data-add-back-btn=\"true\">\n";
				texte += "<h1>" + headerp + "</h1>\n\n";
				texte += "</div>\n";
				texte += "<div data-role=\"content\">\n\n";
				texte += "  " + beforep + "\n";
				texte += "  " + contentp + "\n";
				texte += "  " + afterp + "\n";
				texte += "</div>\n";
				texte += "<div data-role=\"footer\">\n";
				texte += " <h4>" + footerp + " <i class=\"fa fa-" + logop
						+ " fa-2x\"></i></h4>\n";
				texte += "</div>\n";
				texte += "</div>\n";
				texte += "<!-- FIN page " + idp + " -->\n";
				k++;
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}

		texte += "\n<!-- DEBUT page credits -->\n";
		texte += "<div data-role=\"page\" id=\"credits\" data-title=\"OPTI web - V1.0 - Crédits\">\n";
		texte += "<div data-role=\"header\" data-add-back-btn=\"true\">\n";
		texte += "<h1>Crédits</h1>\n";
		texte += "</div>\n";
		texte += "<div data-role=\"content\">\n";
		texte += "    <p>Cette application a été réalisée dans le cadre du module M3301/MPA du DUT Informatique à l'IUT de Blagnac.</p>\n";
		texte += "<ul data-role=\"listview\" data-inset=\"true\" id=\"contacts\" data-theme=\"a\" data-divider-theme=\"b\">\n";
		texte += "    <li data-role=\"list-divider\">Product Owner</li>";
		texte += "    <li>André PÉNINOU</li>\n";
		texte += "    <li>Université Toulouse 2 - IUT de Blagnac\n";
		texte += "    <br/>Département INFORMATIQUE</li>";
		texte += "</ul>\n";
		texte += "<ul data-role=\"listview\" data-inset=\"true\" id=\"listecredits\" data-theme=\"a\" data-divider-theme=\"b\">\n";
		texte += "    <li data-role=\"list-divider\">Membres de l'équipe enseignante</li>\n";
		texte += "<li>Jean-Michel BRUEL</li><li>Jean-Michel INGLEBERT</li><li>André PÉNINOU</li><li>Olivier ROQUES</li>\n";
		texte += "</ul>\n";
		texte += "<ul data-role=\"listview\" data-inset=\"true\" id=\"contacts\" data-theme=\"a\" data-divider-theme=\"b\">\n";
		texte += "    <li data-role=\"list-divider\">Membres de l'équipe étudiante</li>\n";
		texte += "    <li>Loïc Lemonsu</li>\n";
		texte += "    <li>Baptiste Larre</li>\n";
		texte += "    <li>Alexandre Gallo Gonzalez</li>\n";
		texte += "    <li>Cédric Lespagnol</li>\n";
		texte += "    <li>Denis Nguyen</li>\n";
		texte += "    <li>Thibault Crubille</li>\n";
		texte += "</ul>\n";
		texte += "<ul data-role=\"listview\" data-inset=\"true\" id=\"listepowered\" data-theme=\"a\" data-divider-theme=\"b\">\n";
		texte += "    <li data-role=\"list-divider\">Propulsé par</li>\n";
		texte += "    <li><a href=\"http://jquerymobile.com/\" target=\"autrePage\">http://jquerymobile.com/</a></li>\n";
		texte += "    <li><a href=\"http://fortawesome.github.io/Font-Awesome/\" target=\"autrePage\">http://fortawesome.github.io/Font-Awesome/</a></li>\n";
		texte += "</ul>\n";
		texte += "</div>\n";
		texte += "<div data-role=\"footer\"> \n";
		texte += " <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4> \n";
		texte += "</div>\n";
		texte += "</div>\n";
		texte += "<!-- FIN page credits -->\n";

		texte += "<script>\n";
		texte += " // li click handler which fills the projects search bar \n";
		texte += " // with the value of the current data-find attribute\n";
		texte += " $( 'li[data-find]' ).on( 'click',function(event){\n";
		texte += "  $(\"#autocomplete-input-projet\").val($(this).attr('data-find')).trigger('change');\n";
		texte += " });\n";
		texte += "</script>\n";

		texte += "</body>\n";
		texte += "</html>\n";

		return texte;
	}

}
