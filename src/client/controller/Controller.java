package src.client.controller;

import src.client.model.GameInfo;
import src.client.viewconstant.Const;

import java.util.List;
import java.util.ArrayList;
import src.event.*;
//import src.constant.*;
// import src.model.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import src.model.*;
import src.model.minion.*;
import src.model.spell.*;

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
        int x = e.getX();
        int y = e.getY();
        System.out.printf("Mouse clicked at (%d, %d)\n", e.getX(), e.getY());
        if(isInEnturnButton(x, y)){
            System.out.println("End turn button clicked");
            Card card = new PyroBlast();
            this.eventManager.post(new EventHandCardChange(0, card));
        }
        if(isInSelectButton(x, y)){
            
            System.out.println("Select button clicked");
            //Card card = new Goblin();
            //this.eventManager.post(new EventHandCardChange(0, card));
            
            Minion minion = new Goblin();
            this.eventManager.post(new EventBoardChange(1, 0, minion));
        }
        if(position2MinionIndex(x, y) >= 0){
            System.out.printf("Clicked on Minion with player id: %d, index: %d\n", position2MinionPlayerId(x, y), position2MinionIndex(x, y));
        }
        if(position2CardIndex(x, y) >= 0){
            System.out.printf("Clicked on Card with index: %d\n", position2CardIndex(x, y));
            this.eventManager.post(new EventStateChange(Const.STATE_VALID_CARD));
            this.eventManager.post(new EventCardShow(position2CardIndex(x, y), true));
        }
        else if(isEmptyRegion(x, y)){
            if(this.model.getState() == Const.STATE_VALID_CARD)
                this.eventManager.post(new EventStateChange(Const.STATE_PENDING));
        }
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
    
    private boolean isInSelectButton(int x, int y){
        if((x >= Const.SELECT_BUTTON[0]) && (x < Const.SELECT_BUTTON[0] + Const.SELECT_BUTTON[2]) && (y >= Const.SELECT_BUTTON[1]) && (y < Const.SELECT_BUTTON[1] + Const.SELECT_BUTTON[3]))
            return true;
        return false;
    }

    private boolean isInEnturnButton(int x, int y){
        if((x >= Const.ENDTURN_BUTTON[0]) && (x < Const.ENDTURN_BUTTON[0] + Const.ENDTURN_BUTTON[2]) && (y >= Const.ENDTURN_BUTTON[1]) && (y < Const.ENDTURN_BUTTON[1] + Const.ENDTURN_BUTTON[3]))
            return true;
        return false;
    }

    private boolean isEmptyRegion(int x, int y){
        if(isInSelectButton(x, y))
            return false;
        if((x >= Const.CARD_SHOW[0]) && (x < Const.CARD_SHOW[0] + Const.CARD_SHOW[2]) && (y >= Const.CARD_SHOW[1]) && (y < Const.CARD_SHOW[1] + Const.CARD_SHOW[3]))
            return false;
        return true;
    }

    private int position2CardIndex(int x, int y){
        int handSize = this.model.getHandSize(this.model.getPlayerId());
        int handCardsLX = Const.HANDCARD_REGION[0] + (Const.HANDCARD_REGION[2] - (handSize-1)*Const.HANDCARD_GAP - handSize*Const.CARD_W)/2;
        int handCardsRX = Const.HANDCARD_REGION[0] + (Const.HANDCARD_REGION[2] + (handSize-1)*Const.HANDCARD_GAP + handSize*Const.CARD_W)/2;
        // not within hand cards
        if(x < handCardsLX || x >= handCardsRX || y < Const.HANDCARD_REGION[1] || y >= Const.HANDCARD_REGION[1] + Const.HANDCARD_REGION[3])
            return -1;
        //x distance to the closest left edge of a card
        int relative2CardX = (x - handCardsLX)%(Const.HANDCARD_GAP + Const.CARD_W);
        // on the gap
        if(relative2CardX >= Const.CARD_W)
            return -1;
        return (x - handCardsLX)/(Const.HANDCARD_GAP + Const.CARD_W);
    }

    private int position2MinionIndex(int x, int y){
        // on my side
        if(y >= Const.BOARD_REGION[1] && y < Const.BOARD_REGION[1] + Const.BOARD_REGION[3]){
            int boardSize = this.model.getAlly().size() - 1;
            int boardLX = Const.BOARD_REGION[0] + (Const.BOARD_REGION[2] - (boardSize-1)*Const.MINION_GAP - boardSize*Const.MINION_W)/2;
            int boardRX = Const.BOARD_REGION[0] + (Const.BOARD_REGION[2] + (boardSize-1)*Const.MINION_GAP + boardSize*Const.MINION_W)/2;
            if(x < boardLX || x >= boardRX)
                return -1;
            int relative2CardX = (x - boardLX)%(Const.MINION_GAP + Const.MINION_W);
            if(relative2CardX >= Const.MINION_W)
                return -1;
            return (x - boardLX)/(Const.MINION_GAP + Const.MINION_W) + 1;
        }
        // on opponent side
        if(y >= Const.OP_BOARD_REGION[1] && y < Const.OP_BOARD_REGION[1] + Const.OP_BOARD_REGION[3]){
            int boardSize = this.model.getEnemy().size() - 1;
            int boardLX = Const.OP_BOARD_REGION[0] + (Const.OP_BOARD_REGION[2] - (boardSize-1)*Const.MINION_GAP - boardSize*Const.MINION_W)/2;
            int boardRX = Const.OP_BOARD_REGION[0] + (Const.OP_BOARD_REGION[2] + (boardSize-1)*Const.MINION_GAP + boardSize*Const.MINION_W)/2;
            if(x < boardLX || x >= boardRX)
                return -1;
            int relative2CardX = (x - boardLX)%(Const.MINION_GAP + Const.MINION_W);
            if(relative2CardX >= Const.MINION_W)
                return -1;
            return boardSize - (x - boardLX)/(Const.MINION_GAP + Const.MINION_W);
        }
        // neither
        return -1;
    }

    private int position2MinionPlayerId(int x, int y){
        if(y >= Const.BOARD_REGION[1] && y < Const.BOARD_REGION[1] + Const.BOARD_REGION[3]){
            int boardSize = this.model.getAlly().size() - 1;
            int boardLX = Const.BOARD_REGION[0] + (Const.BOARD_REGION[2] - (boardSize-1)*Const.MINION_GAP - boardSize*Const.MINION_W)/2;
            int boardRX = Const.BOARD_REGION[0] + (Const.BOARD_REGION[2] + (boardSize-1)*Const.MINION_GAP + boardSize*Const.MINION_W)/2;
            if(x < boardLX || x >= boardRX)
                return -1;
            int relative2CardX = (x - boardLX)%(Const.MINION_GAP + Const.MINION_W);
            if(relative2CardX >= Const.MINION_W)
                return -1;
            return this.model.getPlayerId();
        }
        // on opponent side
        if(y >= Const.OP_BOARD_REGION[1] && y < Const.OP_BOARD_REGION[1] + Const.OP_BOARD_REGION[3]){
            int boardSize = this.model.getEnemy().size() - 1;
            int boardLX = Const.OP_BOARD_REGION[0] + (Const.OP_BOARD_REGION[2] - (boardSize-1)*Const.MINION_GAP - boardSize*Const.MINION_W)/2;
            int boardRX = Const.OP_BOARD_REGION[0] + (Const.OP_BOARD_REGION[2] + (boardSize-1)*Const.MINION_GAP + boardSize*Const.MINION_W)/2;
            if(x < boardLX || x >= boardRX)
                return -1;
            int relative2CardX = (x - boardLX)%(Const.MINION_GAP + Const.MINION_W);
            if(relative2CardX >= Const.MINION_W)
                return -1;
            return 1 - this.model.getPlayerId();
        }
        // neither
        return -1;
    }
}