package heart.model.spell;

import java.util.ArrayList;
import java.util.List;

import heart.model.Minion;
import heart.model.Player;
import heart.model.Targeting;
import heart.model.minion.Hero;

//Complete
public class InnerFire extends AbstractSpell implements Targeting {

    private static String name = "InnerFire";
    private static String description = "Change a minion's ATK to be equal to its HP";
    private static int baseCost = 1;

    public InnerFire() {
        super(InnerFire.name, InnerFire.description, InnerFire.baseCost);
    }

    @Override
    public List<Minion> getCandidates(Player player) {
        List<Minion> candidates = new ArrayList<Minion>();
        for (Minion minion : player.getAlly()) {
            if (minion.canTargeted() && !(minion instanceof Hero))
                candidates.add(minion);
        }
        for (Minion minion : player.getEnemy()) {
            if (minion.canTargeted() && !(minion instanceof Hero))
                candidates.add(minion);
        }
        return candidates;
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        target.setATK(target.getHP());
    }

}