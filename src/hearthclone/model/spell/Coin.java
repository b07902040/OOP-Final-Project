package hearthclone.model.spell;

import hearthclone.model.Minion;
import hearthclone.model.Player;

//Complete
public class Coin extends AbstractSpell {

    private static String name = "Coin";
    private static String description = "Gain 1 mana this turn only";
    private static int baseCost = 0;

    public Coin() {
        super(Coin.name, Coin.description, Coin.baseCost);
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        user.setMana(user.getMana() + 1);
    }

}