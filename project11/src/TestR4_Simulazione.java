import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import hydraulic.Elemento;
import hydraulic.Rubinetto;
import hydraulic.Scarico;
import hydraulic.Sistema;
import hydraulic.Sorgente;
import hydraulic.Split;
import junit.framework.TestCase;


public class TestR4_Simulazione extends TestCase {

	private PrintStream prevOut;
	private ByteArrayOutputStream realOut;
	/**
	 * starts capturing output into buffer
	 *
	 */
	private void startCapture() {
		prevOut = System.out;
		realOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(realOut));
	}
	/**
	 * stop capturing output, reset normal output
	 * @return the captured output
	 */
	private String stopCapture() {
		System.setOut(prevOut);
		return realOut.toString();
	}

	/**
	 * Finds the the n-th occurrence of a number
	 * after the appearence of the name within
	 * the string str.
	 * 
	 * Es.Given <pre>
	 * 		str = "... Pi 3.14 and 6.28 ..."
	 * 		name = "Pi"
	 * </pre>
	 * For n=1 it returns 3.14.
	 * For n=2 it returns 6.28
	 * 
	 * @param name	The name that marks the part to search for numbers
	 * @param str	The string
	 * @param n		The number of the occurrence to search for
	 * @return		The value of the n-th occurrence of a number
	 * 				after the appearence of the name within
	 * 				the string str.
	 */
	private double findNNum(String name, String str, int n) {
		int posName = str.indexOf(name);
		int startNum;
		for(startNum=posName+name.length();!Character.isDigit(str.charAt(startNum))
							&& str.charAt(startNum)!='.';startNum++);
		int endNum;
		for(endNum=startNum+1;Character.isDigit(str.charAt(endNum))
			|| str.charAt(endNum)=='.';endNum++);
		// first number
		for(int i=1; i<n; ++i){
			for(startNum=endNum+1;!Character.isDigit(str.charAt(startNum))
				&& str.charAt(startNum)!='.';startNum++);
			for(endNum=startNum+1;Character.isDigit(str.charAt(endNum))
				|| str.charAt(endNum)=='.';endNum++);
		}
		String num = str.substring(startNum,endNum);
		return Double.parseDouble(num);
	}

	public void testElementiSemplici(){
		Sistema s = new Sistema();
		Sorgente src = new Sorgente("Src");		
		Rubinetto tap = new Rubinetto("Tap");		
		Elemento sink = new Scarico("Sink");		
		s.aggiungiElemento(src);
		s.aggiungiElemento(tap);
		s.aggiungiElemento(sink);
		
		src.connetti(tap);
		tap.connetti(sink);
		
		double portata = 100.0;
		src.setPortata(portata);
		tap.setOpen(true);
		
		startCapture();
		s.simula();
		String output=stopCapture();
		
		double inTap = findNNum("Tap",output,1);
		double outTap = findNNum("Tap",output,2);
		
		assertEquals(portata,inTap,0.01);
		assertEquals(portata,outTap,0.01);
		assertEquals(portata,findNNum("Sink",output,1),0.01);
	}
	
	public void testSplit(){
		Sistema s = new Sistema();
		Sorgente src = new Sorgente("Src");		
		Split t = new Split("T");	
		Elemento sink1 = new Scarico("Sink 1");		
		Elemento sink2 = new Scarico("Sink 2");		
		s.aggiungiElemento(src);
		s.aggiungiElemento(t);
		s.aggiungiElemento(sink1);
		s.aggiungiElemento(sink2);
		
		src.connetti(t);
		t.connetti(sink1,0);
		t.connetti(sink2,1);
		
		double portata = 100.0;
		src.setPortata(portata);
		
		startCapture();
		s.simula();
		String output=stopCapture();

		assertEquals(50,findNNum("Sink 1",output,1),0.01);
		assertEquals(50,findNNum("Sink 2",output,1),0.01);
	}

	
}
