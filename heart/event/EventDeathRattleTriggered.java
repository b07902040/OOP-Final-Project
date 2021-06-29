package heart.event;

public class EventDeathRattleTriggered implements Event{
    private String name = "EventDeathRattleTriggered";

    @Override
    public String getName(){
        return this.name;
    }
}