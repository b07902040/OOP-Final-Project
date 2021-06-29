package heart.model.spell;

import java.util.ArrayList;
import java.util.List;

import heart.model.Minion;
import heart.model.Player;
import heart.model.minion.Hero;

//Complete
public class FlameStrike extends AbstractSpell {

    private static String name = "FlameStrike";
    private static String description = "Deal 4 damage to all enemy minions";
    private static int baseCost = 7;

    public FlameStrike() {
        super(FlameStrike.name, FlameStrike.description, FlameStrike.baseCost);
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        List<Minion> targets = new ArrayList<Minion>();
        for (Minion minion : user.getEnemy()) {
            if (!(minion instanceof Hero))
                targets.add(minion);
        }
        for (Minion minion : targets)
            minion.setHP(minion.getHP() - 4);

    }

}