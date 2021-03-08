package rubrica;

public class Voce {
	
	String nome, cognome, numero;	
	
	public Voce() {
		this.nome = null;
		this.cognome = null;
		this.numero = null;
	}
	
	public Voce(String name, String surname, String telephone) {
		this.nome = name;
		this.cognome = surname;
		this.numero = telephone;
	}
	
	public String getVoce(Voce panel) {
		if (panel.numero != null)
			return String.format("%s %s: %s", panel.nome, panel.cognome, panel.numero);
		else return null;
	}

	public String getElenco(Voce[] collection) {

		String s = new String();
		
		s = "( ";
		for (int i = 1; i <= 20; i++) {
			if (collection[i] != null) {
				s += "" + getVoce(collection[i]);
				s += ", ";
			}
		}
		s += ")";

		return s;
	}

	public String itemSearch(String daCercare, Voce[] collection) {
		String notFound = "ITEM NOT FOUND";
		
		for (int i = 0; i <= 20; ++i) {
			if ( collection[i] != null ) {
				if ( daCercare.equals(collection[i].nome) )
					return getVoce(collection[i]);
				if ( daCercare.equals(collection[i].cognome) )
					return getVoce(collection[i]);
				if ( daCercare.equals(collection[i].numero) )
					return getVoce(collection[i]);
			}
		}
		return notFound;
	}
}