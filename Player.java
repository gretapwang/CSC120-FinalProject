import java.util.ArrayList;
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
        this.hasLost = false;
        this.hasWon = false;
    }

    // I rearranged some methods a bit just to put them in a more intuitive order, instead of the order we wrote them in

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
    }

    public void printInventory(){
        if (this.inventory.isEmpty()){
            System.out.println("You're not holding any items.");
        } else{
            System.out.println("Here are the items you're holding:");
            for (int i = 0; i < this.inventory.size(); i++){
            System.out.println(" + " + this.inventory.get(i).getName());
        }
        }
    }

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

    public void spendEnergy(int energyUsed){
        if (energyUsed < 0){
            throw new RuntimeException("Cannot spend negative energy.");
        } else{
            this.energy -= energyUsed;
            if (this.energy < 0){
                this.energy = 0;
            }
        }
    }

    public String toString(){
        return ("Your energy level is at " + this.energy + ".");
    }

    public void help(){
        System.out.println("Here are your available commands: \n - go [north/south/east/west] \n - turn [on/off] flashlight \n - change flashlight battery \n - view inventory \n - pick up [item] \n - put down [item] \n - eat [food] \n - drink water \n - open water bottle \n - close water bottle \n - kill monsters");
    }

    public void eat(Food food){
        if (this.isHolding(food)){
            this.energy += food.getEnergyGain();
            this.removeFromInventory(food);
            System.out.println("You ate the " + food.getName() + ". Energy +" + food.getEnergyGain());
        } else{
            throw new RuntimeException("You're not holding the " + food.getName() + ".");
        }
    }

    public void drink(WaterBottle water){
        if (this.isHolding(water)){
            if (water.isOpen()){
                if (water.isFull()){
                    this.energy += water.getEnergyGain();
                    water.emptyOut();
                    System.out.println("You drank the water. Energy +" + water.getEnergyGain());
                } else{
                    throw new RuntimeException("You have the " + water.getName() + ", but it's empty.");
                }
            } else{
                throw new RuntimeException("The " + water.getName() + " is closed.");
            }
        } else{
            throw new RuntimeException("You're not holding the " + water.getName() + ".");
        }
    }

    public void arrive(Location newLocation, Flashlight flashlight){
        this.activeLocation = newLocation;
        this.spendEnergy(2);
        if (this.isHolding(flashlight) && flashlight.isOn()){
            newLocation.printArrivalMessage();
        } else{
            System.out.println("You've arrived somewhere, but it's too dark to see.");
        }
    }

    public void pickUp(Grabbable item){
        if (this.isHolding(item)){
            throw new RuntimeException("You're already holding the " + item.getName() + ".");
        } else if (this.activeLocation.hasInInventory(item)){
            if (this.activeLocation.getNumMonsters() == 0){
                this.addToInventory(item);
                this.activeLocation.removeFromInventory(item);
                System.out.println("You have picked up the " + item.getName() + ".");
            } else{
                throw new RuntimeException("The monsters are guarding the " + item.getName() + "! \nThey growl at you when you get close.");
            }
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

    public void changeFlashlightBattery(Flashlight flashlight, Battery battery){
        if (this.isHolding(flashlight)){
            if (this.isHolding(battery)){
                flashlight.setBatteryLevel(battery.getPower());
                this.removeFromInventory(battery);
                System.out.println("Battery changed.");
            } else{
                throw new RuntimeException("You are not holding a battery.");
            }
        } else{
            throw new RuntimeException("You are not holding the flashlight.");
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
        //initializing locations and grabbable items
        Location startingRoom = new Location("The space appears to be a cave. \nTo the south and west, there are dark passages - you hear faint noises to the south. \nSome vague footprints trail off to the east, and there appears to be light in the distance. \nTo the north is a wall.", "You're back where you started.", false);
        Location monsterRoom = new Location("You've entered a foul-smelling part of the cave. \nLooming in the darkness are the glowing eyes of monsters! \nIt looks like there are roughly 100. \nIn the far corner, you see the gleam of treasure, but the monsters snarl when you try to get closer. \nDark paths lead off to the north and east, and you see a bit of light to the west.", "You're in the spot where you found the monsters and treasure.", false, 117);
        Grabbable treasure = new Grabbable("treasure");
        monsterRoom.addToInventory(treasure);
        Location resourceRoom = new Location("You reach a spot where the dirt underfoot is packed down heavily with footprints - \nperhaps someone's old hideout, but it seems to be abandoned. \nScattered on the ground are a few remnants of human inhabitance: \na granola bar, a water bottle, and a knife. \nThe paths get brighter to the north and east; \nto the south and west, they are dark.", "You're in the area with the footprints, where you found supplies.", false);
        Food granolaBar = new Food("granola bar", 50);
        resourceRoom.addToInventory(granolaBar);
        WaterBottle waterBottle = new WaterBottle("water bottle", 30);
        resourceRoom.addToInventory(waterBottle);
        Grabbable knife = new Grabbable("knife");
        resourceRoom.addToInventory(knife);
        Location exit1 = new Location("You emerge from the cave onto a quiet beach, with gently rolling waves. You've escaped! \nTo win the game, you need to find the treasure in the cave and bring it outside. \nThere are paths back into the cave to the south and west. \nIn other directions, the beach extends as far as you can see - not much worth exploring there.", "You are outside, on the beach.", true);
        Location southOfExit1 = new Location("You reach a small and empty clearing. \nThere is bright light to the north. \nUpon examining the ground, you can make out a few footprints leading to the west. \nTo the south is darkness and strange grumbling noises.", "You're on a familiar path, with light to the north, footprints to the west, and darkness to the south.", false);
        Location westOfExit1 = new Location("You navigate a long, rocky path and eventually come to a bend. \nTo the east, you can vaguely make out an opening with bright sunlight shining through. \nTo the south, footprints trail off in the distance.", "You've been in this passage before. \nThere is light to the east, and footprints to the south.", false);
        Location resourceMonsterRoom = new  Location("You find yourself in a corner of the cave. \nYou can barely make out the shapes of about 20 monsters waiting in the dark. \nOn the ground between you and the monsters, you see a small battery and a bag of dried fruit. \nThe monsters aren't blocking your path to the north or west - you can escape in these directions.", "You're in the corner where you previously found monsters and supplies.", false, 23);
        Battery battery = new Battery("battery", 100);
        resourceMonsterRoom.addToInventory(battery);
        Food driedFruit = new Food("dried fruit", 40);
        resourceMonsterRoom.addToInventory(driedFruit);
        Location southOfResource = new Location("You're in a dark, cramped space. \nThere are paths to the east, west, and north - \nstrange, muffled noises can be heard to the east and west, but the north is silent.", "You've returned to a cramped space, where there are noises to the east and west and a silent path to the north.", false);
        Location exit2 = new Location("The path grows wider as you walk, and you soon find yourself leaving the cave! \nThe outside world looks barren - nothing but flat, sandy ground. \nTo win the game, you need to find the treasure in the cave and bring it outside. \nThere are paths back inside to the south and east.", "You're outside, on the vast sandy plains.", true);
        Location birdRoom = new Location("You encounter a fork in the passage. \nYou see light to the west and south, and a dark trail to the east. \nYou think you hear birdsong, but it's hard to say where it's coming from.", "You find yourself back in the passage where birds can be heard. \nThere is light to the west and south, and a dark path to the east.", false);
        Location southOfExit2 = new Location("You make your way to a small corner of the cave, where bright sunlight is visible down a path to the north. \nThere is also a dark path to the east.", "You once again find yourself at a sharp turn, with light to the north and darkness to the east.", false);
        Location westOfMonsters = new Location("You're on a long path. \nThere is light to the west, and to the east there is darkness and some unidentifiable noises. \nYou also hear noises down a path to the north - these ones sound like birdsong.", "You're on a familiar path. \nThere is light to the west, and strange noises to the east. \nThe path also branches off to the north; you hear faint birdsong coming from this direction.", false);

        //setting up map of locations
        Map map = new Map(5, 3);
        map.add(startingRoom, 2, 1);
        map.add(monsterRoom, 2, 2);
        map.add(resourceRoom, 3, 1);
        map.add(exit1, 4, 0);
        map.add(southOfExit1, 4, 1);
        map.add(westOfExit1, 3, 0);
        map.add(resourceMonsterRoom, 4, 2);
        map.add(southOfResource, 3, 2);
        map.add(exit2, 0, 1);
        map.add(birdRoom, 1, 1);
        map.add(southOfExit2, 0, 2);
        map.add(westOfMonsters, 1, 2);

        //initializing player & printing initial messages
        Player player = new Player(startingRoom);
        Flashlight flashlight = new Flashlight("flashlight", 50);
        player.addToInventory(flashlight);
        System.out.println("\nYou're in a very dark, cold space. The air smells musty. \nYou're holding something - upon inspection, you realize it's a flashlight.");
        System.out.println("Stats: \n - " + player + "\n - " + flashlight);
        System.out.println("At any time, type 'help' to see your options.\n");
        Scanner input = new Scanner(System.in);

        //gameplay
        do{
            System.out.print(">> ");
            String userInput = input.nextLine().toLowerCase().strip();
            System.out.print("\n"); // added this at start and end of loop so the text will appear spaced out & easier to read
            try{
                //movement commands
                if (userInput.equals("go east") || userInput.equals("go west") || userInput.equals("go south") || userInput.equals("go north")){
                    player.arrive(map.getNewLocation(player.getActiveLocation(), userInput), flashlight);

                //help commands
                } else if (userInput.equals("help")){
                    player.help();
                } else if (userInput.equals("view inventory")){
                    player.printInventory();

                //killing monsters command
                } else if (userInput.equals("kill monsters")){
                    if (player.getActiveLocation().getNumMonsters() == 0){
                        throw new RuntimeException("There are no monsters here to kill.");
                    } else{
                        if (player.isHolding(knife)){
                            System.out.println("How many do you want to kill? Warning: killing monsters might take a lot of energy!\n");
                            System.out.print(">> ");
                            int numToKill = input.nextInt();
                            input.nextLine();
                            System.out.print("\n");
                            player.getActiveLocation().killMonsters(numToKill);
                            player.spendEnergy(numToKill/2);
                        } else{
                            throw new RuntimeException("You don't have a weapon.");
                        }
                    }
                    
                //flashlight-specific commands
                } else if (userInput.equals("turn on flashlight")){
                    flashlight.turnOn(player);//for simplicity, I made it so the turnon method checks that the player is holding it - Greta
                    player.getActiveLocation().printArrivalMessage(); //changed this from using arrive method to printarrival message, to avoid side effect of decreasing energy
                } else if (userInput.equals("turn off flashlight")){
                    flashlight.turnOff(player);//see comment for turnon
                } else if (userInput.equals("change flashlight battery")){
                    player.changeFlashlightBattery(flashlight, battery);

                //food/water-specific commands
                } else if (userInput.equals("eat granola bar")){
                    player.eat(granolaBar);
                } else if (userInput.equals("eat dried fruit")){
                    player.eat(driedFruit);
                } else if (userInput.equals("open water bottle")){
                    waterBottle.open(player);
                } else if (userInput.equals("close water bottle")){
                    waterBottle.close(player);
                } else if (userInput.equals("drink water")){
                    player.drink(waterBottle);

                //pick up commands
                } else if (userInput.equals("pick up treasure")){
                    // this part used to have an if block to check there are no monsters, but we were going to have to do that for every pickup command, so I added it to the pickup method instead
                    player.pickUp(treasure);
                } else if (userInput.equals("pick up knife")){
                    player.pickUp(knife);
                } else if (userInput.equals("pick up granola bar")){
                    player.pickUp(granolaBar);
                } else if (userInput.equals("pick up water bottle")){
                    player.pickUp(waterBottle);
                } else if (userInput.equals("pick up battery")){
                    player.pickUp(battery);
                } else if (userInput.equals("pick up dried fruit")){
                    player.pickUp(driedFruit);
                } else if (userInput.equals("pick up flashlight")){
                    player.pickUp(flashlight);

                //put down commands
                } else if (userInput.equals("put down treasure")){
                    player.putDown(treasure);
                } else if (userInput.equals("put down knife")){
                    player.putDown(knife);
                } else if (userInput.equals("put down granola bar")){
                    player.putDown(granolaBar);
                } else if (userInput.equals("put down water bottle")){
                    player.putDown(waterBottle);
                } else if (userInput.equals("put down battery")){
                    player.putDown(battery);
                } else if (userInput.equals("put down dried fruit")){
                    player.putDown(driedFruit);
                } else if (userInput.equals("put down flashlight")){
                    player.putDown(flashlight);

                } else{
                    throw new RuntimeException("We don't understand what you said. Type 'help' to see valid commands.");
                }
                
                //updating and printing information after each turn
                if (flashlight.isOn()){
                    flashlight.updateBatteryLevel(-1);
                }
                System.out.println("Stats: \n - " + player + "\n - " + flashlight);
                player.updateGameStatus(flashlight, treasure);
            } catch (Exception e){
                System.out.println(e.getLocalizedMessage());
            }
            System.out.print("\n");
        } while (!player.hasLost && !player.hasWon);

        //printing ending message
        if (player.hasWon){
            System.out.println("Congratulations, you won! \nYou have escaped with the treasure.");
        } else{
            System.out.println("You lost. \nYou ran out of resources before you could escape with the treasure.");
        }
        input.close();
    }
}