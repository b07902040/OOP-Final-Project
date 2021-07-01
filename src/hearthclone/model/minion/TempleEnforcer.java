package hearthclone.model.minion;

import java.util.ArrayList;
import java.util.List;

import hearthclone.model.BattleCry;
import hearthclone.model.Minion;
import hearthclone.model.Targeting;

//Complete
public class TempleEnforcer extends AbstractMinion implements BattleCry, Targeting {

    private static String name = "TempleEnforcer";
    private static String description = "BattleCry: Give a friendly minion +3 HP";
    private static int baseCost = 5;
    private static int baseATK = 5;
    private static int baseHP = 6;

    public TempleEnforcer() {
        super(TempleEnforcer.name, TempleEnforcer.description, TempleEnforcer.baseCost, TempleEnforcer.baseHP,
                TempleEnforcer.baseATK);
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
        target.setBuffHP(target.getBuffHP() + 3);
        target.setHP(target.getHP() + 3);
    }

}