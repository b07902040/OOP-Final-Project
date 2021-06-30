package heart.model.spell;

import java.util.ArrayList;
import java.util.List;

import heart.model.Card;
import heart.model.Game;
import heart.model.Minion;
import heart.model.Player;
import heart.model.Targeting;

//Complete
public class SoulFire extends AbstractSpell implements Targeting {

    private static String name = "SoulFire";
    private static String description = "Deal 10 damage";
    private static int baseCost = 1;

    public SoulFire() {
        super(SoulFire.name, SoulFire.description, SoulFire.baseCost);
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
        target.setHP(target.getHP() - 4);
        List<Card> handCards = user.getHandCards();
        if (handCards.size() > 0) {
            int index = Game.getRandom(handCards.size()) - 1;
            user.throwCard(index);
        }
    }

}