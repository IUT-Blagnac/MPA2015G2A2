package modele;

/**
 * classe NotFoundException qui h�rite de Exception qui permet de lever une
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

		super("El�ment non trouv�.");

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
