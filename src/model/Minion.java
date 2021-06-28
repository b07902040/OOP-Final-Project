package src.model;

import java.io.Serializable;

public interface Minion extends Serializable {
    static final long serialVersionUID = 2L;
    //todo
    //MAXHP
    Player getMaster();

    void setMaster(Player master);

    int getHP();

    void setHP(int HP);
    
    void reWriteHP(int HP);

    int getBaseHP();

    int getBuffHP();

    void setBuffHP(int buffHP);
     
    int getATK();

    void setATK(int ATK);

    void setAttackLimit(int attackLimit);

    void resetAttackCount();

    int getBaseATK();
    
    int getPlayedOrder();

    void setPlayedOrder(int order);
    
    boolean isDamaged();
    
    boolean isAlive();

    boolean getAlive();
    
    void setAlive(boolean alive);

    boolean canAttack();

    boolean canAttacked();

    boolean canTargeted();

    int getAliveTime();

    void addAliveTime();

    void attack(Minion target);
    
    void doTurnEnd();

    void minionChange();
}