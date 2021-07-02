package hearthclone.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import hearthclone.constant.Const;
import hearthclone.event.*;
import hearthclone.model.GameInfo;

public class Controller extends MouseAdapter implements EventListener {
    private EventManager eventManager;
    private GameInfo model;
    private int lastPressedX = -1000;
    private int lastPressedY = -1000;

    public Controller(EventManager eventManager, GameInfo model) {
        this.eventManager = eventManager;
        this.model = model;
    }

    public void initialize() {
        return;
    };

    @Override
    public void notify(Event event) {
        if (event instanceof EventInitialize) {
            this.initialize();
        } else if (event instanceof EventEveryTick) {
            // handle mouse events
            return;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /**
         * Invoked when the mouse button has been clicked (pressed and released) on a
         * component.
         */
        int x = e.getX();
        int y = e.getY();
        //System.out.printf("Mouse clicked at (%d, %d)\n", e.getX(), e.getY());
        int index, id;

        // skip any mouse click if it is not my turn
        if (!this.model.isMyTurn())
            return;

        // click on hand card
        if ((index = position2CardIndex(x, y)) >= 0) {
            this.eventManager.post(new EventCardClicked(index));
        }
        // click on empty region
        else if (isEmptyRegion(x, y)) {
            this.eventManager.post(new EventClickedEmpty());
        }
        // click on minion
        else if ((id = position2MinionPlayerId(x, y)) >= 0) {
            index = position2MinionIndex(x, y);
            //System.out.printf("Clicked on Minion with player id: %d, index: %d\n", id, index);
            this.eventManager.post(new EventMinionClicked(id, index));
        }

        // click on select button
        if (isInSelectButton(x, y)) {
            if (this.model.getState() == Const.STATE_VALID_CARD)
                this.eventManager.post(new EventCardSelected());
            else if (this.model.getState() == Const.STATE_VALID_TARGET)
                this.eventManager.post(new EventMinionSelected());
            else if (this.model.getState() == Const.STATE_VALID_ATTACKER)
                this.eventManager.post(new EventMinionSelected());
            else if (this.model.getState() == Const.STATE_VALID_ATTACKED)
                this.eventManager.post(new EventMinionSelected());
        }

        // click on end turn button
        if (isInEnturnButton(x, y)) {
            this.eventManager.post(new EventTurnEnd());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /**
         * Invoked when a mouse button has been pressed on a component.
         */
        lastPressedX = e.getX();
        lastPressedY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        /**
         * Invoked when a mouse button has been released on a component.
         */
        int x = e.getX();
        int y = e.getY();
        if (x == lastPressedX && y == lastPressedY)
            return;
        if (Math.abs(x - lastPressedX) < Const.MOUSE_CLICK_THRESHOLD
                && Math.abs(y - lastPressedY) < Const.MOUSE_CLICK_THRESHOLD)
            mouseClicked(e);
    }

    private boolean isInSelectButton(int x, int y) {
        if ((x >= Const.SELECT_BUTTON[0]) && (x < Const.SELECT_BUTTON[0] + Const.SELECT_BUTTON[2])
                && (y >= Const.SELECT_BUTTON[1]) && (y < Const.SELECT_BUTTON[1] + Const.SELECT_BUTTON[3]))
            return true;
        return false;
    }

    private boolean isInEnturnButton(int x, int y) {
        if ((x >= Const.ENDTURN_BUTTON[0]) && (x < Const.ENDTURN_BUTTON[0] + Const.ENDTURN_BUTTON[2])
                && (y >= Const.ENDTURN_BUTTON[1]) && (y < Const.ENDTURN_BUTTON[1] + Const.ENDTURN_BUTTON[3]))
            return true;
        return false;
    }

    private boolean isEmptyRegion(int x, int y) {
        int state = this.model.getState();
        if (state != Const.STATE_VALID_CARD && state != Const.STATE_INVALID_CARD && state != Const.STATE_VALID_ATTACKER
                && state != Const.STATE_VALID_ATTACKED && state != Const.STATE_VALID_TARGET
                && state != Const.STATE_INVALID_ATTACKER && state != Const.STATE_INVALID_ATTACKED
                && state != Const.STATE_INVALID_TARGET)
            return false;
        if (isInSelectButton(x, y))
            return false;
        if ((x >= Const.CARD_SHOW[0]) && (x < Const.CARD_SHOW[0] + Const.CARD_SHOW[2]) && (y >= Const.CARD_SHOW[1])
                && (y < Const.CARD_SHOW[1] + Const.CARD_SHOW[3]))
            return false;
        return true;
    }

    private int position2CardIndex(int x, int y) {
        int handSize = this.model.getHandSize(this.model.getPlayerId());
        int handCardsLX = Const.HANDCARD_REGION[0]
                + (Const.HANDCARD_REGION[2] - (handSize - 1) * Const.HANDCARD_GAP - handSize * Const.CARD_W) / 2;
        int handCardsRX = Const.HANDCARD_REGION[0]
                + (Const.HANDCARD_REGION[2] + (handSize - 1) * Const.HANDCARD_GAP + handSize * Const.CARD_W) / 2;
        // not within hand cards
        if (x < handCardsLX || x >= handCardsRX || y < Const.HANDCARD_REGION[1]
                || y >= Const.HANDCARD_REGION[1] + Const.HANDCARD_REGION[3])
            return -1;
        // x distance to the closest left edge of a card
        int relative2CardX = (x - handCardsLX) % (Const.HANDCARD_GAP + Const.CARD_W);
        // on the gap
        if (relative2CardX >= Const.CARD_W)
            return -1;
        return (x - handCardsLX) / (Const.HANDCARD_GAP + Const.CARD_W);
    }

    private int position2MinionIndex(int x, int y) {
        // my hero
        if ((x >= Const.HERO[0]) && (x < Const.HERO[0] + Const.HERO[2]) && (y >= Const.HERO[1])
                && (y < Const.HERO[1] + Const.HERO[3]))
            return 0;
        // oponent hero
        if ((x >= Const.OP_HERO[0]) && (x < Const.OP_HERO[0] + Const.OP_HERO[2]) && (y >= Const.OP_HERO[1])
                && (y < Const.OP_HERO[1] + Const.OP_HERO[3]))
            return 0;
        // on my side
        if (y >= Const.BOARD_REGION[1] && y < Const.BOARD_REGION[1] + Const.BOARD_REGION[3]) {
            int boardSize = this.model.getAlly().size() - 1;
            int boardLX = Const.BOARD_REGION[0]
                    + (Const.BOARD_REGION[2] - (boardSize - 1) * Const.MINION_GAP - boardSize * Const.MINION_W) / 2;
            int boardRX = Const.BOARD_REGION[0]
                    + (Const.BOARD_REGION[2] + (boardSize - 1) * Const.MINION_GAP + boardSize * Const.MINION_W) / 2;
            if (x < boardLX || x >= boardRX)
                return -1;
            int relative2CardX = (x - boardLX) % (Const.MINION_GAP + Const.MINION_W);
            if (relative2CardX >= Const.MINION_W)
                return -1;
            return (x - boardLX) / (Const.MINION_GAP + Const.MINION_W) + 1;
        }
        // on opponent side
        if (y >= Const.OP_BOARD_REGION[1] && y < Const.OP_BOARD_REGION[1] + Const.OP_BOARD_REGION[3]) {
            int boardSize = this.model.getEnemy().size() - 1;
            int boardLX = Const.OP_BOARD_REGION[0]
                    + (Const.OP_BOARD_REGION[2] - (boardSize - 1) * Const.MINION_GAP - boardSize * Const.MINION_W) / 2;
            int boardRX = Const.OP_BOARD_REGION[0]
                    + (Const.OP_BOARD_REGION[2] + (boardSize - 1) * Const.MINION_GAP + boardSize * Const.MINION_W) / 2;
            if (x < boardLX || x >= boardRX)
                return -1;
            int relative2CardX = (x - boardLX) % (Const.MINION_GAP + Const.MINION_W);
            if (relative2CardX >= Const.MINION_W)
                return -1;
            return boardSize - (x - boardLX) / (Const.MINION_GAP + Const.MINION_W);
        }
        // neither
        return -1;
    }

    private int position2MinionPlayerId(int x, int y) {
        // my hero
        if ((x >= Const.HERO[0]) && (x < Const.HERO[0] + Const.HERO[2]) && (y >= Const.HERO[1])
                && (y < Const.HERO[1] + Const.HERO[3]))
            return this.model.getPlayerId();
        // oponent hero
        if ((x >= Const.OP_HERO[0]) && (x < Const.OP_HERO[0] + Const.OP_HERO[2]) && (y >= Const.OP_HERO[1])
                && (y < Const.OP_HERO[1] + Const.OP_HERO[3]))
            return 1 - this.model.getPlayerId();
        if (y >= Const.BOARD_REGION[1] && y < Const.BOARD_REGION[1] + Const.BOARD_REGION[3]) {
            int boardSize = this.model.getAlly().size() - 1;
            int boardLX = Const.BOARD_REGION[0]
                    + (Const.BOARD_REGION[2] - (boardSize - 1) * Const.MINION_GAP - boardSize * Const.MINION_W) / 2;
            int boardRX = Const.BOARD_REGION[0]
                    + (Const.BOARD_REGION[2] + (boardSize - 1) * Const.MINION_GAP + boardSize * Const.MINION_W) / 2;
            if (x < boardLX || x >= boardRX)
                return -1;
            int relative2CardX = (x - boardLX) % (Const.MINION_GAP + Const.MINION_W);
            if (relative2CardX >= Const.MINION_W)
                return -1;
            return this.model.getPlayerId();
        }
        // on opponent side
        if (y >= Const.OP_BOARD_REGION[1] && y < Const.OP_BOARD_REGION[1] + Const.OP_BOARD_REGION[3]) {
            int boardSize = this.model.getEnemy().size() - 1;
            int boardLX = Const.OP_BOARD_REGION[0]
                    + (Const.OP_BOARD_REGION[2] - (boardSize - 1) * Const.MINION_GAP - boardSize * Const.MINION_W) / 2;
            int boardRX = Const.OP_BOARD_REGION[0]
                    + (Const.OP_BOARD_REGION[2] + (boardSize - 1) * Const.MINION_GAP + boardSize * Const.MINION_W) / 2;
            if (x < boardLX || x >= boardRX)
                return -1;
            int relative2CardX = (x - boardLX) % (Const.MINION_GAP + Const.MINION_W);
            if (relative2CardX >= Const.MINION_W)
                return -1;
            return 1 - this.model.getPlayerId();
        }
        // neither
        return -1;
    }
}