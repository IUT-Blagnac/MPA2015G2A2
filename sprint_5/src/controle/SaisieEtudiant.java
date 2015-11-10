package controle;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import modele.*;
import vue.*;

public class SaisieEtudiant {

	private static Etudiant etu;
	private FenetreOPTI fenetreP;
	private panneauEtudiant panneauE;

	/**
	 * Créateur du contrôleur.
	 * 
	 * @param pEtudiant
	 * @param pFenetreP
	 */
	public SaisieEtudiant(Etudiant pEtudiant, FenetreOPTI pFenetreP) {
		fenetreP = pFenetreP;
		etu = pEtudiant;
	}

	/**
	 * Fonction publique SaisieEtu() qui appelle la classe panneauEtudiant et
	 * qui permet d'avoir accès aux arguments de la classe Etudiant
	 * 
	 * @return panneauEtudiant
	 */
	public panneauEtudiant SaisieEtu() {

		JTextField a = new JTextField(etu.getGroupe());
		JLabel b = new JLabel("" + etu.getId());
		JTextField c = new JTextField(etu.getNom());
		JTextField d = new JTextField(etu.getPrenom());
		panneauE = new panneauEtudiant(a, b, c, d, this);

		return (panneauE);
	}

	/**
	 * Fonction publique implementationEtu() qui prend tout ce que retourne le
	 * panneauEtudiant pour enregistrer un étudiant et supprimer le panneau des
	 * étudiants
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 */
	public void implementationEtu(JTextField a, JLabel b, JTextField c,
			JTextField d) {
		Etudiant nvE = new Etudiant(a.getText(), new Integer(b.getText()),
				c.getText(), d.getText());
		etu = nvE;
		fenetreP.setEtudiantAModifier(etu);
		fenetreP.getRootE().removeAllChildren();
		for (int j = 0; j < fenetreP.getEtudiants().size(); j++) {
			fenetreP.getRootE()
					.add(new DefaultMutableTreeNode(fenetreP.getEtudiants()
							.get(j).getId()
							+ " : "
							+ fenetreP.getEtudiants().get(j).getNom()
							+ " " + fenetreP.getEtudiants().get(j).getPrenom()));
		}
		fenetreP.getTree().updateUI();
		panneauE.setVisible(false);
		fenetreP.repaint();
		fenetreP.setEtuM(false);
	}

}
