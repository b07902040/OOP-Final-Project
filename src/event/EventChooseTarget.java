package src.event;

public class EventChooseTarget implements Event{
    private String name = "EventChooseTarget";
    
    @Override
    public String getName(){
        return this.name;
    }
}