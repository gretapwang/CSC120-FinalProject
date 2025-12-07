import java.util.ArrayList;
public class Location {
    private String initialMessage;
    private String returnMessage;
    private ArrayList<Grabbable> inventory;
    private boolean hasBeenVisited;
    private int numMonsters;
    private boolean isOutside;

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

    public Location(String initialMessage, String returnMessage, boolean isOutside){
        this(initialMessage, returnMessage, isOutside, 0);
    }

    public int getNumMonsters(){
        return this.numMonsters;
    }
    public void setNumMonsters(int num){//diane added this since we needed 
        this.numMonsters = num;
    }

    public boolean isOutside(){
        return this.isOutside;
    }

    public void visit(){
        this.hasBeenVisited = true;
    }

    public void printArrivalMessage(){
        if (this.hasBeenVisited){
            System.out.println(this.returnMessage);
        } else{
            System.out.println(this.initialMessage);
            this.hasBeenVisited = true;
        }
    }

    public boolean hasInInventory(Grabbable item){
        return this.inventory.contains(item);
    }

    public void addToInventory(Grabbable item){
        if (this.hasInInventory(item)){
            throw new RuntimeException("The item is already in inventory.");
        } else{
            this.inventory.add(item);
        }
    }

    public void removeFromInventory(Grabbable item){
        if (this.hasInInventory(item)){
            this.inventory.remove(item);
        } else{
            throw new RuntimeException("The item is not in the inventory.");
        }
    }

    public void killMonsters(int numKilled){
        if (numKilled > this.numMonsters){
            throw new RuntimeException("There aren't " + numKilled + " monsters here to kill.");
        }
        this.numMonsters -= numKilled;
    }
}
