import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Player{
    private int energy;
    private ArrayList<Grabbable> inventory;
    private Location activeLocation;
    private boolean hasLost;
    private boolean hasWon;

    public Player(Location activeLocation){
        this.energy = 100;
        this.inventory = new ArrayList<Grabbable>();
        this.activeLocation = activeLocation;
        activeLocation.visit();
        this.hasLost = false;
        this.hasWon = false;
    }

    public void addToInventory(Grabbable item){
        if (this.isHolding(item)){
            throw new RuntimeException("You're already holding the " + item.getName() + ".");
        } else{
            this.inventory.add(item);
        }
    }

    public void removeFromInventory(Grabbable item){
        if (this.isHolding(item)){
            this.inventory.remove(item);
        } else{
            throw new RuntimeException("You're not holding the " + item.getName() + ".");
        }
    }// Combine this two methods

    public boolean isHolding(Grabbable item){
        if (this.inventory.contains(item)){
            return true;
        } else{
            return false;
        }
    }

    public Location getActiveLocation(){
        return this.activeLocation;                                                                                                                                                                                              
    }

    public String toString(){
        return ("Your energy level is at " + this.energy + ".");
    }

    public void help(){
        System.out.println("Here are your available commands: \n go [east/south/west/north] \n turn [on/off] flashlight \n pick up [supply] \n put down [supply] \n eat [food] \n drink water \n kill monsters");
    }

    public void arrive(Location newLocation, Flashlight flashlight){
        this.activeLocation = newLocation;
        newLocation.visit();
        if (this.isHolding(flashlight) && flashlight.isOn()){
            newLocation.printArrivalMessage();
        } else if (this.isHolding(flashlight)){
            System.out.println("You've arrived somewhere, but it's too dark to see. You need to turn your flashlight on.");
        } else{
            System.out.println("You've arrived somewhere, but it's too dark to see and you don't have your flashlight.");
        }
    }

    public void pickUp(Grabbable item){
        if (this.isHolding(item)){
            throw new RuntimeException("You're already holding the " + item.getName() + ".");
        } else if (this.activeLocation.hasInInventory(item)){
            this.addToInventory(item);
            this.activeLocation.removeFromInventory(item);
            System.out.println("You have picked up the " + item.getName() + ".");
        } else{
            throw new RuntimeException("The " + item.getName() + " is not at your current location.");
        }
    }

    public void putDown(Grabbable item){
        if (this.isHolding(item)){
            this.removeFromInventory(item);
            this.activeLocation.addToInventory(item);
            System.out.println("You have put down the " + item.getName() + ".");
        } else{
            throw new RuntimeException("You aren't holding the " + item.getName() + ".");
        }
    }

    public void updateGameStatus(Flashlight flashlight, Grabbable treasure){
        if (this.activeLocation.isOutside() && this.isHolding(treasure)){
            this.hasWon = true;
        } else if (this.energy == 0 || flashlight.getBatteryLevel() == 0){
            this.hasLost = true;
        }
    }


    public static void main(String[] args){
        Location startingRoom = new Location("starting room intro message (not used)", "You're back where you started.", false);
        Location monsterRoom = new Location("You've entered a foul-smelling part of the cave. Looming in the darkness are the glowing eyes of 100 monsters! In the far corner, you see the gleam of a jewel, but the monsters snarl when you try to get closer. Dark paths lead off to the north and east, and you see a bit of light to the west.", "You're in the spot where you found the monsters and treasure.", false, 100);
        Grabbable treasure = new Grabbable("treasure");
        monsterRoom.addToInventory(treasure);
        Location resourceRoom = new Location("You reach a spot where the dirt underfoot is packed down heavily with footprints - perhaps someone's old hideout, but it seems to be abandoned. Scattered on the ground are a few remnants of human inhabitance: a [food], a bottle of water, and a knife. The paths get brighter to the north and east; to the south and west, they are dark.", "You're in the space with the footprints, where you found supplies.", false);
        //once we have food and water classes, will initialize food and water and add to resourceRoom inventory
        Grabbable knife = new Grabbable("knife");
        resourceRoom.addToInventory(knife);
        Location exit1 = new Location("You emerge from the cave onto a quiet beach, with gently rolling waves. You've escaped! To win the game, you need to find the treasure in the cave and bring it outside. There are paths back into the cave to the south and west. In other directions, the beach extends as far as you can see - not much worth exploring there.", "You are outside, on the beach.", true);
        Location southOfExit1 = new Location("You reach a small and empty clearing. There is bright light to the north. Upon examining the ground, you can make out a few footprints leading to the west.", "You're on a familiar path, with light to the north and footprints to the west.", false);
        Location westOfExit1 = new Location("You navigate a long, rocky path and eventually come to a bend. To the east, you can vaguely make out an opening with bright sunlight shining through. To the south, footprints trail off in the distance.", "You've been in this passage before. There is light to the east, and footprints to the south.", false);
        Location southOfResource = new Location("You're in a dark, cramped space. There are paths to the west and north - strange, muffled noises can be heard to the west, but the north is silent.", "You've returned to a cramped space, where there are noises to the west and a silent path to the north.", false);
        Location exit2 = new Location("The path grows wider as you walk, and you soon find yourself exiting the cave! The outside world looks barren - nothing but flat, sandy ground in every direction. To win the game, you need to find the treasure in the cave and bring it outside. There are paths back inside to the south and east.", "You're outside, on the vast sandy plains.", true);
        Location birdRoom = new Location("You encounter a fork in the passage. You see light to the west and south, and a dark trail to the east. You think you hear birdsong, but it's hard to say where it's coming from.", "You find yourself back in the passage where birds can be heard. There is light to the west and south, and a dark path to the east.", false);
        Location southOfExit2 = new Location("You make your way to a small corner of the cave, where bright sunlight is visible down a path to the north. There is also a dark path to the east.", "You once again find yourself at a sharp turn, with light to the north and darkness to the east.", false);
        Location westOfMonsters = new Location("You're on a long path. There is light to the west, and to the east there is darkness and some unidentifiable noises. You also hear noises down a path to the north - these ones sound like birdsong.", "You're on a familiar path, with sunlight to the west. You hear noises coming from the north and east paths. The north sounds like birds, while the east is muffled and hard to indentify.", false);

        //I think we should make the room setup a bit more complicated - choose some other rooms to have supplies and/or monsters.

        Map map = new Map(5, 3);
        map.add(startingRoom, 2, 1);
        map.add(monsterRoom, 2, 2);
        map.add(resourceRoom, 3, 1);
        map.add(exit1, 4, 0);
        map.add(southOfExit1, 4, 1);
        map.add(westOfExit1, 3, 0);
        map.add(southOfResource, 3, 2);
        map.add(exit2, 0, 1);
        map.add(birdRoom, 1, 1);
        map.add(southOfExit2, 0, 2);
        map.add(westOfMonsters, 1, 2);

        Player player = new Player(startingRoom);
        Flashlight flashlight = new Flashlight("flashlight");
        player.addToInventory(flashlight);
        System.out.println("You are in a dark room. It appears to be a cave. To the south and west, there are dark passages - you hear faint noises to the south. Some vague footprints trail off to the east, and there appears to be light in the distance. To the north is a wall. \nYou are holding a flashlight, which is off.");
        System.out.println(player);
        System.out.println(flashlight);
        System.out.println("At any time, type 'help' to see your options.");
        Scanner input = new Scanner(System.in);

        do{
            String userInput = input.nextLine().toLowerCase();
            try{
                if (userInput.equals("go east") || userInput.equals("go west") || userInput.equals("go south") || userInput.equals("go north")){
                    player.arrive(map.getNewLocation(player.activeLocation, userInput), flashlight);

                } else if (userInput.equals("turn on flashlight")){
                    if(player.isHolding(flashlight)){
                        flashlight.turnOn();// turning on the flashlight
                        System.out.println("The flashlight is now on");//the flashlight is on, but nothing else is orientating them. 

                        player.arrive(player.getActiveLocation(), flashlight);// reprint the arrival message
                        }
                    }
                else if (userInput.equals("turn off flashlight")){
                    if (player.isHolding(flashlight)){
                        flashlight.turnOff();
                    }
                } // add rest of commands
                else if( userInput.equals("help")){
                    player.help();

                }
                else if(userInput.equals("pick up food")){
                    player.pickUp(new Grabbable("food"));
                }
                else if(userInput.equals("pick up water")){
                    player.pickUp(new Grabbable("water"));
                }
                else if(userInput.equals("kill monsters")){
                    if (player.activeLocation== monsterRoom){
                       // monsterRoom.killMonsters(num); we need to discuss about this
                    if (player.isHolding(knife)){
                        System.out.println("ooh how many do you want to kill?");
                        int a=Integer.parseInt(userInput);
                        monsterRoom.killMonsters(a);}
                        else{
                            System.out.println("There are no monsters here to kill. Try another command to keep playing");

                        }
                    }
                }
                else if( userInput.equals("pick up treasure")){
                    player.pickUp(treasure);
                    Random rand = new Random();
                    int b= rand.nextInt(96) + 5; 
                
                    player.activeLocation.setNumMonsters(b);
                    /// Need to  modify this so that once player picks up the treasures, then the monsters appeas and player needs to kill them.

                }
                else if( userInput.equals("pick up knife")){
                    player.pickUp(knife);
                }
                //puting down grabbables commands
                else if( userInput.equals("put down treasure")){
                    player.putDown(treasure);
                }
                else if( userInput.equals("put down knife")){
                    player.putDown(knife);
                }
            
                else{
                    System.out.println("We do not understand what you said. Try Again ");
                }
            
                // I think the next thing to do is to go and write some codes specifically that will comply with each associated location. 
            } catch (Exception e){
                System.out.println(e.getLocalizedMessage());
            }
            System.out.println(player);
            System.out.println(flashlight);
            player.updateGameStatus(flashlight, treasure);
        } while (!player.hasLost && !player.hasWon);
    }
}