/**
 * Battery object contains an amount of power, which player can use to replenish their flashlight.
 * @author Greta Wang, Diane Tuyizere, Alexandra Huayta.
 * @version 12/11/2025.
 */
public class Battery extends Grabbable {
    private int power;

    /**
     * Constructor.
     * @param name Name of the Battery.
     * @param power Amount of energy stored.
     */
    public Battery(String name, int power){
        super(name);
        if (power < 0){
            throw new RuntimeException("Cannot initialize battery with negative charge.");
        }
        this.power = power;
    }

    /**
     * Getter for power.
     * @return Amount of power in the Battery.
     */
    public int getPower(){
        return this.power;
    }
}
