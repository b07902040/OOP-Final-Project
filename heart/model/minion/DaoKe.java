package heart.model.minion;

import heart.model.BattleCry;
import heart.model.Combo;
import heart.model.Minion;
import heart.model.Player;

//Complete
public class DaoKe extends AbstractMinion implements BattleCry, Combo {

    private static String name = "DaoKe";
    private static String description = "BattleCry & Combo: Gain +1/+1";
    private static int baseCost = 1;
    private static int baseATK = 1;
    private static int baseHP = 2;

    public DaoKe() {
        super(DaoKe.name, DaoKe.description, DaoKe.baseCost, DaoKe.baseHP, DaoKe.baseATK);
    }

    @Override
    public int getCombo(Player master) {
        return master.getCardPlayed() - 1;
    }

    @Override
    public void doBattleCryEffect(Minion target) {
        int combo = ((Combo) this).getCombo(this.master);
        if (combo < 1)
            return;
        this.setBuffHP(this.buffHP + 1);
        this.setHP(this.HP + 1);
        this.setATK(this.ATK + 1);
    }

}