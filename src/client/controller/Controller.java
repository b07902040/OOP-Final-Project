package src.client.controller;

import src.client.model.GameInfo;

import java.util.List;
import java.util.ArrayList;
import src.event.*;
import src.constant.*;
// import src.model.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller extends MouseAdapter implements EventListener {
    private EventManager eventManager;
    private GameInfo model;

    public Controller(EventManager eventManager, GameInfo model) {
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

    
    @Override
    public void mouseClicked(MouseEvent e){
        /**
         * Invoked when the mouse button has been clicked (pressed and released) on a component.
         */
         System.out.printf("mouse clicked at (%d, %d)", e.getX(), e.getY());
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        /**
         * Invoked when a mouse button has been pressed on a component.
         */
    }
    
    @Override
    public void mouseReleased(MouseEvent e){
        /**
         * Invoked when a mouse button has been released on a component.
         */
    }
    
    @Override
    public void mouseEntered(MouseEvent e){
        /**
         * Invoked when the mouse enters a component.
         */
    }
    
    @Override
    public void mouseExited(MouseEvent e){
        /**
         * Invoked when the mouse exits a component.
         */
    }
    
    

    @Override
    public void mouseDragged(MouseEvent e){
        /**
         * Invoked when a mouse button is pressed on a component and then dragged. 
         * MOUSE_DRAGGED events will continue to be delivered to the component 
         * where the drag originated until the mouse button is released 
         * (regardless of whether the mouse position is within the bounds of the component).
         */
    }
    
    @Override
    public void mouseMoved(MouseEvent e){
        /**
         * Invoked when the mouse cursor has been moved onto a component 
         * but no buttons have been pushed.
         */
    }
    
}