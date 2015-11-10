package Test;

import controle.CsvRW;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CsvRwTest extends TestCase {

	public void test_ecriture_lecture() {
		String name = "Test.txt";
		String name2 = "Test2.txt";
		CsvRW.ecrire(name, new String[0], false);
		File f = new File(name);
		assertTrue("le fichier n'existe pas", f.exists());
		f.delete();

		String[][] lignes = { { "a" }, { "b", "c" } };
		String[] lignesF = { "a", "b;c" };
		for (String[] l : lignes) {
			CsvRW.ecrire(name2, l, true);
		}

		f = new File(name2);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String s;
			try {
				int i = 0;
				while ((s = br.readLine()) != null) {
					assertTrue("ne correspond pas", lignesF[i].equals(s));
					i++;
				}
				fr.close();
				br.close();
				f.delete();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			fail("le fichier n'existe pas ");
		}
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(new TestSuite(CsvRwTest.class));
	}
}
