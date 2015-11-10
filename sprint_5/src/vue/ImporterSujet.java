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

import modele.Sujet;
import controle.*;

/**
 * Clasee Importer qui correspond à la fenetre afficher lors de la creation du
 * html par import.
 * 
 * @author Alexandre
 *
 */
public class ImporterSujet {

	/**
	 * Constructeur Non Paramétré.<br>
	 * <br>
	 * Creer la fenetre correspondant à l'import.
	 * 
	 * @param pFOPTI
	 */
	public ImporterSujet(final FenetreOPTI pFOPTI) {

		final JDialog fenetre = new JDialog(pFOPTI, "Importer sujets");
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
					String[] sujetSATraiter = CsvRW.lecture(parcourir
							.getSelectedFile().getPath());
					String[] stringTemp;
					Sujet sujetTemp;
					ArrayList<Sujet> alTemp = new ArrayList<Sujet>();

					for (j = 1; j < sujetSATraiter.length; j++) {
						stringTemp = sujetSATraiter[j].split(";");
						sujetTemp = new Sujet(new Integer(stringTemp[0]),
								stringTemp[1], stringTemp[2]);
						alTemp.add(sujetTemp);
					}

					if (verifId(alTemp, pFOPTI) == -1) {
						pFOPTI.getSujets().addAll(alTemp);
						pFOPTI.TriSuj();
						pFOPTI.getRootS().removeAllChildren();
						for (j = 0; j < pFOPTI.getSujets().size(); j++) {
							pFOPTI.getRootS().add(
									new DefaultMutableTreeNode((pFOPTI
											.getSujets().get(j).getId()
											+ " : " + pFOPTI.getSujets().get(j)
											.getNom())));
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

	private int verifId(ArrayList<Sujet> alTemp, final FenetreOPTI pFOPTI) {
		alTemp.addAll(pFOPTI.getSujets());
		Collections.sort(alTemp, new Comparator<Sujet>() {

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
		for (int i = 0; i < alTemp.size() - 2; i++) {
			if (alTemp.get(i).getId() == alTemp.get(i + 1).getId()) {
				return alTemp.get(i).getId();
			}
		}
		return -1;
	}
}
