import src.model.*;
import src.event.*;
import src.constant.*;
import src.utils.*;

public class Test {

    private EventManager eventManager;
    private Game game;

    public Test(EventManager eventManager,Game game){
        this.eventManager = eventManager;            
        this.game = game;
    }
    public void run(){
        game.run();
        this.cardClicked();
        this.printState();
        this.clickedEmpty();
        this.printState();
        this.cardClicked();
        this.printState();
        this.cardSelected();
        this.printState();
        /*while(true){

        }*/
    }
    private void cardClicked(){
        int clickedIndex = Inputs.in.nextInt();
        eventManager.post(new EventCardClicked(clickedIndex));     
    }
    private void cardSelected(){
        eventManager.post(new EventCardSelected());    
    }
    private void clickedEmpty(){
        eventManager.post(new EventClickedEmpty());     
    }
    private void printState(){
        String state = "NO_STATE";
        if(game.isState(Const.STATE_PENDING)) 
            state ="STATE_PENDING";
        else if(game.isState(Const.STATE_GAME_END))
            state ="STATE_GAME_END";

        else if(game.isState(Const.STATE_VALID_CARD))
            state ="STATE_VALID_CARD";
        else if(game.isState(Const.STATE_INVALID_CARD))
            state ="STATE_INVALID_CARD";
        else if(game.isState(Const.STATE_CARD_TARGETING))
            state ="STATE_CARD_TARGETING";
        else if(game.isState(Const.STATE_VALID_TARGET))
            state ="STATE_VALID_TARGET";
        else if(game.isState(Const.STATE_INVALID_TARGET))
            state ="STATE_INVALID_TARGET";
        else if(game.isState(Const.STATE_EFFECTING))
            state ="STATE_EFFECTING";

        else if(game.isState(Const.STATE_VALID_ATTACKER))
            state ="STATE_VALID_ATTACKER";
        else if(game.isState(Const.STATE_INVALID_ATTACKER))
            state ="STATE_INVALID_ATTACKER";
        else if(game.isState(Const.STATE_ATTACKER_TARGETING))
            state ="STATE_ATTACKER_TARGETING";
        else if(game.isState(Const.STATE_VALID_ATTACKED))
            state ="STATE_VALID_ATTACKED";
        else if(game.isState(Const.STATE_INVALID_ATTACKED))
            state ="STATE_INVALID_ATTACKED";
        else if(game.isState(Const.STATE_ATTACKING))
            state ="STATE_ATTACKING";
        System.out.println(state);
    }
        
    
}
