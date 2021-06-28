package src.event;

public class EventAttacking implements Event{
    private String name = "EventAttacking";

    private int attackerIndex;
    private int attackerPlayerIndex;
    private int attackedIndex;
    private int attackedPlayerIndex;

    public EventAttacking(int attackerPlayerIndex, int attackerIndex, int attackedPlayerIndex, int attackedIndex){
        this.attackerIndex = attackerIndex;
        this.attackerPlayerIndex = attackerPlayerIndex;
        this.attackedIndex = attackedIndex;
        this.attackedPlayerIndex = attackedPlayerIndex;
    }
    
    public int getAttackerIndex(){
        return this.attackerIndex;
    }
    public int getAttackerPlayerIndex(){
        return this.attackerPlayerIndex;
    }
    public int getAttackedIndex(){
        return this.attackedIndex;
    }
    public int getAttackedPlayerIndex(){
        return this.attackedPlayerIndex;
    }
    
    @Override
    public String getName(){
        return this.name;
    }
}