package ordini;

public class Cliente implements OrdineClienteI, InfoI{
	
	private String name;
	
	private String codOrdine, codCliente, codProdotto, status;
	private Integer nPezzi, totImporto, totSpesa;
	
	public Cliente(String... args) {
		this.name = args[1];
	}
	
	public Cliente(Prodotto P, String cC, Integer nP, Integer sPrice, int pCounter) {
		this.codOrdine = "oc" + pCounter;
		this.codCliente = this.name = cC;
		this.codProdotto = P.getID();
		this.nPezzi = nP;
		this.totImporto = nP * sPrice;
		this.status = "inserito";
		this.totSpesa = 0;
	}
	
	public String getName() {
		return this.name;
	}

	public String toString() {
		return this.name;
	}
	
	public void addSpesa(int a) {
		this.totSpesa += a;
	}

	@Override
	public String getCodiceOrdine() {	
		return this.codOrdine;
	}

	@Override
	public String getCodiceCliente() {
		return this.codCliente;
	}

	@Override
	public String getCodiceProdotto() {
		return this.codProdotto;
	}

	@Override
	public int getNPezzi() {
		return this.nPezzi;
	}

	@Override
	public String getStato() {
		return this.status;
	}

	@Override
	public int getImporto() {
		return this.totImporto;
	}

	@Override
	public void setStato(String s) {
		this.status = s;
	}

	@Override
	public String getCodice() {
		return this.codCliente;
	}

	@Override
	public int getValore() {
		return this.totSpesa;
	}
}
