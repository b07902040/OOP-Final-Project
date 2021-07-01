package hearthclone.model.minion;

import java.util.ArrayList;
import java.util.List;

import hearthclone.model.BattleCry;
import hearthclone.model.Minion;
import hearthclone.model.Targeting;

//Complete
public class CruelMaster extends AbstractMinion implements BattleCry, Targeting {

    private static String name = "CruelMaster";
    private static String description = "BattleCry: Deal 1 damage to a minion and give it +2 ATK";
    private static int baseCost = 2;
    private static int baseATK = 2;
    private static int baseHP = 2;

    public CruelMaster() {
        super(CruelMaster.name, CruelMaster.description, CruelMaster.baseCost, CruelMaster.baseHP, CruelMaster.baseATK);
    }

    @Override
    public List<Minion> getCandidates(List<Minion> ally, List<Minion> enemy) {
        List<Minion> candidates = new ArrayList<Minion>();
        for (Minion minion : ally) {
            if (!(minion instanceof Hero) && minion.canTargeted())
                candidates.add(minion);
        }
        for (Minion minion : enemy) {
            if (!(minion instanceof Hero) && minion.canTargeted())
                candidates.add(minion);
        }
        return candidates;
    }

    @Override
    public void doBattleCryEffect(Minion target) {
        if(target == null) return;
        target.setHP(target.getHP() - 1);
        target.setATK(target.getATK() + 1);
    }

}