package heart.model.spell;

import heart.constant.Const;
import heart.model.Minion;
import heart.model.Player;

//Complete
public class WildGrowth extends AbstractSpell {

    private static String name = "WildGrowth";
    private static String description = "Gain an empty Mana ";
    private static int baseCost = 2;

    public WildGrowth() {
        super(WildGrowth.name, WildGrowth.description, WildGrowth.baseCost);
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        int mana = user.getFullMana() + 1;
        if (mana > Const.MAX_MANA) {
            user.addHandCards(new ExcessMana());
            return;
        }
        user.setFullMana(mana);
    }

}