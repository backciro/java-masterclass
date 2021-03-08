package tollroad;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class TollManager {
	
	class Person { 
		private Set<Segment> PASS_SEGS = new HashSet<Segment>();
		
		private String Name, Surname;
		private Double Debit, Credit;
		private Integer TransitCounter;
		
		public Person(String n, String s) {
			this.Name = n;
			this.Surname = s;
			
			this.Credit = 10.0;
			this.Debit = 0.0;
			this.TransitCounter = 0;
		}
		
		public Set<Segment> getSet() {
			return this.PASS_SEGS;
		}
		
		public String getName() {
			return this.Name;
		}
		
		public String getSurname() {
			return this.Surname;
		}
		
		public Double getCredit() {
			return this.Credit;
		}
		
		public Double getDebit() {
			return this.Debit;
		}
		
		public Integer getTransit() {
			return this.TransitCounter;
		}
		
		public void addSegmentToCust(Segment S) {
			this.PASS_SEGS.add(S);
		}
		
		public void setCredit(Double D) {
			this.Credit = D;
		}
		
		public void addDebit(Double D) {
			this.Debit += D;
		}
		
		public void transit() {
			this.TransitCounter++;
		}
		
		public void reset() {
			this.Debit = 0.0;
			this.TransitCounter = 0;
		}
	}
	
	class Segment { 
		
		private String Code, Src, Dst;
		private Double Price;
		private Integer Passages;
		
		public Segment(String arg0, String arg1, String arg2, Double arg3) {
			this.Code = arg0;
			this.Src = arg1;
			this.Dst = arg2;
			this.Price = arg3;
			this.Passages = 0;
		}
		
		public String getCode() {
			return this.Code;
		}
		
		public String getSrc() {
			return this.Src;
		}
		
		public String getDst() {
			return this.Dst;
		}
		
		public Double getPrice() {
			return this.Price;
		}
		
		public Integer getPass() {
			return this.Passages;
		}
		
		public void stepper() {
			this.Passages++;
		}
	}
	
	class SegComparator implements Comparator<Segment> {

		@Override
		public int compare(Segment o1, Segment o2) {
			if (o1.getPass() < o2.getPass())
				return -1;
			
			else if (o1.getPass() == o2.getPass()) {
				if (o1.getCode().compareTo(o2.getCode()) < 0)
					return 1;
				else return -1;
			}
			
			else
				return 1;
		}
	}
			
	public Integer TotalTransit;
	Map<String, Person> DB = new HashMap<String, Person>();
	Set<Segment> SEGS = new HashSet<Segment>();

	public TollManager() {
		this.TotalTransit = 0;
	}
	
	public void addCustomer(String SSN, String first, String last) {		
		this.DB.put(SSN, new Person(first, last));
	}

	public void addSegment(String code, String from, String to, double price) {
		this.SEGS.add(new Segment(code, from, to, price));	
	}

	public String getCustomer(String SSN) throws CustomerNotPresent {
		if (this.DB.containsKey(SSN))
			return this.DB.get(SSN).getSurname();
		else
			throw new CustomerNotPresent();
	}

	public double getSegment(String code) throws SegmentNotPresent {
		for ( Segment S : this.SEGS )
			if ( S.getCode().equals(code) )
				return S.getPrice();
		
		throw new SegmentNotPresent();
	}

	public void setCreditThreshold(String SSN, double m) throws CustomerNotPresent {
		if (this.DB.containsKey(SSN))
			this.DB.get(SSN).setCredit(m);
		else
			throw new CustomerNotPresent();
	}

	public void transitOnSegment(String SSN, String segment) 
			throws CustomerNotPresent, SegmentNotPresent, CreditThresholdReached {
		
		Boolean SEGS_EXIST = Boolean.FALSE;
		
		if (this.DB.containsKey(SSN)) {
			for (Segment x : this.SEGS) { 
				if (x.getCode().equals(segment)) {
					if (this.DB.get(SSN).getCredit() > this.DB.get(SSN).getDebit()) {
						this.DB.get(SSN).addSegmentToCust(x);
						this.DB.get(SSN).addDebit(x.getPrice());
						this.DB.get(SSN).transit();
						this.TotalTransit++;
						x.stepper();
						SEGS_EXIST = Boolean.TRUE;
					}
					else throw new CreditThresholdReached();
				}
			}
			if (!SEGS_EXIST)
				throw new SegmentNotPresent();
		}
		else
			throw new CustomerNotPresent();
	}

	public double totalDebt(String SSN) throws CustomerNotPresent {
		if (this.DB.containsKey(SSN))
			return this.DB.get(SSN).getDebit();
		else
			throw new CustomerNotPresent();
	}

	public void resetCustomer(String SSN) throws CustomerNotPresent {
		if (this.DB.containsKey(SSN))
			this.DB.get(SSN).reset();
		else
			throw new CustomerNotPresent();
	}

	public int countTransit(String SSN) throws CustomerNotPresent {
		if (this.DB.containsKey(SSN))
			return this.DB.get(SSN).getTransit();
		else
			throw new CustomerNotPresent();
	}

    public Set<String> segments(String SSN) throws CustomerNotPresent {
        
    	if (this.DB.containsKey(SSN)) {
    		Set<String> seg = new HashSet<String>();
    		
    		for (Segment s : this.DB.get(SSN).getSet())
    			seg.add(s.getCode());
    	
    		return seg.stream().distinct().collect(Collectors.toSet());
    	}
    	else 
    		throw new CustomerNotPresent();
    }

	public int countTransit() {		
		return this.TotalTransit;
	}

	public double totalDebt() {	
		
		Double Debt = 0.0;
		
		Set<Entry<String, Person>> pers = this.DB.entrySet().stream().collect(Collectors.toSet());
		
		for (Entry<String, Person> P : pers)
			Debt += P.getValue().getDebit(); 
		
		return Debt;
	}

	public String mostTransitedSegment() {
		return (this.SEGS.stream().max(new SegComparator()).get().getCode());
	}
}
