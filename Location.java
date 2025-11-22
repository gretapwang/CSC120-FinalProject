import java.util.ArrayList;
public class Location {
    private Location eastAdjacent;
    private Location westAdjacent;
    private Location northAdjacent;
    private Location southAdjacent;
    private ArrayList<Grabbable> inventory;
    private boolean hasBeenVisited;

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
}
