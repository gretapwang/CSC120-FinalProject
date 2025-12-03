public class Weapons extends Grabbable () //constructor must call the superclass's Grabbalbe construcotor using super()
    public Weapons(String name, int damage) {
        super(name); 
        this.maxDamage = damage;
    }

//examples: sword, knide, pan