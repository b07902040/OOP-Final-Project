package heart.model.spell;

import java.util.ArrayList;
import java.util.List;

import heart.model.Minion;
import heart.model.Player;
import heart.model.Targeting;

//Complete
public class DivineHeal extends AbstractSpell implements Targeting {

    private static String name = "DivineHeal";
    private static String description = "Restore 8 HP and draw 3 cards";
    private static int baseCost = 8;

    public DivineHeal() {
        super(DivineHeal.name, DivineHeal.description, DivineHeal.baseCost);
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
        target.setHP(target.getHP() + 8);
        user.drawCards(3);
    }

}