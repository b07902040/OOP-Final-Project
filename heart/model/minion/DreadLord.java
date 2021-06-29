package heart.model.minion;

import java.util.ArrayList;
import java.util.List;

import heart.model.Minion;

//Complete
public class DreadLord extends AbstractMinion {

    private static String name = "DreadLord";
    private static String description = "At the end of your turn, deal 1 damage to all enemy minions";
    private static int baseCost = 5;
    private static int baseATK = 4;
    private static int baseHP = 5;

    public DreadLord() {
        super(DreadLord.name, DreadLord.description, DreadLord.baseCost, DreadLord.baseHP, DreadLord.baseATK);
    }

    @Override
    public void doTurnEnd() {
        List<Minion> targets = new ArrayList<Minion>();
        for (Minion minion : this.master.getEnemy()) {
            if (!(minion instanceof Hero))
                targets.add(minion);
        }
        for (Minion minion : targets)
            minion.setHP(minion.getHP() - 1);
    }

}