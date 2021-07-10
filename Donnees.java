import java.util.*;

public class Donnees {
	/**
	 * les données sont représentées sous le format CSV
	 * ce sont des données en clair séparées par un char
	 * faisant office de séparateur
	 * Il y a 3 données dans la base:
	 * 	service, nomUtilisateur, motDePasse
	 */
	
	private List<Tuple> data = new ArrayList<Tuple>();
	private CSV fichier;
	private AES256 aes;
	private boolean aes_ok = false;
	
	public Donnees () {
		fichier = new CSV();
	}
	public Donnees (String nomFichier) {
		fichier = new CSV(nomFichier);
		aes = new AES256();
		aes_ok = true;
	}
	public Donnees (String nomFichier, String motDePasse) {
		fichier = new CSV(nomFichier);
		aes = new AES256(motDePasse);
		aes_ok = true;
	}
	public Donnees (String nomFichier, String motDePasse, String salt) {
		fichier = new CSV(nomFichier);
		aes = new AES256(motDePasse, salt);
		aes_ok = true;
	}
	public void ajouterTuple (Tuple t) {
		if (t != null)
			data.add(t);
	}
	public void enregistrerTuple (Tuple t) {
		if (!aes_ok)
			System.out.println("Veuillez entrer un mot de passe pour chiffrer le message...");

		if (t != null && aes_ok)
			fichier.ajouterLigne(aes.encrypt(t.toString()));
	}
	public void lireTuples() {
		List<String> tmp = new ArrayList<String>(fichier.getLignesFichier());
		for(int i = 0; i < tmp.size() - 1; i++)
			if (tmp.get(i) != null && aes_ok)
				data.add(Tuple.stringToTuple(aes.decrypt(tmp.get(i))));
	}
	
	public Tuple getTuple (int index) {
		return data.get(index);
	}
	public int getLength () {
		return data.size();
	}
	public void creerFichier () {
		fichier.creationFichier();
	}
}
