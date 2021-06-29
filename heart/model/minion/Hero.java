package heart.model.minion;
import heart.constant.*;
import heart.model.*;

public class Hero extends AbstractMinion implements Card, Minion{
    //static private String name = "HERO";
    static private String description = "I'm HERO!";
    static private int baseCost = 0;
    static private int baseATK = 0;
    static private int baseHP = Const.HERO_HP;
    
    public Hero(String name){
        super(name, Hero.description, Hero.baseCost, Hero.baseHP, Hero.baseATK); 
    } 
}