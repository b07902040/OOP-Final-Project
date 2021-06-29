package heart.model.minion;

import heart.model.DeathRattle;
import heart.model.Taunt;

//Complete
public class Sludge extends AbstractMinion implements DeathRattle, Taunt {

    private static String name = "Sludge";
    private static String description = "Taunt & DeathRattle: Summon a 1/2 Slime with Taunt";
    private static int baseCost = 5;
    private static int baseATK = 3;
    private static int baseHP = 5;

    public Sludge() {
        super(Sludge.name, Sludge.description, Sludge.baseCost, Sludge.baseHP, Sludge.baseATK);
    }

    @Override
    public void doDeathRattleEffect() {
        this.master.summonAlly(new Slime(), this.master.getAlly().size());
    }

}