package modele;

/**
 * objet Intervenant qui définit tous les attributs d'un intervenant
 * 
 * @author Cédric LESPAGNOL
 * 
 */

public class Intervenant {

	private int id;
	private String prenom;
	private String nom;

	/**
	 * Constructeur de l'objet Intervenant
	 * 
	 * @param pId
	 * @param pPrenom
	 * @param pNom
	 */
	public Intervenant(int pId, String pPrenom, String pNom) {
		id = pId;
		prenom = pPrenom;
		nom = pNom;
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
}
