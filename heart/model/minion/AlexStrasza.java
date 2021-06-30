package heart.model.minion;

import java.util.ArrayList;
import java.util.List;

import heart.model.BattleCry;
import heart.model.Minion;
import heart.model.Targeting;

//Complete
public class AlexStrasza extends AbstractMinion implements BattleCry, Targeting {

    private static String name = "AlexStrasza";
    private static String description = "BattleCry: Set a hero's remaining HP to 15";
    private static int baseCost = 9;
    private static int baseATK = 8;
    private static int baseHP = 8;

    public AlexStrasza() {
        super(AlexStrasza.name, AlexStrasza.description, AlexStrasza.baseCost, AlexStrasza.baseHP, AlexStrasza.baseATK);
    }

    @Override
    public List<Minion> getCandidates(List<Minion> ally, List<Minion> enemy) {
        List<Minion> candidates = new ArrayList<Minion>();
        candidates.add(ally.get(0));
        candidates.add(ally.get(1));
        return candidates;
    }

    @Override
    public void doBattleCryEffect(Minion target) {
        target.reWriteHP(15);
    }

}