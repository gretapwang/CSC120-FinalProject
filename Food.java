public class Food extends Grabbable {

    private int energyGain;

    public Food(String name, int energyGain) {
        super(name);
        if (energyGain < 0){
            throw new RuntimeException("Cannot initialize food with negative energy.");
        }
        this.energyGain = energyGain;
    }

    public int getEnergyGain() {
        return this.energyGain;
    }
}