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
import controle.CsvRW;

/**
 * Classe Importer qui correspond à la fenetre afficher lors de la creation du
 * html par import.
 * 
 * @author Cédric
 *
 */

public class ImporterIntervenant {

	/**
	 * Constructeur Non Paramétré.<br>
	 * <br>
	 * Creer la fenetre correspondant à l'import.
	 * 
	 * @param pFOPTI
	 */
	public ImporterIntervenant(final FenetreOPTI pFOPTI) {

		final JDialog fenetre = new JDialog(pFOPTI, "Importer intervenant");
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
					String[] intersATraiter = CsvRW.lecture(parcourir
							.getSelectedFile().getPath());
					String[] stringTemp;
					Intervenant interTemp;
					ArrayList<Intervenant> alTemp = new ArrayList<Intervenant>();

					for (j = 1; j < intersATraiter.length; j++) {
						stringTemp = intersATraiter[j].split(";");
						interTemp = new Intervenant(new Integer(stringTemp[0]),
								stringTemp[1], stringTemp[2]);
						alTemp.add(interTemp);
					}

					if (verifId(alTemp, pFOPTI) == -1) {
						pFOPTI.getIntervenants().addAll(alTemp);
						pFOPTI.TriInter();
						pFOPTI.getRootI().removeAllChildren();
						for (j = 0; j < pFOPTI.getIntervenants().size(); j++) {
							pFOPTI.getRootI().add(
									new DefaultMutableTreeNode(pFOPTI
											.getIntervenants().get(j).getId()
											+ " : "
											+ pFOPTI.getIntervenants().get(j)
													.getPrenom()
											+ " "
											+ pFOPTI.getIntervenants().get(j)
													.getNom()));
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

	private int verifId(ArrayList<Intervenant> alTemp, final FenetreOPTI pFOPTI) {
		alTemp.addAll(pFOPTI.getIntervenants());
		Collections.sort(alTemp, new Comparator<Intervenant>() {

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
		for (int i = 0; i < alTemp.size() - 2; i++) {
			if (alTemp.get(i).getId() == alTemp.get(i + 1).getId()) {
				return alTemp.get(i).getId();
			}
		}
		return -1;
	}

}
