package heart.model.minion;

import heart.model.DeathRattle;

//Complete
public class WolfGrandma extends AbstractMinion implements DeathRattle {

    private static String name = "WolfGrandma";
    private static String description = "DeathRattle: Summon a BigBadWolf";
    private static int baseCost = 2;
    private static int baseATK = 1;
    private static int baseHP = 1;

    public WolfGrandma() {
        super(WolfGrandma.name, WolfGrandma.description, WolfGrandma.baseCost, WolfGrandma.baseHP, WolfGrandma.baseATK);
    }

    @Override
    public void doDeathRattleEffect() {
        this.master.summonAlly(new BigBadWolf(), this.master.getAlly().size());
    }

}