public class Tuple {
	private String service, username, password;
	
	public Tuple (String service, String username, String password) {
		if (service != null)
			this.service = service;
		if (username != null)
			this.username = username;
		if (password != null)
			this.password = password;
	}

	//GETTERS
	String getUsername () {
		return this.username;
	}
	
	String getService () {
		return this.service;
	}

	String getPassword () {
		return this.password;
	}

	//SETTERS
	void setUsername (String username) {
		if (username != null)
			this.username = username;
	}
	void setService (String service) {
		if (service != null)
			this.service = service;
	}
	void setPassword (String password) {
		if (password != null)
			this.password = password;
	}
	
	/**
	 * Une méthode de parsing de String en Tuple
	 * @param s (une ligne de )
	 * @return
	 */
	public static Tuple stringToTuple(String s) {
		String serv, user, pass;
		if (s == null)
			return null;

		int first, sec;
		first = s.indexOf(",");
		sec = s.indexOf(",", first+1);

		serv = s.substring(0,(first));
		System.out.println("first = " + first);
		System.out.println("sec = " + sec);
		user = s.substring((first+1), (sec));
		pass = s.substring(sec+1);
		Tuple res = new Tuple (serv, user, pass);
		return res;
	}

	/**
	 * méthode toString
	 */
	public String toString () {
		String res = String.format("%s, %s, %s", service, username, password);
		return res;
	}
}
