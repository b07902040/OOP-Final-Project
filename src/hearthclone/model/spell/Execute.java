package hearthclone.model.spell;

import java.util.ArrayList;
import java.util.List;

import hearthclone.model.Minion;
import hearthclone.model.Player;
import hearthclone.model.Targeting;
import hearthclone.model.minion.Hero;

//Complete
public class Execute extends AbstractSpell implements Targeting {

    private static String name = "Execute";
    private static String description = "Destroy a damaged enemy minion";
    private static int baseCost = 1;

    public Execute() {
        super(Execute.name, Execute.description, Execute.baseCost);
    }

    @Override
    public List<Minion> getCandidates(List<Minion> ally, List<Minion> enemy) {
        List<Minion> candidates = new ArrayList<Minion>();
        for (Minion minion : enemy) {
            if (minion.canTargeted() && !(minion instanceof Hero))
                if (minion.getHP() < minion.getBuffHP()) {
                    candidates.add(minion);
                }
        }
        return candidates;
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        target.setAlive(false);
    }

}