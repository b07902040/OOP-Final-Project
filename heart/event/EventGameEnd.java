package heart.event;

public class EventGameEnd implements Event{
    private String name = "EventGameEnd";
    private int winner;

    public EventGameEnd(int winner){
        this.winner = winner;
    }

    public int getWinner(){
        return this.winner;
    }
    @Override
    public String getName(){
        return this.name;
    }
}