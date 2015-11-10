package vue;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import controle.*;

/**
 * Classe SauvegarderSujet qui permet d'extraire une liste d'étudiant pour creer
 * un fichier csv
 * 
 * @author Alexandre
 *
 */
public class SauvegarderIntervenant {
	private File file;

	/**
	 * Constructeur.<br>
	 * <br>
	 * Creer la fenetre correspondant à l'export.
	 * 
	 * @param pFOPTI
	 * 
	 */
	public SauvegarderIntervenant(final FenetreOPTI pFOPTI) {

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

		if (parcourir.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = new File(parcourir.getSelectedFile().getAbsolutePath());
			if (file.exists()) {
				file.delete();
			}
			int i;
			ArrayList<String[]> stringTemp = new ArrayList<String[]>();
			String[] test = { "id", "prenom", "nom" };
			stringTemp.add(test.clone());
			for (i = 0; i < pFOPTI.getIntervenants().size(); i++) {
				test[0] = new Integer(pFOPTI.getIntervenants().get(i).getId())
						.toString();
				test[1] = pFOPTI.getIntervenants().get(i).getPrenom();
				test[2] = pFOPTI.getIntervenants().get(i).getNom();
				stringTemp.add(test.clone());
			}

			String[][] temp = stringTemp.toArray(new String[0][0]);
			String name = parcourir.getSelectedFile().getAbsolutePath();
			if (!name.endsWith(".csv"))
				name += ".csv";
			for (String[] ligne : temp) {
				CsvRW.ecrire(name, ligne, true);
			}
			parcourir.setVisible(false);

		} else {
			parcourir.setVisible(false);
		}

	}
}
