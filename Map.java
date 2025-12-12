import java.util.ArrayList;
/**
 * Stores the game's layout in a 2D ArrayList of Locations
 * @author Greta Wang, Diane Tuyizere, Alexa Huayta
 * @version 12/11/2025
 */
public class Map {
    private int nColumns;
    private int nRows;
    private ArrayList<ArrayList<Location>> map;
    private Location emptySpace = new Location("a", "b", false);

    /**
     * Constructor 
     * @param nColumns Number of columns on the map
     * @param nRows Number of rows on the map
     */
    public Map(int nColumns, int nRows){
        this.nColumns = nColumns;
        this.nRows = nRows;
        this.map = new ArrayList<ArrayList<Location>>();
        for (int i = 0; i < nRows; i++){
            this.map.add(new ArrayList<Location>());
            for (int j = 0; j < nColumns; j++){
                this.map.get(i).add(this.emptySpace);
            }
        }
    }

    /**
     * Returns true if the given Location is on the Map, false otherwise
     * @param location Location to check status of
     * @return True if location is on map, false otherwise
     */
    public boolean hasLocation(Location location){
        int numOccurrences = 0;
        for (int i = 0; i < this.nRows; i++){
            if (this.map.get(i).contains(location)){
                numOccurrences += 1;
            }
        }
        return (numOccurrences > 0);
    }

    /**
     * Adds the given Location to the Map at the specified position, unless it is already on the Map
     * @param location Location to add to Map
     * @param column Column number to add Location at
     * @param row Row number to add Location at
     */
    public void add(Location location, int column, int row){
        if (this.hasLocation(location)){
            throw new RuntimeException("Location already on map.");
        } else if (0 <= row && row < this.nRows && 0 <= column && column < this.nColumns){
            this.map.get(row).set(column, location);
        } else{
            throw new RuntimeException("Invalid map index.");
        }
    }

    /**
     * Returns the row number where the given Location is located on the Map
     * @param location Location to get row for
     * @return Row number of the Location
     */
    public int getRow(Location location){
        if (this.hasLocation(location)){
            int row = 0;
            for (int i = 0; i < this.nRows; i++){
                if (this.map.get(i).contains(location)){
                    row = i;
                }
            }
            return row;
        } else{
            throw new RuntimeException("Location not on map.");
        }
    }

    /**
     * Returns the column number where the given Location is located on the Map
     * @param location Location to get column for
     * @return Column number of the Location
     */
    public int getColumn(Location location){
        int row = this.getRow(location);
        return this.map.get(row).indexOf(location);
    }

    /**
     * Given a Location on the Map and a direction, returns the adjacent Location in that direction, or throws a RuntimeException if there is none
     * @param oldLocation Location to move from
     * @param userInput String indicating direction to move in
     * @return Adjacent Location in the specified direction
     */
    public Location getNewLocation(Location oldLocation, String userInput){
        int row = this.getRow(oldLocation);
        int column = this.getColumn(oldLocation);
        if (userInput.equals("go north")){
            row -= 1;
        } else if (userInput.equals("go south")){
            row += 1;
        } else if (userInput.equals("go east")){
            column += 1;
        } else if (userInput.equals("go west")){
            column -= 1;
        } else{
            throw new RuntimeException("Invalid command.");
        }
        if (0 <= row && row < this.nRows && 0 <= column && column < this.nColumns){
            if (this.map.get(row).get(column) != this.emptySpace){
                return this.map.get(row).get(column);
            } else{
                throw new RuntimeException("There is no path in that direction.");
            }
        } else{
            throw new RuntimeException("There is no path in that direction.");
        }
    }
}
