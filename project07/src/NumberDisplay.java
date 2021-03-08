/**
 * The NumberDisplay class represents a digital number display that can hold
 * values from zero to a given limit. The limit can be specified when creating
 * the display. The values range from zero (inclusive) to limit-1. If used,
 * for example, for the seconds on a digital clock, the limit would be 60, 
 * resulting in display values from 0 to 59. When incremented, the display 
 * automatically rolls over to zero when reaching the limit.
 */
public class NumberDisplay {
	
	int HOURS, MINUTES;
	static int H_MAX = 23, M_MAX = 59;
	
	public NumberDisplay() {
		this.HOURS = 00;
		this.MINUTES = 00;
	}
	
	public NumberDisplay(int hour, int minute) {
		this.HOURS = hour;
		this.MINUTES = minute;
	}
	
	public static void tickTock(NumberDisplay digit) {
		digit.MINUTES++;
		
		if (digit.MINUTES > M_MAX) {
			digit.MINUTES = 00;
			
			digit.HOURS++;
			
			if (digit.HOURS > H_MAX)
				digit.HOURS = 00;
		}		
	}
	
	public static boolean isEqual(NumberDisplay dataTime, NumberDisplay alarmTime) {
		
		if (dataTime.HOURS == alarmTime.HOURS && dataTime.MINUTES == alarmTime.MINUTES)
			return true;
		else 
			return false;
	}
	
	public static void setter(NumberDisplay data, int h, int m) {
		data.HOURS = h;
		data.MINUTES = m;
	}
	
	public String timeStamp(NumberDisplay data) {
		String date ;
		
		if (data.HOURS < 10 && data.MINUTES < 10)
			date = "0"+ data.HOURS +":0"+ data.MINUTES +"";
		else if (data.HOURS < 10 && data.MINUTES > 10)
			date = "0"+ data.HOURS +":"+ data.MINUTES +"";
		else if (data.HOURS > 10 && data.MINUTES < 10)
			date = ""+ data.HOURS +":0"+ data.MINUTES +"";
		else
			date = ""+ data.HOURS +":"+ data.MINUTES +"";
		
		return date;
	}

	
	
}
