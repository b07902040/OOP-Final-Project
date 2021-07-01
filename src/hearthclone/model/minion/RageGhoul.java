package hearthclone.model.minion;

import java.util.ArrayList;
import java.util.List;

import hearthclone.model.BattleCry;
import hearthclone.model.Minion;

//Complete
public class RageGhoul extends AbstractMinion implements BattleCry {

    private static String name = "RageGhoul";
    private static String description = "BattleCry: Deal 1 damage to all other minions.";
    private static int baseCost = 3;
    private static int baseATK = 3;
    private static int baseHP = 3;

    public RageGhoul() {
        super(RageGhoul.name, RageGhoul.description, RageGhoul.baseCost, RageGhoul.baseHP, RageGhoul.baseATK);
    }

    @Override
    public void doBattleCryEffect(Minion target) {
        List<Minion> targets = new ArrayList<Minion>();
        for (Minion minion : this.master.getAlly()) {
            if (!(minion instanceof Hero))
                targets.add(minion);
        }
        for (Minion minion : this.master.getEnemy()) {
            if (!(minion instanceof Hero))
                targets.add(minion);
        }
        targets.remove(this);
        for (Minion minion : targets) {
            ;
            minion.setHP(minion.getHP() - 1);
        }
    }

}