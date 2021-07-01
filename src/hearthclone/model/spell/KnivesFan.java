package hearthclone.model.spell;

import java.util.ArrayList;
import java.util.List;

import hearthclone.model.Minion;
import hearthclone.model.Player;
import hearthclone.model.minion.Hero;

//Complete
public class KnivesFan extends AbstractSpell {

    private static String name = "KnivesFan";
    private static String description = "Deal 1 damage to all enemy minions and draw a card";
    private static int baseCost = 3;

    public KnivesFan() {
        super(KnivesFan.name, KnivesFan.description, KnivesFan.baseCost);
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        List<Minion> targets = new ArrayList<Minion>();
        for (Minion minion : user.getEnemy()) {
            if (!(minion instanceof Hero))
                targets.add(minion);
        }
        for (Minion minion : targets)
            minion.setHP(minion.getHP() - 1);
        user.drawCards();

    }

}