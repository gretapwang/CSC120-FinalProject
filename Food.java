public class Food extends Grabbable {

    private int energyGain;

    public Food(String name, int energyGain) {
        super(name);
        this.energyGain = energyGain;
    }

    public int getEnergyGain() {
        return this.energyGain;
    }
}