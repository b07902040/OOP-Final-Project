package hearthclone.model.spell;

import hearthclone.model.Minion;
import hearthclone.model.Player;

//Complete
public class MindBlast extends AbstractSpell {

    private static String name = "MindBlast";
    private static String description = "Deal 5 damage to the enemy hero";
    private static int baseCost = 2;

    public MindBlast() {
        super(MindBlast.name, MindBlast.description, MindBlast.baseCost);
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        Minion enemyHero = user.getOpponent().getHero();
        enemyHero.setHP(enemyHero.getHP() - 5);
    }

}