
public class Heater {
   
	int MIN;
	int MAX;
	int TEMP;
	int STEP_INC = 5; // default steps
    
    /**
     *  Creates a new Heater with an initial temperature .
     */
    public Heater(int initial, int minimum, int maximum) {
    	this.TEMP = initial;
    	this.MIN = minimum;
    	this.MAX = maximum;
    }
    
    /**
     * Increases the temperature
     */ 
    public void warmer() {
    	
    	if (this.TEMP + this.STEP_INC <= this.MAX)
    		this.TEMP += this.STEP_INC;
    	else {
    		this.TEMP = this.MAX;
    		System.out.println("TEMPERATURE SETTED TO MAX");
    	}
    }
    
    /**
     * Decreases the temperature
     */ 
    public void cooler() {
    	if (this.TEMP - this.STEP_INC >= this.MIN)
    		this.TEMP -= this.STEP_INC;
    	else {
    		this.TEMP = this.MIN;
    		System.out.println("TEMPERATURE SETTED TO MIN");
    	}
    }
    
    /**
     * Sets the increment, which determines how much the two methods 
     * warmer() and cooler() changes the temperature.
     */
    public void setIncrement(int inc)  {
    	this.STEP_INC = inc;
    }
    
    /**
     * Gets the current temperature of the heater
     */
    public int getTemperature() {
    	return this.TEMP;
    }
}
