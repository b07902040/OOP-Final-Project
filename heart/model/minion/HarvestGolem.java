package heart.model.minion;
import java.util.List;

import heart.model.*;
//Complete
public class HarvestGolem extends AbstractMinion implements Card, Minion, DeathRattle{
    
    private static String name = "HarvestGolem";
    private static String description = "DeathRattle: Summon a 2/1 DamagedGolem";
    private static int baseCost = 3;
    private static int baseATK = 2;
    private static int baseHP = 3;
    
    public HarvestGolem (){
        super(HarvestGolem.name, HarvestGolem.description, HarvestGolem.baseCost,
                 HarvestGolem.baseHP, HarvestGolem.baseATK);    
    }


    @Override
    public void doDeathRattleEffect(){
        this.master.summonAlly(new DamagedGolem(), this.master.getAlly().size());
    }
    
}