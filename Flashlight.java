/**
 * Contains the flashlight workability in the game.
 * @author Greta Wang, Diane Tuyizere, Alexandra Huayta.
 * @version 12/15/2025.
 */
public class Flashlight extends Grabbable {
    private int batteryLevel;
    private boolean isOn;

    /**
     * Class constructor.
     * @param name default name for the flashlight.
     * @param batteryLevel the level of the battery.
     */
    public Flashlight(String name, int batteryLevel){
        super(name);
        if (batteryLevel < 0){
            throw new RuntimeException("Cannot set negative battery level.");
        }
        this.batteryLevel = batteryLevel;
        this.isOn = false;
    }

    /**
     * Getter method to access the batteryLevel.
     * @return the batteryLevel. 
     */    
    public int getBatteryLevel(){  
        return this.batteryLevel;
    }

    /**
     * Setter method that allows the modification on the flashlight battery level.
     * @param newBatteryLevel new battery level. 
     */
    public void setBatteryLevel(int newBatteryLevel){
        if (newBatteryLevel < 0){
            throw new RuntimeException("Cannot set negative battery level.");
        }
        this.batteryLevel = newBatteryLevel;
    }

    /**
     * Method to update the battery level by increasing/decreasing while limiting them in the range of 0 to 100.
     * @param changeInBattery amount to change battery level by.
     */
    public void updateBatteryLevel(int changeInBattery){
        this.batteryLevel += changeInBattery;
        if (this.batteryLevel > 100){
            this.batteryLevel = 100;
        } else if (this.batteryLevel < 0){
            this.batteryLevel = 0;
        }
    }

    /**
     * Boolean to check if the flashlight is on.
     * @return true if flashlight is on and false if otherwise.
     */
    public boolean isOn(){
        return this.isOn;
    }

    /**
     * Helps in turning on the flashlight under some conditions.
     * @param player the player to turn on the flashlight.
     */
    public void turnOn(Player player){
        if (player.isHolding(this)){
            if (this.isOn){
                throw new RuntimeException("The flashlight is already on.");
            } else{
                this.isOn = true;
                System.out.println("The flashlight is now on.");
            }
        } else{
            throw new RuntimeException("You're not holding the flashlight.");
        }
    }

    /**
     * Turning off the flashlight.
     * @param player the user of the flashlight.
     */
    public void turnOff(Player player){
        if (player.isHolding(this)){
            if (this.isOn){
                this.isOn = false;
                System.out.println("The flashlight is now off.");
            } else{
                throw new RuntimeException("The flashlight is already off.");
            }
        } else{
            throw new RuntimeException("You're not holding the flashlight.");
        }
    }

    /**
     * Go string to print the Battery status.
     * @return String stating battery status.
     */
    public String toString(){  
        return("Your flashlight battery is at " + this.batteryLevel + ".");
    }
}
