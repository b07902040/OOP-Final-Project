package hearthclone.model.minion;

import hearthclone.model.BattleCry;
import hearthclone.model.Minion;

//Complete
public class ForgottenKing extends AbstractMinion implements BattleCry {

    private static String name = "ForgottenKing";
    private static String description = "BattleCry:  Full your mana";
    private static int baseCost = 10;
    private static int baseATK = 7;
    private static int baseHP = 7;

    public ForgottenKing() {
        super(ForgottenKing.name, ForgottenKing.description, ForgottenKing.baseCost, ForgottenKing.baseHP,
                ForgottenKing.baseATK);
    }

    @Override
    public void doBattleCryEffect(Minion target) {
        this.master.setMana(this.master.getFullMana());
    }

}