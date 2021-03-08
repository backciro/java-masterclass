package rubrica;
import rubrica.Voce;

public class Rubrica extends Voce {

	String title;
	Voce[] r_collection;
	
	static int adder = 0;
    
  /**
   * crea una nuova rubrica con il titolo specificato
   */
  public Rubrica(String titolo) {
	  this.title = titolo;
	  this.r_collection = new Voce[20 + 1]; // 20 dimensione nota
  }

  /**
   * inserisce una nuova voce nella rubrica 
   */  
  public void aggiungi(String name, String surname, String tel) 
  {
	 adder = 1;
	  
	  while ( this.r_collection[adder] != null ) 
		adder++;
	  
	  if (adder > 20)
		  return;
	  
	  this.r_collection[adder] = new Voce(name, surname, tel);  
	  }

  /**
   * restituisce la prima voce inserita nella rubrica
   */  
  public String primo() {
      return getVoce(this.r_collection[1]);
  }

  /**
   * restituisce il nome della rubrica
   */
  public String getTitolo() {
    return this.title;
  }

  /**
   * Restituisce la voce i-esima inserita nella rubrica
   */
  public String voce(int i) {
      return getVoce(this.r_collection[i]);
  }

  /**
   * Restituisce una stringa con l'elenco delle voci
   * della rubrica separate da ", ".
   * L'elenco inizia con "(" e termina con ")"
   */
  public String elenco() {
  	return getElenco(this.r_collection);
  }
  

/**
   * Restituisce la stringa con la prima voce che
   * contiene il parametro daCercare come nome,
   * cognome oppure telefono.
   */
  public String ricerca(String daCercare) {
	  return itemSearch(daCercare, this.r_collection);
  }
}
