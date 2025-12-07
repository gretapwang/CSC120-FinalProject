import java.util.ArrayList;
public class Map {
    private int nColumns;
    private int nRows;
    private ArrayList<ArrayList<Location>> map;
    private Location emptySpace = new Location("a", "b", false);

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

    public boolean hasLocation(Location location){
        int numOccurrences = 0;
        for (int i = 0; i < this.nRows; i++){
            if (this.map.get(i).contains(location)){
                numOccurrences += 1;
            }
        }
        return (numOccurrences > 0);
    }

    public void add(Location location, int column, int row){
        if (this.hasLocation(location)){
            throw new RuntimeException("Location already on map.");
        } else if (0 <= row && row < this.nRows && 0 <= column && column < this.nColumns){
            this.map.get(row).set(column, location);
        } else{
            throw new RuntimeException("Invalid map index.");
        }
    }

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

    public int getColumn(Location location){
        int row = this.getRow(location);
        return this.map.get(row).indexOf(location);
    }

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
