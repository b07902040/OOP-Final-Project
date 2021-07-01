package hearthclone.model.spell;

import java.util.ArrayList;
import java.util.List;

import hearthclone.model.Minion;
import hearthclone.model.Player;
import hearthclone.model.minion.Hero;

//Complete
public class BlackHole extends AbstractSpell {

    private static String name = "BlackHole";
    private static String description = "Destroy all minions";
    private static int baseCost = 8;

    public BlackHole() {
        super(BlackHole.name, BlackHole.description, BlackHole.baseCost);
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        List<Minion> targets = new ArrayList<Minion>();
        for (Minion minion : user.getAlly()) {
            if (!(minion instanceof Hero))
                targets.add(minion);
        }
        for (Minion minion : user.getEnemy()) {
            if (!(minion instanceof Hero))
                targets.add(minion);
        }
        for (Minion minion : targets)
            minion.setAlive(false);
    }

}