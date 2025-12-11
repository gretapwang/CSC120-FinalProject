public class Grabbable {

    protected String name;

    public Grabbable(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // ------------------------- Food -------------------------
    public static class Food extends Grabbable {

        private int energyGain;

        public Food(String name, int energyGain) {
            super(name);
            this.energyGain = energyGain;
        }

        public int getEnergyGain() {
            return energyGain;
        }
    }

    // ------------------------- Water -------------------------
    public static class Water extends Grabbable {

        private int energyGain;

        public Water(String name, int energyGain) {
            super(name);
            this.energyGain = energyGain;
        }

        public int getEnergyGain() {
            return energyGain;
        }
    }
}
