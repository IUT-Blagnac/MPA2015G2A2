package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.tree.*;

import controle.*;
import modele.*;

/**
 * Classe FenetreOPTI qui génère l'interface graphique
 * 
 * @author Alexandre GALLO
 * @author Cédric LESPAGNOL
 */
public class FenetreOPTI extends JFrame {
	private static final long serialVersionUID = 1L;
	private DefaultMutableTreeNode root;
	private DefaultMutableTreeNode rootS;
	private DefaultMutableTreeNode rootE;
	private DefaultMutableTreeNode rootI;
	private DefaultMutableTreeNode rootP;
	private int iSuj;
	private int iEtu = 1;
	private int iInter = 1;
	private int iProj = 1;
	private int k = 0;
	private boolean sujetM;
	private boolean etuM;
	private boolean interM;
	private boolean projM;
	private JTree tree;
	private ArrayList<Sujet> Sujets = new ArrayList<Sujet>();
	private ArrayList<Etudiant> Etudiants = new ArrayList<Etudiant>();
	private ArrayList<Intervenant> Intervenants = new ArrayList<Intervenant>();
	private ArrayList<Projet> Projets = new ArrayList<Projet>();
	private JPanel panneau;
	private FenetreOPTI fp;

	/**
	 * Constructeur sans paramètre qui crée une fenetre ayant comme titre "OPTI"
	 */
	public FenetreOPTI() {

		super("OPTI");
		fp = this;
		sujetM = false;
		etuM = false;
		interM = false;
		projM = false;
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(e.getComponent(),
						"Etes-vous sur de vouloir quitter ?", "Confirmation",
						JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);
				}
			}
		});
		setSize(400, 500);
		panel();
		setContentPane(panneau);
		pack();
		setLocationRelativeTo(null);
		this.setVisible(true);

	}

	/**
	 * Méthode privée qui retourne le panneau (JPanel) de la fenetre.
	 * 
	 * @return JPanel
	 */
	private JPanel panel() {

		panneau = new JPanel();
		panneau.setLayout(new BorderLayout());
		this.add(panneau);
		JMenuBar JMB = menu();

		panneau.add(JMB, BorderLayout.NORTH);
		panneau.add(TreeSujets(), BorderLayout.WEST);
		return panneau;
	}

	/**
	 * Méthode privée qui crée le JMenuBar qui donne le menu
	 * 
	 * @return JMenuBar
	 */
	private JMenuBar menu() {
		JMenuBar JMB = new JMenuBar();
		JMenu sujet = new JMenu("Sujet");
		JMenu etu = new JMenu("Etudiant");
		JMenu inter = new JMenu("Intervenant");
		JMenu projet = new JMenu("Projet");
		JMenuItem sujetImport = new JMenuItem("Importer");
		sujetImport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ImporterSujet(fp);

			}
		});
		JMenuItem sujetCreer = new JMenuItem("Nouveau");
		sujetCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isSujetModifier() && !isEtuModifier()
						&& !isInterModifier()) {
					boolean trouve = false;
					if (Sujets.isEmpty() || Sujets.get(0).getId() > 1) {
						iSuj = 1;
					} else {
						for (int parcours = 1; parcours < Sujets.size()
								&& trouve == false; parcours++) {
							if (parcours + 1 < Sujets.get(parcours).getId()) {
								trouve = true;
								iSuj = parcours + 1;
							}
						}
						if (trouve == false) {
							iSuj = Sujets.size() + 1;
						}

					}
					Sujets.add(new Sujet(iSuj, "Nouveau Sujet " + iSuj,
							"Nouveau Sujet " + iSuj));
					rootS.removeAllChildren();
					TriSuj();
					for (int j = 0; j < Sujets.size(); j++) {
						rootS.add(new DefaultMutableTreeNode(Sujets.get(j)
								.getId() + " : " + Sujets.get(j).getNom()));
					}
					tree.updateUI();
					for (int j = 0; j < Sujets.size(); j++) {
						if (Sujets.get(j).getId() == iSuj) {
							k = j;
							SaisieSujet saisieTemp = new SaisieSujet(Sujets
									.get(j), fp);
							panneauSujet temp = saisieTemp.SaisieSuje();
							panneau.add(temp, BorderLayout.CENTER);
							setSujetM(true);
							panneau.updateUI();
						}
					}
				} else {
					JDialog fenetre = new JDialog();
					JLabel text = new JLabel(
							"Impossible de créer un sujet si vous modifiez quelque chose");
					fenetre.setTitle("Création");
					fenetre.add(text);
					fenetre.pack();
					fenetre.setLocationRelativeTo(null);
					fenetre.setModal(true);
					fenetre.setVisible(true);
				}
			}
		});
		JMenuItem sujetModif = new JMenuItem("Modifier");
		sujetModif.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isSujetModifier() && !isEtuModifier()
						&& !isInterModifier() && !isProjetModifier())
					modifierSujet();
				else {
					JDialog fenetre = new JDialog();
					JLabel text = new JLabel(
							"Impossible de supprimer un sujet si vous modifiez quelque chose");
					fenetre.setTitle("Suppression");
					fenetre.add(text);
					fenetre.pack();
					fenetre.setLocationRelativeTo(null);
					fenetre.setModal(true);
					fenetre.setVisible(true);
				}
			}
		});
		
		
		JMenuItem sujetSuppr = new JMenuItem("Supprimer");
		sujetSuppr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isSujetModifier() && !isEtuModifier()
						&& !isInterModifier())
					SupprimerSujet();
				else {
					JDialog fenetre = new JDialog();
					JLabel text = new JLabel(
							"Impossible de supprimer un sujet si vous modifiez quelque chose");
					fenetre.setTitle("Suppression");
					fenetre.add(text);
					fenetre.pack();
					fenetre.setLocationRelativeTo(null);
					fenetre.setModal(true);
					fenetre.setVisible(true);
				}

			}
		});
		JMenuItem sujetSave = new JMenuItem("Sauvegarder");
		sujetSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SauvegarderSujet(fp);

			}
		});
		JMenuItem etuImport = new JMenuItem("Importer");
		etuImport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ImporterEtudiant(fp);

			}
		});
		JMenuItem etuCreer = new JMenuItem("Nouveau");
		etuCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isSujetModifier() && !isEtuModifier()
						&& !isInterModifier()) {
					boolean trouve = false;
					if (Etudiants.isEmpty() || Etudiants.get(0).getId() > 1) {
						iEtu = 1;
					} else {
						for (int parcours = 1; parcours < Etudiants.size()
								&& trouve == false; parcours++) {
							if (parcours + 1 < Etudiants.get(parcours).getId()) {
								trouve = true;
								iEtu = parcours + 1;
							}
						}
						if (trouve == false) {
							iEtu = Etudiants.size() + 1;
						}

					}
					Etudiants.add(new Etudiant("X", iEtu, "Nouvel", "Etudiant "
							+ iEtu));
					rootE.removeAllChildren();
					TriEtu();
					for (int j = 0; j < Etudiants.size(); j++) {
						rootE.add(new DefaultMutableTreeNode(Etudiants.get(j)
								.getId()
								+ " : "
								+ Etudiants.get(j).getNom()
								+ " " + Etudiants.get(j).getPrenom()));
					}
					tree.updateUI();
					for (int j = 0; j < Etudiants.size(); j++) {
						if (Etudiants.get(j).getId() == iEtu) {
							k = j;
							SaisieEtudiant saisieTemp = new SaisieEtudiant(
									Etudiants.get(j), fp);
							panneauEtudiant temp = saisieTemp.SaisieEtu();
							panneau.add(temp, BorderLayout.CENTER);
							setEtuM(true);
							panneau.updateUI();
						}
					}
				} else {
					JDialog fenetre = new JDialog();
					JLabel text = new JLabel(
							"Impossible de créer un étudiant si vous modifiez quelque chose");
					fenetre.setTitle("Création");
					fenetre.add(text);
					fenetre.pack();
					fenetre.setLocationRelativeTo(null);
					fenetre.setModal(true);
					fenetre.setVisible(true);
				}
			}
		});
		JMenuItem etuSuppr = new JMenuItem("Supprimer");
		etuSuppr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isSujetModifier() && !isEtuModifier()
						&& !isInterModifier())
					SupprimerEtudiant();
				else {
					JDialog fenetre = new JDialog();
					JLabel text = new JLabel(
							"Impossible de supprimer un etudiant si vous modifiez quelque chose");
					fenetre.setTitle("Suppression");
					fenetre.add(text);
					fenetre.pack();
					fenetre.setLocationRelativeTo(null);
					fenetre.setModal(true);
					fenetre.setVisible(true);
				}

			}
		});
		JMenuItem etuSave = new JMenuItem("Sauvegarder");
		etuSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SauvegarderEtudiant(fp);

			}
		});

		JMenuItem interImport = new JMenuItem("Importer");
		interImport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ImporterIntervenant(fp);

			}
		});

		JMenuItem interCreer = new JMenuItem("Nouveau");
		interCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isSujetModifier() && !isEtuModifier()
						&& !isInterModifier()) {
					boolean trouve = false;
					if (Intervenants.isEmpty()
							|| Intervenants.get(0).getId() > 1) {
						iInter = 1;
					} else {
						for (int parcours = 1; parcours < Intervenants.size()
								&& trouve == false; parcours++) {
							if (parcours + 1 < Intervenants.get(parcours)
									.getId()) {
								trouve = true;
								iInter = parcours + 1;
							}
						}
						if (trouve == false) {
							iInter = Intervenants.size() + 1;
						}

					}
					Intervenants.add(new Intervenant(iInter, "Nouvel",
							"Intervenant " + iInter));
					rootI.removeAllChildren();
					TriInter();
					for (int j = 0; j < Intervenants.size(); j++) {
						rootI.add(new DefaultMutableTreeNode(Intervenants
								.get(j).getId()
								+ " : "
								+ Intervenants.get(j).getPrenom()
								+ " "
								+ Intervenants.get(j).getNom()));
					}
					tree.updateUI();
					for (int j = 0; j < Intervenants.size(); j++) {
						if (Intervenants.get(j).getId() == iInter) {
							k = j;
							SaisieIntervenant saisieTemp = new SaisieIntervenant(
									Intervenants.get(j), fp);
							panneauIntervenant temp = saisieTemp.SaisieInter();
							panneau.add(temp, BorderLayout.CENTER);
							setInterM(true);
							panneau.updateUI();
						}
					}
				} else {
					JDialog fenetre = new JDialog();
					JLabel text = new JLabel(
							"Impossible de créer un intervenant si vous modifiez quelque chose");
					fenetre.setTitle("Création");
					fenetre.add(text);
					fenetre.pack();
					fenetre.setLocationRelativeTo(null);
					fenetre.setModal(true);
					fenetre.setVisible(true);
				}
			}
		});


		JMenuItem interSuppr = new JMenuItem("Supprimer");
		interSuppr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isSujetModifier() && !isEtuModifier()
						&& !isInterModifier() && !isProjetModifier())
					SupprimerIntervenant();
				else {
					JDialog fenetre = new JDialog();
					JLabel text = new JLabel(
							"Impossible de supprimer un intervenant si vous modifiez quelque chose");
					fenetre.setTitle("Suppression");
					fenetre.add(text);
					fenetre.pack();
					fenetre.setLocationRelativeTo(null);
					fenetre.setModal(true);
					fenetre.setVisible(true);
				}

			}
		});

		JMenuItem interSave = new JMenuItem("Sauvegarder");
		interSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SauvegarderIntervenant(fp);

			}
		});
		JMenuItem projetImpor = new JMenuItem("Importer");
		projetImpor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ImporterProjet(fp);

			}
		});
		JMenuItem projetCreer = new JMenuItem("Nouveau");
		projetCreer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isSujetModifier() && !isEtuModifier()
						&& !isInterModifier() && !isProjetModifier()) {
					boolean trouve = false;
					if (Projets.isEmpty() || Projets.get(0).getId() > 1) {
						iProj = 1;
					} else {
						for (int parcours = 1; parcours < Projets.size()
								&& trouve == false; parcours++) {
							if (parcours + 1 < Projets.get(parcours).getId()) {
								trouve = true;
								iProj = parcours + 1;
							}
						}
						if (trouve == false) {
							iProj = Projets.size() + 1;
						}

					}
					Projets.add(new Projet(iProj, new Groupe("X", null, null),
							new Sujet(0, null, null), new Intervenant(0, null,
									null), new Intervenant(0, null, null)));
					rootP.removeAllChildren();
					TriProj();
					for (int j = 0; j < Projets.size(); j++) {
						rootP.add(new DefaultMutableTreeNode("Projet "
								+ Projets.get(j).getId()));
					}
					tree.updateUI();
					for (int j = 0; j < Projets.size(); j++) {
						if (Projets.get(j).getId() == iProj) {
							k = j;
							SaisieProjet saisieTemp = new SaisieProjet(Projets
									.get(j), fp);
							panneauProjet temp = saisieTemp.SaisieProj();
							panneau.add(temp, BorderLayout.CENTER);
							setProjM(true);
							panneau.updateUI();
						}
					}
				} else {
					JDialog fenetre = new JDialog();
					JLabel text = new JLabel(
							"Impossible de créer un projet si vous modifiez quelque chose");
					fenetre.setTitle("Création");
					fenetre.add(text);
					fenetre.pack();
					fenetre.setLocationRelativeTo(null);
					fenetre.setModal(true);
					fenetre.setVisible(true);
				}

			}
		});
		JMenuItem projetModif = new JMenuItem("Modifier");
		projetModif.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isSujetModifier() && !isEtuModifier()
						&& !isInterModifier() && !isProjetModifier())
					ModifierProjet();
				else {
					JDialog fenetre = new JDialog();
					JLabel text = new JLabel(
							"Impossible de modifier un projet si vous modifiez quelque chose");
					fenetre.setTitle("Suppression");
					fenetre.add(text);
					fenetre.pack();
					fenetre.setLocationRelativeTo(null);
					fenetre.setModal(true);
					fenetre.setVisible(true);
				}
			}
		});
		JMenuItem projetSuppr = new JMenuItem("Supprimer");
		projetSuppr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isSujetModifier() && !isEtuModifier()
						&& !isInterModifier() && !isProjetModifier())
					SupprimerProjet();
				else {
					JDialog fenetre = new JDialog();
					JLabel text = new JLabel(
							"Impossible de supprimer un projet si vous modifiez quelque chose");
					fenetre.setTitle("Suppression");
					fenetre.add(text);
					fenetre.pack();
					fenetre.setLocationRelativeTo(null);
					fenetre.setModal(true);
					fenetre.setVisible(true);
				}

			}
		});
		JMenuItem projetSave = new JMenuItem("Sauvegarder");
		projetSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SauvegarderProjet(fp);

			}
		});

		sujet.add(sujetImport);
		sujet.add(sujetCreer);
		sujet.add(sujetModif);
		sujet.add(sujetSuppr);
		sujet.add(sujetSave);

		etu.add(etuImport);
		etu.add(etuCreer);
		etu.add(etuSuppr);
		etu.add(etuSave);

		inter.add(interImport);
		inter.add(interCreer);
		inter.add(interSuppr);
		inter.add(interSave);

		projet.add(projetImpor);
		projet.add(projetCreer);
		projet.add(projetModif);
		projet.add(projetSuppr);
		projet.add(projetSave);

		JMenuItem boutonAP = new JMenuItem("A propos");
		boutonAP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				APropos();
			}
		});
		JMB.add(sujet);
		JMB.add(etu);
		JMB.add(inter);
		JMB.add(projet);
		JMB.add(boutonAP);

		JMenuItem boutonQu = new JMenuItem("Quitter");
		boutonQu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmation = JOptionPane
						.showConfirmDialog(new JFrame(),
								"Etes-vous sur de vouloir quitter ?",
								"Confirmation", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
				if (confirmation == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		JMB.add(boutonQu);

		return JMB;
	}

	/**
	 * Méthode privée qui crée le "Tree"
	 * 
	 * @return JScrollPane
	 */
	private JScrollPane TreeSujets() {

		root = new DefaultMutableTreeNode("PTUT");
		rootS = new DefaultMutableTreeNode("Sujets");
		rootE = new DefaultMutableTreeNode("Etudiants");
		rootI = new DefaultMutableTreeNode("Intervenants");
		rootP = new DefaultMutableTreeNode("Projets");
		root.add(rootS);
		root.add(rootE);
		root.add(rootI);
		root.add(rootP);

		for (int i = 0; i < Sujets.size(); i++) {
			rootS.add(new DefaultMutableTreeNode(Sujets.get(i).getNom()));
		}
		tree = new JTree(root);
		JScrollPane scrollPane = new JScrollPane(tree);
		return scrollPane;
	}

	/**
	 * Méthode privée qui crée la fenetre ayant pour titre "A propos"
	 */
	private void APropos() {

		JDialog fenetre = new JDialog(this, "A Propos");
		JLabel text = new JLabel(
				"<html><body>Groupe : 2A2<br/>Membres :<br/>    <ul><li>Loïc Lemonsu</li>    <li>Alexandre Gallo Gonzalez</li>   <li>Cédric Lespagnol</li>    <li>Baptiste Larre</li>    <li>Thibault Crubille</li>    <li>Denis Nguyen</li></ul><br/>Université Toulouse 2<br/><br/>IUT de Blagnac<br/><br/>DUT INFO S3/Module MPA<br/><br/>Projet OPTI</body></html>",
				JLabel.CENTER);
		fenetre.add(text);
		fenetre.setSize(300, 400);
		fenetre.setLocationRelativeTo(null);
		fenetre.setModal(true);
		fenetre.setVisible(true);

	}

	/**
	 * Méthode privée qui crée la fenetre JDialog pour séléctionner un sujet à
	 * modifier
	 */
	private void modifierSujet() {
		if (!isSujetModifier() || !isEtuModifier()) {
			final JDialog f = new JDialog(this, "Modifier");
			JPanel panelG = new JPanel(new GridLayout(Sujets.size(), 1));
			JPanel panelB = new JPanel(new FlowLayout());
			int i;
			ButtonGroup groupSubject = new ButtonGroup();
			final ArrayList<JRadioButton> cases = new ArrayList<JRadioButton>();
			for (i = 0; i < Sujets.size(); i++) {
				cases.add(new JRadioButton(Sujets.get(i).getId() + " : "
						+ Sujets.get(i).getNom()));
				groupSubject.add(cases.get(i));
				panelG.add(cases.get(i));
			}
			JButton apply = new JButton("OK");
			apply.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int j;
					for (j = 0; j < cases.size(); j++) {
						if (cases.get(j).isSelected()) {
							k = j;
							SaisieSujet saisieTemp = new SaisieSujet(Sujets
									.get(j), fp);
							panneauSujet temp = saisieTemp.SaisieSuje();
							panneau.add(temp, BorderLayout.CENTER);
							setSujetM(true);
							panneau.updateUI();
							f.dispose();
						}
					}

				}
			});

			JButton cancel = new JButton("Annuler");
			cancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					f.dispose();
				}
			});

			panelB.add(apply);
			panelB.add(cancel);
			f.add(panelG, BorderLayout.CENTER);
			f.add(panelB, BorderLayout.SOUTH);
			f.setLocationRelativeTo(null);
			f.setModal(true);
			f.pack();
			f.setVisible(true);

		} else {
			JDialog fenetre = new JDialog();
			JLabel text = new JLabel(
					"Impossible de modifier un autre sujet si vous modifiez quelque chose");
			fenetre.setTitle("Modification");
			fenetre.add(text);
			fenetre.pack();
			fenetre.setLocationRelativeTo(null);
			fenetre.setModal(true);
			fenetre.setVisible(true);
		}
	}

	/**
	 * Méthode privée qui crée la fenetre JDialog pour séléctionner des sujets à
	 * supprimer
	 */
	private void SupprimerSujet() {
		final JDialog f = new JDialog(this, "Supprimer");
		JPanel panelG = new JPanel(new GridLayout(15, Sujets.size() / 15));
		JPanel panelB = new JPanel(new FlowLayout());
		JButton delete = new JButton("Supprimer");
		int i;
		final ArrayList<JCheckBox> cases = new ArrayList<JCheckBox>();
		for (i = 0; i < Sujets.size(); i++) {
			cases.add(new JCheckBox(Sujets.get(i).getId() + " : "
					+ Sujets.get(i).getNom()));
			panelG.add(cases.get(i));
		}

		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean suppr = false;
				int j;
				for (j = 0; j < Sujets.size() && suppr == false; j++) {
					if (cases.get(j).isSelected()) {
						suppr = true;
					}
				}
				if (suppr == true) {
					int confirmation = JOptionPane.showConfirmDialog(
							new JFrame(),
							"Voulez-vous vraiment supprimer ces éléments ?",
							"Supprimer ?", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (confirmation == JOptionPane.YES_OPTION) {
						for (j = 0; j < Sujets.size(); j++) {
							if (cases.get(j).isSelected()) {
								Sujets.remove(j);
								cases.remove(j);
								j--;
							}
						}
						rootS.removeAllChildren();
						for (j = 0; j < Sujets.size(); j++) {
							rootS.add(new DefaultMutableTreeNode(Sujets.get(j)
									.getNom()));
						}
						tree.updateUI();
						f.dispose();
					}
				}
			}
		});
		JButton cancel = new JButton("Annuler");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});

		panelB.add(delete);
		panelB.add(cancel);
		f.add(panelG, BorderLayout.CENTER);
		f.add(panelB, BorderLayout.SOUTH);
		f.setLocationRelativeTo(null);
		f.setModal(true);
		f.pack();
		f.setVisible(true);

	}

	private void SupprimerEtudiant() {
		final JDialog f = new JDialog(this, "Supprimer");
		JPanel panelG = new JPanel(new GridLayout(15, Etudiants.size() / 15));
		JPanel panelB = new JPanel(new FlowLayout());
		JButton delete = new JButton("Supprimer");
		int i;
		final ArrayList<JCheckBox> cases = new ArrayList<JCheckBox>();
		for (i = 0; i < Etudiants.size(); i++) {
			cases.add(new JCheckBox(Etudiants.get(i).getId() + " : "
					+ Etudiants.get(i).getNom() + " "
					+ Etudiants.get(i).getPrenom()));
			panelG.add(cases.get(i));
		}

		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean suppr = false;
				int j;
				for (j = 0; j < Etudiants.size() && suppr == false; j++) {
					if (cases.get(j).isSelected()) {
						suppr = true;
					}
				}
				if (suppr == true) {
					int confirmation = JOptionPane.showConfirmDialog(
							new JFrame(),
							"Voulez-vous vraiment supprimer ces éléments ?",
							"Supprimer ?", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (confirmation == JOptionPane.YES_OPTION) {
						for (j = 0; j < Etudiants.size(); j++) {
							if (cases.get(j).isSelected()) {
								Etudiants.remove(j);
								cases.remove(j);
								j--;
							}
						}
						rootE.removeAllChildren();
						for (j = 0; j < Etudiants.size(); j++) {
							rootE.add(new DefaultMutableTreeNode(Etudiants.get(
									j).getNom()
									+ " " + Etudiants.get(j).getPrenom()));
						}
						tree.updateUI();
						f.dispose();
					}
				}
			}
		});
		JButton cancel = new JButton("Annuler");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});

		panelB.add(delete);
		panelB.add(cancel);
		f.add(panelG, BorderLayout.CENTER);
		f.add(panelB, BorderLayout.SOUTH);
		f.setLocationRelativeTo(null);
		f.setModal(true);
		f.pack();
		f.setVisible(true);

	}

	private void SupprimerIntervenant() {
		final JDialog f = new JDialog(this, "Supprimer");
		JPanel panelG = new JPanel(new GridLayout(15, Intervenants.size() / 15));
		JPanel panelB = new JPanel(new FlowLayout());
		JButton delete = new JButton("Supprimer");
		int i;
		final ArrayList<JCheckBox> cases = new ArrayList<JCheckBox>();
		for (i = 0; i < Intervenants.size(); i++) {
			cases.add(new JCheckBox(Intervenants.get(i).getId() + " : "
					+ Intervenants.get(i).getNom()));
			panelG.add(cases.get(i));
		}

		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean suppr = false;
				int j;
				for (j = 0; j < Intervenants.size() && suppr == false; j++) {
					if (cases.get(j).isSelected()) {
						suppr = true;
					}
				}
				if (suppr == true) {
					int confirmation = JOptionPane.showConfirmDialog(
							new JFrame(),
							"Voulez-vous vraiment supprimer ces éléments ?",
							"Supprimer ?", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (confirmation == JOptionPane.YES_OPTION) {
						for (j = 0; j < Intervenants.size(); j++) {
							if (cases.get(j).isSelected()) {
								Intervenants.remove(j);
								cases.remove(j);
								j--;
							}
						}
						rootI.removeAllChildren();
						for (j = 0; j < Intervenants.size(); j++) {
							rootI.add(new DefaultMutableTreeNode(Intervenants
									.get(j).getNom()
									+ " "
									+ Intervenants.get(j).getPrenom()));
						}
						tree.updateUI();
						f.dispose();
					}
				}
			}
		});
		JButton cancel = new JButton("Annuler");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});

		panelB.add(delete);
		panelB.add(cancel);
		f.add(panelG, BorderLayout.CENTER);
		f.add(panelB, BorderLayout.SOUTH);
		f.setLocationRelativeTo(null);
		f.setModal(true);
		f.pack();
		f.setVisible(true);

	}

	public void ModifierProjet() {
		if (!isSujetModifier() && !isEtuModifier() && !isInterModifier()
				&& !isProjetModifier()) {
			final JDialog f = new JDialog(this, "Modifier");
			JPanel panelG = new JPanel(new GridLayout(Projets.size(), 1));
			JPanel panelB = new JPanel(new FlowLayout());
			int i;
			ButtonGroup groupProjet = new ButtonGroup();
			final ArrayList<JRadioButton> cases = new ArrayList<JRadioButton>();
			for (i = 0; i < Projets.size(); i++) {
				cases.add(new JRadioButton("Projet " + Projets.get(i).getId()));
				groupProjet.add(cases.get(i));
				panelG.add(cases.get(i));
			}
			JButton apply = new JButton("OK");
			apply.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int j;
					for (j = 0; j < cases.size(); j++) {
						if (cases.get(j).isSelected()) {
							k = j;
							SaisieProjet saisieTemp = new SaisieProjet(Projets
									.get(j), fp);
							panneauProjet temp = saisieTemp.SaisieProj();
							panneau.add(temp, BorderLayout.CENTER);
							setProjM(true);
							panneau.updateUI();
							f.dispose();
						}
					}

				}
			});

			JButton cancel = new JButton("Annuler");
			cancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					f.dispose();
				}
			});

			panelB.add(apply);
			panelB.add(cancel);
			f.add(panelG, BorderLayout.CENTER);
			f.add(panelB, BorderLayout.SOUTH);
			f.setLocationRelativeTo(null);
			f.setModal(true);
			f.pack();
			f.setVisible(true);

		} else {
			JDialog fenetre = new JDialog();
			JLabel text = new JLabel(
					"Impossible de modifier un autre sujet si vous modifiez quelque chose");
			fenetre.setTitle("Modification");
			fenetre.add(text);
			fenetre.pack();
			fenetre.setLocationRelativeTo(null);
			fenetre.setModal(true);
			fenetre.setVisible(true);
		}
	}

	private void SupprimerProjet() {
		final JDialog f = new JDialog(this, "Supprimer");
		JPanel panelG = new JPanel(new GridLayout(15, Projets.size() / 15));
		JPanel panelB = new JPanel(new FlowLayout());
		JButton delete = new JButton("Supprimer");
		int i;
		final ArrayList<JCheckBox> cases = new ArrayList<JCheckBox>();
		for (i = 0; i < Projets.size(); i++) {
			cases.add(new JCheckBox("Projets " + Projets.get(i).getId()));
			panelG.add(cases.get(i));
		}

		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean suppr = false;
				int j;
				for (j = 0; j < Projets.size() && suppr == false; j++) {
					if (cases.get(j).isSelected()) {
						suppr = true;
					}
				}
				if (suppr == true) {
					int confirmation = JOptionPane.showConfirmDialog(
							new JFrame(),
							"Voulez-vous vraiment supprimer ces éléments ?",
							"Supprimer ?", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (confirmation == JOptionPane.YES_OPTION) {
						for (j = 0; j < Projets.size(); j++) {
							if (cases.get(j).isSelected()) {
								Projets.remove(j);
								cases.remove(j);
								j--;
							}
						}
						rootP.removeAllChildren();
						for (j = 0; j < Projets.size(); j++) {
							rootP.add(new DefaultMutableTreeNode("Projet "
									+ Projets.get(j).getId()));
						}
						tree.updateUI();
						f.dispose();
					}
				}
			}
		});
		JButton cancel = new JButton("Annuler");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});

		panelB.add(delete);
		panelB.add(cancel);
		f.add(panelG, BorderLayout.CENTER);
		f.add(panelB, BorderLayout.SOUTH);
		f.setLocationRelativeTo(null);
		f.setModal(true);
		f.pack();
		f.setVisible(true);
	}

	public void TriEtu() {
		Collections.sort(Etudiants, new Comparator<Etudiant>() {

			@Override
			public int compare(Etudiant o1, Etudiant o2) {
				int nombre1 = o1.getId();
				int nombre2 = o2.getId();
				if (nombre1 < nombre2)
					return -1;
				else if (nombre1 == nombre2)
					return 0;
				else
					return 1;
			}

		});
	}

	public void TriInter() {
		Collections.sort(Intervenants, new Comparator<Intervenant>() {
			@Override
			public int compare(Intervenant o1, Intervenant o2) {

				int nombre1 = o1.getId();
				int nombre2 = o2.getId();
				if (nombre1 < nombre2)
					return -1;
				else if (nombre1 == nombre2)
					return 0;
				else
					return 1;
			}
		});
	}

	public void TriSuj() {
		Collections.sort(Sujets, new Comparator<Sujet>() {

			@Override
			public int compare(Sujet o1, Sujet o2) {
				int nombre1 = o1.getId();
				int nombre2 = o2.getId();
				if (nombre1 < nombre2)
					return -1;
				else if (nombre1 == nombre2)
					return 0;
				else
					return 1;
			}
		});
	}

	public void TriProj() {
		Collections.sort(Projets, new Comparator<Projet>() {

			@Override
			public int compare(Projet o1, Projet o2) {
				int nombre1 = o1.getId();
				int nombre2 = o2.getId();
				if (nombre1 < nombre2)
					return -1;
				else if (nombre1 == nombre2)
					return 0;
				else
					return 1;
			}
		});
	}

	public boolean isSujetModifier() {
		return sujetM;
	}

	public void setSujetM(boolean pSujetM) {
		this.sujetM = pSujetM;
	}

	public boolean isEtuModifier() {
		return etuM;
	}

	public void setEtuM(boolean pEtuM) {
		this.etuM = pEtuM;
	}

	public boolean isInterModifier() {
		return interM;
	}

	public void setInterM(boolean pInterM) {
		this.interM = pInterM;
	}

	public boolean isProjetModifier() {
		return projM;
	}

	public void setProjM(boolean pProjM) {
		this.projM = pProjM;
	}

	/**
	 * Setter d'un sujet de l'arraylist particulier définie avec "k"
	 * 
	 * @param pSubject
	 */
	public void setSujetAModifier(Sujet pSubject) {
		Sujets.set(k, pSubject);
	}

	/**
	 * Getter de root
	 * 
	 * @return DefaultMutableTreeNode
	 */
	public DefaultMutableTreeNode getRootS() {
		return rootS;
	}

	public DefaultMutableTreeNode getRootP() {
		return rootP;
	}

	/**
	 * Getter de tree
	 * 
	 * @return JTree
	 */
	public JTree getTree() {
		return tree;
	}

	/**
	 * Getter de Sujets
	 * 
	 * @return ArrayList<Sujet>
	 */
	public ArrayList<Sujet> getSujets() {
		return Sujets;
	}

	public void setEtudiantAModifier(Etudiant pEtu) {
		Etudiants.set(k, pEtu);

	}

	public void setInterAModifier(Intervenant pInter) {
		Intervenants.set(k, pInter);

	}

	public void setProjetAModifier(Projet pProjet) {
		Projets.set(k, pProjet);
	}

	public ArrayList<Projet> getProjets() {
		return Projets;
	}

	public ArrayList<Etudiant> getEtudiants() {
		return this.Etudiants;
	}

	public DefaultMutableTreeNode getRootE() {
		return rootE;
	}

	public ArrayList<Intervenant> getIntervenants() {
		return this.Intervenants;
	}

	public DefaultMutableTreeNode getRootI() {
		return rootI;
	}

	public int getK() {
		return k;
	}

}
