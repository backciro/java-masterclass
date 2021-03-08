import ordini.*;
public class EsempioE {

	public static void main(String[] args) throws Exception {
		Ordini ordini = new Ordini();
		ordini.readAnagrafica("anagraficaE.txt");
		System.out.println(ordini.getCodiciProdotti()); // [divano2pP, divano2pT, libreria, poltronaP, poltronaT, scaffale]
		System.out.println(ordini.getCodiciClienti()); // [Bianchi, Rossi, Verdi]
		System.out.println(ordini.getCodiciFornitori()); // [AlfaMobili, BetaMobili]
		
	}
}
