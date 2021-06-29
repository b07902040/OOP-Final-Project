package heart.model.spell;

import heart.model.Minion;
import heart.model.Player;
import heart.model.minion.Hero;

//Complete
public class LightBomb extends AbstractSpell {

    private static String name = "LightBomb";
    private static String description = "Deal damage to each minion equal to its ATK";
    private static int baseCost = 6;

    public LightBomb() {
        super(LightBomb.name, LightBomb.description, LightBomb.baseCost);
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        for (Minion minion : user.getAlly()) {
            if (!(minion instanceof Hero))
                minion.setHP(minion.getHP() - minion.getATK());
        }

        for (Minion minion : user.getEnemy()) {
            if (!(minion instanceof Hero))
                minion.setHP(minion.getHP() - minion.getATK());
        }

    }

}