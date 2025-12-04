import java.util.ArrayList;
public class Location {
    private Location eastAdjacent;
    private Location westAdjacent;
    private Location northAdjacent;
    private Location southAdjacent;
    private String initialMessage;
    private String returnMessage;
    private ArrayList<Grabbable> inventory;
    private boolean hasBeenVisited;
    private int numMonsters;

    public Location(String initialMessage, String returnMessage, int numMonsters){
        if (numMonsters < 0){
            throw new RuntimeException("Cannot initialize a negative number of monsters.");
        }
        this.initialMessage = initialMessage;
        this.returnMessage = returnMessage;
        this.inventory = new ArrayList<Grabbable>();
        this.hasBeenVisited = false;
        this.numMonsters = numMonsters;
    }

    public Location(String initialMessage, String returnMessage){
        this.initialMessage = initialMessage;
        this.returnMessage = returnMessage;
        this.inventory = new ArrayList<Grabbable>();
        this.hasBeenVisited = false;
    }

    public int getNumMonsters(){
        return this.numMonsters;
    }

    public void visit(){
        this.hasBeenVisited = true;
    }

    public Location getNewLocation(String command){
        if (command.equals("go east")){
            return this.eastAdjacent;
        } else if (command.equals("go west")){
            return this.westAdjacent;
        } else if (command.equals("go north")){
            return this.northAdjacent;
        } else if (command.equals("go south")){
            return this.southAdjacent;
        } else{
            throw new RuntimeException("Invalid command.");
        }
    }

    public void printArrivalMessage(){
        if (this.hasBeenVisited){
            System.out.println(this.returnMessage);
        } else{
            System.out.println(this.initialMessage);
            this.hasBeenVisited = true;
        }
    }

    public void addToInventory(Grabbable item){
        if (this.inventory.contains(item)){
            throw new RuntimeException("The item is already in inventory.");
        } else{
            this.inventory.add(item);
        }
    }

    public void removeFromInventory(Grabbable item){
        if (this.inventory.contains(item)){
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
