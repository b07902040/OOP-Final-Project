package heart.model.minion;

import java.util.ArrayList;
import java.util.List;

import heart.model.BattleCry;
import heart.model.Minion;
import heart.model.Player;
import heart.model.Targeting;

//Complete
public class PeaceKeeper extends AbstractMinion implements BattleCry, Targeting {

    private static String name = "PeaceKeeper";
    private static String description = "BattleCry:Change an enemy minion's ATK to 1";
    private static int baseCost = 3;
    private static int baseATK = 3;
    private static int baseHP = 3;

    public PeaceKeeper() {
        super(PeaceKeeper.name, PeaceKeeper.description, PeaceKeeper.baseCost, PeaceKeeper.baseHP, PeaceKeeper.baseATK);
    }

    @Override
    public List<Minion> getCandidates(Player player) {
        List<Minion> candidates = new ArrayList<Minion>();
        for (Minion minion : player.getEnemy()) {
            if (!(minion instanceof Hero) && minion.canTargeted())
                candidates.add(minion);
        }
        return candidates;
    }

    @Override
    public void doBattleCryEffect(Minion target) {
        target.setATK(1);
    }

}