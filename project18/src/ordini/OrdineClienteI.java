package ordini;

public interface OrdineClienteI {
	String getCodiceOrdine();
	String getCodiceCliente();
	String getCodiceProdotto();
	String getStato();
	int getNPezzi();
	int getImporto();
	void setStato(String s);
	
}
