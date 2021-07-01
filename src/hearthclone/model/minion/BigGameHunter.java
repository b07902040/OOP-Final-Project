package hearthclone.model.minion;

import java.util.ArrayList;
import java.util.List;

import hearthclone.model.BattleCry;
import hearthclone.model.Minion;
import hearthclone.model.Targeting;

//Complete
public class BigGameHunter extends AbstractMinion implements BattleCry, Targeting {

    private static String name = "BigGameHunter";
    private static String description = "BattleCry: Destroy a minion with 7 or more ATK";
    private static int baseCost = 4;
    private static int baseATK = 4;
    private static int baseHP = 2;

    public BigGameHunter() {
        super(BigGameHunter.name, BigGameHunter.description, BigGameHunter.baseCost, BigGameHunter.baseHP,
                BigGameHunter.baseATK);
    }

    @Override
    public List<Minion> getCandidates(List<Minion> ally, List<Minion> enemy) {
        List<Minion> candidates = new ArrayList<Minion>();
        for (Minion minion : ally) {
            if (minion.getATK() < 7)
                continue;
            if (!(minion instanceof Hero) && minion.canTargeted())
                candidates.add(minion);
        }
        for (Minion minion : enemy) {
            if (minion.getATK() < 7)
                continue;
            if (!(minion instanceof Hero) && minion.canTargeted())
                candidates.add(minion);
        }
        return candidates;
    }

    @Override
    public void doBattleCryEffect(Minion target) {
        if(target == null) return;
        target.setAlive(false);
    }

}