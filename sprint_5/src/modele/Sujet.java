package modele;

/**
 * 
 * @author Thibault-Denis
 *
 */

public class Sujet {

	private int id;
	private String nom;
	private String titre;

	/**
	 * 
	 * @param Aid
	 * @param Anom
	 * @param Atitre
	 * 
	 */

	public Sujet(int Aid, String Anom, String Atitre) {

		this.id = Aid;
		this.nom = Anom;
		this.titre = Atitre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
}
