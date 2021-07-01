package hearthclone.model.spell;

import java.util.ArrayList;
import java.util.List;

import hearthclone.model.Minion;
import hearthclone.model.Player;

//Complete
public class Decay extends AbstractSpell {

    private static String name = "Decay";
    private static String description = "Deal 3 damge to all enemies";
    private static int baseCost = 3;

    public Decay() {
        super(Decay.name, Decay.description, Decay.baseCost);
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        List<Minion> targets = new ArrayList<Minion>();
        for (Minion minion : user.getEnemy())
            targets.add(minion);
        for (Minion minion : targets)
            minion.setHP(minion.getHP() - 3);
    }

}