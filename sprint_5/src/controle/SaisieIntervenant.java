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
public class SaisieIntervenant {
	private static Intervenant intervenant;
	private FenetreOPTI fenetreP;
	private panneauIntervenant panneauI;

	/**
	 * Créateur du contrôleur.
	 * 
	 * @param pIntervenant
	 * @param pFenetreP
	 */
	public SaisieIntervenant(Intervenant pIntervenant, FenetreOPTI pFenetreP) {
		fenetreP = pFenetreP;
		intervenant = pIntervenant;
	}

	/**
	 * Fonction publique SaisieSuje() qui appelle la classe panneauSujet et qui
	 * permet d'avoir accès aux arguments de la classe Sujet
	 * 
	 * @return panneauIntervenant
	 */
	public panneauIntervenant SaisieInter() {

		JLabel a = new JLabel("" + intervenant.getId());
		JTextField b = new JTextField(intervenant.getPrenom());
		JTextField c = new JTextField(intervenant.getNom());
		panneauI = new panneauIntervenant(a, b, c, this, fenetreP);

		return (panneauI);
	}

	/**
	 * Fonction publique implementationSuj() qui prend tout ce que retourne le
	 * panneauSujet pour enregistrer un sujet et supprimer le panneau des sujets
	 * 
	 * @param a
	 * @param b
	 * @param c
	 */
	public void implementationInter(JLabel a, JTextField b, JTextField c) {
		Intervenant nvI = new Intervenant(new Integer(a.getText()),
				b.getText(), c.getText());
		intervenant = nvI;
		fenetreP.setInterAModifier(intervenant);
		fenetreP.getRootI().removeAllChildren();
		fenetreP.TriInter();
		for (int j = 0; j < fenetreP.getIntervenants().size(); j++) {
			fenetreP.getRootI()
					.add(new DefaultMutableTreeNode(fenetreP.getIntervenants()
							.get(j).getId()
							+ " : "
							+ fenetreP.getIntervenants().get(j).getPrenom()
							+ " " + fenetreP.getIntervenants().get(j).getNom()));
		}
		fenetreP.getTree().updateUI();
		panneauI.setVisible(false);
		fenetreP.repaint();
		fenetreP.setInterM(false);
	}
}