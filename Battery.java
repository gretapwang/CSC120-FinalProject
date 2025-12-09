public class Battery extends Grabbable {
    private int power;

    public Battery(String name, int power){
        super(name);
        if (power < 0){
            throw new RuntimeException("Cannot initialize battery with negative charge.");
        }
        this.power = power;
    }

    public int getPower(){
        return this.power;
    }
}
