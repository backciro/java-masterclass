package orari;


public class Passaggio {

	class Time { 
		private int ore, minuti; 
		
		public Time(int h, int m) {
			this.ore = h;
			this.minuti = m;
		}
		
		public int getH() {
			return this.ore;
		}
		
		public int getM() {
			return this.minuti;
		}
	} 
	
	String Stazione;
	Time ExpectedPass; 
	Time RealPass;
	
	public Passaggio(String arg, int h, int m) {
		this.Stazione = arg;
		this.RealPass = new Time (h,m);
	}
	
	public void setExpected(int h, int m) {
		this.ExpectedPass = new Time(h, m);
	}
	
  public String getStazione() {
	  return this.Stazione;
  }

  public int getOra() {
	  return this.RealPass.getH();
  }

  public int getMinuti() {
	  return this.RealPass.getM();
  }

  public int ritardo() {
	  
	  if (this.ExpectedPass.getH() == this.RealPass.getH() && this.ExpectedPass.getM() == this.RealPass.getM())
		  return 0;
	  
	  else if (this.ExpectedPass.getH() == this.RealPass.getH() && this.ExpectedPass.getM() < this.RealPass.getM())
		  return (this.RealPass.getM() - this.ExpectedPass.getM());
	  
	  else {
		  Integer HRIT, MRIT, RIT;
		  
		  HRIT = (this.RealPass.getH() - this.ExpectedPass.getH()) * 60;
		  MRIT = (this.RealPass.getM() - this.ExpectedPass.getM());
		  RIT = HRIT + MRIT;
		  
		  return RIT;
	  }
  }
}
