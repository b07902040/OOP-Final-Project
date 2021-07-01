package hearthclone.model.minion;

import hearthclone.model.Taunt;

//Complete
public class Gnoll extends AbstractMinion implements Taunt {

    private static String name = "Gnoll";
    private static String description = "Taunt";
    private static int baseCost = 2;
    private static int baseATK = 2;
    private static int baseHP = 2;

    public Gnoll() {
        super(Gnoll.name, Gnoll.description, Gnoll.baseCost, Gnoll.baseHP, Gnoll.baseATK);
    }

}