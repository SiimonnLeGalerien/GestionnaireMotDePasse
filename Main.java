public class Main {
	public static void main (String[] args) {
		Donnees dat = new Donnees();
		dat.ajouterTuple(new Tuple ("Amazon", "admin", "1234"));
		dat.enregistrerTuple(dat.getTuple(0));

		dat.lireTuples();
		
		for (int i = 0; i < dat.getLength(); i++)
			System.out.println(dat.getTuple(i));

	}
}
