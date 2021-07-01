package heart.constant;

public class Const {
    public static final int FPS = 60;
    // StateMachine
    public static final int STATE_INITIALIZED = 0;
    public static final int STATE_NULL = 89;
    public static final int STATE_PENDING = 1;
    public static final int STATE_GAME_END = 111;
    // Play card
    public static final int STATE_VALID_CARD = 2;
    public static final int STATE_INVALID_CARD = 3;
    public static final int STATE_CARD_TARGETING = 4;
    public static final int STATE_VALID_TARGET = 5;
    public static final int STATE_INVALID_TARGET = 6;
    public static final int STATE_EFFECTING = 7;
    // Attack
    public static final int STATE_VALID_ATTACKER = 8;
    public static final int STATE_INVALID_ATTACKER = 9;
    public static final int STATE_ATTACKER_TARGETING = 10;
    public static final int STATE_VALID_ATTACKED = 11;
    public static final int STATE_INVALID_ATTACKED = 12;
    public static final int STATE_ATTACKING = 13;
    //Draw
    public static final int STATE_DRAWING = 123;

    public static final int STATE_END = 99;
    // Game
    public static final int MAX_HAND_SIZE = 10;
    public static final int STARTING_HAND_SIZE = 3;
    public static final int DECK_SIZE = 30;
    public static final int MAX_MANA = 10; 
    public static final int BOARD_SPACE = 7;
    public static final int HERO_HP = 30;
    public static final int PLAYER1_WIN = 1;
    public static final int PLAYER2_WIN = 2;
    public static final int PLAYER_DRAW = 3;

    // FOR TESTING
    public static final int INIT_MANA = 0;

    // Controller
    public static final int MOUSE_CLICK_THRESHOLD = 100;
    // View
    public static final int SCREEN_W = 1200;
    public static final int SCREEN_H = 1000;

    public static final int[] MIDDLE_BAR = { 0, 480, 1200, 8 };
    public static final int[] BOTTOM_BAR = { 0, 850, 1200, 8 };
    public static final int[] OPPONENT_CARD_REGION = { 300, -30, 600, 100 };
    public static final int[] SELECT_BUTTON = { 1030, 900, 150, 60 };
    public static final int[] ENDTURN_BUTTON = { 1100, 460, 100, 40 };
    public static final int[] MANA_BOX = { 750, 805, 450, 50 };
    public static final int[] OP_MANA_BOX = { 950, 10, 135, 50 };
    public static final int[] MESSAGE_BOX = { 5, 700, 450, 150 };
    public static final int[] HANDCARD_REGION = { 50, 860, 950, 125 };
    public static final int[] CARD_SHOW = { 50, 90, 375, 595 };
    public static final int[] SPELL_SHOW = { 400, 90, 375, 595 };
    public static final int[] DRAW_SHOW = { 700, 90, 375, 595 };
    public static final int[] BOARD_REGION = { 0, 520, 1200, 125 };
    public static final int[] OP_BOARD_REGION = { 0, 330, 1200, 125 };
    public static final int[] HERO = { 525, 690, 150, 150 };
    public static final int[] OP_HERO = { 525, 120, 150, 150 };
    public static final int[] DECK = { 1150, 590, 79, 125 };
    public static final int[] OP_DECK = { 1150, 190, 79, 125 };

    public static final int MANA_X = 770;
    public static final int MANA_Y = 815;
    public static final int MANA_SIZE = 30;
    public static final int MANA_GAP = 4;
    public static final int MANA_MESSAGE_X = 1145;
    public static final int MANA_MESSAGE_Y = 835;
    public static final int OP_MANA_MESSAGE_Y = 45;

    // font size
    public static final int MANA_FONT_SIZE = 26;
    public static final int HANDCARD_NAME_FONT_SIZE = 10;
    public static final int SHOWEDCARD_NAME_FONT_SIZE = 36;
    public static final int TURN_MESSAGE_FONT_SIZE = 26;
    public static final int MESSAGE_FONT_SIZE = 20;
    public static final int CARD_SHOW_STATUS_FONT_SIZE = 50;
    public static final int MINION_SHOW_STATUS_FONT_SIZE = 20;
    public static final int HERO_SHOW_STATUS_FONT_SIZE = 24;
    public static final int DESCRIPTION_FONT_SIZE = 20;
    public static final int DECKNUM_FONT_SIZE = 24;

    public static final int HANDCARD_GAP = 15;
    public static final int MINION_GAP = 15;

    public static final int OP_HANDCARD_X = 370;
    public static final int OP_HANDCARD_Y = -30;
    public static final int OP_HANDCARD_GAP = -15;
    public static final int CARD_SHADOW_W = 20;

    public static final int CARD_W = 85;
    public static final int CARD_H = 135;
    public static final int MINION_W = 125;
    public static final int MINION_H = 125;
    public static final int MINION_IMG_W = 105;
    public static final int MINION_IMG_H = 105;
    public static final int CARDSHOW_IMG_W = 300;
    public static final int CARDSHOW_IMG_H = 300;
    public static final int HERO_IMG_W = 126;
    public static final int HERO_IMG_H = 126;

    // X Ratio: (x - cardX)/cardW
    // Y Ratio: (y - cardY)/cardH
    // W Ratio: w/CardW
    // H Ratio: h/CardH
    public static final double CARD_NAME_Y_RATIO = 0.58;
    public static final double CARD_NAME_X_RATIO = 0.5;
    public static final double CARD_ATTACK_X_RATIO = 0.15;
    public static final double CARD_ATTACK_Y_RATIO = 0.92;
    public static final double CARD_HEALTH_X_RATIO = 0.87;
    public static final double CARD_HEALTH_Y_RATIO = 0.93;
    public static final double CARD_MANA_X_RATIO = 0.17;
    public static final double CARD_MANA_Y_RATIO = 0.1;
    public static final double CARD_IMG_X_RATIO = 0.1;
    public static final double CARD_IMG_Y_RATIO = 0.06;
    public static final double CARD_IMG_W_RATIO = 0.82;
    public static final double CARD_IMG_H_RATIO = 0.52;
    public static final double CARD_DESCRIPTION_X_RATIO = 0.2;
    public static final double CARD_DESCRIPTION_Y_RATIO = 0.65;
    public static final double CARD_DESCRIPTION_W_RATIO = 0.62;
    public static final double CARD_TAUNT_X_RATIO = -0.03;
    public static final double CARD_TAUNT_Y_RATIO = -0.05;
    public static final double CARD_TAUNT_W_RATIO = 1.06;
    public static final double CARD_TAUNT_H_RATIO = 1.2;
    public static final double CARD_DEATHRATTLE_X_RATIO = 0.36;
    public static final double CARD_DEATHRATTLE_Y_RATIO = 0.72;
    public static final double CARD_DEATHRATTLE_W_RATIO = 0.28;
    public static final double CARD_DEATHRATTLE_H_RATIO = 0.25;

    public static final double MINION_ATTACK_X_RATIO = 0.16;
    public static final double MINION_ATTACK_Y_RATIO = 0.85;
    public static final double MINION_HEALTH_X_RATIO = 0.82;
    public static final double MINION_HEALTH_Y_RATIO = 0.88;
    public static final double MINION_IMG_X_RATIO = 0.08;
    public static final double MINION_IMG_Y_RATIO = 0.05;
    public static final double MINION_IMG_W_RATIO = 0.84;
    public static final double MINION_IMG_H_RATIO = 0.84;
    public static final double VALID_MINION_EFFECT_X_RATIO = -0.09;
    public static final double VALID_MINION_EFFECT_Y_RATIO = -0.103;
    public static final double VALID_MINION_EFFECT_W_RATIO = 1.18;
    public static final double VALID_MINION_EFFECT_H_RATIO = 1.206;
    public static final double VALID_MINION_CARD_X_RATIO = -0.05;
    public static final double VALID_MINION_CARD_Y_RATIO = -0.04;
    public static final double VALID_MINION_CARD_W_RATIO = 1.1;
    public static final double VALID_MINION_CARD_H_RATIO = 1.08;
    public static final double VALID_SPELL_CARD_X_RATIO = -0.008;
    public static final double VALID_SPELL_CARD_Y_RATIO = -0.026;
    public static final double VALID_SPELL_CARD_W_RATIO = 1.017;
    public static final double VALID_SPELL_CARD_H_RATIO = 1.051;


    public static final int MESSAGE_X = 30;
    public static final int MESSAGE_Y = 735;
    public static final int MESSAGE_H = 30;

    // Path
    public static final String BACKGROUND_IMG_PATH = "image/background.png";
    public static final String BAR_IMG_PATH = "image/bar.png";
    public static final String OPPONENT_CARD_REGION_PATH = "image/opponent_card_region.png";
    public static final String SELECT_BUTTON_PATH = "image/select.png";
    public static final String DIM_SELECT_BUTTON_PATH = "image/select_dim.png";
    public static final String ENDTURN_BUTTON_PATH = "image/end_turn.png";
    public static final String DIM_ENDTURN_BUTTON_PATH = "image/end_turn_dim.png";
    public static final String MANA_BOX_IMG_PATH = "image/mana_box.png";
    public static final String OPPONENT_MANA_BOX_IMG_PATH = "image/opponent_mana_box.png";
    public static final String MESSAGE_BOX_IMG_PATH = "image/message_box.png";

    public static final String FULL_MANA_PATH = "image/full_mana.png";
    public static final String EMPTY_MANA_PATH = "image/empty_mana.png";

    public static final String MINION_CARD_FRAME_PATH = "image/minion_card_frame.png";
    public static final String SPELL_CARD_FRAME_PATH = "image/spell_card_frame.png";
    public static final String MINION_FRAME_PATH = "image/minion_frame.png";
    public static final String CARD_BACK_PATH = "image/card_back.png";
    public static final String CARD_SHADOW_PATH = "image/card_shadow.png";
    public static final String CARD_IMG_DIR = "image/cards/";

    public static final String TAUNT_IMG_PATH = "image/taunt.png";
    public static final String DIVINE_SHIELD_IMG_PATH = "image/divine_shield.png";
    public static final String DEATHRATTLE_IMG_PATH = "image/death_rattle.png";
    public static final String VALID_MINION_EFFECT_PATH = "image/valid_minion_effect.png";
    public static final String VALID_MINION_CARD_EFFECT_PATH = "image/valid_minion_card_effect.png";
    public static final String VALID_SPELL_CARD_EFFECT_PATH = "image/valid_spell_card_effect.png";

    public static final String BGM_PATH = "./music/bgm.wav";
    
}