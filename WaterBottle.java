public class WaterBottle extends Grabbable {

    private int energyGain;
    private boolean isOpen;
    private boolean isFull;

    public WaterBottle(String name, int energyGain) {
        super(name);
        if (energyGain < 0){
            throw new RuntimeException("Cannot initialize water with negative energy.");
        }
        this.energyGain = energyGain;
        this.isOpen = false; //I made water into waterbottle which can be opened/closed and emptied
        this.isFull = true;
    }

    public int getEnergyGain() {
        return this.energyGain;
    }

    public boolean isOpen(){
        return this.isOpen;
    }

    public boolean isFull(){
        return this.isFull;
    }

    public void emptyOut(){
        this.isFull = false;
    }

    public void open(Player player){
        if (player.isHolding(this)){
            if (this.isOpen){
                throw new RuntimeException("The water bottle is already open.");
            } else{
                this.isOpen = true;
                System.out.println("The water bottle is now open.");
            }
        } else{
            throw new RuntimeException("You're not holding the water bottle.");
        }
    }

    public void close(Player player){
        if (player.isHolding(this)){
            if (this.isOpen){
                this.isOpen = false;
                System.out.println("The water bottle is now closed.");
            } else{
                throw new RuntimeException("The water bottle is already closed.");
            }
        } else{
            throw new RuntimeException("You're not holding the water bottle.");
        }
    }
}
