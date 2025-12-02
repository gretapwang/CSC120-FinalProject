public class Flashlight extends Grabbable {
    private boolean isOn;

    public Flashlight(String name){
        super(name);
        this.isOn = false;
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
}
