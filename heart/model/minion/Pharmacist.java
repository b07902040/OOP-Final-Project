package heart.model.minion;

import heart.model.DeathRattle;
import heart.model.Minion;

//Complete
public class Pharmacist extends AbstractMinion implements DeathRattle {

    private static String name = "Pharmacist";
    private static String description = "DeathRattle: Restore 4 HP to each hero.";
    private static int baseCost = 1;
    private static int baseATK = 2;
    private static int baseHP = 2;

    public Pharmacist() {
        super(Pharmacist.name, Pharmacist.description, Pharmacist.baseCost, Pharmacist.baseHP, Pharmacist.baseATK);
    }

    @Override
    public void doDeathRattleEffect() {
        Minion hero = this.master.getHero();
        Minion enemyHero = this.master.getOpponent().getHero();
        hero.setHP(hero.getHP() + 4);
        enemyHero.setHP(enemyHero.getHP() + 4);
    }

}