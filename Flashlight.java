public class Flashlight extends Grabbable {
    private int batteryLevel;
    private boolean isOn;

    public Flashlight(String name){
        super(name);
        this.batteryLevel = 100;
        this.isOn = false;
    }
    
    public int getBatteryLevel(){
        return this.batteryLevel;
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

    public void turnOn(){
        if (this.isOn){
            throw new RuntimeException("The flashlight is already on.");
        } else{
            this.isOn = true;
        }
    }

    public void turnOff(){
        if (this.isOn){
            this.isOn = false;
        } else{
            throw new RuntimeException("The flashlight is already off.");
        }
    }

    public String toString(){
        return("Your flashlight battery is at " + this.batteryLevel + ".");
    }
}
