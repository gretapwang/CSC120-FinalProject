/**
 * this is the footprint of the grabbable items in the game.
 * @author Greta Wang, Diane Tuyizere, Alexandra Huayta.
 * @version 12/11/2025.
 */
public class Grabbable {

    protected String name;



    /**
    * the constructor
    * @param name
    */
    public Grabbable(String name) {
        this.name = name;
    }

    /**
    * the getter method to help accessing the name.
    * @return the item's name.
    */
    public String getName() {
        return this.name;
    }
}

