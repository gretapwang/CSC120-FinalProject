public class Monsters {
    int number;

    public Monsters(int num){
        if (num < 0){
            throw new RuntimeException("Cannot have a negative number of monsters.");
        }
        this.number=num;
    }

    public int getNum(){
        return this.number;
    }

    public void die(int numKilled){
        if (numKilled > this.number){
            throw new RuntimeException("You don't have this many monsters left to kill.");
        }
        this.number -= numKilled;
    }
}