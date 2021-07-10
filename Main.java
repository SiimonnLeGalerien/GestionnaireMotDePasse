public class Main {
	public static void main (String[] args) {
		Donnees dat = new Donnees("test.csv", "MotDePasse Vraiment Basique");
		dat.ajouterTuple(new Tuple("Amazon", "admin", "1234"));
		dat.enregistrerTuple(dat.getTuple(0));
		
	}
}
