/**  
* The class Food extends the Grabbable class to create food objects that give the player energy when food is eaten.
* @author [Greta Wang, Diane Tuyizere, Alexandra Huayta]
* @version December 11, 2025
*/
public class Food extends Grabbable {
    /** The attribute private int EnergyGain is a instance variable that stores the quantity of energy the food provides. */
    private int energyGain;

    /** 
    * Constructor for food class that initialized the food item with a name and energy gain value. 
    * @param name the name of the food item.
    * @param energyGain the amount of energy the food gives to player when food is eaten.
    */
    public Food(String name, int energyGain) {
        super(name);
        if (energyGain < 0){
            throw new RuntimeException("Cannot initialize food with negative energy.");
        }
        this.energyGain = energyGain;
    }

/** 
* getEnergyGain method returns the energy gain value of the food item when called.
* @return the amount of energy the food gives to the player. 
*/
    public int getEnergyGain() {
        return this.energyGain;
    }
}