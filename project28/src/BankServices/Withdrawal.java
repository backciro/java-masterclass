package BankServices;

public class Withdrawal extends Operation {

	private int date;
	private double amount;
	
	public Withdrawal(int dateL, Double amount) {
		this.date = dateL;
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return this.date + "," + this.amount + "-";
	}

	@Override
	public Double getAmount() {
		return this.amount;
	}

	@Override
	public Integer getDate() {
		return this.date;
	}

	@Override
	public Boolean isDep() {
		return (this instanceof Withdrawal);
	}

	@Override
	public Boolean isWit() {
		// TODO Auto-generated method stub
		return null;
	}	
}
