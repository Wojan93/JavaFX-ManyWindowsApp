package load;

public class Dane {
	private String haslo;
	private String definicja;

	public Dane() {
	}

	public Dane(String haslo, String definicja) {
		this.haslo = haslo;
		this.definicja = definicja;
	}

	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public String getDefinicja() {
		return definicja;
	}

	public void setDefinicja(String definicja) {
		this.definicja = definicja;
	}

	@Override
	public String toString() {
		return haslo + ": " + definicja;
	}
}
