/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 */
public class ClockDisplay extends NumberDisplay {
	
		NumberDisplay dataTime ;
		NumberDisplay alarmTime ;
		
		private boolean isAlarmOn = false;
		
		/**
		 * Constructor for ClockDisplay objects. This constructor 
		 * creates a new clock set at 00:00.
		 */
		public ClockDisplay()
		{
			this.dataTime = new NumberDisplay();
			this.alarmTime = new NumberDisplay();
			this.isAlarmOn = false;
		}

		/**
		 * Constructor for ClockDisplay objects. This constructor
		 * creates a new clock set at the time specified by the 
		 * parameters.
		 */
		public ClockDisplay(int hour, int minute)
		{
			this.dataTime = new NumberDisplay(hour, minute);	
			this.alarmTime = new NumberDisplay();
			this.isAlarmOn = false;
		}

		/**
		 * This method should get called once every minute - it makes
		 * the clock display go one minute forward.
		 * If the alarm is ON and the current time equals the alarm time,
		 * it prints an alarm message
		 */
		public void timeTick()
		{
			tickTock(this.dataTime);
			
			if (isEqual(this.dataTime, this.alarmTime) && this.isAlarmOn)
				System.out.println("RING RING !! IT's "+ getTime());
		}

		/**
		 * Set the time of the display to the specified hour and
		 * minute.
		 */
		public void setTime(int hour, int minute)
		{
			setter(this.dataTime, hour, minute);
		}

		/**
		 * Return the current time of this display in the format HH:MM.
		 */
		public String getTime()
		{
			String s = timeStamp(this.dataTime);
			return s;
		}
		
		/**
		 * Set alarm ON to the specified hour and minute.
		 */
		public void setAlarmOn(int hour, int minute)
		{
			this.isAlarmOn = true;
			setter(this.alarmTime, hour, minute);
		}
		
		/**
		 * Set alarm OFF
		 */
		public void setAlarmOff()
		{
			this.isAlarmOn = false;
		}
}
