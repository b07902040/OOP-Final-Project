package hearthclone.model.spell;

import java.util.ArrayList;
import java.util.List;

import hearthclone.model.Minion;
import hearthclone.model.Player;
import hearthclone.model.Targeting;

//Complete
public class DeathCoil extends AbstractSpell implements Targeting {

    private static String name = "DeathCoil";
    private static String description = "Deal 5 damge to an enemy, or restore 5 HP to ally";
    private static int baseCost = 2;

    public DeathCoil() {
        super(DeathCoil.name, DeathCoil.description, DeathCoil.baseCost);
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
        if (user.getAlly().contains(target))
            target.setHP(target.getHP() + 5);
        else
            target.setHP(target.getHP() - 5);
    }

}