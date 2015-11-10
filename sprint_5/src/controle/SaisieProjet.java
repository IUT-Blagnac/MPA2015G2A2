package controle;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import modele.*;
import vue.*;

public class SaisieProjet {
	private static Projet projet;
	private FenetreOPTI fenetreP;
	private panneauProjet panneauP;

	/**
	 * Créateur du contrôleur.
	 * 
	 * @param pProjet
	 * @param pFenetreP
	 */
	public SaisieProjet(Projet pProjet, FenetreOPTI pFenetreP) {
		fenetreP = pFenetreP;
		projet = pProjet;
	}

	/**
	 * Fonction public SaisieProjet() qui appele la classe panneauProjet et qui
	 * permet d'avoir accès aux arguments de la classe Projet
	 * 
	 * @return panneauProjet
	 */
	public panneauProjet SaisieProj() {

		JLabel a = new JLabel("" + projet.getId());
		JTextField b = new JTextField(projet.getGroupe().getId());
		JTextField c = new JTextField("" + projet.getSujet().getId());
		JTextField d = new JTextField("" + projet.getClient().getId());
		JTextField e = new JTextField("" + projet.getSuperviseur().getId());
		panneauP = new panneauProjet(a, b, c, d, e, this);

		return (panneauP);
	}

	/**
	 * Fonction public implementationProjet() qui prend tout ce que retourne le
	 * panneauProjet pour enregistrer un projet et supprimer le panneau des
	 * projets
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 */
	public void implementationProjet(JLabel a, JTextField b, JTextField c,
			JTextField d, JTextField e) {
		Projet nvP = new Projet(new Integer(a.getText()), new Groupe(
				b.getText(), null, null), new Sujet(new Integer(c.getText()),
				null, null), new Intervenant(new Integer(d.getText()), null,
				null), new Intervenant(new Integer(d.getText()), null, null));
		projet = nvP;
		fenetreP.setProjetAModifier(projet);
		fenetreP.getRootP().removeAllChildren();
		for (int j = 0; j < fenetreP.getProjets().size(); j++) {
			fenetreP.getRootP().add(
					new DefaultMutableTreeNode("Projet "
							+ fenetreP.getProjets().get(j).getId()));
		}
		fenetreP.getTree().updateUI();
		panneauP.setVisible(false);
		fenetreP.repaint();
		fenetreP.setProjM(false);
	}

}
