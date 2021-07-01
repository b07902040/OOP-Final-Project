package hearthclone.model.minion;

import hearthclone.model.DeathRattle;

//Complete
public class LootThief extends AbstractMinion implements DeathRattle {

    private static String name = "LootThief";
    private static String description = "DeathRattle: Deal 2 damage to the enemy Hero";
    private static int baseCost = 2;
    private static int baseATK = 2;
    private static int baseHP = 1;

    public LootThief() {
        super(LootThief.name, LootThief.description, LootThief.baseCost, LootThief.baseHP, LootThief.baseATK);
    }

    @Override
    public void doDeathRattleEffect() {
        this.master.drawCards();
    }

}