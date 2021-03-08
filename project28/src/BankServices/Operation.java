package BankServices;

public abstract class Operation {
	
	public abstract String toString();
	public abstract Double getAmount();
	public abstract Integer getDate();
	
	public abstract Boolean isDep();
	public abstract Boolean isWit();
	
}
