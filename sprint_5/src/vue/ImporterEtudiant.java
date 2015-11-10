package vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;

import modele.*;
import controle.*;

/**
 * Clasee Importer qui correspond à la fenetre afficher lors de la creation du
 * html par import.
 * 
 * @author Alexandre
 *
 */
public class ImporterEtudiant {

	/**
	 * Constructeur Non Paramétré.<br>
	 * <br>
	 * Creer la fenetre correspondant à l'import.
	 * 
	 * @param pFOPTI
	 */
	public ImporterEtudiant(final FenetreOPTI pFOPTI) {

		final JDialog fenetre = new JDialog(pFOPTI, "Importer etudiants");
		fenetre.setModal(true);
		fenetre.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		final JFileChooser parcourir = new JFileChooser();
		parcourir.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "Fichier csv";
			}

			@Override
			public boolean accept(File arg0) {
				if (arg0.isDirectory())
					return true;
				else if (arg0.getName().endsWith(".csv"))
					return true;
				else
					return false;
			}
		});
		parcourir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("ApproveSelection")) {
					int j;
					String[] etusATraiter = CsvRW.lecture(parcourir
							.getSelectedFile().getPath());
					String[] stringTemp;
					Etudiant etuTemp;
					ArrayList<Etudiant> alTemp = new ArrayList<Etudiant>();

					for (j = 1; j < etusATraiter.length; j++) {
						stringTemp = etusATraiter[j].split(";");
						etuTemp = new Etudiant(stringTemp[0], new Integer(
								stringTemp[1]), stringTemp[2], stringTemp[3]);
						alTemp.add(etuTemp);
					}
					if (verifId(alTemp, pFOPTI) == -1) {
						pFOPTI.getEtudiants().addAll(alTemp);
						pFOPTI.TriEtu();
						pFOPTI.getRootE().removeAllChildren();
						for (j = 0; j < pFOPTI.getEtudiants().size(); j++) {
							pFOPTI.getRootE().add(
									new DefaultMutableTreeNode(pFOPTI
											.getEtudiants().get(j).getId()
											+ " : "
											+ pFOPTI.getEtudiants().get(j)
													.getNom()
											+ " "
											+ pFOPTI.getEtudiants().get(j)
													.getPrenom()));
						}
						pFOPTI.getTree().updateUI();
					} else {
						JDialog f = new JDialog();
						JLabel text = new JLabel(
								"Impossible d'importer cette liste, l'id "
										+ verifId(alTemp, pFOPTI)
										+ " est déjà utilisé");
						f.setTitle("Erreur");
						f.add(text);
						f.pack();
						f.setLocationRelativeTo(null);
						f.setModal(true);
						f.setVisible(true);
					}

					fenetre.dispose();

				} else {
					fenetre.dispose();
				}
			}
		});

		fenetre.add(parcourir, BorderLayout.CENTER);
		fenetre.pack();
		fenetre.setVisible(true);

	}

	private int verifId(ArrayList<Etudiant> alTemp, final FenetreOPTI pFOPTI) {
		alTemp.addAll(pFOPTI.getEtudiants());
		Collections.sort(alTemp, new Comparator<Etudiant>() {

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
		for (int i = 0; i < alTemp.size() - 2; i++) {
			if (alTemp.get(i).getId() == alTemp.get(i + 1).getId()) {
				return alTemp.get(i).getId();
			}
		}
		return -1;
	}

}
