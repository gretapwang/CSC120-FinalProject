public class Monsters {
    String monsterType;
    int number;
    // Mosters can be called by their types and numbers
    public Monsters(String monType, int num){
        this.monsterType=monType;
        this.number=num;

    }
    // I do not really think that the monster is required to have methods but player can kill the monster.
    // methods can be included in player file.
}