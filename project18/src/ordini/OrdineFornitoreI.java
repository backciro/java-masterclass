package ordini;

import java.util.*;

public interface OrdineFornitoreI {
	String getCodiceOrdine();
	String getCodiceFornitore();
	ArrayList<String> getCodiciOrdiniCliente();
	String getStato();
	int getImporto();
	void setStato(String s);
	
}
