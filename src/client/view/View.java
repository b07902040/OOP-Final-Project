package src.client.view;
import java.util.List;
import java.util.ArrayList;
import src.event.*;
import src.constant.*;

public class View implements EventListener{
    private EventManager eventManager;
    private Game model;
    
    public View(EventManager eventManager, Game model) {
        this.eventManager = eventManager;
        this.model = model;
    }

    public void initialize(){
        //TODO
        return;
    };

    @Override
    public void notify(Event event){
        if(event instanceof EventInitialize){
            this.initialize();
        }
        else if(event instanceof EventEveryTick){
            //TODO
            return;
        }
    }
}