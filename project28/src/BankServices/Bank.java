package BankServices;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Bank {
	
	public static <T>Collector<T, List<T>, T> singletonCollector() {
		return Collector.of(ArrayList::new, 
				List::add, 
				(left, right) -> { left.addAll(right); return left;}, 
				list -> {
					if (list.size() != 1)
						throw new IllegalStateException();
					return list.get(0);
				});
	}

	
	static Integer cCounter;
	private String Name;
	private List<Account> Users = new ArrayList<Account>();
	
	public Bank(String n) {
		this.Name = n;
		Bank.cCounter = 0;
	}
	
	public String getName() {
		return this.Name;
	}
	
	public int createAccount(String name, int date, double initial) {
		
		++Bank.cCounter;
		Account A1 = new Account(name, date, initial, Bank.cCounter);
		this.Users.add(A1);
		
		return A1.getID();
	}
	
	public Account deleteAccount(int code, int date) throws InvalidCode {
		
		--Bank.cCounter;
		Account A0 = this.Users.stream().filter(a -> a.getID() == code).collect(singletonCollector());
		
		try {
			A0.removeCash(A0.getCash(), date);
			A0.closeAccount(date);
		} catch (InvalidValue e) {}

		return A0;
	}
	
	public Account getAccount(int code) throws InvalidCode {
		try {
			return this.Users.stream().filter(a -> (a.getID() == code)).collect(singletonCollector());
		} catch(IllegalStateException ISE) {
			throw new InvalidCode();
		}
	}

	public void deposit(int code, int date, double value) throws InvalidCode {
		
		try {
			Account A1 = this.Users.stream().filter(a -> (a.getID() == code)).collect(singletonCollector());
			A1.addCash(value, date);
		
		} catch (IllegalStateException ISE) {
			throw new InvalidCode();
		}
	}

	public void withdraw(int code, int date, double value) throws InvalidCode, InvalidValue {
		try {
			Account A2 = this.Users.stream().filter(a -> (a.getID() == code)).collect(singletonCollector());
			A2.removeCash(value, date);
		
		} catch (IllegalStateException ISE) {
			throw new InvalidCode();
		}
	}
	
	public void transfer(int fromCode, int toCode, int date, double value) throws InvalidCode, InvalidValue {
	
		try {
			Account A1 = this.Users.stream().filter(a -> (a.getID() == fromCode)).collect(singletonCollector());
		
			try {
				Account A2 = this.Users.stream().filter(a -> (a.getID() == toCode)).collect(singletonCollector());
				
				A1.removeCash(value, date);
				A2.addCash(value, date);
				
			} catch (IllegalStateException ISE) {
				throw new InvalidCode();
			}	
		} catch (IllegalStateException ISE) {
			throw new InvalidCode();
		}
	}
	
	public double getTotalDeposit() {
		return this.Users.stream().collect(Collectors.summingDouble(Account::getCash));
	}
	
	public List<Account> getAccounts() {
		return this.Users.stream().sorted((a, b) -> Integer.compare(a.getID(), b.getID())).collect(Collectors.toList());
	}
	
	public List<Account> getAccountsByBalance(double low, double high) {
		return this.Users.stream().filter(a -> a.getCash() >= low && a.getCash() <= high).collect(Collectors.toList());
	}
	
	public long getPerCentHigher(double min) {
		
		int tCount = this.Users.size();
		int pCount = 0;
		
		for (Account A : this.Users)
			if (A.getCash() >= min)
				pCount++;
		
		return (pCount * 100) / tCount;
	}
}
