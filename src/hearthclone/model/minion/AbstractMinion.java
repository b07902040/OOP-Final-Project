package hearthclone.model.minion;

import hearthclone.model.Charge;
import hearthclone.model.Minion;
import hearthclone.model.Player;
import hearthclone.model.Poisonous;
import hearthclone.model.Taunt;
import hearthclone.model.WindFury;

public abstract class AbstractMinion implements Minion, Cloneable{
    // minion property
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

    // card property
    protected int cost;
    protected int baseCost;
    protected String name;
    protected String description;

    public AbstractMinion(String name, String description, int baseCost, int baseHP, int baseATK) {
        this.name = name;
        this.description = description;
        this.baseCost = baseCost;
        this.cost = baseCost;
        this.baseHP = baseHP;
        this.HP = baseHP;
        this.buffHP = baseHP;
        this.baseATK = baseATK;
        this.ATK = baseATK;
        this.attackLimit = (this instanceof WindFury) ? 2 : 1;
    }
    @Override
    public AbstractMinion clone() {
        try{
            AbstractMinion  cloneObj = (AbstractMinion) super.clone();
            return cloneObj;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    // card
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public void setCost(int cost) {
        this.cost = cost;
        this.minionChange();
    }

    @Override
    public int getBaseCost() {
        return this.baseCost;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    // minion
    @Override
    public int getAttackLimit(){
        return this.attackLimit;
    }

    @Override
    public Player getMaster() {
        return this.master;
    }

    @Override
    public void setMaster(Player master) {
        this.master = master;
    }

    @Override
    public int getHP() {
        return this.HP;
    }

    @Override
    public void setHP(int HP) {
        // Heal
        if (this.HP < HP) {
            System.out.printf("%s +%d HP.\n", this.name, HP - this.HP);
            this.HP = Math.min(HP, this.buffHP);
        }
        // damage
        else if (this.HP > HP) {
            System.out.printf("%s -%d HP.\n", this.name, this.HP - HP);
            this.HP = HP;
        }
        this.minionChange();
    }

    @Override
    public void reWriteHP(int HP) {
        this.HP = HP;
        this.minionChange();
    }

    @Override
    public int getBaseHP() {
        return this.baseHP;
    }

    @Override
    public int getBuffHP() {
        return this.buffHP;
    }

    @Override
    public void setBuffHP(int buffHP) {
        this.buffHP = buffHP;
        this.minionChange();
    }

    @Override
    public int getATK() {
        return this.ATK;
    }

    @Override
    public void setATK(int ATK) {
        this.ATK = ATK;
        this.minionChange();
    }

    @Override
    public int getBaseATK() {
        return this.baseATK;
    }

    @Override
    public void setAttackLimit(int attackLimit) {
        this.attackLimit = attackLimit;
    }

    @Override
    public void resetAttackCount() {
        this.attackCount = 0;
        this.minionChange();
    }

    @Override
    public int getPlayedOrder() {
        return this.playedOrder;
    }

    @Override
    public void setPlayedOrder(int order) {
        this.playedOrder = order;
    }

    @Override
    public boolean isDamaged() {
        return this.HP < this.buffHP;
    }

    @Override
    public boolean isAlive() {
        if (this.HP <= 0)
            return false;
        return this.alive;
    }

    @Override
    public boolean getAlive() {
        return this.alive;
    }

    @Override
    public void setAlive(boolean alive) {
        this.alive = alive;
        this.minionChange();
    }

    @Override
    public int getAliveTime() {
        return this.aliveTime;
    }

    @Override
    public void addAliveTime() {
        this.aliveTime++;
        this.minionChange();
    }

    @Override
    public boolean canAttack() {
        if (this.ATK == 0)
            return false;
        if (this.attackCount < this.attackLimit) {
            if (this.aliveTime == 0) {
                if (this instanceof Charge)
                    return true;
                return false;
            } else
                return true;
        }
        return false;
    }

    @Override
    public boolean canAttacked() {
        if (this instanceof Taunt)
            return true;
        for (Minion minion : this.master.getAlly()) {
            if ((minion instanceof Taunt))
                return false;
        }
        return true;
    }

    @Override
    public boolean canTargeted() {
        return true;
    }

    @Override
    public void attack(Minion target) {
        System.out.printf("%s attack %s\n", this.name, ((AbstractMinion) target).getName());
        this.attackCount++;
        if (this instanceof Poisonous && !(target instanceof Hero))
            target.setHP(0);
        else
            target.setHP(target.getHP() - this.getATK());
        if (target instanceof Poisonous && !(this instanceof Hero))
            this.setHP(0);
        else
            this.setHP(this.getHP() - target.getATK());
        
    }

    @Override
    public void doTurnEnd() {
        return;
    }

    @Override
    public void minionChange() {
        int index = this.master.getAlly().indexOf(this);
        this.master.getGame().minionChange(this.master.getPlayerId(), index, this);
    }

}