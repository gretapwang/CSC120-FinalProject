abstract class Grabbable { 
    protected String name;

    public Grabbable(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void pickUp(Player player, Location location){
        player.addToInventory(this);
        location.removeFromInventory(this);
        System.out.println("You have picked up the " + this.name + ".");
    }
    public void putDown(Player player, Location location){
        player.removeFromInventory(this);
        location.addToInventory(this);
        System.out.println("You have put down the " + this.name + ".");
    }
}
