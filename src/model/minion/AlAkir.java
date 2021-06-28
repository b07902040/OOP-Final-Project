package src.model.minion;
import src.model.*;
//Complete
public class AlAkir extends AbstractMinion implements Card, Minion, Charge, DivineShield, Taunt, WindFury{
    
    private static String name = "AlAkir";
    private static String description = "Charge & DivineShield & Taunt & WindFury";
    private static int baseCost = 8;
    private static int baseATK = 3;
    private static int baseHP = 6;
    private boolean divineShield = true;
    
    public AlAkir (){
        super(AlAkir.name, AlAkir.description, AlAkir.baseCost,
                 AlAkir.baseHP, AlAkir.baseATK);    
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

}