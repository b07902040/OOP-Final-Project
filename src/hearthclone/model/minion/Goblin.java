package hearthclone.model.minion;

import hearthclone.model.DeathRattle;
import hearthclone.model.Minion;

//Complete
public class Goblin extends AbstractMinion implements DeathRattle {

    private static String name = "Goblin";
    private static String description = "DeathRattle: Deal 2 damage to the enemy Hero";
    private static int baseCost = 1;
    private static int baseATK = 2;
    private static int baseHP = 1;

    public Goblin() {
        super(Goblin.name, Goblin.description, Goblin.baseCost, Goblin.baseHP, Goblin.baseATK);
    }

    @Override
    public void doDeathRattleEffect() {
        System.out.printf("%s Deathrattle!\n", name);
        Minion enemyHero = this.master.getOpponent().getHero();
        enemyHero.setHP(enemyHero.getHP() - 2);
    }

}