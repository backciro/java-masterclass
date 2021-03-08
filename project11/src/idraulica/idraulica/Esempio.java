package idraulica;

import hydraulic.Elemento;

public class Esempio {

	public static void main(String args[]) {
		
		Sistema s = new Sistema();
	
		// 1) si definiscono gli elementi
		Sorgente src = new Sorgente("Src");
		s.aggiungiElemento(src);
		
		Rubinetto r = new Rubinetto("R");
		s.aggiungiElemento(r);
		
		Scarico sink1 = new Scarico("Sink1");
		s.aggiungiElemento(sink1);
		
		Scarico sink2 = new Scarico("Sink2");
		s.aggiungiElemento(sink2);
		
		Split t = new Split("T");
		s.aggiungiElemento(t);
		
		System.out.println("Elementi:");
		
		Elemento[] el = s.getElementi();
		for(int i = 0; el[i] != null; i++)
			System.out.println(el[i].getNome());
	
		// sink1.connetti(r);
		
		// 2) si connettono
		src.connetti(r);
		System.out.println( "uscita "+src.getNome()+": "+ src.getUscita().getNome() );
		
		r.connetti(t);
		System.out.println( "uscita "+r.getNome()+": "+ r.getUscita().getNome() );
		
		t.connetti(sink1,0);
		t.connetti(sink2,1);
		System.out.println( "uscite "+t.getNome()+": "+ t.getUscite()[0].getNome()+","+ t.getUscite()[1].getNome() );
		
		// 3) si definiscono i parametri
		src.setPortata(140);
		r.setOpen(true);
		
		// 4) si simula
		s.simula();
		
	}
}
