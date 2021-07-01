package hearthclone.model.minion;

import hearthclone.model.spell.Coin;

//Complete
public class Thaurissan extends AbstractMinion {

    private static String name = "Thaurissan";
    private static String description = "At the end of your turn, give you 3 Coin";
    private static int baseCost = 6;
    private static int baseATK = 5;
    private static int baseHP = 5;

    public Thaurissan() {
        super(Thaurissan.name, Thaurissan.description, Thaurissan.baseCost, Thaurissan.baseHP, Thaurissan.baseATK);
    }

    @Override
    public void doTurnEnd() {
        for (int i = 0; i < 3; i++)
            this.master.addHandCards(new Coin());
    }
}