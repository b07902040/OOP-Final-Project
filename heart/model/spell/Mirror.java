package heart.model.spell;

import heart.model.Minion;
import heart.model.Player;
import heart.model.minion.MirrorImage;

//Complete
public class Mirror extends AbstractSpell {

    private static String name = "Mirror";
    private static String description = "Summon two 0/2 minions with Taunt";
    private static int baseCost = 1;

    public Mirror() {
        super(Mirror.name, Mirror.description, Mirror.baseCost);
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        user.summonAlly(new MirrorImage(), user.getAlly().size());
        user.summonAlly(new MirrorImage(), user.getAlly().size());
    }

}