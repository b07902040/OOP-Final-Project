package hearthclone.model.minion;

import java.util.ArrayList;
import java.util.List;

import hearthclone.model.BattleCry;
import hearthclone.model.Minion;
import hearthclone.model.Targeting;

//Complete
public class SunCleric extends AbstractMinion implements BattleCry, Targeting {

    private static String name = "SunCleric";
    private static String description = "BattleCry: Give a friendly minion +1/+1";
    private static int baseCost = 3;
    private static int baseATK = 3;
    private static int baseHP = 2;

    public SunCleric() {
        super(SunCleric.name, SunCleric.description, SunCleric.baseCost, SunCleric.baseHP, SunCleric.baseATK);
    }

    @Override
    public List<Minion> getCandidates(List<Minion> ally, List<Minion> enemy) {
        List<Minion> candidates = new ArrayList<Minion>();
        for (Minion minion : ally) {
            if (!(minion instanceof Hero) && minion.canTargeted())
                candidates.add(minion);
        }
        return candidates;
    }

    @Override
    public void doBattleCryEffect(Minion target) {
        if(target == null) return;
        target.setBuffHP(target.getBuffHP() + 1);
        target.setHP(target.getHP() + 1);
        target.setATK(target.getATK() + 1);
    }

}