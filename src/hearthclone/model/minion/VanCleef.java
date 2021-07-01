package hearthclone.model.minion;

import hearthclone.model.BattleCry;
import hearthclone.model.Combo;
import hearthclone.model.Minion;
import hearthclone.model.Player;

//Complete
public class VanCleef extends AbstractMinion implements BattleCry, Combo {

    private static String name = "VanCleef";
    private static String description = "BattleCry & Combo : Gain +2/+2 for each card played earlier this turn";
    private static int baseCost = 3;
    private static int baseATK = 2;
    private static int baseHP = 2;

    public VanCleef() {
        super(VanCleef.name, VanCleef.description, VanCleef.baseCost, VanCleef.baseHP, VanCleef.baseATK);
    }

    @Override
    public int getCombo(Player master) {
        return master.getCardPlayed() - 1;
    }

    @Override
    public void doBattleCryEffect(Minion target) {
        int combo = ((Combo) this).getCombo(this.master);
        int buffValue = 2 * combo;
        this.setATK(this.getATK() + buffValue);
        this.setBuffHP(this.getBuffHP() + buffValue);
        this.setHP(this.getHP() + buffValue);
    }

}