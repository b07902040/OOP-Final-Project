package src.model.minion;
import src.model.*;
import src.constant.*;

public class Hero extends AbstractMinion implements Card, Minion{
    static private String name = "HERO";
    static private String description = "I'm HERO!";
    static private int baseCost = 0;
    static private int baseHP = Const.HERO_HP;
    static private int baseATK = 0;


    public Hero(){
        super(Hero.name, Hero.description, Hero.baseCost, Hero.baseHP, Hero.baseATK); 
        super.setAttackLimit(0);
    }

    
}