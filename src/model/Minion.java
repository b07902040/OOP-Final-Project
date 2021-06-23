package src.model;

public interface Minion {
    //todo
    //MAXHP
    Player getMaster();

    int getHP();

    void setHP(int HP);

    int getATK();

    void setATK(int ATK);
    
    int getPlayedOrder();

    void setPlayedOrder(int order);

    boolean isAlive();

    void setAlive(boolean alive);

    boolean canAttack();

    boolean canAttacked();


    void attack(Minion target);
}