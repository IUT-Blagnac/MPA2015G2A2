package modele;

public class Projet {

	private int id;
	private Groupe groupe;
	private Sujet sujet;
	private Intervenant client;
	private Intervenant superviseur;

	public Projet(int id, Groupe groupe, Sujet sujet, Intervenant client,
			Intervenant superviseur) {
		super();
		this.id = id;
		this.groupe = groupe;
		this.sujet = sujet;
		this.client = client;
		this.superviseur = superviseur;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public Sujet getSujet() {
		return sujet;
	}

	public void setSujet(Sujet sujet) {
		this.sujet = sujet;
	}

	public Intervenant getClient() {
		return client;
	}

	public void setClient(Intervenant client) {
		this.client = client;
	}

	public Intervenant getSuperviseur() {
		return superviseur;
	}

	public void setSuperviseur(Intervenant superviseur) {
		this.superviseur = superviseur;
	}
}
