package Aerei;

public class Volo {
	
	private String CompanyBrand;
	private String FlightCode;	
	private String PlaneCode;
	
	private String DepartCode;	
	private String ArriveCode;
	
	private String WeekDay;
	private Integer FreeSeats;
	private Integer TotalSeats;
	
	private Integer DepartRetard;
	private Integer LandRetard;

	private Boolean isTakenoff;
	private Boolean isLanded;
	
	public Volo(String FC, String PC, String DC, String AC, String WD) {
		
		this.FlightCode = FC;
		this.PlaneCode = PC;
		this.DepartCode = DC;
		this.ArriveCode = AC;
		this.WeekDay = WD;
		this.FreeSeats = -1;
		this.TotalSeats = -1;
		this.DepartRetard = 0;
		this.LandRetard = 0;
	}
	
	public void finalize() {
		System.out.println("\nIl volo '" + this.FlightCode + "' e' stato cancellato!\n");
	}
	
	public String getFCode() {
		return this.FlightCode;
	}
	
	public String getPCode() {
		return this.PlaneCode;
	}
	
	public String getDestination() {
		return this.ArriveCode;
	}
	
	public String getSource() {
		return this.DepartCode;
	}
	
	public Integer getFreeSeats() {
		return this.FreeSeats;
	}
	
	public void brandFlight(String b) {
		this.CompanyBrand = b;
	}
	
	public void occupySeats(int o) {
		this.FreeSeats -= o;
	}
	
	public void setFreeSeats(int a) {
		this.FreeSeats = a;
	}
	
	public void setTotalSeats(int a) {
		this.TotalSeats = a;
	}
	
	public void setDepRet(int m) {
		this.isTakenoff = true;
		this.DepartRetard = m;
	}
	
	public void setLandRet(int m) {
		this.isLanded = true;
		this.LandRetard = m;
	}
	
	public int getDepRet() {
		return this.DepartRetard;
	}
	
	public int getLandRet() {
		return this.LandRetard;
	}
	
	public Double percentFree() {
		Double D = (( (double) this.FreeSeats / (double) this.TotalSeats ) * 100.0) ;
		return D;
	}
	
	public boolean Landed() {
		return this.isLanded;
	}
	
	public boolean Departed() {
		return (this.isTakenoff == true);
	}

	public String toString(){
		return (this.CompanyBrand + ";" + this.FlightCode + ";" + this.PlaneCode + ";" + this.DepartCode + ";" + this.ArriveCode + ";" + this.WeekDay + ";" + this.FreeSeats); 
	}
}
