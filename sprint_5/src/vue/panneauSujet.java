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
public class panneauSujet extends JPanel {

	private static final long serialVersionUID = 1L;
	private SaisieSujet saisie;
	private JLabel idText;
	private JTextField nomText;
	private JTextField titreText;

	/**
	 * Constructeur par défaut du panneauSujet qui appelle l'autre constructeur
	 * 
	 * @param pSaisie
	 * @param fP
	 */
	public panneauSujet(SaisieSujet pSaisie, final FenetreOPTI fP) {
		this(new JLabel(), new JTextField(1), new JTextField(1), pSaisie, fP);
	}

	/**
	 * Constructeur qui implémente la classe et qui crée le panneau pour la
	 * saisie d'un sujet.
	 * 
	 * @param pId
	 * @param pName
	 * @param pTitle
	 * @param pSaisie
	 * @param fP
	 */
	public panneauSujet(final JLabel pId, JTextField pName, JTextField pTitle,
			SaisieSujet pSaisie, final FenetreOPTI fP) {
		JPanel north = new JPanel(new GridLayout(4, 2));
		final String sID;

		JLabel id = new JLabel("ID :");
		JLabel nom = new JLabel("Nom :");
		JLabel titre = new JLabel("Titre :");
		JButton confirmation = new JButton("OK");
		idText = pId;
		nomText = pName;
		titreText = pTitle;
		saisie = pSaisie;
		sID = pId.getText();
		north.add(id);
		north.add(idText);
		north.add(nom);
		north.add(nomText);
		north.add(titre);
		north.add(titreText);
		north.add(confirmation);

		confirmation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (sID.equals(pId.getText())) {
					saisie.implementationSuj(idText, nomText, titreText);
				} else {
					int i;
					boolean trouve = false;
					for (i = 0; i < fP.getSujets().size(); i++) {
						if (pId.getText().equals(
								"" + fP.getSujets().get(i).getId())
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
						saisie.implementationSuj(idText, nomText, titreText);
					}
				}
			}
		});

		super.add(north, BorderLayout.NORTH);
	}

	/**
	 * Getter du JTextField de l'Id
	 * 
	 * @return JTextField
	 */
	public JLabel getIdText() {
		return idText;
	}

	/**
	 * Setter du JTextField de l'Id
	 * 
	 * @param idText
	 */
	public void setIdText(JLabel idText) {
		this.idText = idText;
	}

	/**
	 * Getter du JTextField titre
	 * 
	 * @return JTextField
	 */
	public JTextField getTitreText() {
		return titreText;
	}

	/**
	 * Setter du JTextField titre
	 * 
	 * @param titreText
	 */
	public void setTitreText(JTextField titreText) {
		this.titreText = titreText;
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
