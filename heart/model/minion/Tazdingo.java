package heart.model.minion;

import heart.model.Taunt;

//Complete
public class Tazdingo extends AbstractMinion implements Taunt {

    private static String name = "Tazdingo";
    private static String description = "Taunt";
    private static int baseCost = 4;
    private static int baseATK = 3;
    private static int baseHP = 5;

    public Tazdingo() {  
        super(Tazdingo.name, Tazdingo.description, Tazdingo.baseCost, Tazdingo.baseHP, Tazdingo.baseATK);
    }

}