package heart.model.minion;

import heart.model.DeathRattle;

//Complete
public class LionKing extends AbstractMinion implements DeathRattle {

    private static String name = "LionKing";
    private static String description = "DeathRattle: Summon two 2/2 Hyenas";
    private static int baseCost = 6;
    private static int baseATK = 6;
    private static int baseHP = 5;

    public LionKing() {
        super(LionKing.name, LionKing.description, LionKing.baseCost, LionKing.baseHP, LionKing.baseATK);
    }

    @Override
    public void doDeathRattleEffect() {
        this.master.summonAlly(new Hyena(), this.master.getAlly().size());
        this.master.summonAlly(new Hyena(), this.master.getAlly().size());
    }

}