package heart.model.minion;

import heart.model.*;

//Complete
public class VoidLord extends AbstractMinion implements DeathRattle, Taunt {

    private static String name = "VoidLord";
    private static String description = "Taunt & DeathRattle: Summon three VoidWalker";
    private static int baseCost = 9;
    private static int baseATK = 3;
    private static int baseHP = 9;

    public VoidLord() {
        super(VoidLord.name, VoidLord.description, VoidLord.baseCost, VoidLord.baseHP, VoidLord.baseATK);
    }

    @Override
    public void doDeathRattleEffect() {
        this.master.summonAlly(new VoidWalker(), this.master.getAlly().size());
        this.master.summonAlly(new VoidWalker(), this.master.getAlly().size());
        this.master.summonAlly(new VoidWalker(), this.master.getAlly().size());
    }

}