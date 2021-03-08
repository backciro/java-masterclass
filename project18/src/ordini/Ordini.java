package ordini;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;


public class Ordini {

	private int ordersCounter = 0;
	private int fornituresCounter = 0;
	
	Comparator<OrdineClienteI> byPrice = (c1, c2) -> Integer.compare(c2.getImporto(), c1.getImporto());
	Comparator<OrdineClienteI> byName = (c1, c2) -> c1.getCodiceOrdine().compareTo(c2.getCodiceOrdine());
	Comparator<OrdineClienteI> byFinal = byPrice.thenComparing(byName);
	
	Comparator<InfoI> byPriceI = (c1, c2) -> Integer.compare(c2.getValore(), c1.getValore());
	Comparator<InfoI> byNameI = (c1, c2) -> c1.getCodice().compareTo(c2.getCodice());
	Comparator<InfoI> byFinalI = byPriceI.thenComparing(byNameI);	

	// STRUTTURE DATI
	
	List<Cliente> codiciClienti = new ArrayList<Cliente>();
	List<Prodotto> codiciProdotti = new ArrayList<Prodotto>();
	List<Fornitore> codiciFornitori = new ArrayList<Fornitore>();
	
	List<OrdineClienteI> ordiniCliente = new ArrayList<OrdineClienteI>();
	List<OrdineFornitoreI> ordiniFornitore = new ArrayList<OrdineFornitoreI>();
	
	List<InfoI> statisticheClienti = new ArrayList<InfoI>();
	List<InfoI> statisticheProdotti = new ArrayList<InfoI>();
	List<InfoI> statisticheFornitori = new ArrayList<InfoI>();
	
	
	// Anagrafica OK
	public void readAnagrafica(String fileName) {
		
		String buffer;
		String[] trims;
		Boolean present;

		try {
			Scanner input = new Scanner(new File(fileName));
			
			while (input.hasNextLine()) {
				
				buffer =  input.nextLine();
				trims = buffer.split(",");
				present = Boolean.FALSE;
				
				if (buffer.startsWith("C")) {
					this.codiciClienti.add(new Cliente(trims));
				}
				
				if (buffer.startsWith("P")) {
					
					for (Prodotto p : this.codiciProdotti)
						if ( p.getID().equals(trims[1]) )
							present = Boolean.TRUE;

					if (!present)
						this.codiciProdotti.add(new Prodotto(trims));
				}
				
				if (buffer.startsWith("F")) {
					
					Integer counter = 0;
					
					for (int i = 2; i < trims.length; i++)
						for (Prodotto p : this.codiciProdotti)
							if (trims[i].equals(p.getID()))
								counter++;
					
					if (trims.length - 2 == counter)
						this.codiciFornitori.add(new Fornitore(trims));
				}
				
			}	input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getCodiciClienti() {
		return (ArrayList<String>) this.codiciClienti.stream().map(Cliente::getName).sorted((a, b) -> a.compareTo(b)).collect(Collectors.toList());
		}
	public ArrayList<String> getCodiciProdotti() {
		return (ArrayList<String>) this.codiciProdotti.stream().map(Prodotto::getID).sorted((a, b) -> a.compareTo(b)).collect(Collectors.toList());
		}
	public ArrayList<String> getCodiciFornitori() {
		return (ArrayList<String>) this.codiciFornitori.stream().map(Fornitore::getCEO).sorted((a, b) -> a.compareTo(b)).collect(Collectors.toList());
		}

	public ArrayList<String> getCodiciProdottiFornitore(String codice) 
			throws CodiceInesistente {
		
		for (Fornitore F : this.codiciFornitori)
			if (F.getCEO().equals(codice))
				return F.getSCatalog();
					
		throw new CodiceInesistente();
	}

	
	// Ordini clienti
	
	
	// ordini cliente OK
	public String newOrdineCliente(String codiceCliente, String codiceProdotto, int nPezzi) 
			throws CodiceInesistente {
		
		Boolean validCCode = Boolean.FALSE, validPCode = Boolean.FALSE;
		Prodotto currProd = null;
		Integer price = -1; 
		
		for (Cliente C : this.codiciClienti)
			if (C.getName().equals(codiceCliente)) {
				validCCode = Boolean.TRUE;
			}
		
		for (Prodotto P : this.codiciProdotti)
			if (P.getID().equals(codiceProdotto)) {
				validPCode = Boolean.TRUE;
				price = P.getPrice();
				currProd = P;
			}
		
		if (!validCCode || !validPCode)
			throw new CodiceInesistente();
		
		else {
			
			this.ordersCounter++;
			
			Cliente COrdine = new Cliente(currProd, codiceCliente, nPezzi, price, this.ordersCounter);
			COrdine.addSpesa(nPezzi * price);
			
			this.ordiniCliente.add(COrdine);
			
			if (!this.statisticheClienti.contains(COrdine))
				this.statisticheClienti.add(COrdine);	
			
			currProd.sellItem(nPezzi);
			
			if (!this.statisticheProdotti.contains(currProd))
				this.statisticheProdotti.add(currProd);
			
			return COrdine.getCodiceOrdine();
		}
	}
	
	public ArrayList<OrdineClienteI>getOrdiniCliente(String codiceCliente) 
			throws CodiceInesistente {
		
		Boolean valid = Boolean.FALSE;
		
		for (OrdineClienteI c : this.ordiniCliente)
			if (c.getCodiceCliente().equals(codiceCliente))
				valid = Boolean.TRUE;
		if (valid)
			return (ArrayList<OrdineClienteI>) this.ordiniCliente.stream().filter(x -> codiceCliente.equals(x.getCodiceCliente())).sorted(byFinal).collect(Collectors.toList());
		
		else throw new CodiceInesistente();
	}
	
	public OrdineClienteI getOrdineCliente(String codiceOrdineCliente) 
			throws CodiceInesistente {
		
		for (OrdineClienteI oc : this.ordiniCliente)
			if (oc.getCodiceOrdine().equals(codiceOrdineCliente)) {
				return oc;
			}
		
		throw new CodiceInesistente();
	}
	
	
	// Ordini fornitori OK
	public String newOrdineFornitore(String codiceFornitore, String... codiciOC) 
			throws CodiceInesistente, OrdineInaccettabile {
		
		String[] productsInForniture = null; int orderPrice = 0;
		
		OrdineClienteI[] buffer = new Cliente[codiciOC.length];
		
		Boolean validF = Boolean.FALSE ; 		
		Boolean inStock = Boolean.FALSE;
		
		Boolean[] validCodes = new Boolean[codiciOC.length];
		Boolean[] processedOrders = new Boolean[codiciOC.length];

		Arrays.fill(validCodes, Boolean.FALSE);	
		Arrays.fill(processedOrders, Boolean.FALSE);
		
		//controllo esistenza fornitore
		
		for (Fornitore f : this.codiciFornitori)
			if (f.getCEO().equals(codiceFornitore)) {
				validF = Boolean.TRUE;

				productsInForniture = new String[f.getSCatalog().size()];
				productsInForniture = f.getSCatalog().toArray(productsInForniture);
			}
		
		// controllo esistenza OCs
		for (int i = 0; i < codiciOC.length; i++ )
			for (OrdineClienteI o : this.ordiniCliente)
				if (codiciOC[i].equals(o.getCodiceOrdine())) {
					validCodes[i] = Boolean.TRUE;

					if (o.getStato().equals("inserito")) {
						processedOrders[i] = Boolean.TRUE;
						orderPrice += o.getImporto();
						buffer[i] = o;
					}
				}
		
		// conotrollo fornitura

		for (int i = 0; i < productsInForniture.length; i++)
			for (OrdineClienteI o : this.ordiniCliente)
				if (o.getCodiceProdotto().equals(productsInForniture[i]))
					inStock = Boolean.TRUE;
			
		// check finali
		
		if (!validF)
			throw new CodiceInesistente();
		
		for (int i = 0; i < codiciOC.length; i++)
			if (validCodes[i] == false || processedOrders[i] == false)
				throw new OrdineInaccettabile();
		
			if (!inStock)
				throw new OrdineInaccettabile();
		
		// se super i check aggiungo l ordine	
			
		this.fornituresCounter++;

		Fornitore FOrder = new Fornitore(codiceFornitore, this.fornituresCounter, orderPrice, productsInForniture, codiciOC);
		boolean in = false;
		
		for (InfoI I : this.statisticheFornitori)
			if (I.getCodice().equals(FOrder.getCEO())) {
				
				this.statisticheFornitori.remove(I);
				in = true;
			}
		
		if (!in)
			this.statisticheFornitori.add(FOrder);
		else {
			FOrder.addCash(orderPrice);
			this.statisticheFornitori.add(FOrder);
		}
		
		this.ordiniFornitore.add(FOrder);
		
		for (OrdineClienteI o : buffer)
			o.setStato("trattato");
		
		return FOrder.getCodiceOrdine();
	}
	
	public OrdineFornitoreI getOrdineFornitore(String codiceOrdineFornitore) 
			throws CodiceInesistente {
		
		for (OrdineFornitoreI OF : this.ordiniFornitore)
			if (OF.getCodiceOrdine().equals(codiceOrdineFornitore))
				return OF;
		
		throw new CodiceInesistente();
	}

	public void consegnaOrdineFornitore(String codiceOrdineFornitore) 
			throws CodiceInesistente, ConsegnaInaccettabile {
		
		Boolean valid = Boolean.FALSE;
		
		for (OrdineFornitoreI OF : this.ordiniFornitore) {
			if (OF.getCodiceOrdine().equals(codiceOrdineFornitore)) 
				if (OF.getStato().equals("inserito")) {
					valid = Boolean.TRUE;
					OF.setStato("consegnato");
				}
				else
					throw new ConsegnaInaccettabile();
		}
		if (!valid)
			throw new CodiceInesistente();	
		else 
			for (OrdineFornitoreI OF : this.ordiniFornitore) 
				if (OF.getCodiceOrdine().equals(codiceOrdineFornitore)) 
					for (String s : OF.getCodiciOrdiniCliente())
						for (OrdineClienteI OC : this.ordiniCliente)
							if (OC.getCodiceOrdine().equals(s))
								OC.setStato("consegnato");
	}
	
	
	// Statistiche
	public ArrayList<InfoI> statisticaProdotti() {
		return (ArrayList<InfoI>) this.statisticheProdotti.stream().sorted(byFinalI).collect(Collectors.toList());
	}
	public ArrayList<InfoI> statisticaClienti() {
		return (ArrayList<InfoI>) this.statisticheClienti.stream().sorted(byFinalI).collect(Collectors.toList());
	}
	public ArrayList<InfoI>  statisticaFornitori() {
		return (ArrayList<InfoI>) this.statisticheFornitori.stream().sorted(byFinalI).collect(Collectors.toList());
	}
}


