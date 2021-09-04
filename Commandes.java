import java.util.*;

public class Commandes {

	private List<String> commandesList = new ArrayList<String>();
	private CSV csv = new CSV("commandes.list");
	public Commandes () {
		csv.creationFichier();
		csv.accederFichierTest();
		System.out.println(csv.getAccesOK());
	}

	public boolean presentDansListe (String commande) {
		return false;
	}
}