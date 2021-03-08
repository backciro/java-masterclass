
public class MainClass {

	public static void main(String[] args) {
		ClockDisplay cd1 = new ClockDisplay();
		System.out.println("clock1: "+cd1.getTime());
		
		ClockDisplay cd2 = new ClockDisplay(17,25);
		System.out.println("clock2: "+cd2.getTime());
		
		cd1.setTime(14, 10);
		System.out.println("clock1: "+cd1.getTime());
		for(int i=1; i<=150; i++) 
			cd1.timeTick();
		System.out.println("clock1: "+cd1.getTime());
		
		cd1.setAlarmOn(18,25);
		for(int i=1; i<=120; i++) 
			cd1.timeTick();
		System.out.println("clock1: "+cd1.getTime());
		
		cd1.setAlarmOn(19,25);
		cd1.setAlarmOff();
		
		for(int i=1; i<=120; i++) 
			cd1.timeTick();
		System.out.println("clock1: "+cd1.getTime());
		
		cd2.setTime(6, 5);
		System.out.println("clock2: "+cd2.getTime());
		
		cd2.setAlarmOn(9,0);
		
		for(int i=1; i<=180; i++) 
			cd2.timeTick();
		
		System.out.println("clock2: "+cd2.getTime());
	}

}
