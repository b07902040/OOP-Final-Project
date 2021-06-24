package src.model;

public interface Minion {
    //todo
    //MAXHP
    Player getMaster();

    void setMaster(Player master);

    int getHP();

    void setHP(int HP);

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

    boolean isAlive();

    void setAlive(boolean alive);

    boolean canAttack();

    boolean canAttacked();

    int getAliveTime();

    void addAliveTime();

    void attack(Minion target);
}