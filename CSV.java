import java.io.*;
import java.util.*;

public class CSV {

	//Attributs

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
		if (nomFichier != null)
			this.nomFichier = nomFichier;
	}


	/**
	 * Une méthode de test de lecture du fichier 
	 */
	
	public void accederFichierTest () {
		try {
			fr = new FileReader(nomFichier);
			fr.close();
		} catch (Exception ex) {
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
		} catch (Exception e) {
			if (!accesOK)
				return;
			
			accesOK = false;
			return;
		}
		accesOK = true;
	}

	/**
	 * Lit les lignes d'un fichier
	 * @return La liste les lignes
	 */

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

	//Crée un fichier vide avec le nom donné dans le constructeur
	public void creationFichier () {
		try {
			fw = new FileWriter(nomFichier);
		} catch (Exception e) { return;}
		try {
			bw = new BufferedWriter(fw);
			bw.write("");
			bw.close();
			fw.close();
		} catch (Exception e) {
			accesOK = false;
			return;}
	}

	//Ajoute une ligne de texte à la fin du fichier et le ferme
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

	/**
	 * Getter accesOK
	 * @return le booléen
	 */
	public boolean getAccesOK(){
		return accesOK;
	}
}
