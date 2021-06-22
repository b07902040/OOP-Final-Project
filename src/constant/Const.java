package src.constant;
public class Const{
    //StateMachine
    public static final int STATE_PENDING = 1;
    //Play card
    public static final int STATE_VALID_CARD = 2;
    public static final int STATE_INVALID_CARD = 3;
    public static final int STATE_CARD_TARGETING = 4; 
    public static final int STATE_VALID_TARGET = 5;  
    public static final int STATE_INVALID_TARGET = 6;
    public static final int STATE_EFFECTING = 7; 
    //Attack
    public static final int STATE_VALID_ATTACKER = 8;  
    public static final int STATE_INVALID_ATTACKER = 9; 
    public static final int STATE_ATTACKER_TARGETING = 10;     
    public static final int STATE_VALID_ATTACKED = 11;  
    public static final int STATE_INVALID_ATTACKED = 12; 
    public static final int STATE_ATTACKING = 13; 
    
    



    public static final int STATE_END = 99;
    //Game
    public static final int MAX_HAND_SIZE = 7;
    public static final int STARTING_HAND_SIZE= 3;
    public static final int DECK_SIZE = 30; 
    public static final int MAX_MANA = 10;
    public static final int BOARD_SPACE = 5;
}