package src.model.minion;
import src.model.*;
import src.constant.*;

public abstract class AbstractMinion implements Minion, Card{
    //minion property
    protected int HP;
    protected int baseHP;
    protected int buffHP;
    protected int ATK;
    protected int baseATK;
    protected int attackCount;
    protected int attackLimit;
    protected int playedOrder;
    protected boolean alive = true;
    protected Player master;
    protected int aliveTime = 0;
    protected boolean devineShield = false;

    //card property
    protected int cost;
    protected int baseCost;
    protected String name;
    protected String description;       

    public  AbstractMinion(String name, String description, int baseCost , int baseHP, int baseATK){
        this.name = name;
        this.description = description;
        this.baseCost = baseCost;
        this.cost = baseCost;
        this.baseHP = baseHP;
        this.HP = baseHP;
        this.buffHP = baseHP;
        this.baseATK = baseATK;
        this.ATK = baseATK;
        this.attackLimit = 1;      
    }

    //card
    @Override   
    public String getName(){
        return this.name;
    }

    @Override    
    public int getCost(){
        return this.cost;
    }

    @Override    
    public void setCost(int cost){
        this.cost = cost;
    }
    @Override
    public int getBaseCost(){
        return this.baseCost;
    }

    @Override
    public String getDescription(){
        return this.description;
    }
    
    @Override
    public void setDescription(String description){
        this.description = description;
    }

    //minion
    @Override
    public Player getMaster(){
        return this.master;
    }

    @Override
    public void setMaster(Player master){
        this.master = master;
    }

    @Override
    public int getHP(){
        return this.HP;
    }

    @Override
    public void setHP(int HP){
        //Heal
        if(this.HP < HP){
            System.out.printf("%s +%d HP.\n",this.name,HP-this.HP);
            this.HP = Math.min(HP, this.buffHP);    
        }
        else if(this.HP > HP){
            if(this.devineShield){
                System.out.printf("%s deny damage by shield.\n",this.name);
                this.devineShield = false;
            }
            else{
                System.out.printf("%s -%d HP.\n",this.name,this.HP-HP);
                this.HP = HP;    
            }
        }            
    }

    @Override
    public int getBaseHP(){
        return this.baseHP;
    }

    @Override
    public int getBuffHP(){
        return this.buffHP;
    }  

    @Override
    public void setBuffHP(int buffHP){
        this.buffHP = buffHP;
    }

    @Override
    public int getATK(){
        return this.ATK;
    }

    @Override
    public void setATK(int ATK){
        this.ATK = ATK;
    }

    @Override
    public int getBaseATK(){
        return this.baseATK;
    }

    @Override
    public void setAttackLimit(int attackLimit){
        this.attackLimit = attackLimit;
    }
    
    @Override
    public void resetAttackCount(){
        this.attackCount = 0;
    }

    @Override
    public int getPlayedOrder(){
        return this.playedOrder;
    }

    @Override
    public void setPlayedOrder(int order){
        this.playedOrder = order;
    }
    
    @Override
    public boolean isAlive(){
        if(this.HP <= 0) 
            return false;
        return this.alive;
    }

    @Override
    public void setAlive(boolean alive){
        this.alive = alive;
    }

    @Override
    public int getAliveTime(){
        return this.aliveTime;
    }

    @Override
    public void addAliveTime(){
        this.aliveTime++;
    }

    @Override
    public boolean canAttack(){        
        //TODO 
        //FROZEN return false;
        if(this.attackCount < this.attackLimit){
            if(this.aliveTime == 0){
                if(this instanceof Charge)
                    return true;
                return false;
            }
            else
                return true;
        }
        return false;
    }

    @Override
    public boolean canAttacked(){
        if(this instanceof Taunt)
            return true;
        for(Minion minion : this.master.getAlly()){
            if(minion instanceof Taunt) 
                return false;
        }
        return true;
    }

    @Override
    public void attack(Minion target){
        System.out.printf("%s attack %s\n",this.name,((AbstractMinion)target).getName());
        if(this instanceof Poisonous)
            target.setHP(0);
        else
            target.setHP(target.getHP() - this.getATK());
        this.setHP(this.getHP() - target.getATK());
        this.attackCount++;
    }

}