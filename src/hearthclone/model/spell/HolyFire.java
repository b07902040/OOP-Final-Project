package hearthclone.model.spell;

import java.util.ArrayList;
import java.util.List;

import hearthclone.model.Minion;
import hearthclone.model.Player;
import hearthclone.model.Targeting;

//Complete
public class HolyFire extends AbstractSpell implements Targeting {

    private static String name = "HolyFire";
    private static String description = "Deal 5 damage.Restore 5 HP to your hero";
    private static int baseCost = 5;

    public HolyFire() {
        super(HolyFire.name, HolyFire.description, HolyFire.baseCost);
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
    public void takeEffect(Player user, Minion target) {
        target.setHP(target.getHP() - 5);
        user.getHero().setHP(user.getHero().getHP() + 5);
    }

}