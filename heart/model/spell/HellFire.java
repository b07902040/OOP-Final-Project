package heart.model.spell;

import java.util.ArrayList;
import java.util.List;

import heart.model.Minion;
import heart.model.Player;

//Complete
public class HellFire extends AbstractSpell {

    private static String name = "HellFire";
    private static String description = "Deal 3 damage to all characters";
    private static int baseCost = 0;

    public HellFire() {
        super(HellFire.name, HellFire.description, HellFire.baseCost);
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        List<Minion> targets = new ArrayList<Minion>();
        for (Minion minion : user.getAlly())
            targets.add(minion);
        for (Minion minion : user.getEnemy())
            targets.add(minion);
        for (Minion minion : targets)
            minion.setHP(minion.getHP() - 3);
    }

}