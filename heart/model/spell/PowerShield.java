package heart.model.spell;

import java.util.ArrayList;
import java.util.List;

import heart.model.Minion;
import heart.model.Player;
import heart.model.Targeting;
import heart.model.minion.Hero;

//Complete
public class PowerShield extends AbstractSpell implements Targeting {

    private static String name = "PowerShield";
    private static String description = "Give a minion +2 HP. Draw a card";
    private static int baseCost = 1;

    public PowerShield() {
        super(PowerShield.name, PowerShield.description, PowerShield.baseCost);
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
        target.setBuffHP(target.getBuffHP() + 2);
        target.setHP(target.getHP() + 2);
        user.drawCards();
    }

}