package hearthclone.model.minion;

import hearthclone.model.Charge;

//Complete
public class Patches extends AbstractMinion implements Charge {

    private static String name = "Patches";
    private static String description = "Charge";
    private static int baseCost = 1;
    private static int baseATK = 2;
    private static int baseHP = 2;

    public Patches() {
        super(Patches.name, Patches.description, Patches.baseCost, Patches.baseHP, Patches.baseATK);
    }

}