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
 * Class qui crée le panneau des saisies.
 * 
 * @author Baptiste LARRE utilisation du code de panneauSujet
 */
public class panneauProjet extends JPanel {

	private static final long serialVersionUID = 1L;
	private SaisieProjet saisie;
	private JLabel idText;
	private JTextField groupeText;
	private JTextField sujetText;
	private JTextField clientText;
	private JTextField superviseurText;

	/**
	 * Constructeur par défaut du panneauProjet qui appelle l'autre constructeur
	 * 
	 * @param pSaisie
	 */
	public panneauProjet(SaisieProjet pSaisie) {
		this(new JLabel(), new JTextField(1), new JTextField(1),
				new JTextField(1), new JTextField(1), pSaisie);
	}

	/**
	 * Constructeur qui implémente la class et qui créer le panneau pour la
	 * saisie d'un projet.
	 * 
	 * @param pId
	 * @param pGroupe
	 * @param pSujet
	 * @param pClient
	 * @param pSuperviseur
	 * @param pSaisie
	 */
	public panneauProjet(JLabel pId, JTextField pGroupe, JTextField pSujet,
			JTextField pClient, JTextField pSuperviseur, SaisieProjet pSaisie) {
		JPanel north = new JPanel(new GridLayout(6, 2));

		JLabel groupe = new JLabel("Groupe :");
		JLabel id = new JLabel("ID :");
		JLabel sujet = new JLabel("Sujet :");
		JLabel client = new JLabel("Client :");
		JLabel superviseur = new JLabel("Superviseur :");
		JButton confirmation = new JButton("OK");
		groupeText = pGroupe;
		idText = pId;
		sujetText = pSujet;
		clientText = pClient;
		superviseurText = pSuperviseur;
		saisie = pSaisie;

		north.add(groupe);
		north.add(groupeText);
		north.add(id);
		north.add(idText);
		north.add(sujet);
		north.add(sujetText);
		north.add(client);
		north.add(clientText);
		north.add(superviseur);
		north.add(superviseurText);
		north.add(confirmation);

		confirmation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saisie.implementationProjet(idText, groupeText, sujetText,
						clientText, superviseurText);

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
	 */
	public void setIdText(JLabel idText) {
		this.idText = idText;
	}

	/**
	 * Getter du JTextField groupe
	 * 
	 * @return JTextField
	 */
	public JTextField getGroupeText() {
		return groupeText;
	}

	/**
	 * Setter du JTextField groupe
	 */
	public void setGroupeText(JTextField groupeText) {
		this.groupeText = groupeText;
	}

	/**
	 * Getter du JTextField sujet
	 * 
	 * @return JTextField
	 */
	public JTextField getSujetText() {
		return sujetText;
	}

	/**
	 * Setter du JTextField sujet
	 */
	public void setNomText(JTextField sujetText) {
		this.sujetText = sujetText;
	}

	/**
	 * Getter du JTextField client
	 * 
	 * @return JTextField
	 */
	public JTextField getClientText() {
		return clientText;
	}

	/**
	 * Setter du JTextField client
	 */
	public void setClientText(JTextField clientText) {
		this.clientText = clientText;
	}

	/**
	 * Getter du JTextField superviseur
	 * 
	 * @return JTextField
	 */
	public JTextField getSuperviseurText() {
		return superviseurText;
	}

	/**
	 * Setter du JTextField superviseur
	 */
	public void setSuperviseurText(JTextField superviseurText) {
		this.superviseurText = superviseurText;
	}
}
