package hearthclone.model.spell;

import hearthclone.model.Minion;
import hearthclone.model.Player;

//Complete
public class Intellect extends AbstractSpell {

    private static String name = "Intellect";
    private static String description = "Draw 2 cards";
    private static int baseCost = 3;

    public Intellect() {
        super(Intellect.name, Intellect.description, Intellect.baseCost);
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        for(int t = 0; t < 2;t++)
            user.drawCards();
    }

}