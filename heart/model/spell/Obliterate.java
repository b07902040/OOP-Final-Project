package heart.model.spell;

import java.util.ArrayList;
import java.util.List;

import heart.model.Minion;
import heart.model.Player;
import heart.model.Targeting;
import heart.model.minion.Hero;

//Complete
public class Obliterate extends AbstractSpell implements Targeting {
    
    private static String name = "Obliterate";
    private static String description = "Destroy a minion, your hero takes damage equal to its HP";
    private static int baseCost = 2;

    public Obliterate() {
        super(Obliterate.name, Obliterate.description, Obliterate.baseCost);
    }

    @Override
    public List<Minion> getCandidates(List<Minion> ally, List<Minion> enemy) {
        List<Minion> candidates = new ArrayList<Minion>();
        for (Minion minion : ally) {
            if (!(minion instanceof Hero) && minion.canTargeted())
                candidates.add(minion);
        }
        for (Minion minion : enemy) {
            if (!(minion instanceof Hero) && minion.canTargeted())
                candidates.add(minion);
        }
        return candidates;
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        target.setAlive(false);
        user.getHero().setHP(user.getHero().getHP() - target.getHP());
    }

}