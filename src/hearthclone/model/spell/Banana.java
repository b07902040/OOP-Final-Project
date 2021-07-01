package hearthclone.model.spell;

import java.util.ArrayList;
import java.util.List;

import hearthclone.model.Minion;
import hearthclone.model.Player;
import hearthclone.model.Targeting;
import hearthclone.model.minion.Hero;

//Complete
public class Banana extends AbstractSpell implements Targeting {

    private static String name = "Banana";
    private static String description = "Give a minion +1/+1";
    private static int baseCost = 1;

    public Banana() {
        super(Banana.name, Banana.description, Banana.baseCost);
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
    public void takeEffect(Player user, Minion target) {
        target.setBuffHP(target.getBuffHP() + 1);
        target.setHP(target.getHP() + 1);
        target.setATK(target.getATK() + 1);
    }

}