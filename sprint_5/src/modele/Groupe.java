package modele;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * objet Groupe qui définit tous les attributs d'un groupe
 * 
 * @author Cédric LESPAGNOL
 *
 */
public class Groupe {

	private String id;
	private ArrayList<Etudiant> etus;
	private Sujet sujet;
	private HashSet<Voeux> voeux;

	/**
	 * Constructeur de l'objet Groupe
	 * 
	 * @param pId
	 * @param pEtus
	 * @param pVoeux
	 */
	public Groupe(String pId, ArrayList<Etudiant> pEtus, HashSet<Voeux> pVoeux) {
		id = pId;
		etus = pEtus;
		voeux = pVoeux;
	}

	/**
	 * Constructeur de l'objet Groupe
	 * 
	 * @param pId
	 * @param pEtus
	 * @param pSuj
	 * @param pVoeux
	 */
	public Groupe(String pId, ArrayList<Etudiant> pEtus, Sujet pSuj,
			HashSet<Voeux> pVoeux) {

		id = pId;
		sujet = pSuj;
		etus = pEtus;
		voeux = pVoeux;
	}

	/**
	 * Getter de id
	 * 
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter de id
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Getter de sujet
	 * 
	 * @return Sujet
	 */
	public Sujet getSujet(Sujet sujet) {

		return sujet;
	}

	/**
	 * Setter de sujet
	 * 
	 * @param sujet
	 */
	public void setSujet(Sujet sujet) {
		this.sujet = sujet;

	}

	/**
	 * Getter de etus
	 * 
	 * @return ArrayList<Etudiant>
	 */
	public ArrayList<Etudiant> getEtus() {
		return etus;
	}

	/**
	 * Setter de etus
	 * 
	 * @param etus
	 */
	public void setEtus(ArrayList<Etudiant> etus) {
		this.etus = etus;
	}

	/**
	 * Getter de voeux
	 * 
	 * @return HashSet<Voeux>
	 */
	public HashSet<Voeux> getVoeux() {
		return voeux;
	}

	/**
	 * Setter de voeux
	 * 
	 * @param voeux
	 */
	public void setVoeux(HashSet<Voeux> voeux) {
		this.voeux = voeux;
	}
}
