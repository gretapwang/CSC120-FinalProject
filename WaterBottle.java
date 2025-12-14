/** 
* The class WaterBottle extends the Grabbable class to create water bottle object that gives the player energy when water is drunk.
* @author [Greta Wang, Diane Tuyizere, Alexandra Huayta]
* @version December 11, 2025
 */

public class WaterBottle extends Grabbable {
    /**
    * The attribute private int Energy Gain is a instance variable that stores the quantity of energy the water gives.
    * The attribute private boolen isOpen is an instance variable that stores whether the water bottle is open or closed.
    * The attribute private boolean isFull is an instance variable that stores whether the water bottle is full or empty.
    */
    private int energyGain;
    private boolean isOpen;
    private boolean isFull;

    /** 
    * Constructor for water bottle class that initialized the water bottle item with a name and energy gain value.
    * @param name the name of the water bottle item.
    * @param energyGain the amount of energy the water gives to player when water is drunk.
    */
    public WaterBottle(String name, int energyGain) {
        super(name);
        if (energyGain < 0){
            throw new RuntimeException("Cannot initialize water with negative energy.");
        }
        this.energyGain = energyGain;
        this.isOpen = false; //I made water into waterbottle which can be opened/closed and emptied
        this.isFull = true;
    }
    /** 
    * getEnergyGain method returns the energy gain value of the water bottle when called.
    * @return the amount of energy the water bottle gives to the player/
    */
    public int getEnergyGain() {
        return this.energyGain;
    }

    /** 
    * isOpen method returns whether the water bottle is open or closed when called.
    * @return the boolean value of isOpen attribute.
    */
    public boolean isOpen(){
        return this.isOpen;
    }

    /** 
    * isFull method returns whether the water bottle is full or empty when called.
    * @return the boolean value of isFullattribute.
    */
    public boolean isFull(){
        return this.isFull;
    }

    /** 
    * isEmptyOut method makes the water bottle empty when called.
    * @return the boolean value of isFull attribute as false.
    */
    public void emptyOut(){
        this.isFull = false;
    }

    /** 
    * @param player the player object that is trying to open the water bottle.
    * open method opens the water bottle when called if the player is holding the water bottle.
    */
    public void open(Player player){
        if (player.isHolding(this)){
            if (this.isOpen){
                throw new RuntimeException("The water bottle is already open.");
            } else{
                this.isOpen = true;
                System.out.println("The water bottle is now open.");
            }
        } else{
            throw new RuntimeException("You're not holding the water bottle.");
        }
    }

    /** 
    * @param player the player object that is trying to close the water bottle.
    * close method closes the water bottle when called if the player is holding the water bottle.
    */
    public void close(Player player){
        if (player.isHolding(this)){
            if (this.isOpen){
                this.isOpen = false;
                System.out.println("The water bottle is now closed.");
            } else{
                throw new RuntimeException("The water bottle is already closed.");
            }
        } else{
            throw new RuntimeException("You're not holding the water bottle.");
        }
    }
}
