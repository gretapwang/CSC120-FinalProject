import java.util.ArrayList;
import java.util.Scanner;
public class Player{
    private int energy;
    private int batteryLevel;
    private ArrayList<Grabbable> inventory;
    private Location activeLocation;
    private boolean isLost;
    private boolean isWon; // can we make this maybe "hasWon" or just "won"

    public Player(Location activeLocation){
        this.energy = 100;
        this.batteryLevel = 50;
        this.inventory = new ArrayList<Grabbable>();
        this.activeLocation = activeLocation;
        this.isLost = false;
        this.isWon = false;
    }

    public void replenishBatteryLevel(int power){// energy limit
        this.batteryLevel += power;
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
    }// Combine this two methods

    public void checkIfInInventory(Grabbable item){
        if (!this.inventory.contains(item)){
            throw new RuntimeException("You are not holding this item.");
        }
    }

    public Location getActiveLocation(){
        return this.activeLocation;                                                                                                                                                                                              
    }

    public String toString(){
        return ("Your energy level is at " + this.energy + " and your flashlight battery is at " + this.batteryLevel);
    }

    public void help(){
        System.out.println("Here are your available commands: \n go [east/south/west/north] \n turn [on/off] flashlight \n pick up [supply] \n put down [supply] \n eat [food] \n drink water \n kill monster");
    }

    public void arrive(Location newLocation, boolean lightOn){
        this.activeLocation = newLocation;
        if (lightOn){// Call by flashlight method like flashlight.ison()
            newLocation.printArrivalMessage();
        } else{
            System.out.println("You've arrived somewhere, but it's too dark to see. You need to turn your flashlight on.");
        }
    }


    public static void main(String[] args){
        Location startingRoom = new Location("starting room intro message", "starting room return message");
        Location monsterRoom = new Location("monster room intro message", "monster room return message");
        Location resourceRoom = new Location("resource room intro message", "resource room return message");
        Location prizeRoom = new Location("prize room intro message", "prize room return message");
        Player player = new Player(startingRoom);
        Flashlight flashlight = new Flashlight("flashlight");
        player.addToInventory(flashlight);
        System.out.println("You are in a dark room. It appears to be a cave. To the south and west, there are dark passages - you hear faint noises to the south. Some vague footprints trail off to the east, and there appears to be light in the distance. To the north is a wall. \n You are holding a flashlight, which is off.");
        System.out.println(player);
        System.out.println("At any time, type 'help' to see your options.");
        Scanner input = new Scanner(System.in);
        while (!player.isLost && !player.isWon){
            String userInput = input.nextLine();
            try{
                if (userInput.equals("go east") || userInput.equals("go west") || userInput.equals("go south") || userInput.equals("go north")){
                    Location newLocation = player.activeLocation.getNewLocation(userInput);
                    if (newLocation != null){
                        player.arrive(newLocation, flashlight.isOn());
                    } else{
                        throw new RuntimeException("There is no path in that direction.");
                    }
                } else if (userInput.equals("turn on flashlight")){
                    player.checkIfInInventory(flashlight);
                    flashlight.turnOn();
                } else if (userInput.equals("turn off flashlight")){
                    player.checkIfInInventory(flashlight);
                    flashlight.turnOff();
                } // add rest of commands
                else if( userInput.equals("help")){
                    player.help();
                }
                // I think the next thing to do is to go and write some codes specifically that will comply with each associated location. 
            } catch (Exception e){
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
}