package heart.model.minion;

import heart.model.Taunt;

//Complete
public class Slime extends AbstractMinion implements Taunt {

    private static String name = "Slime";
    private static String description = "Taunt";
    private static int baseCost = 1;
    private static int baseATK = 1;
    private static int baseHP = 2;

    public Slime() {
        super(Slime.name, Slime.description, Slime.baseCost, Slime.baseHP, Slime.baseATK);
    }

}