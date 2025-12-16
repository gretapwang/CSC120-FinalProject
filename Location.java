import java.util.ArrayList;
/**
 * Contains information about a location in the game.
 * @author Greta Wang, Diane Tuyizere, Alexandra Huayta.
 * @version 12/11/2025.
 */
public class Location {
    private String initialMessage;
    private String returnMessage;
    private ArrayList<Grabbable> inventory;
    private boolean hasBeenVisited;
    private int numMonsters;
    private boolean isOutside;

    /**
     * Full constructor.
     * @param initialMessage Message to print the first time Player arrives.
     * @param returnMessage Message to print at subsequent arrivals.
     * @param isOutside True if the Location is outside the cave, false otherwise.
     * @param numMonsters Number of monsters at this Location..
     */
    public Location(String initialMessage, String returnMessage, boolean isOutside, int numMonsters){
        if (numMonsters < 0){
            throw new RuntimeException("Cannot initialize a negative number of monsters.");
        }
        this.initialMessage = initialMessage;
        this.returnMessage = returnMessage;
        this.isOutside = isOutside;
        this.numMonsters = numMonsters;
        this.inventory = new ArrayList<Grabbable>();
        this.hasBeenVisited = false;
    }

    /**
     * Overloaded constructor without numMonsters. automatically sets Location to have 0 monsters.
     * @param initialMessage Message to print the first time Player arrives.
     * @param returnMessage Message to print at subsequent arrivals.
     * @param isOutside True if the Location is outside the cave, false otherwise.
     */
    public Location(String initialMessage, String returnMessage, boolean isOutside){
        this(initialMessage, returnMessage, isOutside, 0);
    }

    /**
     * Getter for numMonsters.
     * @return Number of monsters at the Location.
     */
    public int getNumMonsters(){
        return this.numMonsters;
    }

    /**
     * Setter for numMonsters.
     * @param num Number of monsters to set at the Location.
     */
    public void setNumMonsters(int num){
        this.numMonsters = num;
    }

    /**
     * Getter for isOutside.
     * @return True if the Location is outside the cave, false otherwise.
     */
    public boolean isOutside(){
        return this.isOutside;
    }

    /**
     * Prints an appropriate message for Player's arrival, depending on whether the Location has been visited before.
     * If visiting for the first time, sets hasBeenVisited to true.
     */
    public void printArrivalMessage(){
        if (this.hasBeenVisited){
            System.out.println(this.returnMessage);
            if (this.numMonsters > 0){
                System.out.println("There are " + this.numMonsters + " remaining here.");
            }
        } else{
            System.out.println(this.initialMessage);
            this.hasBeenVisited = true;
        }
    }

    /**
     * Returns true if the given Grabbable is in the Location's inventory, false otherwise.
     * @param item Grabbable to check status of.
     * @return True if item is in inventory, false otherwise.
     */
    public boolean hasInInventory(Grabbable item){
        return this.inventory.contains(item);
    }

    /**
     * Adds the given Grabbable to the Location's inventory.
     * @param item Grabbable to be added to inventory.
     */
    public void addToInventory(Grabbable item){
        if (this.hasInInventory(item)){
            throw new RuntimeException("The item is already in inventory.");
        } else{
            this.inventory.add(item);
        }
    }

    /**
     * Removes the given Grabbable from the Location's inventory.
     * @param item Grabbable to remove from inventory.
     */
    public void removeFromInventory(Grabbable item){
        if (this.hasInInventory(item)){
            this.inventory.remove(item);
        } else{
            throw new RuntimeException("The item is not in the inventory.");
        }
    }

    /**
     * Decreases number of monsters by the given amount, if possible.
     * @param numKilled Number of monsters to kill.
     */
    public void killMonsters(int numKilled){
        if (numKilled < 0){
            throw new RuntimeException("You can't kill a negative number of monsters;");
        } else if (numKilled > this.numMonsters){
            throw new RuntimeException("There aren't " + numKilled + " monsters here to kill.");
        }
        this.numMonsters -= numKilled;
        System.out.println("You have killed " + numKilled + " monsters. \nThere are " + this.numMonsters + " remaining.");
    }
}
