package modele;

/**
 * objet Etudiant qui définit tous les attributs d'un étudiant
 * 
 * @author CRUBILLE-NGUYEN
 *
 */

public class Etudiant {

	private String groupe;
	private int id;
	private String nom;
	private String prenom;

	/**
	 * Constructeur de l'objet Etudiant
	 * 
	 * @param Egroupe
	 * @param Eid
	 * @param Enom
	 * @param Eprenom
	 */
	public Etudiant(String Egroupe, int Eid, String Enom, String Eprenom) {

		this.id = Eid;
		this.groupe = Egroupe;
		this.nom = Enom;
		this.prenom = Eprenom;
	}

	/**
	 * Getter de id
	 * 
	 * @return int
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter de id
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter de groupe
	 * 
	 * @return String
	 */
	public String getGroupe() {
		return groupe;
	}

	/**
	 * Setter de groupe
	 * 
	 * @param groupe
	 */
	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	/**
	 * Getter de nom
	 * 
	 * @return String
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter de nom
	 * 
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter de prenom
	 * 
	 * @return String
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Setter de prenom
	 * 
	 * @param prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}
