package modele;

/**
 * objet Voeux qui définit tous les attributs d'un groupe
 * 
 * @author Cédric LESPAGNOL
 *
 */
public class Voeux {

	private Sujet suj;
	private int rang;

	/**
	 * Constructeur de l'objet Voeux
	 * 
	 * @param pSuj
	 * @param pRang
	 */
	public Voeux(Sujet pSuj, int pRang) {
		this.suj = pSuj;
		this.rang = pRang;
	}

	/**
	 * Getter de rang
	 * 
	 * @return int
	 */
	public int getRang() {
		return rang;
	}

	/**
	 * Getter de suj
	 * 
	 * @return Sujet
	 */
	public Sujet getSuj() {
		return suj;
	}

	/**
	 * Setter de rang
	 * 
	 * @param rang
	 */
	public void setRang(int rang) {
		this.rang = rang;
	}

	/**
	 * Setter de Sujet
	 * 
	 * @param suj
	 */
	public void setSuj(Sujet suj) {
		this.suj = suj;
	}

}
