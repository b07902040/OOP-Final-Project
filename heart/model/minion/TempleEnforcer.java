package heart.model.minion;

import java.util.ArrayList;
import java.util.List;

import heart.model.BattleCry;
import heart.model.Minion;
import heart.model.Player;
import heart.model.Targeting;

//Complete
public class TempleEnforcer extends AbstractMinion implements BattleCry, Targeting {

    private static String name = "TempleEnforcer";
    private static String description = "BattleCry: Give a friendly minion +3 HP";
    private static int baseCost = 5;
    private static int baseATK = 5;
    private static int baseHP = 6;

    public TempleEnforcer() {
        super(TempleEnforcer.name, TempleEnforcer.description, TempleEnforcer.baseCost, TempleEnforcer.baseHP,
                TempleEnforcer.baseATK);
    }

    @Override
    public List<Minion> getCandidates(Player player) {
        List<Minion> candidates = new ArrayList<Minion>();
        for (Minion minion : player.getAlly()) {
            if (!(minion instanceof Hero) && minion.canTargeted())
                candidates.add(minion);
        }
        return candidates;
    }

    @Override
    public void doBattleCryEffect(Minion target) {
        target.setBuffHP(target.getBuffHP() + 3);
        target.setHP(target.getHP() + 3);
    }

}