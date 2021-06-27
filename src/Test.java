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
        while(true){
            this.getMessage();
            System.out.println(this.getState());
            if(this.getState().equals("STATE_GAME_END")) 
                break;
        }
    }
    public void getMessage(){
        String msg = Inputs.in.nextLine();
        String segMsg[] = msg.split(" ");
        if(segMsg[0].equals("c")){
            if(segMsg[1].equals("m")){
                int playerId = Integer.parseInt(segMsg[2]);
                int index = Integer.parseInt(segMsg[3]);
                eventManager.post(new EventMinionClicked(playerId, index));
            }
            else if(segMsg[1].equals("c")){
                int index = Integer.parseInt(segMsg[2]);
                eventManager.post(new EventCardClicked(index));
            }
            else if(segMsg[1].equals("e")){
                eventManager.post(new EventClickedEmpty());
            }
        }
        else if(segMsg[0].equals("s")){
            if(this.getState().equals("STATE_VALID_ATTACKER")){
                eventManager.post(new EventMinionSelected());
            }
            else if(this.getState().equals("STATE_VALID_ATTACKED")){
                eventManager.post(new EventMinionSelected());
            }
            else if(this.getState().equals("STATE_VALID_CARD")){
                eventManager.post(new EventCardSelected());
            }
            else if(this.getState().equals("STATE_VALID_TARGET")){
                eventManager.post(new EventMinionSelected());
            }
        }    
        else if(segMsg[0].equals("n")){
            if(this.getState().equals("STATE_ATTACKING")){
                eventManager.post(new EventMinionAttacked());
            }
            else if(this.getState().equals("STATE_EFFECTING")){
                eventManager.post(new EventCardEffected());
            }
        }   
        else if(segMsg[0].equals("e")){
            if(this.getState().equals("STATE_PENDING"))
                eventManager.post(new EventTurnEnd());
        }
        
    }

    private String getState(){
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
        return state;
    }
        
    
}
