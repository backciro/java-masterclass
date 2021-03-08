package tollroad;

@SuppressWarnings("serial")
public class CreditThresholdReached extends Exception {
	
	public CreditThresholdReached() {
		System.out.println("CREDIT THRESHOLD REACHED! PLEASE RECHARGE!\n");
	}
}
