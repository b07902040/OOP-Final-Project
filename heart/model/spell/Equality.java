package heart.model.spell;

import java.util.List;

import heart.model.*;
import heart.model.minion.*;

import java.util.ArrayList;

//Complete
public class Equality extends AbstractSpell {

    private static String name = "Equality";
    private static String description = "Change the HP of  all minions to 1";
    private static int baseCost = 3;

    public Equality() {
        super(Equality.name, Equality.description, Equality.baseCost);
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
        for (Minion minion : targets) {
            minion.setBuffHP(1);
            minion.reWriteHP(1);
        }
    }

}