package heart.model.spell;

import java.util.ArrayList;
import java.util.List;

import heart.model.Minion;
import heart.model.Player;
import heart.model.Targeting;
import heart.model.minion.Hero;

//Complete
public class Blessing extends AbstractSpell implements Targeting {

    private static String name = "Blessing";
    private static String description = "Give a minion +4/+4";
    private static int baseCost = 4;

    public Blessing() {
        super(Blessing.name, Blessing.description, Blessing.baseCost);
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
        target.setBuffHP(target.getBuffHP() + 4);
        target.setHP(target.getHP() + 4);
        target.setATK(target.getATK() + 4);
    }

}