import ordini.*;
public class Esempio {

	public static void main(String[] args) throws Exception {
		Ordini ordini = new Ordini();
		// Anagrafica
		ordini.readAnagrafica("anagrafica.txt");
		System.out.println(ordini.getCodiciProdotti()); //[divano2pP, divano2pT, libreria, mensola, poltronaP, poltronaT, scaffale]
		System.out.println(ordini.getCodiciClienti()); //[Bianchi, Rossi, Verdi]
		System.out.println(ordini.getCodiciFornitori()); //[AlfaMobili, BetaMobili, TuttoMobili]
		System.out.println(ordini.getCodiciProdottiFornitore("AlfaMobili")); //[divano2pT, libreria, poltronaT]
		// Ordini clienti
		String oc1 = ordini.newOrdineCliente("Bianchi", "divano2pT",1);
		String oc2 = ordini.newOrdineCliente("Bianchi", "poltronaT",2);
		OrdineClienteI ordinec2 = ordini.getOrdineCliente(oc2);
		System.out.print("ordine cliente "); System.out.println(ordinec2.getCodiceCliente() + " " + ordinec2.getCodiceOrdine() + " " + ordinec2.getCodiceProdotto()  + " " + ordinec2.getImporto() + " " + ordinec2.getStato());
		//ordine cliente Bianchi oc2 poltronaT 300 inserito
		String oc3 = ordini.newOrdineCliente("Rossi", "libreria",1);
		System.out.println("ordini inseriti da Bianchi");
		for (OrdineClienteI o: ordini.getOrdiniCliente("Bianchi")) {
			System.out.println("   " + o.getCodiceOrdine() + " " + o.getCodiceProdotto() + " " +o.getNPezzi() + " " + o.getImporto() + " " + o.getStato());
		}
		//ordini inseriti da Bianchi
		//   oc2 poltronaT 2 300 inserito
		//   oc1 divano2pT 1 200 inserito
		// Ordini fornitori
		String of1 = ordini.newOrdineFornitore("AlfaMobili",oc3,oc1);
		OrdineFornitoreI ordinef1 = ordini.getOrdineFornitore(of1);
		System.out.print("ordine fornitore "); System.out.println(ordinef1.getCodiceOrdine() + " " + ordinef1.getCodiciOrdiniCliente()  + " " + ordinef1.getImporto() + " " + ordinef1.getStato());
		//ordine fornitore of1 [oc1, oc3] 500 inserito
		ordini.consegnaOrdineFornitore(of1);
		System.out.println("ordini inseriti da Bianchi");
		for (OrdineClienteI o: ordini.getOrdiniCliente("Bianchi")) {
			System.out.println("   " + o.getCodiceOrdine() + " " + o.getCodiceProdotto() + " " +o.getNPezzi() + " " + o.getImporto() + " " + o.getStato());
		}
		//ordini inseriti da Bianchi
		//   oc2 poltronaT 2 300 inserito
		//   oc1 divano2pT 1 200 consegnato
		System.out.print("ordine fornitore "); System.out.println(ordinef1.getCodiceOrdine() + " " + ordinef1.getCodiciOrdiniCliente()  + " " + ordinef1.getImporto() + " " + ordinef1.getStato());
		//ordine fornitore of1 [oc1, oc3] 500 consegnato
		// Statistiche

		
		System.out.println("statistica Prodotti");
		for (InfoI info:ordini.statisticaProdotti()) System.out.println("   " + info.getCodice() + ": " + info.getValore());
		//statistica Prodotti   poltronaT: 2  divano2pT: 1   libreria: 1   divano2pP: 0   mensola: 0  poltronaP: 0   scaffale: 0
		System.out.println("statistica Clienti");
		for (InfoI info:ordini.statisticaClienti()) System.out.println("   " + info.getCodice() + ": " + info.getValore());
		//statistica Clienti  Bianchi: 500   Rossi: 300   Verdi: 0
		System.out.println("statistica Fornitori");
		for (InfoI info:ordini.statisticaFornitori()) System.out.println("   " + info.getCodice() + ": " + info.getValore());
		//statistica Fornitori AlfaMobili: 500   BetaMobili: 0   TuttoMobili: 0
	}
}
