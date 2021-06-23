package src.model.minion;
import src.model.*;
import src.constant.*;

public class Hero implements Minion, Card{
    private int HP = Const.HERO_HP;
    private int ATK = 0;
    private int cost = 0;
    private int playedOrder = -1;
    private boolean alive = true;
    private String name = "HERO";
    private String description = "I'm HERO!";
    private Player master;
    public Hero(Player master){
        this.master = master;
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
    public String getDescription(){
        return this.description;
    }
    //minion
    @Override
    public Player getMaster(){
        return this.master;
    }

    @Override
    public int getHP(){
        return this.HP;
    }

    @Override
    public void setHP(int HP){
        this.HP = HP;                
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
    public boolean canAttack(){
        return false;
    }

    @Override
    public boolean canAttacked(){
        return true;
    }

    @Override
    public void attack(Minion target){
        return;
    }
}