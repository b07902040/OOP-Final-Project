package heart.model.spell;

import heart.model.Minion;
import heart.model.Player;
import heart.model.minion.Hero;

//Complete
public class HolyNova extends AbstractSpell {

    private static String name = "HolyNova";
    private static String description = "Deal 2 damage to all enemy minions. Restore 2 HP to all friendly characters.";
    private static int baseCost = 5;

    public HolyNova() {
        super(HolyNova.name, HolyNova.description, HolyNova.baseCost);
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        for (Minion minion : user.getAlly()) {
            minion.setHP(minion.getHP() + 2);
        }

        for (Minion minion : user.getEnemy()) {
            if (!(minion instanceof Hero))
                minion.setHP(minion.getHP() - 2);
        }

    }

}