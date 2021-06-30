package heart.model.spell;

import java.util.ArrayList;
import java.util.List;

import heart.model.Minion;
import heart.model.Player;
import heart.model.Targeting;
import heart.model.minion.Hero;

//Complete
public class ShadowWord extends AbstractSpell implements Targeting {

    private static String name = "ShadowWord";
    private static String description = "Destroy a minion with 5 or more ATK";
    private static int baseCost = 3;

    public ShadowWord() {
        super(ShadowWord.name, ShadowWord.description, ShadowWord.baseCost);
    }

    @Override
    public List<Minion> getCandidates(List<Minion> ally, List<Minion> enemy) {
        List<Minion> candidates = new ArrayList<Minion>();
        for (Minion minion : ally) {
            if (minion.canTargeted() && !(minion instanceof Hero)) {
                if (minion.getATK() >= 5)
                    candidates.add(minion);
            }
        }
        for (Minion minion : enemy) {
            if (minion.canTargeted() && !(minion instanceof Hero)) {
                if (minion.getATK() >= 5)
                    candidates.add(minion);
            }
        }
        return candidates;
    }

    @Override
    public void takeEffect(Player user, Minion target) {
        target.setAlive(false);
    }

}