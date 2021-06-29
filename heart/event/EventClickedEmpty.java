package heart.event;

public class EventClickedEmpty implements Event{
    private String name = "EventClickedEmpty";

    @Override
    public String getName(){
        return this.name;
    }
    
}