import java.io.*;
import java.util.*;

public class CSV {
	private char sep = ',';
	private String nomFichier;
	private FileReader fr;
	private FileWriter fw;
	private BufferedReader br;
	private BufferedWriter bw;
	private boolean accesOK = false;
	
	//Constructeurs
	public CSV () {
		nomFichier = "nameless.csv";
	}
	public CSV (String nomFichier) {
		if (this.nomFichier != null)
			this.nomFichier = nomFichier;
	}
	public CSV (String nomFichier, char sep) {
		if (nomFichier != null)
			this.nomFichier = nomFichier;
	
		if (sep != '\0')
			this.sep = sep;
	}

	public void accederFichierTest () {
		try {
			fr = new FileReader(nomFichier);
			fr.close();
		} catch (IOException ex) {
			try {
				fw = new FileWriter ("log.txt");
				bw = new BufferedWriter(fw);
				bw.write("Erreur : fichier " + nomFichier + " non trouvé ou non accessible\n");
				bw.close();
				fw.close();
				return;
			} catch (Exception e) {}
		}
		try {
			br = new BufferedReader(fr);
			br.close();
			fr.close();
		} catch (Exception e) {}
		accesOK = true;
	}
	
	public List<String> getLignesFichier() {
		
		List<String> l = new ArrayList<String>();
			try {
				fr = new FileReader(nomFichier);
			} catch (Exception e) {
				accederFichierTest();
				return null;
			}
			try {
				br = new BufferedReader(fr);
				int c = 0;
				while(l.add(br.readLine())){
					System.out.println(l.get(c));
					if (l.get(c) == null) {
						l.remove(c);
						return l;
					}
					c++;
				}
			} catch(Exception e) {
				accederFichierTest();
				return null;
			}
			return l;
	}
	public void creationFichier () {
		try {
			fw = new FileWriter(nomFichier);
		} catch (Exception e) { return;}
		try {
			bw = new BufferedWriter(fw);
			bw.write("");
			bw.close();
			fw.close();
		} catch (Exception e) {return;}
	}
	public void ajouterLigne (String contenu) {
		try {
			fw = new FileWriter(nomFichier, true);
		} catch (Exception e) { return;}
		try {
			bw = new BufferedWriter(fw);
			bw.write(contenu + "\n");
			bw.close();
			fw.close();
		} catch (Exception e) {return;}
	}
}
