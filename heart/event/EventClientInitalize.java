package heart.event;

public class EventClientInitalize implements Event{
    private String name = "EventClientInitalize";
    private int clientId;

    public EventClientInitalize(int clientId){
        this.clientId = clientId;
    }
    
    @Override
    public String getName(){
        return this.name;
    }

    public int getClientId(){
        return this.clientId;        
    }
}