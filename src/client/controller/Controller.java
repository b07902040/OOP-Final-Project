package src.client.controller;
import java.util.List;
import java.util.ArrayList;
import src.event.*;
import src.constant.*;

public class Controller implements EventListener {
    private EventManager eventManager;
    private Game model;
    
    public Controller(EventManager eventManager, Game model) {
        this.eventManager = eventManager;
        this.model = model;
    }

    public void initialize(){
        return;
    };

    @Override
    public void notify(Event event){
        if(event instanceof EventInitialize){
            this.initialize();
        }
        else if(event instanceof EventEveryTick){
            //TODO:
            //handle mouse events
            return;
        }
    }
}