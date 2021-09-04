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

	/**
	 * ajoute le tuple t dans la liste en mémoire
	 * @param t
	 */
	public void ajouterTuple (Tuple t) {
		if (t != null)
			data.add(t);
	}

	/**
	 * ajoute le tuple t à la fin du fichier et l'enregistre
	 * @param t
	 */
	public void enregistrerTuple (Tuple t) {
		if (!aes_ok)
			System.out.println("Veuillez entrer un mot de passe pour chiffrer le message...");

		if (t != null && aes_ok)
			fichier.ajouterLigne(aes.encrypt(t.toString()));
	}

	/**
	 * Lit les Tuples du fichier et les insère dans le
	 * conteneur de tuples.
	 */
	public void lireTuples() {
		List<String> tmp = new ArrayList<String>(fichier.getLignesFichier());
		for(int i = 0; i < tmp.size() - 1; i++)
			if (tmp.get(i) != null && aes_ok)
				data.add(Tuple.stringToTuple(aes.decrypt(tmp.get(i))));
	}
	/**
	 * Getter tuple avec index
	 * @param index
	 * @return tuple indexé
	 */
	public Tuple getTuple (int index) {
		return data.get(index);
	}

	/**
	 * Getter de taille du conteneur de tuples
	 * @return la taille
	 */
	public int getLength () {
		return data.size();
	}

	/**
	 * Crée le fichier avec le nom donné lors de 
	 * l'initialisation de la classe CSV
	 */
	public void creerFichier () {
		fichier.creationFichier();
	}
}
