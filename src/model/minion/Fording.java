package src.model.minion;
import src.model.*;
import java.util.List;
//Complete
public class Fording extends AbstractMinion implements Card, Minion, DeathRattle, DivineShield, Taunt{
    
    private static String name = "Fording";
    private static String description = "DivineShield & Taunt & DeathRattle: Summon a 5/3 AshBringer";
    private static int baseCost = 8;
    private static int baseATK = 6;
    private static int baseHP = 6;
    private boolean divineShield = true;
    
    public Fording (){
        super(Fording.name, Fording.description, Fording.baseCost,
                 Fording.baseHP, Fording.baseATK);    
    }

    @Override
    public void setHP(int HP){
        //Heal        
        if(this.HP < HP){
            System.out.printf("%s +%d HP.\n",this.name,HP-this.HP);
            this.HP = Math.min(HP, this.buffHP);    
        }
        //damage
        else if(this.HP > HP){
            if(this.divineShield){
                System.out.printf("%s deny damage by DivineShield.\n",this.name);
                this.divineShield = false;
            }
            else{
                System.out.printf("%s -%d HP.\n",this.name,this.HP - HP);
                this.HP = HP;    
            }
        }    
        this.minionChange();
    }

    @Override
    public void doDeathRattleEffect(){
        this.master.summonAlly(new AshBringer(), this.master.getAlly().size());
    }
    
}