package heart.model.minion;

import heart.model.Taunt;

//Complete
public class VoidWalker extends AbstractMinion implements Taunt {

    private static String name = "VoidWalker";
    private static String description = "Taunt";
    private static int baseCost = 1;
    private static int baseATK = 1;
    private static int baseHP = 3;

    public VoidWalker() {
        super(VoidWalker.name, VoidWalker.description, VoidWalker.baseCost, VoidWalker.baseHP, VoidWalker.baseATK);
    }

}