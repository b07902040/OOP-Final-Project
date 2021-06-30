package heart.model.minion;

import java.util.ArrayList;
import java.util.List;

import heart.model.BattleCry;
import heart.model.Minion;
import heart.model.Targeting;
import heart.model.Taunt;

//Complete
public class HotSpring extends AbstractMinion implements BattleCry, Targeting, Taunt {

    private static String name = "HotSpring";
    private static String description = "Taunt & BattleCry: Restore 3 HP";
    private static int baseCost = 3;
    private static int baseATK = 2;
    private static int baseHP = 4;

    public HotSpring() {
        super(HotSpring.name, HotSpring.description, HotSpring.baseCost, HotSpring.baseHP, HotSpring.baseATK);
    }

    @Override
    public List<Minion> getCandidates(List<Minion> ally, List<Minion> enemy) {
        List<Minion> candidates = new ArrayList<Minion>();
        for (Minion minion : ally) {
            if (minion.canTargeted())
                candidates.add(minion);
        }
        for (Minion minion : enemy) {
            if (minion.canTargeted())
                candidates.add(minion);
        }
        return candidates;
    }

    @Override
    public void doBattleCryEffect(Minion target) {
        if(target == null) return;
        target.setHP(target.getHP() + 3);
    }

}