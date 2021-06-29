package heart.model.minion;

import heart.model.BattleCry;
import heart.model.Charge;
import heart.model.Minion;
import heart.model.Player;

//Complete
public class FriedChicken extends AbstractMinion implements BattleCry, Charge {

    private static String name = "FriedChicken";
    private static String description = "Charge & BattleCry: Summon two 1/1 Whelp for your opponent";
    private static int baseCost = 5;
    private static int baseATK = 6;
    private static int baseHP = 2;

    public FriedChicken() {
        super(FriedChicken.name, FriedChicken.description, FriedChicken.baseCost, FriedChicken.baseHP,
                FriedChicken.baseATK);
    }

    @Override
    public void doBattleCryEffect(Minion target) {
        Player opponent = this.master.getOpponent();
        opponent.summonAlly(new Whelp(), opponent.getAlly().size());
        opponent.summonAlly(new Whelp(), opponent.getAlly().size());
    }

}