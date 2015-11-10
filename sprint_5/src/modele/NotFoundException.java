package modele;

/**
 * classe NotFoundException qui hérite de Exception qui permet de lever une
 * exception
 * 
 * @author CRUBILLE-NGUYEN
 * 
 */
public class NotFoundException extends Exception {

	/**
	 * Constructeur de NotFoundException
	 */
	public NotFoundException() {

		super("Elément non trouvé.");

	}

	/**
	 * Constructeur de NotFoundException
	 * 
	 * @param message
	 */
	public NotFoundException(String message) {

		super(message);

	}

}
