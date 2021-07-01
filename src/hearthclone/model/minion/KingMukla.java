package hearthclone.model.minion;

import hearthclone.model.BattleCry;
import hearthclone.model.Minion;
import hearthclone.model.Player;
import hearthclone.model.spell.Banana;

//Complete
public class KingMukla extends AbstractMinion implements BattleCry {

    private static String name = "KingMukla";
    private static String description = "BattleCry: Give your opponent 2 Bananas.";
    private static int baseCost = 3;
    private static int baseATK = 5;
    private static int baseHP = 5;

    public KingMukla() {
        super(KingMukla.name, KingMukla.description, KingMukla.baseCost, KingMukla.baseHP, KingMukla.baseATK);
    }

    @Override
    public void doBattleCryEffect(Minion target) {
        Player opponent = this.master.getOpponent();
        opponent.addHandCards(new Banana());
        opponent.addHandCards(new Banana());
    }

}