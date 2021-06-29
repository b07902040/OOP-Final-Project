package heart.model.minion;

import heart.model.Poisonous;
import heart.model.Taunt;

//Complete
public class PoisonSnail extends AbstractMinion implements Poisonous, Taunt {

    private static String name = "PoisonSnail";
    private static String description = "Poisonous & Taunt";
    private static int baseCost = 2;
    private static int baseATK = 1;
    private static int baseHP = 2;

    public PoisonSnail() {
        super(PoisonSnail.name, PoisonSnail.description, PoisonSnail.baseCost, PoisonSnail.baseHP, PoisonSnail.baseATK);
    }

}