package controle;
public class Etudia {

	private int id;
	private String groupe;
	private String prenom;
	private String nom;

	public Etudia(int id, String groupe, String nom, String prenom) {

		this.id = id;
		this.groupe = groupe;
		this.prenom = prenom;
		this.nom = nom;

	}

	public int getId() {
		return id;
	}

	public String getGroupe() {
		return groupe;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getNom() {
		return nom;
	}

}
