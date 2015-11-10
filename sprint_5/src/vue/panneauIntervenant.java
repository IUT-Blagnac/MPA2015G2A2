package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controle.*;

/**
 * Classe qui crée le panneau des saisies.
 * 
 * 
 * @author Alexandre GALLO
 * @author Cédric LESPAGNOL
 */
public class panneauIntervenant extends JPanel {

	private static final long serialVersionUID = 1L;
	private SaisieIntervenant saisie;
	private JLabel idText;
	private JTextField prenomText;
	private JTextField nomText;

	/**
	 * Constructeur par défaut du panneauIntervenant qui appelle l'autre
	 * constructeur
	 * 
	 * @param pSaisie
	 * @param fP
	 */
	public panneauIntervenant(SaisieIntervenant pSaisie, final FenetreOPTI fP) {
		this(new JLabel(), new JTextField(1), new JTextField(1), pSaisie, fP);
	}

	/**
	 * Constructeur qui implémente la classe et qui crée le panneau pour la
	 * saisie d'un intervenant.
	 * 
	 * @param pId
	 * @param pPrenom
	 * @param pNom
	 * @param pSaisie
	 * @param fP
	 */
	public panneauIntervenant(final JLabel pId, JTextField pPrenom,
			JTextField pNom, SaisieIntervenant pSaisie, final FenetreOPTI fP) {
		JPanel north = new JPanel(new GridLayout(4, 2));
		final String sID;

		JLabel id = new JLabel("ID :");
		JLabel prenom = new JLabel("Prenom :");
		JLabel nom = new JLabel("Nom :");
		JButton confirmation = new JButton("OK");
		idText = pId;
		prenomText = pPrenom;
		nomText = pNom;
		saisie = pSaisie;
		sID = pId.getText();
		north.add(id);
		north.add(idText);
		north.add(prenom);
		north.add(prenomText);
		north.add(nom);
		north.add(nomText);
		north.add(confirmation);

		confirmation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (sID.equals(pId.getText())) {
					saisie.implementationInter(idText, prenomText, nomText);
				} else {
					int i;
					boolean trouve = false;
					for (i = 0; i < fP.getIntervenants().size(); i++) {
						if (pId.getText().equals(
								"" + fP.getIntervenants().get(i).getId())
								&& !pId.getText().equals(fP.getK())) {
							JDialog fenetre = new JDialog();
							JLabel text = new JLabel("Cet ID est déjà utilisé");
							fenetre.setTitle("Modification");
							fenetre.add(text);
							fenetre.pack();
							fenetre.setLocationRelativeTo(null);
							fenetre.setModal(true);
							fenetre.setVisible(true);
							trouve = true;
							break;
						}
					}
					if (trouve == false) {
						saisie.implementationInter(idText, prenomText, nomText);
					}
				}
			}
		});

		super.add(north, BorderLayout.NORTH);
	}

	/**
	 * Getter du JLabel de l'Id
	 * 
	 * @return JLabel
	 */
	public JLabel getIdText() {
		return idText;
	}

	/**
	 * Setter du JLabel de l'Id
	 * 
	 * @param idText
	 */
	public void setIdText(JLabel idText) {
		this.idText = idText;
	}

	/**
	 * Getter du JTextField prenom
	 * 
	 * @return JTextField
	 */
	public JTextField getPrenomText() {
		return prenomText;
	}

	/**
	 * Setter du JTextField prenom
	 * 
	 * @param prenomText
	 */
	public void setTitreText(JTextField prenomText) {
		this.prenomText = prenomText;
	}

	/**
	 * Getter du JTextField nom
	 * 
	 * @return JTextField
	 */
	public JTextField getNomText() {
		return nomText;
	}

	/**
	 * Setter du JTextField nom
	 * 
	 * @param nomText
	 */
	public void setNomText(JTextField nomText) {
		this.nomText = nomText;
	}
}
