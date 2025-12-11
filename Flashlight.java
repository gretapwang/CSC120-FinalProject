public class Flashlight extends Grabbable {
    private int batteryLevel;
    private boolean isOn;

    public Flashlight(String name, int batteryLevel){
        super(name);
        this.batteryLevel = batteryLevel;
        this.isOn = false;
    }
    
    public int getBatteryLevel(){
        return this.batteryLevel;
    }

    public void setBatteryLevel(int newBatteryLevel){
        this.batteryLevel = newBatteryLevel;
    }

    public void updateBatteryLevel(int changeInBattery){
        this.batteryLevel += changeInBattery;
        if (this.batteryLevel > 100){
            this.batteryLevel = 100;
        } else if (this.batteryLevel < 0){
            this.batteryLevel = 0;
        }
    }

    public boolean isOn(){
        return this.isOn;
    }

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

    public String toString(){
        return("Your flashlight battery is at " + this.batteryLevel + ".");
    }
}
