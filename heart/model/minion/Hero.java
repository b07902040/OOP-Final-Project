package heart.model.minion;

import heart.constant.Const;

public abstract class Hero extends AbstractMinion {

    // static private String name = "HERO";
    private static int baseCost = 0;
    private static int baseATK = 0;
    private static int baseHP = Const.HERO_HP;

    public Hero(String name, String description) {
        super(name, description, Hero.baseCost, Hero.baseHP, Hero.baseATK);
    }
}