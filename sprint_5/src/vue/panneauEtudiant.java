package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controle.*;

/**
 * Classe qui crée le panneau des saisies.
 * 
 * @author Alexandre GALLO
 * @author Cédric LESPAGNOL
 */
public class panneauEtudiant extends JPanel {

	private static final long serialVersionUID = 1L;
	private SaisieEtudiant saisie;
	private JTextField groupeText;
	private JLabel idText;
	private JTextField nomText;
	private JTextField prenomText;

	/**
	 * Constructeur par défaut du panneauSujet qui appelle l'autre constructeur
	 * 
	 * @param pSaisie
	 */
	public panneauEtudiant(SaisieEtudiant pSaisie) {
		this(new JTextField(1), new JLabel(), new JTextField(1),
				new JTextField(1), pSaisie);
	}

	/**
	 * Constructeur qui implémente la classe et qui crée le panneau pour la
	 * saisie d'un sujet.
	 * 
	 * @param pGroupe
	 * @param pId
	 * @param pName
	 * @param pPrenom
	 * @param pSaisie
	 */
	public panneauEtudiant(JTextField pGroupe, JLabel pId, JTextField pName,
			JTextField pPrenom, SaisieEtudiant pSaisie) {
		JPanel north = new JPanel(new GridLayout(5, 2));

		JLabel groupe = new JLabel("Groupe :");
		JLabel id = new JLabel("ID :");
		JLabel nom = new JLabel("Nom :");
		JLabel prenom = new JLabel("Prenom :");
		JButton confirmation = new JButton("OK");
		groupeText = pGroupe;
		idText = pId;
		nomText = pName;
		prenomText = pPrenom;
		saisie = pSaisie;

		north.add(groupe);
		north.add(groupeText);
		north.add(id);
		north.add(idText);
		north.add(nom);
		north.add(nomText);
		north.add(prenom);
		north.add(prenomText);
		north.add(confirmation);

		confirmation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saisie.implementationEtu(groupeText, idText, nomText,
						prenomText);

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
	 * @param titreText
	 */
	public void setPrenomText(JTextField titreText) {
		this.prenomText = titreText;
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
