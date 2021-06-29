package heart.model.spell;

import heart.model.Minion;
import heart.model.Player;

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
        user.drawCards(20);
    }

}