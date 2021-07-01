package hearthclone.model.minion;

import java.util.ArrayList;
import java.util.List;

import hearthclone.model.BattleCry;
import hearthclone.model.Minion;
import hearthclone.model.Targeting;

//Complete
public class PeaceKeeper extends AbstractMinion implements BattleCry, Targeting {

    private static String name = "PeaceKeeper";
    private static String description = "BattleCry: Change an enemy minion's ATK to 1";
    private static int baseCost = 3;
    private static int baseATK = 3;
    private static int baseHP = 3;

    public PeaceKeeper() {
        super(PeaceKeeper.name, PeaceKeeper.description, PeaceKeeper.baseCost, PeaceKeeper.baseHP, PeaceKeeper.baseATK);
    }

    @Override
    public List<Minion> getCandidates(List<Minion> ally, List<Minion> enemy) {
        List<Minion> candidates = new ArrayList<Minion>();
        for (Minion minion : enemy) {
            if (!(minion instanceof Hero) && minion.canTargeted())
                candidates.add(minion);
        }
        return candidates;
    }

    @Override
    public void doBattleCryEffect(Minion target) {
        if(target == null) return;
        target.setATK(1);
    }

}