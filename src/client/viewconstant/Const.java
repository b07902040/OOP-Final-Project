package src.client.viewconstant;

public class Const {
    //Controller
    public static final int MOUSE_CLICK_THRESHOLD = 100;
    //View
    public static final int SCREEN_W = 1200;
    public static final int SCREEN_H = 1000;

    public static final int[] MIDDLE_BAR = {0, 480, 1200, 8};
    public static final int[] BOTTOM_BAR = {0, 850, 1200, 8};
    public static final int[] OPPONENT_CARD_REGION = {350, 0, 500, 100};
    public static final int[] SELECT_BUTTON = {1030, 870, 100, 40};
    public static final int[] ENDTURN_BUTTON = {1100, 460, 100, 40};
    public static final int[] MANA_BOX = {750, 805, 450, 50};
    public static final int[] HANDCARD_REGION = {50, 870, 950, 125};
    public static final int[] CARD_SHOW = {50, 200, 375, 600};
    public static final int[] BOARD_REGION = {0, 520, 1200, 125};
    public static final int[] OP_BOARD_REGION = {0, 330, 1200, 125};
    
    public static final int MANA_X = 770;
    public static final int MANA_Y = 815;
    public static final int MANA_SIZE = 30;
    public static final int MANA_GAP = 4;
    public static final int MANA_FONT_Y = 840;
    public static final int MAX_MANA = 10;

    // font size
    public static final int MANA_FONT_SIZE = 26;
    public static final int HANDCARD_NAME_FONT_SIZE = 10;
    public static final int SHOWEDCARD_NAME_FONT_SIZE = 36;
    public static final int MESSAGE_FONT_SIZE = 26;
    public static final int CARD_SHOW_STATUS_FONT_SIZE = 50;

    public static final int HANDCARD_GAP = 15;
    public static final int MINION_GAP = 15;

    public static final int OP_HANDCARD_X = 370;
    public static final int OP_HANDCARD_Y = -30;
    public static final int OP_HANDCARD_GAP = -15;

    public static final int CARD_W = 75;
    public static final int CARD_H = 125;
    public static final int MINION_W = 125;
    public static final int MINION_H = 125;

    // X Ratio: (x - cardX)/cardW
    // Y Ratio: (y - cardY)/cardH
    public static final double CARD_NAME_Y_RATIO = 0.58;
    public static final double CARD_NAME_X_RATIO = 0.5;
    public static final double CARD_ATTACK_X_RATIO = 0.15;
    public static final double CARD_ATTACK_Y_RATIO = 0.92;
    public static final double CARD_HEALTH_X_RATIO = 0.87;
    public static final double CARD_HEALTH_Y_RATIO = 0.93;
    public static final double CARD_MANA_X_RATIO = 0.17;
    public static final double CARD_MANA_Y_RATIO = 0.1;



    public static final int MESSAGE_X = 950;
    public static final int MESSAGE_Y = 800;

    //Path
    public static final String BACKGROUND_IMG_PATH = "image/background.png";
    public static final String BAR_IMG_PATH = "image/bar.png";
    public static final String OPPONENT_CARD_REGION_PATH = "image/opponent_card_region.png";
    public static final String SELECT_BUTTON_PATH = "image/select.png";
    public static final String ENDTURN_BUTTON_PATH = "image/end_turn.png";
    public static final String MANA_BOX_IMG_PATH = "image/mana_box.png";
    
    public static final String FULL_MANA_PATH = "image/full_mana.png";
    public static final String EMPTY_MANA_PATH = "image/empty_mana.png";

    public static final String MINION_CARD_FRAME_PATH = "image/minion_card_frame.png";
    public static final String SPELL_CARD_FRAME_PATH = "image/spell_card_frame.png";
    public static final String CARD_BACK_PATH = "image/card_back.png";
    public static final String CARD_IMG_DIR = "image/cards/";

    public static final String MINION_FRAME_PATH = "image/minion_frame.png";

    //StateMachine
    public static final int STATE_PENDING = 1;
    public static final int STATE_GAME_END = 111;
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
}