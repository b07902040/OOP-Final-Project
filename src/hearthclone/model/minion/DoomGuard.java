package hearthclone.model.minion;

import hearthclone.model.BattleCry;
import hearthclone.model.Charge;
import hearthclone.model.Game;
import hearthclone.model.Minion;

//Complete
public class DoomGuard extends AbstractMinion implements BattleCry, Charge {

    private static String name = "DoomGuard";
    private static String description = "Charge & BattleCry: Discard two random handcards";
    private static int baseCost = 5;
    private static int baseATK = 5;
    private static int baseHP = 7;

    public DoomGuard() {
        super(DoomGuard.name, DoomGuard.description, DoomGuard.baseCost, DoomGuard.baseHP, DoomGuard.baseATK);
    }

    @Override
    public void doBattleCryEffect(Minion target) {
        for (int i = 0; i < 2; i++) {
            int handNum = this.master.getHandCards().size();
            if (handNum <= 0)
                return;
            int throwIndex = Game.getRandom(handNum) - 1;
            this.master.throwCard(throwIndex);
        }
    }

}