package heart.model.minion;

import java.util.List;

import heart.model.DeathRattle;
import heart.model.Game;
import heart.model.Minion;

//Complete
public class BoomBot extends AbstractMinion implements DeathRattle {

    private static String name = "BoomBot";
    private static String description = "DeathRattle: Deal 1~4 damage to a random enemy";
    private static int baseCost = 1;
    private static int baseATK = 1;
    private static int baseHP = 1;

    public BoomBot() {
        super(BoomBot.name, BoomBot.description, BoomBot.baseCost, BoomBot.baseHP, BoomBot.baseATK);
    }

    @Override
    public void doDeathRattleEffect() {
        List<Minion> enemy = this.master.getEnemy();
        int targetId = Game.getRandom(enemy.size()) - 1;
        int dmg = Game.getRandom(4);
        Minion e = enemy.get(targetId);
        e.setHP(e.getHP() - dmg);
    }

}