package hearthclone.model.minion;

import java.util.List;

import hearthclone.model.Game;
import hearthclone.model.Minion;

//Complete
public class Ragnaros extends AbstractMinion {

    private static String name = "Ragnaros";
    private static String description = "Can't Attack. At the end of your turn, deal 8 damge to a random enemy";
    private static int baseCost = 8;
    private static int baseATK = 8;
    private static int baseHP = 8;

    public Ragnaros() {
        super(Ragnaros.name, Ragnaros.description, Ragnaros.baseCost, Ragnaros.baseHP, Ragnaros.baseATK);
    }

    @Override
    public boolean canAttack() {
        return false;
    }

    @Override
    public void doTurnEnd() {
        List<Minion> enemy = this.master.getEnemy();
        int targetId = Game.getRandom(enemy.size()) - 1;
        Minion e = enemy.get(targetId);
        e.setHP(e.getHP() - 8);
    }

}