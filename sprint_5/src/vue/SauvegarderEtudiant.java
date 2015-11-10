package vue;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import controle.*;

public class SauvegarderEtudiant {
	private File file;

	/**
	 * Constructeur.<br>
	 * <br>
	 * Crée la fenetre correspondant à l'export.
	 * 
	 * @param pFOPTI
	 */
	public SauvegarderEtudiant(final FenetreOPTI pFOPTI) {

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
			String[] test = { "groupe", "id", "nom", "prenom" };
			stringTemp.add(test.clone());
			for (i = 0; i < pFOPTI.getEtudiants().size(); i++) {
				test[0] = pFOPTI.getEtudiants().get(i).getGroupe();
				test[1] = new Integer(pFOPTI.getEtudiants().get(i).getId())
						.toString();
				test[2] = pFOPTI.getEtudiants().get(i).getNom();
				test[3] = pFOPTI.getEtudiants().get(i).getPrenom();
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
