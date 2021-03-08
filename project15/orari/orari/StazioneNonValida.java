package orari;

public class StazioneNonValida extends Exception {

	private static final long serialVersionUID = -8412124529385212599L;

	public StazioneNonValida() {
		System.out.println("STAZIONE inserita non valida\nPrego riprovare"); 
	}
	
}
