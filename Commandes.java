import java.util.*;

public class Commandes {

	private List<String> commandesList = new ArrayList<String>();
	private CSV csv = new CSV("commandes.list");
	public Commandes () {
		csv.accederFichierTest();
		if (!csv.getAccesOK())
			csv.creationFichier();
		
		commandesList = csv.getLignesFichier();
	}

	public boolean presentDansListe (String commande) {
		for (int i = 0; i < commandesList.size(); i++) {
			if (commande.equals(commandesList.get(i)))
				return true;
		}
		return false;
	}
}