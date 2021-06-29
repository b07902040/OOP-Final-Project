package heart.event;

public class EventManaChange implements Event{
    private String name = "EventManaChange";
    private int playerId;
    private int mana = 0;
    private int fullMana = 0;

    public EventManaChange(int playerId, int mana, int fullMana){
        this.playerId = playerId;
        this.mana = mana;
        this.fullMana = fullMana;
    }

    public int getPlayerId(){
        return this.playerId;
    }
    
    public int getMana() {
        return this.mana;
    }

    public int getFullMana() {
        return this.fullMana;
    }

    @Override
    public String getName(){
        return this.name;
    }
    
}