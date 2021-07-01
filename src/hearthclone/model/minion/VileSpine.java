package hearthclone.model.minion;

import java.util.ArrayList;
import java.util.List;

import hearthclone.model.BattleCry;
import hearthclone.model.Combo;
import hearthclone.model.Game;
import hearthclone.model.Minion;
import hearthclone.model.Player;

//Complete
public class VileSpine extends AbstractMinion implements BattleCry, Combo {

    private static String name = "VileSpine";
    private static String description = "BattleCry & Combo: Destroy a random enemy minion";
    private static int baseCost = 5;
    private static int baseATK = 4;
    private static int baseHP = 4;

    public VileSpine() {
        super(VileSpine.name, VileSpine.description, VileSpine.baseCost, VileSpine.baseHP, VileSpine.baseATK);
    }

    @Override
    public int getCombo(Player master) {
        return master.getCardPlayed() - 1;
    }

    @Override
    public void doBattleCryEffect(Minion target) {
        int combo = ((Combo) this).getCombo(this.master);
        if (combo < 1)
            return;
        List<Minion> candidates = new ArrayList<Minion>();
        for (Minion minion : this.master.getEnemy()) {
            if (!(minion instanceof Hero))
                candidates.add(minion);
        }
        if (candidates.size() == 0)
            return;
        int targetId = Game.getRandom(candidates.size()) - 1;
        Minion e = candidates.get(targetId);
        e.setAlive(false);
    }

}