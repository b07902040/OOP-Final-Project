package hearthclone.model.spell;

import java.util.ArrayList;
import java.util.List;

import hearthclone.model.Minion;
import hearthclone.model.Player;
import hearthclone.model.Targeting;
import hearthclone.model.minion.Ghoul;

//Complete
public class Infestation extends AbstractSpell implements Targeting {

    private static String name = "Infestation";
    private static String description = "Deal 5 damage. Draw 5 cards. Restore 5 HP to your hero. Summon a 5/5 Ghoul.";
    private static int baseCost = 10;

    public Infestation() {
        super(Infestation.name, Infestation.description, Infestation.baseCost);
    }

    @Override
    public List<Minion> getCandidates(List<Minion> ally, List<Minion> enemy) {
        List<Minion> candidates = new ArrayList<Minion>();
        for (Minion minion : ally) {
            if (minion.canTargeted())
                candidates.add(minion);
        }
        for (Minion minion : enemy) {
            if (minion.canTargeted())
                candidates.add(minion);
        }
        return candidates;
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        target.setHP(target.getHP() - 5);
        for(int t = 0; t < 5;t++)
            user.drawCards();
        user.getHero().setHP(user.getHero().getHP() + 5);
        user.summonAlly(new Ghoul(), user.getAlly().size());
    }

}