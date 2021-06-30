package heart.model.spell;

import java.util.List;

import heart.model.*;
import heart.model.minion.*;

import java.util.ArrayList;

//Complete
public class Doom extends AbstractSpell {

    private static String name = "Doom";
    private static String description = "Destroy all minions. Draw a card for each.";
    private static int baseCost = 10;

    public Doom() {
        super(Doom.name, Doom.description, Doom.baseCost);
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
        for(int t = 0; t < targets.size();t++)
            user.drawCards();
    }

}