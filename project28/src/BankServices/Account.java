package BankServices;

import java.util.*;
import java.util.stream.Collectors;

public class Account {
	
	private int dateStart;
	private int dateClose;
	
	private Boolean Active;
	
	private String ClientID;
	private Integer AccountID;
	private Double Holding;
	
	private Integer opCounter;
	List<Operation> Operations = new ArrayList<Operation>();
	
	Comparator<Operation> opComparator = (a, b) -> Double.compare(b.getAmount(), a.getAmount());
	
	public Account(String name, int date, double initial, Integer cCounter) {
		this.Active = Boolean.TRUE;
		this.ClientID = name;
		this.AccountID = cCounter;
		this.dateStart = date;
		this.dateClose = -1;
		this.Holding = initial;
		this.opCounter = 1;
	}
	
	public boolean isActive() {
		return this.Active;
	}
	
	public double getCash() {
		return this.Holding;
	}
	
	public int getID() {
		return this.AccountID;
	}
	
	public int detCreation() {
		return this.dateStart;
	}

	public String toString() {
		return this.AccountID + "," + this.ClientID + "," + this.Operations.get(this.Operations.size() - 1).getDate() + "," + this.Holding;
	}
	
	public int closeAccount(int date) {
		this.Active = Boolean.FALSE;
		this.dateClose = date;
		
		return this.dateClose;
	}
	
	public void addCash(Double val, int dateL) {
		this.Holding += val;
		this.opCounter++;
		
		this.Operations.add(new Deposit(dateL, val));
	}
	
	public void removeCash(Double val, int dateL) throws InvalidValue {
		
		if (this.Holding - val < 0)
			throw new InvalidValue();
		
		this.Holding -= val;
		this.opCounter++;
		
		this.Operations.add(new Withdrawal(dateL, val));
	}
		
	public List<Operation> getMovements() {
		return this.Operations.stream().sorted((a, b) -> Integer.compare(b.getDate(), a.getDate())).collect(Collectors.toList());
	}
	
	public List<Deposit> getDeposits() {

		List<Deposit> retval = new ArrayList<Deposit>();
		for (Operation O : this.Operations)
			if (O instanceof Deposit)
				retval.add((Deposit) O);

		Collections.sort(retval, opComparator);
		
		return retval;
	}

	public List<Withdrawal> getWithdrawals() {
		
		List<Withdrawal> retval = new ArrayList<Withdrawal>();
		for (Operation O : this.Operations)
			if (O instanceof Withdrawal)
				retval.add((Withdrawal) O);
		
		Collections.sort(retval, opComparator);
	
		return retval;
	}
}
