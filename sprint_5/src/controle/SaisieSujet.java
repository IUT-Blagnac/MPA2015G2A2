package controle;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import modele.*;
import vue.*;

/**
 * Le contrôleur des saisies d'un sujet choisi qui est appelé lors de la
 * modification d'un sujet existant
 *
 * @author Alexandre GALLO
 * @author Cédric LESPAGNOL
 */
public class SaisieSujet {
	private static Sujet subject;
	private FenetreOPTI fenetreP;
	private panneauSujet panneauS;

	/**
	 * Créateur du contrôleur.
	 * 
	 * @param psubject
	 * @param pFenetreP
	 */
	public SaisieSujet(Sujet psubject, FenetreOPTI pFenetreP) {
		fenetreP = pFenetreP;
		subject = psubject;
	}

	/**
	 * Fonction publique SaisieSuje() qui appelle la classe panneauSujet et qui
	 * permet d'avoir accès aux arguments de la classe Sujet
	 * 
	 * @return panneauSujet
	 */
	public panneauSujet SaisieSuje() {

		JLabel a = new JLabel("" + subject.getId());
		JTextField b = new JTextField(subject.getNom());
		JTextField c = new JTextField(subject.getTitre());
		panneauS = new panneauSujet(a, b, c, this, fenetreP);

		return (panneauS);
	}

	/**
	 * Fonction publique implementationSuj() qui prend tout ce que retourne le
	 * panneauSujet pour enregistrer un sujet et supprimer le panneau des sujets
	 * 
	 * @param a
	 * @param b
	 * @param c
	 */
	public void implementationSuj(JLabel a, JTextField b, JTextField c) {
		Sujet nvS = new Sujet(new Integer(a.getText()), b.getText(),
				c.getText());
		subject = nvS;
		fenetreP.setSujetAModifier(subject);
		fenetreP.getRootS().removeAllChildren();
		fenetreP.TriSuj();
		for (int j = 0; j < fenetreP.getSujets().size(); j++) {
			fenetreP.getRootS().add(
					new DefaultMutableTreeNode(fenetreP.getSujets().get(j)
							.getId()
							+ " : " + fenetreP.getSujets().get(j).getNom()));
		}
		fenetreP.getTree().updateUI();
		panneauS.setVisible(false);
		fenetreP.repaint();
		fenetreP.setSujetM(false);
	}
}
