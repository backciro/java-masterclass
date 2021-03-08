package ordini;

import java.util.*;
import java.util.stream.Collectors;

public class Fornitore implements OrdineFornitoreI, InfoI {

	private String CEO;
	private List<String> Catalog = new ArrayList<String>();
	
	private String codFornitura, status;
	private List<String> codOrdiniClienti = new ArrayList<String>();
	
	private int totalPrice, value;
	
	public Fornitore(String... args) {

		this.CEO = args[1];
		
		for (int i = 2; i < args.length; i++) {
			this.Catalog.add(args[i]);
		}
	}
	
	public Fornitore(String codiceFornitore, int fornCnt, int totPrice, String[] catalog, String... codiciOC) {
		
		this.codFornitura = "of" + fornCnt;
		this.CEO = codiceFornitore;
		this.Catalog = Arrays.asList(catalog);
		
		for (String s : codiciOC) 
			this.codOrdiniClienti.add(s);
		
		this.status = "inserito";
		this.totalPrice = totPrice;
		this.value = totPrice;
	}
	
	public String getCEO() {
		return this.CEO;
	}
	
	public ArrayList<String> getSCatalog() {
		return (ArrayList<String>) this.Catalog.stream().sorted((a, b) -> a.compareTo(b)).collect(Collectors.toList());
	}
	
	public String toString() {
		return this.CEO + ", " + this.Catalog.stream().sorted((a, b) -> a.compareTo(b)).toString();
	}
	
	public void addCash(int a) {
		this.value += a;
	}

	@Override
	public String getCodiceOrdine() {
		return this.codFornitura;
	}

	@Override
	public String getCodiceFornitore() {
		return this.CEO;
	}

	@Override
	public ArrayList<String> getCodiciOrdiniCliente() {
		return (ArrayList<String>) this.codOrdiniClienti.stream().sorted((a,b) -> a.compareTo(b)).collect(Collectors.toList());
	}
	
	@Override
	public void setStato(String s) {
		this.status = s;
	}

	@Override
	public String getStato() {
		return this.status;
	}

	@Override
	public int getImporto() {
		return this.totalPrice;
	}

	@Override
	public String getCodice() {
		return this.CEO;
	}

	@Override
	public int getValore() {
		return this.value;
	}
}
