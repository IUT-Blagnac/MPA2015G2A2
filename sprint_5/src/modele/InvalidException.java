package modele;

/**
 * classe InvalidException qui hérite de Exception qui permet de lever une
 * exception
 * 
 * @author CRUBILLE-NGUYEN
 * 
 */
public class InvalidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de InvalidException
	 * 
	 * @param message
	 */
	public InvalidException(String message) {

		super(message);

	}

}
