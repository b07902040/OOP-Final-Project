package heart.model.spell;

import java.util.ArrayList;
import java.util.List;

import heart.model.Minion;
import heart.model.Player;
import heart.model.Targeting;
import heart.model.minion.Hero;

//Complete
public class DivineSpirit extends AbstractSpell implements Targeting {

    private static String name = "DivineSpirit";
    private static String description = "Double a minion's HP";
    private static int baseCost = 2;

    public DivineSpirit() {
        super(DivineSpirit.name, DivineSpirit.description, DivineSpirit.baseCost);
    }

    @Override
    public List<Minion> getCandidates(List<Minion> ally, List<Minion> enemy) {
        List<Minion> candidates = new ArrayList<Minion>();
        for (Minion minion : ally) {
            if (minion.canTargeted() && !(minion instanceof Hero))
                candidates.add(minion);
        }
        for (Minion minion : enemy) {
            if (minion.canTargeted() && !(minion instanceof Hero))
                candidates.add(minion);
        }
        return candidates;
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        target.setBuffHP(target.getBuffHP() * 2);
        target.setHP(target.getHP() * 2);
    }

}